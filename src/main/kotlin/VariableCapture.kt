@file:Suppress("UNCHECKED_CAST")

open class FunX<P: Const<P, in Number>>(override val left: BiFn<*>,
                                        override val right: BiFn<*>,
                                        override val op: Op<P>): BiFn<P>(left, right, op) {
    constructor(c: Const<P, in Number>): this(c, c, First())

    operator fun plus(that: P): FunX<P> = FunX(this, that, add)
    operator fun times(that: P): FunX<P> = FunX(this, that, mul)
    operator fun minus(that: P): FunX<P> = FunX(this, that, sub)
    operator fun div(that: P): FunX<P> = FunX(this, that, div)

    operator fun minus(that: FunX<P>): FunX<P> = FunX(this, that, sub)
    operator fun minus(that: FunY<P>): FunXY<P> = FunXY(this, that, sub)
    operator fun minus(that: FunZ<P>): FunXZ<P> = FunXZ(this, that, sub)
    operator fun minus(that: FunXY<P>): FunXY<P> = FunXY(this, that, sub)
    operator fun minus(that: FunXZ<P>): FunXZ<P> = FunXZ(this, that, sub)
    operator fun minus(that: FunYZ<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun minus(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun div(that: FunX<P>): FunX<P> = FunX(this, that, div)
    operator fun div(that: FunY<P>): FunXY<P> = FunXY(this, that, div)
    operator fun div(that: FunZ<P>): FunXZ<P> = FunXZ(this, that, div)
    operator fun div(that: FunXY<P>): FunXY<P> = FunXY(this, that, div)
    operator fun div(that: FunXZ<P>): FunXZ<P> = FunXZ(this, that, div)
    operator fun div(that: FunYZ<P>): FunXYZ<P> = FunXYZ(this, that, div)
    operator fun div(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, div)

    operator fun plus(that: FunX<P>): FunX<P> = FunX(this, that, add)
    operator fun plus(that: FunY<P>): FunXY<P> = FunXY(this, that, add)
    operator fun plus(that: FunZ<P>): FunXZ<P> = FunXZ(this, that, add)
    operator fun plus(that: FunXY<P>): FunXY<P> = FunXY(this, that, add)
    operator fun plus(that: FunXZ<P>): FunXZ<P> = FunXZ(this, that, add)
    operator fun plus(that: FunYZ<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun plus(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun times(that: FunX<P>): FunX<P> = FunX(this, that, mul)
    operator fun times(that: FunY<P>): FunXY<P> = FunXY(this, that, mul)
    operator fun times(that: FunZ<P>): FunXZ<P> = FunXZ(this, that, mul)
    operator fun times(that: FunXY<P>): FunXY<P> = FunXY(this, that, mul)
    operator fun times(that: FunXZ<P>): FunXZ<P> = FunXZ(this, that, mul)
    operator fun times(that: FunYZ<P>): FunXYZ<P> = FunXYZ(this, that, mul)
    operator fun times(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, mul)

    open operator fun invoke(X: XBnd<P>): P =
        op(left(X), right(X))

    private operator fun BiFn<*>.invoke(X: XBnd<P>): P =
        when (this) {
            is Const<*, *> -> this as P
            is FunX<*> -> (this as FunX<P>)(X)
            else -> throw IllegalStateException(toString())
        }
}

open class FunY<P: Const<P, in Number>>(override val left: BiFn<*>,
                                        override val right: BiFn<*>,
                                        override val op: Op<P>): BiFn<P>(left, right, op) {
    constructor(c: Const<P, in Number>): this(c, c, First())

    operator fun plus(that: P): FunY<P> = FunY(this, that, add)
    operator fun times(that: P): FunY<P> = FunY(this, that, mul)
    operator fun minus(that: P): FunY<P> = FunY(this, that, sub)
    operator fun div(that: P): FunY<P> = FunY(this, that, div)

    operator fun minus(that: FunY<P>): FunY<P> = FunY(this, that, sub)
    operator fun minus(that: FunX<P>): FunXY<P> = FunXY(this, that, sub)
    operator fun minus(that: FunZ<P>): FunYZ<P> = FunYZ(this, that, sub)
    operator fun minus(that: FunXY<P>): FunXY<P> = FunXY(this, that, sub)
    operator fun minus(that: FunXZ<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun minus(that: FunYZ<P>): FunYZ<P> = FunYZ(this, that, sub)
    operator fun minus(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun div(that: FunY<P>): FunY<P> = FunY(this, that, div)
    operator fun div(that: FunX<P>): FunXY<P> = FunXY(this, that, div)
    operator fun div(that: FunZ<P>): FunYZ<P> = FunYZ(this, that, div)
    operator fun div(that: FunXY<P>): FunXY<P> = FunXY(this, that, div)
    operator fun div(that: FunXZ<P>): FunXYZ<P> = FunXYZ(this, that, div)
    operator fun div(that: FunYZ<P>): FunYZ<P> = FunYZ(this, that, div)
    operator fun div(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, div)

    operator fun plus(that: FunY<P>): FunY<P> = FunY(this, that, add)
    operator fun plus(that: FunX<P>): FunXY<P> = FunXY(this, that, add)
    operator fun plus(that: FunZ<P>): FunYZ<P> = FunYZ(this, that, add)
    operator fun plus(that: FunXY<P>): FunXY<P> = FunXY(this, that, add)
    operator fun plus(that: FunXZ<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun plus(that: FunYZ<P>): FunYZ<P> = FunYZ(this, that, add)
    operator fun plus(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun times(that: FunY<P>): FunY<P> = FunY(this, that, mul)
    operator fun times(that: FunX<P>): FunXY<P> = FunXY(this, that, mul)
    operator fun times(that: FunZ<P>): FunYZ<P> = FunYZ(this, that, mul)
    operator fun times(that: FunXY<P>): FunXY<P> = FunXY(this, that, mul)
    operator fun times(that: FunXZ<P>): FunXYZ<P> = FunXYZ(this, that, mul)
    operator fun times(that: FunYZ<P>): FunYZ<P> = FunYZ(this, that, mul)
    operator fun times(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, mul)

    open operator fun invoke(Y: YBnd<P>): P =
        op(left(Y), right(Y))

    private operator fun BiFn<*>.invoke(Y: YBnd<P>): P =
        when (this) {
            is Const<*, *> -> this as P
            is FunY<*> -> (this as FunY<P>)(Y)
            else -> throw IllegalStateException(toString())
        }
}

open class FunZ<P: Const<P, in Number>>(override val left: BiFn<*>,
                                        override val right: BiFn<*>,
                                        override val op: Op<P>): BiFn<P>(left, right, op) {
    constructor(c: Const<P, in Number>): this(c, c, First())

    operator fun plus(that: P): FunZ<P> = FunZ(this, that, add)
    operator fun times(that: P): FunZ<P> = FunZ(this, that, mul)
    operator fun minus(that: P): FunZ<P> = FunZ(this, that, sub)
    operator fun div(that: P): FunZ<P> = FunZ(this, that, div)

    operator fun minus(that: FunZ<P>): FunZ<P> = FunZ(this, that, sub)
    operator fun minus(that: FunY<P>): FunYZ<P> = FunYZ(this, that, sub)
    operator fun minus(that: FunX<P>): FunXZ<P> = FunXZ(this, that, sub)
    operator fun minus(that: FunXY<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun minus(that: FunXZ<P>): FunXZ<P> = FunXZ(this, that, sub)
    operator fun minus(that: FunYZ<P>): FunYZ<P> = FunYZ(this, that, sub)
    operator fun minus(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun div(that: FunZ<P>): FunZ<P> = FunZ(this, that, div)
    operator fun div(that: FunY<P>): FunYZ<P> = FunYZ(this, that, div)
    operator fun div(that: FunX<P>): FunXZ<P> = FunXZ(this, that, div)
    operator fun div(that: FunXY<P>): FunXYZ<P> = FunXYZ(this, that, div)
    operator fun div(that: FunXZ<P>): FunXZ<P> = FunXZ(this, that, div)
    operator fun div(that: FunYZ<P>): FunYZ<P> = FunYZ(this, that, div)
    operator fun div(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, div)

    operator fun plus(that: FunZ<P>): FunZ<P> = FunZ(this, that, add)
    operator fun plus(that: FunY<P>): FunYZ<P> = FunYZ(this, that, add)
    operator fun plus(that: FunX<P>): FunXZ<P> = FunXZ(this, that, add)
    operator fun plus(that: FunXY<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun plus(that: FunXZ<P>): FunXZ<P> = FunXZ(this, that, add)
    operator fun plus(that: FunYZ<P>): FunYZ<P> = FunYZ(this, that, add)
    operator fun plus(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun times(that: FunZ<P>): FunZ<P> = FunZ(this, that, mul)
    operator fun times(that: FunY<P>): FunYZ<P> = FunYZ(this, that, mul)
    operator fun times(that: FunX<P>): FunXZ<P> = FunXZ(this, that, mul)
    operator fun times(that: FunXY<P>): FunXYZ<P> = FunXYZ(this, that, mul)
    operator fun times(that: FunXZ<P>): FunXZ<P> = FunXZ(this, that, mul)
    operator fun times(that: FunYZ<P>): FunYZ<P> = FunYZ(this, that, mul)
    operator fun times(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, mul)

    open operator fun invoke(Z: ZBnd<P>): P = op(left(Z), right(Z))

    private operator fun BiFn<*>.invoke(Z: ZBnd<P>): P =
        when (this) {
            is Const<*, *> -> this as P
            is FunZ<*> -> (this as FunZ<P>)(Z)
            else -> throw IllegalStateException(toString())
        }
}

class FunXY<P: Const<P, in Number>>(override val left: BiFn<*>,
                                    override val right: BiFn<*>,
                                    override val op: Op<P>): BiFn<P>(left, right, op) {
    constructor(f: BiFn<*>): this(f, f, First())

    operator fun plus(that: P): FunXY<P> = FunXY(this, that, add)
    operator fun times(that: P): FunXY<P> = FunXY(this, that, mul)
    operator fun minus(that: P): FunXY<P> = FunXY(this, that, sub)
    operator fun div(that: P): FunXY<P> = FunXY(this, that, div)

    operator fun minus(that: FunX<P>): FunXY<P> = FunXY(this, that, sub)
    operator fun minus(that: FunY<P>): FunXY<P> = FunXY(this, that, sub)
    operator fun minus(that: FunZ<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun minus(that: FunXY<P>): FunXY<P> = FunXY(this, that, sub)
    operator fun minus(that: FunXZ<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun minus(that: FunYZ<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun minus(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun div(that: FunX<P>): FunXY<P> = FunXY(this, that, div)
    operator fun div(that: FunY<P>): FunXY<P> = FunXY(this, that, div)
    operator fun div(that: FunZ<P>): FunXYZ<P> = FunXYZ(this, that, div)
    operator fun div(that: FunXY<P>): FunXY<P> = FunXY(this, that, div)
    operator fun div(that: FunXZ<P>): FunXYZ<P> = FunXYZ(this, that, div)
    operator fun div(that: FunYZ<P>): FunXYZ<P> = FunXYZ(this, that, div)
    operator fun div(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, div)

    operator fun plus(that: FunX<P>): FunXY<P> = FunXY(this, that, add)
    operator fun plus(that: FunY<P>): FunXY<P> = FunXY(this, that, add)
    operator fun plus(that: FunZ<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun plus(that: FunXY<P>): FunXY<P> = FunXY(this, that, add)
    operator fun plus(that: FunXZ<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun plus(that: FunYZ<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun plus(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun times(that: FunX<P>): FunXY<P> = FunXY(this, that, mul)
    operator fun times(that: FunY<P>): FunXY<P> = FunXY(this, that, mul)
    operator fun times(that: FunZ<P>): FunXYZ<P> = FunXYZ(this, that, mul)
    operator fun times(that: FunXY<P>): FunXY<P> = FunXY(this, that, mul)
    operator fun times(that: FunXZ<P>): FunXYZ<P> = FunXYZ(this, that, mul)
    operator fun times(that: FunYZ<P>): FunXYZ<P> = FunXYZ(this, that, mul)
    operator fun times(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, mul)

    private operator fun BiFn<*>.invoke(X: XBnd<P>, Y: YBnd<P>): P =
        when (this) {
            is Const<*, *> -> this as P
            is FunXY<*> -> (this as FunXY<P>)(X, Y)
            is FunX<*> -> (this as FunX<P>)(X)
            is FunY<*> -> (this as FunY<P>)(Y)
            else -> throw IllegalStateException(toString())
        }

    operator fun invoke(X: XBnd<P>, Y: YBnd<P>): P = op(left(X, Y), right(X, Y))

    private operator fun BiFn<*>.invoke(X: XBnd<P>): FunY<P> =
        when (this) {
            is Const<*, *> -> FunY(this as P)
            is FunXY<*> -> (this as FunXY<P>)(X)
            is FunX<*> -> FunY((this as FunX<P>)(X))
            is FunY<*> -> this as FunY<P>
            else -> throw IllegalStateException(toString())
        }

    private operator fun BiFn<*>.invoke(Y: YBnd<P>): FunX<P> =
        when (this) {
            is Const<*, *> -> FunX(this as P)
            is FunXY<*> -> (this as FunXY<P>)(Y)
            is FunY<*> -> FunX((this as FunY<P>)(Y))
            is FunX<*> -> this as FunX<P>
            else -> throw IllegalStateException(toString())
        }

    operator fun invoke(X: XBnd<P>): FunY<P> = FunY(left(X), right(X), op)
    operator fun invoke(Y: YBnd<P>): FunX<P> = FunX(left(Y), right(Y), op)
}

class FunXZ<P: Const<P, in Number>>(override val left: BiFn<*>,
                                    override val right: BiFn<*>,
                                    override val op: Op<P>): BiFn<P>(left, right, op) {
    constructor(f: BiFn<*>): this(f, f, First())

    operator fun plus(that: P): FunXZ<P> = FunXZ(this, that, add)
    operator fun times(that: P): FunXZ<P> = FunXZ(this, that, mul)
    operator fun minus(that: P): FunXZ<P> = FunXZ(this, that, sub)
    operator fun div(that: P): FunXZ<P> = FunXZ(this, that, div)

    operator fun minus(that: FunX<P>): FunXZ<P> = FunXZ(this, that, sub)
    operator fun minus(that: FunY<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun minus(that: FunZ<P>): FunXZ<P> = FunXZ(this, that, sub)
    operator fun minus(that: FunXY<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun minus(that: FunXZ<P>): FunXZ<P> = FunXZ(this, that, sub)
    operator fun minus(that: FunYZ<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun minus(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun div(that: FunX<P>): FunXZ<P> = FunXZ(this, that, div)
    operator fun div(that: FunY<P>): FunXYZ<P> = FunXYZ(this, that, div)
    operator fun div(that: FunZ<P>): FunXZ<P> = FunXZ(this, that, div)
    operator fun div(that: FunXY<P>): FunXYZ<P> = FunXYZ(this, that, div)
    operator fun div(that: FunXZ<P>): FunXZ<P> = FunXZ(this, that, div)
    operator fun div(that: FunYZ<P>): FunXYZ<P> = FunXYZ(this, that, div)
    operator fun div(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, div)

    operator fun plus(that: FunX<P>): FunXZ<P> = FunXZ(this, that, add)
    operator fun plus(that: FunY<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun plus(that: FunZ<P>): FunXZ<P> = FunXZ(this, that, add)
    operator fun plus(that: FunXY<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun plus(that: FunXZ<P>): FunXZ<P> = FunXZ(this, that, add)
    operator fun plus(that: FunYZ<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun plus(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun times(that: FunX<P>): FunXZ<P> = FunXZ(this, that, mul)
    operator fun times(that: FunY<P>): FunXYZ<P> = FunXYZ(this, that, mul)
    operator fun times(that: FunZ<P>): FunXZ<P> = FunXZ(this, that, mul)
    operator fun times(that: FunXY<P>): FunXYZ<P> = FunXYZ(this, that, mul)
    operator fun times(that: FunXZ<P>): FunXZ<P> = FunXZ(this, that, mul)
    operator fun times(that: FunYZ<P>): FunXYZ<P> = FunXYZ(this, that, mul)
    operator fun times(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, mul)

    private operator fun BiFn<*>.invoke(X: XBnd<P>, Z: ZBnd<P>): P =
        when (this) {
            is Const<*, *> -> this as P
            is FunXZ<*> -> (this as FunXZ<P>)(X, Z)
            is FunX<*> -> (this as FunX<P>)(X)
            is FunZ<*> -> (this as FunZ<P>)(Z)
            else -> throw IllegalStateException(toString())
        }

    operator fun invoke(X: XBnd<P>, Z: ZBnd<P>): P = op(left(X, Z), right(X, Z))

    private operator fun BiFn<*>.invoke(X: XBnd<P>): FunZ<P> =
        when (this) {
            is Const<*, *> -> FunZ(this as P)
            is FunXZ<*> -> (this as FunXZ<P>)(X)
            is FunX<*> -> FunZ((this as FunX<P>)(X))
            is FunZ<*> -> this as FunZ<P>
            else -> throw IllegalStateException(toString())
        }

    private operator fun BiFn<*>.invoke(Z: ZBnd<P>): FunX<P> =
        when (this) {
            is Const<*, *> -> FunX(this as P)
            is FunXZ<*> -> (this as FunXZ<P>)(Z)
            is FunZ<*> -> FunX((this as FunZ<P>)(Z))
            is FunX<*> -> this as FunX<P>
            else -> throw IllegalStateException(toString())
        }

    operator fun invoke(X: XBnd<P>): FunZ<P> = FunZ(left(X), right(X), op)
    operator fun invoke(Z: ZBnd<P>): FunX<P> = FunX(left(Z), right(Z), op)
}

class FunYZ<P: Const<P, in Number>>(override val left: BiFn<*>,
                                    override val right: BiFn<*>,
                                    override val op: Op<P>): BiFn<P>(left, right, op) {
    constructor(f: BiFn<*>): this(f, f, First())

    operator fun plus(that: P): FunYZ<P> = FunYZ(this, that, add)
    operator fun times(that: P): FunYZ<P> = FunYZ(this, that, mul)
    operator fun minus(that: P): FunYZ<P> = FunYZ(this, that, sub)
    operator fun div(that: P): FunYZ<P> = FunYZ(this, that, div)

    operator fun minus(that: FunX<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun minus(that: FunZ<P>): FunYZ<P> = FunYZ(this, that, sub)
    operator fun minus(that: FunY<P>): FunYZ<P> = FunYZ(this, that, sub)
    operator fun minus(that: FunXY<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun minus(that: FunXZ<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun minus(that: FunYZ<P>): FunYZ<P> = FunYZ(this, that, sub)
    operator fun minus(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun div(that: FunX<P>): FunXYZ<P> = FunXYZ(this, that, div)
    operator fun div(that: FunZ<P>): FunYZ<P> = FunYZ(this, that, div)
    operator fun div(that: FunY<P>): FunYZ<P> = FunYZ(this, that, div)
    operator fun div(that: FunXY<P>): FunXYZ<P> = FunXYZ(this, that, div)
    operator fun div(that: FunXZ<P>): FunXYZ<P> = FunXYZ(this, that, div)
    operator fun div(that: FunYZ<P>): FunYZ<P> = FunYZ(this, that, div)
    operator fun div(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, div)

    operator fun plus(that: FunX<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun plus(that: FunZ<P>): FunYZ<P> = FunYZ(this, that, add)
    operator fun plus(that: FunY<P>): FunYZ<P> = FunYZ(this, that, add)
    operator fun plus(that: FunXY<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun plus(that: FunXZ<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun plus(that: FunYZ<P>): FunYZ<P> = FunYZ(this, that, add)
    operator fun plus(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun times(that: FunX<P>): FunXYZ<P> = FunXYZ(this, that, mul)
    operator fun times(that: FunZ<P>): FunYZ<P> = FunYZ(this, that, mul)
    operator fun times(that: FunY<P>): FunYZ<P> = FunYZ(this, that, mul)
    operator fun times(that: FunXY<P>): FunXYZ<P> = FunXYZ(this, that, mul)
    operator fun times(that: FunXZ<P>): FunXYZ<P> = FunXYZ(this, that, mul)
    operator fun times(that: FunYZ<P>): FunYZ<P> = FunYZ(this, that, mul)
    operator fun times(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, mul)

    private operator fun BiFn<*>.invoke(Y: YBnd<P>, Z: ZBnd<P>): P =
        when (this) {
            is Const<*, *> -> this as P
            is FunYZ<*> -> (this as FunYZ<P>)(Y, Z)
            is FunY<*> -> (this as FunY<P>)(Y)
            is FunZ<*> -> (this as FunZ<P>)(Z)
            else -> throw IllegalStateException(toString())
        }

    operator fun invoke(Y: YBnd<P>, Z: ZBnd<P>): P =
        op(left(Y, Z), right(Y, Z))

    private operator fun BiFn<*>.invoke(Y: YBnd<P>): FunZ<P> =
        when (this) {
            is Const<*, *> -> FunZ(this as P)
            is FunYZ<*> -> (this as FunYZ<P>)(Y)
            is FunY<*> -> FunZ((this as FunY<P>)(Y))
            is FunZ<*> -> this as FunZ<P>
            else -> throw IllegalStateException(toString())
        }

    private operator fun BiFn<*>.invoke(Z: ZBnd<P>): FunY<P> =
        when (this) {
            is Const<*, *> -> FunY(this as P)
            is FunYZ<*> -> (this as FunYZ<P>)(Z)
            is FunZ<*> -> FunY((this as FunZ<P>)(Z))
            is FunY<*> -> this as FunY<P>
            else -> throw IllegalStateException(toString())
        }

    operator fun invoke(Y: YBnd<P>): FunZ<P> = FunZ(left(Y), right(Y), op)
    operator fun invoke(Z: ZBnd<P>): FunY<P> = FunY(left(Z), right(Z), op)
}

class FunXYZ<P: Const<P, in Number>>(override val left: BiFn<*>,
                                     override val right: BiFn<*>,
                                     override val op: Op<P>): BiFn<P>(left, right, op) {
    operator fun plus(that: P): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun times(that: P): FunXYZ<P> = FunXYZ(this, that, mul)
    operator fun minus(that: P): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun div(that: P): FunXYZ<P> = FunXYZ(this, that, div)

    operator fun minus(that: FunX<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun minus(that: FunY<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun minus(that: FunZ<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun minus(that: FunXY<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun minus(that: FunXZ<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun minus(that: FunYZ<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun minus(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, sub)
    operator fun div(that: FunX<P>): FunXYZ<P> = FunXYZ(this, that, div)
    operator fun div(that: FunY<P>): FunXYZ<P> = FunXYZ(this, that, div)
    operator fun div(that: FunZ<P>): FunXYZ<P> = FunXYZ(this, that, div)
    operator fun div(that: FunXY<P>): FunXYZ<P> = FunXYZ(this, that, div)
    operator fun div(that: FunXZ<P>): FunXYZ<P> = FunXYZ(this, that, div)
    operator fun div(that: FunYZ<P>): FunXYZ<P> = FunXYZ(this, that, div)
    operator fun div(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, div)

    operator fun plus(that: FunX<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun plus(that: FunY<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun plus(that: FunZ<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun plus(that: FunXY<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun plus(that: FunXZ<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun plus(that: FunYZ<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun plus(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, add)
    operator fun times(that: FunX<P>): FunXYZ<P> = FunXYZ(this, that, mul)
    operator fun times(that: FunY<P>): FunXYZ<P> = FunXYZ(this, that, mul)
    operator fun times(that: FunZ<P>): FunXYZ<P> = FunXYZ(this, that, mul)
    operator fun times(that: FunXY<P>): FunXYZ<P> = FunXYZ(this, that, mul)
    operator fun times(that: FunXZ<P>): FunXYZ<P> = FunXYZ(this, that, mul)
    operator fun times(that: FunYZ<P>): FunXYZ<P> = FunXYZ(this, that, mul)
    operator fun times(that: FunXYZ<P>): FunXYZ<P> = FunXYZ(this, that, mul)

    private operator fun BiFn<*>.invoke(X: XBnd<P>, Y: YBnd<P>, Z: ZBnd<P>): P =
        when (this) {
            is Const<*, *> -> this as P
            is FunXYZ<*> -> (this as FunXYZ<P>)(X, Y, Z)
            is FunXY<*> -> (this as FunXY<P>)(X, Y)
            is FunXZ<*> -> (this as FunXZ<P>)(X, Z)
            is FunYZ<*> -> (this as FunYZ<P>)(Y, Z)
            is FunX<*> -> (this as FunX<P>)(X)
            is FunY<*> -> (this as FunY<P>)(Y)
            is FunZ<*> -> (this as FunZ<P>)(Z)
            else -> throw IllegalStateException(toString())
        }

    operator fun invoke(X: XBnd<P>, Y: YBnd<P>, Z: ZBnd<P>): P =
        op(left(X, Y, Z), right(X, Y, Z))

    private operator fun BiFn<*>.invoke(X: XBnd<P>, Z: ZBnd<P>): FunY<P> =
        when (this) {
            is Const<*, *> -> FunY(this as P)
            is FunXYZ<*> -> (this as FunXYZ<P>)(X, Z)
            is FunXY<*> -> (this as FunXY<P>)(X)
            is FunXZ<*> -> FunY((this as FunXZ<P>)(X, Z))
            is FunYZ<*> -> (this as FunYZ<P>)(Z)
            is FunX<*> -> FunY((this as FunX<P>)(X))
            is FunY<*> -> this as FunY<P>
            is FunZ<*> -> FunY((this as FunZ<P>)(Z))
            else -> throw IllegalStateException(toString())
        }

    operator fun invoke(X: XBnd<P>, Z: ZBnd<P>): FunY<P> =
        FunY(left(X, Z), right(X, Z), op)

    private operator fun BiFn<*>.invoke(X: XBnd<P>, Y: YBnd<P>): FunZ<P> =
        when (this) {
            is Const<*, *> -> FunZ(this as P)
            is FunXYZ<*> -> (this as FunXYZ<P>)(X, Y)
            is FunXY<*> -> FunZ((this as FunXY<P>)(X, Y))
            is FunXZ<*> -> (this as FunXZ<P>)(X)
            is FunYZ<*> -> (this as FunYZ<P>)(Y)
            is FunX<*> -> FunZ((this as FunX<P>)(X))
            is FunY<*> -> FunZ((this as FunY<P>)(Y))
            is FunZ<*> -> this as FunZ<P>
            else -> throw IllegalStateException(toString())
        }

    operator fun invoke(X: XBnd<P>, Y: YBnd<P>): FunZ<P> =
        FunZ(left(X, Y), right(X, Y), op)

    private operator fun BiFn<*>.invoke(Y: YBnd<P>, Z: ZBnd<P>): FunX<P> =
        when (this) {
            is Const<*, *> -> FunX(this as P)
            is FunXYZ<*> -> (this as FunXYZ<P>)(Y, Z)
            is FunXY<*> -> (this as FunXY<P>)(Y)
            is FunXZ<*> -> (this as FunXZ<P>)(Z)
            is FunYZ<*> -> FunX((this as FunYZ<P>)(Y, Z))
            is FunX<*> -> this as FunX<P>
            is FunY<*> -> FunX((this as FunY<P>)(Y))
            is FunZ<*> -> FunX((this as FunZ<P>)(Z))
            else -> throw IllegalStateException(toString())
        }

    operator fun invoke(Y: YBnd<P>, Z: ZBnd<P>): FunX<P> =
        FunX(left(Y, Z), right(Y, Z), op)

    private operator fun BiFn<*>.invoke(X: XBnd<P>): FunYZ<P> =
        when (this) {
            is Const<*, *> -> FunYZ(this as P)
            is FunXYZ<*> -> (this as FunXYZ<P>)(X)
            is FunXY<*> -> FunYZ((this as FunXY<P>)(X))
            is FunXZ<*> -> FunYZ((this as FunXZ<P>)(X))
            is FunYZ<*> -> this as FunYZ<P>
            is FunX<*> -> FunYZ(X.const)
            is FunY<*> -> FunYZ(this as FunY<P>)
            is FunZ<*> -> FunYZ(this as FunZ<P>)
            else -> throw IllegalStateException(toString())
        }

    operator fun invoke(X: XBnd<P>): FunYZ<P> = FunYZ(left(X), right(X), op)

    private operator fun BiFn<*>.invoke(Y: YBnd<P>): FunXZ<P> =
        when (this) {
            is Const<*, *> -> FunXZ(this as P)
            is FunXYZ<*> -> (this as FunXYZ<P>)(Y)
            is FunXY<*> -> FunXZ((this as FunXY<P>)(Y))
            is FunXZ<*> -> this as FunXZ<P>
            is FunYZ<*> -> FunXZ((this as FunYZ<P>)(Y))
            is FunX<*> -> FunXZ(this as FunX<P>)
            is FunY<*> -> FunXZ(Y.const)
            is FunZ<*> -> FunXZ(this as FunZ<P>)
            else -> throw IllegalStateException(toString())
        }

    operator fun invoke(Y: YBnd<P>): FunXZ<P> = FunXZ(left(Y), right(Y), op)

    private operator fun BiFn<*>.invoke(Z: ZBnd<P>): FunXY<P> =
        when (this) {
            is Const<*, *> -> FunXY(this as P)
            is FunXYZ<*> -> (this as FunXYZ<P>)(Z)
            is FunXY<*> -> this as FunXY<P>
            is FunXZ<*> -> FunXY((this as FunXZ<P>)(Z))
            is FunYZ<*> -> FunXY((this as FunYZ<P>)(Z))
            is FunX<*> -> FunXY(this as FunX<P>)
            is FunY<*> -> FunXY(this as FunY<P>)
            is FunZ<*> -> FunXY(Z.const)
            else -> throw IllegalStateException(toString())
        }

    operator fun invoke(Z: ZBnd<P>): FunXY<P> = FunXY(left(Z), right(Z), op)
}

abstract class BiFn<T: Const<T, Number>>(open val left: BiFn<*>? = null,
                                         open val right: BiFn<*>? = null,
                                         open val op: Op<*>? = null) {
    val add: Add<T> by lazy { Add<T>() }
    val mul: Mul<T> by lazy { Mul<T>() }
    val sub: Sub<T> by lazy { Sub<T>() }
    val div: Div<T> by lazy { Div<T>() }

    override fun toString() = "$left $op $right"
}

abstract class Const<T: Const<T, Number>, Y: Number>(internal open val c: Y): BiFn<T>() {
    override fun toString() = c.toString()
    abstract operator fun plus(that: T): T
    abstract operator fun times(that: T): T
    abstract operator fun minus(that: T): T
    abstract operator fun div(that: T): T
}

class DConst(override val c: Double): Const<DConst, Number>(c) {
    override fun plus(that: DConst) = DConst(this.c + that.c)
    override fun times(that: DConst) = DConst(this.c * that.c)
    override fun minus(that: DConst) = DConst(this.c - that.c)
    override fun div(that: DConst) = DConst(this.c / that.c)
}

class Int32(override val c: Int): Const<Int32, Number>(c) {
    override fun plus(that: Int32) = Int32(this.c + that.c)
    override fun times(that: Int32) = Int32(this.c * that.c)
    override fun minus(that: Int32) = Int32(this.c - that.c)
    override fun div(that: Int32) = Int32(this.c / that.c)
}

abstract class Op<T: Const<T, in Number>>(val string: String): (T, T) -> T {
    override fun toString() = string
}

class Add<T: Const<T, in Number>>: Op<T>("+") {
    override fun invoke(l: T, r: T): T = l + r
}

class Mul<T: Const<T, in Number>>: Op<T>("*") {
    override fun invoke(l: T, r: T): T = l * r
}

class Sub<T: Const<T, in Number>>: Op<T>("-") {
    override fun invoke(l: T, r: T): T = l - r
}

class Div<T: Const<T, in Number>>: Op<T>("/") {
    override fun invoke(l: T, r: T): T = l / r
}

class First<T: Const<T, in Number>>: Op<T>("") {
    override fun invoke(l: T, r: T): T = l
}

@Suppress("PropertyName")
sealed class Proto<T: Const<T, in Number>, Q: Number> {
    abstract fun wrap(default: Number): T

    abstract val X: FunX<T>
    abstract val Y: FunY<T>
    abstract val Z: FunZ<T>

    operator fun FunX<T>.plus(c: Q): FunX<T> = this + wrap(c)
    operator fun FunY<T>.plus(c: Q): FunY<T> = this + wrap(c)
    operator fun FunZ<T>.plus(c: Q): FunZ<T> = this + wrap(c)
    operator fun FunXY<T>.plus(c: Q): FunXY<T> = this + wrap(c)
    operator fun FunXZ<T>.plus(c: Q): FunXZ<T> = this + wrap(c)
    operator fun FunYZ<T>.plus(c: Q): FunYZ<T> = this + wrap(c)
    operator fun FunXYZ<T>.plus(c: Q): FunXYZ<T> = this + wrap(c)

    operator fun FunX<T>.times(c: Q): FunX<T> = this * wrap(c)
    operator fun FunY<T>.times(c: Q): FunY<T> = this * wrap(c)
    operator fun FunZ<T>.times(c: Q): FunZ<T> = this * wrap(c)
    operator fun FunXY<T>.times(c: Q): FunXY<T> = this * wrap(c)
    operator fun FunXZ<T>.times(c: Q): FunXZ<T> = this * wrap(c)
    operator fun FunYZ<T>.times(c: Q): FunYZ<T> = this * wrap(c)
    operator fun FunXYZ<T>.times(c: Q): FunXYZ<T> = this * wrap(c)

    // TODO: Make these order-preserving to support non-commutative algebras
    operator fun Q.plus(c: FunX<T>): FunX<T> = c + wrap(this)
    operator fun Q.plus(c: FunY<T>): FunY<T> = c + wrap(this)
    operator fun Q.plus(c: FunZ<T>): FunZ<T> = c + wrap(this)
    operator fun Q.plus(c: FunXY<T>): FunXY<T> = c + wrap(this)
    operator fun Q.plus(c: FunXZ<T>): FunXZ<T> = c + wrap(this)
    operator fun Q.plus(c: FunYZ<T>): FunYZ<T> = c + wrap(this)
    operator fun Q.plus(c: FunXYZ<T>): FunXYZ<T> = c + wrap(this)

    operator fun Q.times(c: FunX<T>): FunX<T> = c * wrap(this)
    operator fun Q.times(c: FunY<T>): FunY<T> = c * wrap(this)
    operator fun Q.times(c: FunZ<T>): FunZ<T> = c * wrap(this)
    operator fun Q.times(c: FunXY<T>): FunXY<T> = c * wrap(this)
    operator fun Q.times(c: FunXZ<T>): FunXZ<T> = c * wrap(this)
    operator fun Q.times(c: FunYZ<T>): FunYZ<T> = c * wrap(this)
    operator fun Q.times(c: FunXYZ<T>): FunXYZ<T> = c * wrap(this)

    abstract infix fun FunX<T>.to(c: Q): XBnd<T>
    abstract infix fun FunY<T>.to(c: Q): YBnd<T>
    abstract infix fun FunZ<T>.to(c: Q): ZBnd<T>
    abstract val Const<T, Number>.value: Q
}

object DoubleContext: Proto<DConst, Double>() {
    override val Const<DConst, Number>.value: Double
        get() = c.toDouble()

    override fun wrap(default: Number): DConst = DConst(default.toDouble())
    override val X: FunX<DConst> = object: FunX<DConst>(DConst(0.0), DConst(0.0), First()) {
        override fun invoke(X: XBnd<DConst>): DConst = X.const
        override fun toString() = "X"
    }
    override val Y: FunY<DConst> = object: FunY<DConst>(DConst(0.0), DConst(0.0), First()) {
        override fun invoke(Y: YBnd<DConst>): DConst = Y.const
        override fun toString() = "Y"
    }
    override val Z: FunZ<DConst> = object: FunZ<DConst>(DConst(0.0), DConst(0.0), First()) {
        override fun invoke(Z: ZBnd<DConst>): DConst = Z.const
        override fun toString() = "Z"
    }

    operator fun FunX<DConst>.invoke(X: Double) = this(XBnd(DConst(X)))
    operator fun FunY<DConst>.invoke(Y: Double) = this(YBnd(DConst(Y)))
    operator fun FunZ<DConst>.invoke(Z: Double) = this(ZBnd(DConst(Z)))
    operator fun FunXY<DConst>.invoke(X: Double, Y: Double) =
        this(XBnd(DConst(X)), YBnd(DConst(Y)))
    operator fun FunXZ<DConst>.invoke(X: Double, Z: Double) =
        this(XBnd(DConst(X)), ZBnd(DConst(Z)))
    operator fun FunYZ<DConst>.invoke(Y: Double, Z: Double) =
        this(YBnd(DConst(Y)), ZBnd(DConst(Z)))
    operator fun FunXYZ<DConst>.invoke(X: Double, Y: Double, Z: Double) =
        this(XBnd(DConst(X)), YBnd(DConst(Y)), ZBnd(DConst(Z)))

    override infix fun FunX<DConst>.to(c: Double) = XBnd(DConst(c))
    override infix fun FunY<DConst>.to(c: Double) = YBnd(DConst(c))
    override infix fun FunZ<DConst>.to(c: Double) = ZBnd(DConst(c))
}

object IntContext: Proto<Int32, Int>() {
    override val Const<Int32, Number>.value: Int
        get() = c.toInt()

    override fun wrap(default: Number): Int32 = Int32(default.toInt())
    override val X: FunX<Int32> = object: FunX<Int32>(Int32(0), Int32(0), First()) {
        override fun invoke(X: XBnd<Int32>): Int32 = X.const
        override fun toString() = "X"
    }
    override val Y: FunY<Int32> = object: FunY<Int32>(Int32(0), Int32(0), First()) {
        override fun invoke(Y: YBnd<Int32>): Int32 = Y.const
        override fun toString() = "Y"
    }
    override val Z: FunZ<Int32> = object: FunZ<Int32>(Int32(0), Int32(0), First()) {
        override fun invoke(Z: ZBnd<Int32>): Int32 = Z.const
        override fun toString() = "Z"
    }

    operator fun FunX<Int32>.invoke(X: Int) = this(XBnd(Int32(X)))
    operator fun FunY<Int32>.invoke(Y: Int) = this(YBnd(Int32(Y)))
    operator fun FunZ<Int32>.invoke(Z: Int) = this(ZBnd(Int32(Z)))
    operator fun FunXY<Int32>.invoke(X: Int, Y: Int) =
        this(XBnd(Int32(X)), YBnd(Int32(Y)))
    operator fun FunXZ<Int32>.invoke(X: Int, Z: Int) =
        this(XBnd(Int32(X)), ZBnd(Int32(Z)))
    operator fun FunYZ<Int32>.invoke(Y: Int, Z: Int) =
        this(YBnd(Int32(Y)), ZBnd(Int32(Z)))
    operator fun FunXYZ<Int32>.invoke(X: Int, Y: Int, Z: Int) =
        this(XBnd(Int32(X)), YBnd(Int32(Y)), ZBnd(Int32(Z)))

    override infix fun FunX<Int32>.to(c: Int) = XBnd(Int32(c))
    override infix fun FunY<Int32>.to(c: Int) = YBnd(Int32(c))
    override infix fun FunZ<Int32>.to(c: Int) = ZBnd(Int32(c))
}

class XBnd<T: Const<T, in Number>>(val const: T)
class YBnd<T: Const<T, in Number>>(val const: T)
class ZBnd<T: Const<T, in Number>>(val const: T)