
import kotlin.math.E
import kotlin.math.ln
import kotlin.math.pow

/**
 * Algebraic primitives.
 */

interface Group<X : Group<X>> {
    operator fun unaryMinus(): X
    operator fun plus(addend: X): X
    operator fun minus(subtrahend: X): X = this + -subtrahend
    operator fun times(multiplicand: X): X
}

interface Field<X : Field<X>> : Group<X> {
    operator fun div(dividend: X): X = this * dividend.pow(-one)
    infix fun pow(exp: X): X
    fun ln(): X
    val e: X
    val one: X
    val zero: X
}

/**
 * Scalar function.
 */

sealed class Fun<X : Fun<X>>(open val vars: Set<Var<X>> = emptySet()) : Field<Fun<X>>, (Map<Var<X>, X>) -> Fun<X> {
    constructor(fn: Fun<X>) : this(fn.vars)
    constructor(vararg fns: Fun<X>) : this(fns.flatMap { it.vars }.toSet())

    override operator fun plus(addend: Fun<X>): Fun<X> = Sum(this, addend)
    override operator fun times(multiplicand: Fun<X>): Fun<X> = Prod(this, multiplicand)

    operator fun <E : `1`> times(multiplicand: Vec<X, E>): Vec<X, E> = SVProd(this, multiplicand)

    override operator fun invoke(map: Map<Var<X>, X>): Fun<X> = when (this) {
        is SConst -> this
        is Var -> map.getOrElse(this) { this }
        is Prod -> left(map) * right(map)
        is Sum -> left(map) + right(map)
        is Power -> base(map) pow exponent(map)
        is Negative -> -value(map)
        is Log -> logarithmand(map).ln()
    }

    open fun diff(variable: Var<X>): Fun<X> = when (this) {
        is Var -> if (variable == this) one else zero
        is SConst -> zero
        is Sum -> left.diff(variable) + right.diff(variable)
        is Prod -> left.diff(variable) * right + left * right.diff(variable)
        is Power -> this * (exponent * Log(base)).diff(variable)
        is Negative -> -value.diff(variable)
        is Log -> logarithmand.pow(-one) * logarithmand.diff(variable)
    }

    override fun ln(): Fun<X> = Log(this)

    override fun pow(exp: Fun<X>): Fun<X> = Power(this, exp)

    override fun unaryMinus(): Fun<X> = Negative(this)

    override val e: SConst<X> by lazy { proto.e }
    override val one: SConst<X> by lazy { proto.one }
    override val zero: SConst<X> by lazy { proto.zero }
    private val proto: X by lazy { vars.first().value }

    override fun toString(): String = when {
        this is Log -> "ln($logarithmand)"
        this is Negative -> "-$value"
        this is Power -> "$base^($exponent)"
        this is Prod && right is Sum -> "$left⋅($right)"
        this is Prod && left is Sum -> "($left)⋅$right"
        this is Prod -> "$left⋅$right"
        this is Sum && right is Negative -> "$left - ${right.value}"
        this is Sum -> "$left + $right"
        this is Var -> name
        else -> super.toString()
    }
}

/**
 * Symbolic operators.
 */

class Sum<X : Fun<X>>(val left: Fun<X>, val right: Fun<X>) : Fun<X>(left, right)

class Negative<X : Fun<X>>(val value: Fun<X>) : Fun<X>(value)
class Prod<X : Fun<X>>(val left: Fun<X>, val right: Fun<X>) : Fun<X>(left, right)
class Power<X : Fun<X>> internal constructor(val base: Fun<X>, val exponent: Fun<X>) : Fun<X>(base, exponent)
class Log<X : Fun<X>> internal constructor(val logarithmand: Fun<X>) : Fun<X>(logarithmand)
interface Variable {
    val name: String
}

class Var<X : Fun<X>>(override val name: String, val value: X) : Variable, Fun<X>() {
    override val vars: Set<Var<X>> = setOf(this)
}

open class SConst<X : Fun<X>> : Fun<X>()
abstract class RealNumber<X : Fun<X>>(open val value: Number) : SConst<X>()

class DoubleReal(override val value: Double) : RealNumber<DoubleReal>(value) {
    override val e by lazy { DoubleReal(E) }
    override val one by lazy { DoubleReal(1.0) }
    override val zero by lazy { DoubleReal(0.0) }

