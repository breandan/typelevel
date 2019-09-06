@file:Suppress("UnusedImport", "LocalVariableName", "SpellCheckingInspection")

package answers

import Airplane
import Chassis
import DoublePrecision
import IntPrecision
import Vec
import addEngines
import addTail
import addWheels
import addWings
import build
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import org.jooq.DSLContext
import org.jooq.Result
import org.jooq.example.db.h2.Tables.*

/*=====================================================




                       Kotlin∇:

                 A Shape-Safe eDSL for
         Differentiable Functional Programming

            (or types and why they matter)

                   Code: kg.ndan.co


                  Breandan Considine



=======================================================


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


=====================================================*/


// Gentle Introduction to Type Safety

fun length(a: Any): Int {
    return when (a) {
        is String -> a.length
        is Collection<*> -> a.size
        is Map<*, *> -> a.size
        else -> 1
    }
}

// Type-safe Builder pattern

fun buildAirplane(): Airplane {
    return Chassis
        .addWings()
        .addEngines()
        .addWheels()
        .addTail()
        .build()
}

// "Fluent Interface"

fun getQueryResults(context: DSLContext): Result<*>? {
    return context
        .select(AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME, BOOK.TITLE)
        .from(AUTHOR)
        .join(BOOK).on(BOOK.AUTHOR_ID.eq(AUTHOR.ID))
        .where(AUTHOR.LAST_NAME.contains("Iverson")).fetch()
}

// Embedded Domain Specific Langauge

fun buildWebpage(): String {
    return StringBuilder().appendHTML().html {
        head {
            title("test")
        }
        body {
            a(href = "http://montrealaisymposium.com") {
                +"Hello MAIS!"
            }
        }
    }.toString()
}

/*======================================================


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


========================================================*/

// Primitive type-safety

fun typeSafeDemo() {
    with(DoublePrecision) {
        val f = x pow 2
        val df_dx = f.diff(x)
        val g = x pow x
        val dg_dx = g.diff(x)
        val h = x + y
        val dh_dx = d(h) / d(x)
        val d2h_dx2 = d(dh_dx) / d(x)
    }
}

// Shape-safety

fun shapeSafeDemo() {
    with(DoublePrecision) {
        val vf1 = Vec(y + x, y * 2) + Vec(1.0, 2.0)
        val bh = x * vf1 + Vec(1.0, 2.0)
        val vf2 = Vec(x, y)
        val q = vf1 + vf2
        val z = q(x to 1.0, y to 2.0)
        val c = Vec(1.0, 2.0, 3.0) * Vec(1.0, 2.0, 3.0)
        val m = vf1.diff(x)
        println("m = $m")
    }
}

// Type-safe variable capture

fun variableCapture() {
    with(IntPrecision) {
        val q = X + Y - Z + Y + 3
        val m = q(X = 1, Y = 2, Z = 3)
        val r = q(X to 1, Y to 1)(Z to 1)
        val s = q(X to 1)(Y to 1)(Z to 1)
        val u = q(Z to 1)(Y to 1)
    }
}

/*===============================================








" The expressive power of a programming langauge
  comes from its stricutres, not its affordances."

  -- Robert Harper







===================================================





  IFT 6172 -- Semantics of programming languages

                   Stefan Monnier

         Day 	    Time 	        Place
         Monday 	10h30-12h30 	Y-115
         Wednesday 	10h30-12h30 	Z-205

 www.iro.umontreal.ca/~monnier/6172/index.en.html






===================================================


                Special thanks to:
                                 _
                   Liam Paull     |
                                  |- Super 'visors
                Michalis Famelis _|

                                   _
         Hanneli Tavante            |
         Alexander Nozik            |
         Erik Meijer                |- Great advice
         Maxime Chevalier-Boisvert  |
         Kiran Gopinathan          _|



===================================================*/
