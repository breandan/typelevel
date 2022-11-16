@file:Suppress("UnusedImport", "LocalVariableName", "SpellCheckingInspection")

package answers

import answers.libs.*
import answers.libs.BVec
import libs.Airplane
import libs.Chassis
import libs.DoublePrecision
import libs.Vec
import libs.addEngines
import libs.addTail
import libs.addWheels
import libs.addWings
import libs.*
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import org.jooq.DSLContext
import org.jooq.Result
import org.jooq.example.db.h2.Tables.*
import libs.x
import libs.y
import libs.z


/*=====================================================




               Types and why they matter


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
      a(href = "http://google.com") {
        +"Hello world!"
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
  val q = x + y - z + y + 3
  val m = q(x to 1, y to 2, z to 3)
  val r = q(x to 1, y to 1)(z to 1)
  val s = q(x to 1)(y to 1)(z to 1)
  val u = q(z to 1)(y to 1)
}

// LFSR

fun lfsr5() = BVec(T, F, F, T, T)
  .lfsr().lfsr().lfsr().lfsr().lfsr().lfsr()
  .lfsr().lfsr().lfsr().lfsr().lfsr().lfsr()
  .lfsr().lfsr().lfsr().lfsr().lfsr().lfsr()
  .lfsr().lfsr().lfsr().lfsr().lfsr().lfsr()
  .lfsr().lfsr().lfsr().lfsr().lfsr().lfsr()
  .lfsr().lfsr().lfsr().lfsr().lfsr().lfsr()
  .lfsr().lfsr().lfsr().lfsr().lfsr().lfsr()
  .lfsr().lfsr().lfsr()

// Elementary cellular automata

fun eca10() = BVec(F, F, F, F, F, F, F, F, F, T)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r) // BVec10<F, F, F, F, F, F, F, F, F, T>
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r, ::r)

// Type-safe vector operations

fun vectorTest() {
  val t = VT(1, 2, 3)
  assert(S3 == t.len)
  assert(3 == t.size)
//  val e4 = t[S4] // Compile error
  val e3 = t[S3] // Okay

  assert(3 == e3)
  assert(1 == t[S1])

  val r = t.take3()
//    t.take4() // Compile error

  val t2 = t cc t.take2()
  assert(S5 == t2.len)
  assert(2 == t2[S5])

  t2[S2..S4]
//    t2[S2..S1] // Compile error
//    t2[S3..S5] // Compile error

  val t3 = t2.drop3().append(0)
  assert(2 == t3[S2])
  assert(S3 == t3.len)
}

fun concatTest() {
  val t = VT(1, 2, 3, 4)
  val s = t cc t cc VT(1) cc VT(2)
}

fun naperianVTtorTest() {
  val t: TS4<Int> = TV(1, 2, 3, 4)
  assert(S4 == t.len())
//  val e4 = t[S5] // Compile error
  val e3 = t[S3] // Okay

  assert(3 == e3)
  assert(t[S1] == 1)

  val r = t.take4().drop2()
//    t.take5() // Compile error

  assert(3 == r[S1])
  assert(S2 == r.len())
}

fun matMulTest() {
  val m3x3 = VT(VT(1, 2, 3), VT(1, 2, 3), VT(1, 2, 3))
  val m3x2 = VT(VT(1, 2), VT(1, 2), VT(1, 2))
  val m2x2 = VT(VT(1, 2), VT(1, 2))

  try { m3x3 * m3x2 * m2x2 } catch (e: NotImplementedError) {}
//  m3x2 * m3x2 // Compile error
}

fun naperianMatTest() {
  val m3x3: TM3x3<Int> = TV(TV(1, 2, 3), TV(1, 2, 3), TV(1, 2, 3))
  val m3x2: TM3x2<Int> = TV(TV(1, 2), TV(1, 2), TV(1, 2))
  val m2x2: TM2x2<Int> = TV(TV(1, 2), TV(1, 2))

//    try { m3x3 * m3x2 * m2x2 } catch (e: NotImplementedError) {}
//  m3x2 * m3x2 // Compile error
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