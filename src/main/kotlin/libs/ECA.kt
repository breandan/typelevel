package answers.libs

import kotlin.math.absoluteValue

// Rule 110 Encoding
fun r(p: T, q: T, r: T) = F //(q and p.flip()) or (q xor r)
fun r(p: T, q: T, r: F) = T //(q and p.flip()) or (q xor r)
fun r(p: T, q: F, r: T) = T //(q and p.flip()) or (q xor r)
fun r(p: T, q: F, r: F) = F //(q and p.flip()) or (q xor r)
fun r(p: F, q: T, r: T) = T //(q and p.flip()) or (q xor r)
fun r(p: F, q: T, r: F) = T //(q and p.flip()) or (q xor r)
fun r(p: F, q: F, r: T) = T //(q and p.flip()) or (q xor r)
fun r(p: F, q: F, r: F) = F //(q and p.flip()) or (q xor r)

// Typelevel implementation of Rule 110
val eca10 = BVec(F, F, F, F, F, F, F, F, F, T)
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

val eca4 = BVec(T, F, F, T)
  .eca(::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r)
  .eca(::r, ::r, ::r, ::r)

fun <
  B0, B1, B2, B3,
  Y0, Y1, Y2, Y3
  > BVec4<B0, B1, B2, B3>.eca(
  // Encodes periodic boundary conditions
  op0: (B3, B0, B1) -> Y0,
  op1: (B0, B1, B2) -> Y1,
  op2: (B1, B2, B3) -> Y2,
  op3: (B2, B3, B0) -> Y3,
): BVec4<Y0, Y1, Y2, Y3> =
  BVec4(
    op0(d, a, b),
    op1(a, b, c),
    op2(b, c, d),
    op3(c, d, a),
  )

fun <
  B0, B1, B2, B3, B4, B5, B6, B7, B8, B9,
  Y0, Y1, Y2, Y3, Y4, Y5, Y6, Y7, Y8, Y9,
> BVec10<B0, B1, B2, B3, B4, B5, B6, B7, B8, B9>.eca(
  // Encodes periodic boundary conditions
  op0: (B9, B0, B1) -> Y0,
  op1: (B0, B1, B2) -> Y1,
  op2: (B1, B2, B3) -> Y2,
  op3: (B2, B3, B4) -> Y3,
  op4: (B3, B4, B5) -> Y4,
  op5: (B4, B5, B6) -> Y5,
  op6: (B5, B6, B7) -> Y6,
  op7: (B6, B7, B8) -> Y7,
  op8: (B7, B8, B9) -> Y8,
  op9: (B8, B9, B0) -> Y9,
): BVec10<Y0, Y1, Y2, Y3, Y4, Y5, Y6, Y7, Y8, Y9> =
  BVec10(
    op0(b9, b0, b1),
    op1(b0, b1, b2),
    op2(b1, b2, b3),
    op3(b2, b3, b4),
    op4(b3, b4, b5),
    op5(b4, b5, b6),
    op6(b5, b6, b7),
    op7(b6, b7, b8),
    op8(b7, b8, b9),
    op9(b8, b9, b0),
  )