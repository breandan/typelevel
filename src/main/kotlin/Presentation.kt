import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import org.jooq.DSLContext
import org.jooq.example.db.h2.Tables.*
import org.jooq.impl.DSL
import java.io.PrintStream

/*============================================
                 Kotlin∇:





           A Shape-Safe eDSL for
   Differentiable Functional Programming





             Breandan Considine
============================================*/


/*===============================================


"There is a race between the increasing complexity
of the systems we build and our ability to develop
intellectual tools for understanding their
complexity. If the race is won by our tools, then
systems will eventually become easier to use and
more reliable. If not, they will continue to
become harder to use and less reliable for all
but a relatively small set of common tasks. Given
how hard thinking is, if those intellectual tools
are to succeed, they will have to substitute
calculation for thought." -- Leslie Lamport


===============================================*/


// An Introduction to Type Safety

fun getLength(x: Any) =
//    if (x is String) x.length else 0
    when (x) {
        is String -> x.length
        is List<*> -> x.size
        is Map<*, *> -> x.size
        is Number -> x.toString().length
        else -> 0
    }


val airplane = Chassis
    .addWings()
    .addEngines()
    .addWheels()
    .addTail()
    .build()


val t = StringBuilder(1).appendHTML().html {
    head {
        title { +"Hello MAIS" }
    }
    body {
        a("http://website.com") {
            +"A link"
        }
    }
}

// A SQL DSL

fun DSLContext.use() {
    val a = AUTHOR
    val b = BOOK

    select(a.FIRST_NAME, a.LAST_NAME, b.TITLE)
        .from(a)
        .join(b).on(a.ID.eq(b.AUTHOR_ID))
}

fun test() {
    with(DoublePrecision) {
        val x = Var("x", 0.0)
        val y = Var("y", 0.0)

        val f = x pow 2
        println(f(x to 3.0))
        println("f(x) = $f")
        val df_dx = f.diff(x)
        println("f'(x) = $df_dx")

        val g = x pow x
        println("g(x) = $g")
        val dg_dx = g.diff(x)
        println("g'(x) = $dg_dx")

        val h = x + y
        println("h(x) = $h")
        val dh_dx = h.diff(x)
        println("h'(x) = $dh_dx")

        val vf1 = VFun(y + x, y * 2)
        val bh = x * vf1
        val vf2 = VFun(x, y)
        val q = vf1 + vf2
        val z = q(x to 1.0, y to 2.0)
        println("z: $z")
    }
}

// Variable capture

fun example3() {
    with(DoubleContext) {
        val q = X + Y + Z + Y + 0.0
        println("q = $q")
        val totalApp = q(X to 1.0, Y to 2.0, Z to 3.0)
        println(totalApp)
        val partialApp = q(X to 1.0, Y to 1.0)(Z to 1.0)
        println(partialApp)
        val partialApp2 = q(X to 1.0)(Y to 1.0, Z to 1.0)
        println(partialApp2)
        val partialApp3 = q(Z to 1.0)(X to 1.0, Y to 1.0)
        println(partialApp3)
    }
}