    override fun unaryMinus() = DoubleReal(-value)
    override fun ln() = DoubleReal(ln(value))
    override fun toString() = value.toString()

    /**
     * Constant propagation.
     */

    override fun plus(addend: Fun<DoubleReal>): Fun<DoubleReal> = when (addend) {
        is DoubleReal -> DoubleReal(value + addend.value)
        else -> super.plus(addend)
    }

    override fun times(multiplicand: Fun<DoubleReal>): Fun<DoubleReal> = when (multiplicand) {
        is DoubleReal -> DoubleReal(value * multiplicand.value)
        else -> super.times(multiplicand)
    }

    override fun pow(exp: Fun<DoubleReal>) = when (exp) {
        is DoubleReal -> DoubleReal(value.pow(exp.value))
        else -> super.pow(exp)
    }
}

/**
 * Numerical context.
 */

sealed class Protocol<X : RealNumber<X>> {
    abstract fun wrap(default: Number): X

    operator fun Number.times(multiplicand: Fun<X>) = multiplicand * wrap(this)
    operator fun Fun<X>.times(multiplicand: Number) = wrap(multiplicand) * this

    operator fun Number.plus(addend: Fun<X>) = addend + wrap(this)
    operator fun Fun<X>.plus(addend: Number) = wrap(addend) + this

    fun Number.pow(exp: Fun<X>) = wrap(this) pow exp
    infix fun Fun<X>.pow(exp: Number) = this pow wrap(exp)

    class Differential<X: Fun<X>>(private val scalarFun: Fun<X>) {
        // TODO: make sure this notation works for arbitrary nested functions using the Chain rule
        infix operator fun div(arg: Differential<X>) = scalarFun.diff(arg.scalarFun.vars.first())
    }

    fun <X: Fun<X>> d(scalarFun: Fun<X>) = Differential(scalarFun)
}

object DoublePrecision : Protocol<DoubleReal>() {
    override fun wrap(default: Number): DoubleReal = DoubleReal(default.toDouble())

    fun Var(name: String, default: Number) = Var(name, wrap(default))
    operator fun Fun<DoubleReal>.invoke(vararg pairs: Pair<Var<DoubleReal>, Number>) =
        this(pairs.map { (it.first to wrap(it.second)) }.toMap())
    operator fun <Y: `1`> Vec<DoubleReal, Y>.invoke(vararg sPairs: Pair<Var<DoubleReal>, Number>) =
        this(sPairs.map { (it.first to wrap(it.second)) }.toMap())

    val x = Var("x", 0.0)
    val y = Var("y", 0.0)
    val z = Var("z", 0.0)

    fun Vec(d0: Double) = Vec(DoubleReal(d0))
    fun Vec(d0: Double, d1: Double): Vec<DoubleReal, `2`> = Vec(DoubleReal(d0), DoubleReal(d1))
    fun Vec(d0: Double, d1: Double, d2: Double): Vec<DoubleReal, `3`> = Vec(DoubleReal(d0), DoubleReal(d1), DoubleReal(d2))
    fun Vec(d0: Double, d1: Double, d2: Double, d3: Double) = Vec(DoubleReal(d0), DoubleReal(d1), DoubleReal(d2), DoubleReal(d3))
    fun Vec(d0: Double, d1: Double, d2: Double, d3: Double, d4: Double) = Vec(DoubleReal(d0), DoubleReal(d1), DoubleReal(d2), DoubleReal(d3), DoubleReal(d4))
    fun Vec(d0: Double, d1: Double, d2: Double, d3: Double, d4: Double, d5: Double) = Vec(DoubleReal(d0), DoubleReal(d1), DoubleReal(d2), DoubleReal(d3), DoubleReal(d4), DoubleReal(d5))
    fun Vec(d0: Double, d1: Double, d2: Double, d3: Double, d4: Double, d5: Double, d6: Double) = Vec(DoubleReal(d0), DoubleReal(d1), DoubleReal(d2), DoubleReal(d3), DoubleReal(d4), DoubleReal(d5), DoubleReal(d6))
    fun Vec(d0: Double, d1: Double, d2: Double, d3: Double, d4: Double, d5: Double, d6: Double, d7: Double) = Vec(DoubleReal(d0), DoubleReal(d1), DoubleReal(d2), DoubleReal(d3), DoubleReal(d4), DoubleReal(d5), DoubleReal(d6), DoubleReal(d7))
    fun Vec(d0: Double, d1: Double, d2: Double, d3: Double, d4: Double, d5: Double, d6: Double, d7: Double, d8: Double) = Vec(DoubleReal(d0), DoubleReal(d1), DoubleReal(d2), DoubleReal(d3), DoubleReal(d4), DoubleReal(d5), DoubleReal(d6), DoubleReal(d7), DoubleReal(d8))
}

