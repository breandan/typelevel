@file:Suppress("UnusedImport", "LocalVariableName", "SpellCheckingInspection")

import DoublePrecision.x
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import org.jooq.DSLContext
import org.jooq.Record3
import org.jooq.Result
import org.jooq.example.db.h2.Tables.*
import kotlin.random.Random
import java.lang.StringBuilder

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
    TODO()
}














// Type-safe Builder pattern

fun buildAirplane(): Airplane {
    TODO()
}















// "Fluent Interface"

fun getQueryResults(context: DSLContext): Result<*>? {
    TODO()
}














// Embedded Domain Specific Langauge

fun buildWebpage(): String {
    TODO()
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

fun kotlingradSafeDemo() {
    TODO()
}





















// Shape-safety

fun shapeSafeDemo() {
    TODO()
}

















// Type-safe variable capture

fun variableCapture() {
    TODO()
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
