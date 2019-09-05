import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import org.jooq.DSLContext
import org.jooq.example.db.h2.Tables.*
import org.jooq.impl.DSL
import java.io.PrintStream
import kotlin.random.Random
import java.lang.StringBuilder

/*==============================================



                     Kotlin∇:

               A Shape-Safe eDSL for
       Differentiable Functional Programming

                 Code: kg.ndan.co


                Breandan Considine




================================================


" There is a race between the increasing
  complexity of the systems we build and our
  ability to develop intellectual tools for
  understanding their complexity.

  If the race is won by our tools, then
  systems will eventually become easier to
  use and more reliable. If not, they will
  continue to become harder to use and less
  reliable for all but a relatively small
  set of common tasks.

  Given how hard thinking is, if those
  intellectual tools are to succeed,
  they will have to substitute calculation
  for thought. " --Leslie Lamport


===============================================*/


// An Gentle Introduction to Type Safety

fun getLength(a: Any) =
    when(a) {
        is String -> a.length
        is Collection<*> -> a.size
        is Map<*, *> -> a.size
        else -> 1
    }


val q = if(Random.nextBoolean()) null else "t"
val r = q?.length ?: 0
val s = r / 2


val airplane = Chassis
    .addWings()
    .addEngines()
    .addWheels()
    .addTail()
    .build()


val t = StringBuilder().appendHTML().html {
    head {
        title("test")
    }
    body {
        a(href = "http://montrealaisymposium.com") {
            +"Hello MAIS!"
        }
    }
}

// A SQL DSL

fun DSLContext.query() {
    select(AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME, BOOK.TITLE)
        .from(AUTHOR)
        .join(BOOK).on(BOOK.AUTHOR_ID.eq(AUTHOR.ID))
        .where(AUTHOR.LAST_NAME.contains("Iverson"))
}



/*====================================================


Although mathematical notation undoubtedly possesses
parsing rules, they are rather loose, sometimes
contradictory, and seldom clearly stated...

The proliferation of programming languages shows no more
uniformity than mathematics. Nevertheless, programming
languages do bring a different perspective...

Because of their application to a broad range of topics,
their strict grammar, and their strict interpretation,
programming languages can provide new insights into
mathematical notation. --Kenneth Iverson


=====================================================*/



fun test() {
    with(DoublePrecision) {
        val x = Var("x", 0.0)
        val y = Var("y", 0.0)

        val f = x pow 2
        val df_dx = f.diff(x)
        val g = x pow x
        val dg_dx = g.diff(x)

        val h = x + y
        val dh_dx = h.diff(x)

        val vf1 = VFun(y + x, y * 2)
        val bh = x * vf1
        val vf2 = VFun(x, y)
        val q = vf1 + vf2
        val z = q(x to 1.0, y to 2.0)
    }
}

// Type-safe variable capture

fun variableCapture() {
    with(IntContext) {
        val q = X + Y - Z + Y + 3
        val m = q(X = 1, Y = 2, Z = 3)
        val r = q(X to 1, Y to 1)(Z to 1)
        val s = q(X to 1)(Y to 1)(Z to 1)
        val u = q(Z to 1)(Y to 1)
    }
}