/**
 * Vector function.
 */

open class Vec<X: Fun<X>, E: `1`>(
    open val length: Nat<E>,
    val sVars: Set<Var<X>> = emptySet(),
    open val vVars: Set<VVar<X, *>> = emptySet(),
    open vararg val contents: Fun<X>
) {
    constructor(length: Nat<E>, contents: List<Fun<X>>): this(length, contents.flatMap { it.vars }.toSet(), emptySet(), *contents.toTypedArray())
    constructor(length: Nat<E>, vararg contents: Fun<X>): this(length, contents.flatMap { it.vars }.toSet(), emptySet(), *contents)
    constructor(length: Nat<E>, vararg vFns: Vec<X, E>): this(length, vFns.flatMap { it.sVars }.toSet(), vFns.flatMap { it.vVars }.toSet())

    companion object {
        operator fun <T: Fun<T>> invoke(t: Fun<T>): Vec<T, `1`> = Vec(`1`, arrayListOf(t))
        operator fun <T: Fun<T>> invoke(t0: Fun<T>, t1: Fun<T>): Vec<T, `2`> = Vec(`2`, arrayListOf(t0, t1))
        operator fun <T: Fun<T>> invoke(t0: Fun<T>, t1: Fun<T>, t2: Fun<T>): Vec<T, `3`> = Vec(`3`, arrayListOf(t0, t1, t2))
        operator fun <T: Fun<T>> invoke(t0: Fun<T>, t1: Fun<T>, t2: Fun<T>, t3: Fun<T>): Vec<T, `4`> = Vec(`4`, arrayListOf(t0, t1, t2, t3))
        operator fun <T: Fun<T>> invoke(t0: Fun<T>, t1: Fun<T>, t2: Fun<T>, t3: Fun<T>, t4: Fun<T>): Vec<T, `5`> = Vec(`5`, arrayListOf(t0, t1, t2, t3, t4))
        operator fun <T: Fun<T>> invoke(t0: Fun<T>, t1: Fun<T>, t2: Fun<T>, t3: Fun<T>, t4: Fun<T>, t5: Fun<T>): Vec<T, `6`> = Vec(`6`, arrayListOf(t0, t1, t2, t3, t4, t5))
        operator fun <T: Fun<T>> invoke(t0: Fun<T>, t1: Fun<T>, t2: Fun<T>, t3: Fun<T>, t4: Fun<T>, t5: Fun<T>, t6: Fun<T>): Vec<T, `7`> = Vec(`7`, arrayListOf(t0, t1, t2, t3, t4, t5, t6))
        operator fun <T: Fun<T>> invoke(t0: Fun<T>, t1: Fun<T>, t2: Fun<T>, t3: Fun<T>, t4: Fun<T>, t5: Fun<T>, t6: Fun<T>, t7: Fun<T>): Vec<T, `8`> = Vec(`8`, arrayListOf(t0, t1, t2, t3, t4, t5, t6, t7))
        operator fun <T: Fun<T>> invoke(t0: Fun<T>, t1: Fun<T>, t2: Fun<T>, t3: Fun<T>, t4: Fun<T>, t5: Fun<T>, t6: Fun<T>, t7: Fun<T>, t8: Fun<T>): Vec<T, `9`> = Vec(`9`, arrayListOf(t0, t1, t2, t3, t4, t5, t6, t7, t8))
    }

    init {
        if (length.i != contents.size && contents.isNotEmpty()) throw IllegalArgumentException("Declared answers.length, $length != ${contents.size}")
    }

    operator fun invoke(sMap: Map<Var<X>, X> = emptyMap(), vMap: Map<VVar<X, E>, VConst<X, E>> = emptyMap()): Vec<X, E> =
        when (this) {
            is VNegative<X, E> -> Vec(length, value(sMap, vMap).contents.map { -it })
            is VSum<X, E> -> left(sMap, vMap) + right(sMap, vMap)
            is VVProd<X, E> -> left(sMap, vMap) * right(sMap, vMap)
//    is VDot<X, E> -> VFun(`1`, contents.reduceIndexed { index, acc, element -> acc + element * right[index] })
            is VVar<X, E> -> vMap.getOrElse(this) { this }
            else -> this
        }

    open fun diff(variable: Var<X>): Vec<X, E> =
        when (this) {
            is VConst -> VConst(length, *contents.map { it.zero }.toTypedArray())
            is VSum -> left.diff(variable) + right.diff(variable)
            is VVProd -> left.diff(variable) * right + right.diff(variable) * left
//    is SVProd -> left.diff(variable) * right + right.diff(variable) * left
            else -> Vec(length, contents.map { it.diff(variable) })
        }

    open operator fun unaryMinus(): Vec<X, E> = VNegative(this)
    open operator fun plus(addend: Vec<X, E>): Vec<X, E> = VSum(this, addend)
    open operator fun times(multiplicand: Vec<X, E>): Vec<X, E> = VVProd(this, multiplicand)
    open operator fun times(multiplicand: Fun<X>): Vec<X, E> = Vec(length, contents.map { it * multiplicand })

    infix fun dot(multiplicand: Vec<X, E>): Fun<X> =
        contents.reduceIndexed { index, acc, element -> acc + element * multiplicand[index] }

    operator fun get(index: Int) = contents[index]

    override fun toString() =
        when (this) {
            is VSum -> "$left + $right"
            is VVProd -> "$left * $right"
            else -> contents.joinToString(", ", "[", "]")
        }
}

class VNegative<X: Fun<X>, E: `1`>(val value: Vec<X, E>): Vec<X, E>(value.length, value)
class VSum<X: Fun<X>, E: `1`>(val left: Vec<X, E>, val right: Vec<X, E>): Vec<X, E>(left.length, left, right)
//class VDot<X: Fun<X>, E: `1`>(val left: VFun<X, E>, val right: VFun<X, E>): Fun<X>(left.vars + right.vars)

class VVProd<X: Fun<X>, E: `1`>(val left: Vec<X, E>, val right: Vec<X, E>): Vec<X, E>(left.length, left, right)
class SVProd<X: Fun<X>, E: `1`>(val left: Fun<X>, val right: Vec<X, E>): Vec<X, E>(right.length, left.vars + right.sVars, right.vVars, *right.contents)

class VVar<X: Fun<X>, E: `1`>(override val name: String, override val length: Nat<E>, vararg val value: X): Variable, Vec<X, E>(length, *value) { override val vVars: Set<VVar<X, *>> = setOf(this) }
open class VConst<X: Fun<X>, E: `1`>(length: Nat<E>, override vararg val contents: SConst<X>): Vec<X, E>(length, *contents)
abstract class RealVector<X: Fun<X>, E: `1`>(length: Nat<E>, override vararg val contents: SConst<X>): VConst<X, E>(length, *contents)
//class VDoubleReal<E: `1`>(answers.length: Nat<E>, override vararg val contents: DoubleReal): RealVector<DoubleReal, E>(answers.length, *contents) {
//  override fun plus(addend: VFun<DoubleReal, E>): VFun<DoubleReal, E> = VDoubleReal(answers.length, *contents.zip(addend.contents).map { (it.first + it.second) }.toTypedArray())
//}

/**
 * Type level integers.
 */
interface Nat<T: `0`> { val i: Int }
sealed class `0`(open val i: Int = 0) {
    companion object: `0`(), Nat<`0`>

    override fun toString() = "$i"
}

sealed class `1`(override val i: Int = 1): `0`(i) { companion object: `1`(), Nat<`1`> }
sealed class `2`(override val i: Int = 2): `1`(i) { companion object: `2`(), Nat<`2`> }
sealed class `3`(override val i: Int = 3): `2`(i) { companion object: `3`(), Nat<`3`> }
sealed class `4`(override val i: Int = 4): `3`(i) { companion object: `4`(), Nat<`4`> }
sealed class `5`(override val i: Int = 5): `4`(i) { companion object: `5`(), Nat<`5`> }
sealed class `6`(override val i: Int = 6): `5`(i) { companion object: `6`(), Nat<`6`> }
sealed class `7`(override val i: Int = 7): `6`(i) { companion object: `7`(), Nat<`7`> }
sealed class `8`(override val i: Int = 8): `7`(i) { companion object: `8`(), Nat<`8`> }
sealed class `9`(override val i: Int = 9): `8`(i) { companion object: `9`(), Nat<`9`> }


