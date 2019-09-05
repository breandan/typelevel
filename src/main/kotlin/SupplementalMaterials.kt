import Airplane.*

class Airplane {
    abstract class OK
    abstract class NO

    open class PlaneBuilder<A, B, C, D> internal constructor()
}

object Chassis: PlaneBuilder<NO, NO, NO, NO>()

fun <X, Y> PlaneBuilder<NO, X, OK, Y>.addWheels(/*vararg wheel: Wheel*/): PlaneBuilder<OK, X, OK, Y> = PlaneBuilder()
fun <X, Y> PlaneBuilder<X, NO, OK, Y>.addEngines(/*left: Engine, right: Engine*/): PlaneBuilder<X, OK, OK, Y> = PlaneBuilder()
fun <X> PlaneBuilder<NO, NO, NO, X>.addWings(/*left: Wing, right: Wing*/): PlaneBuilder<NO, NO, OK, X> = PlaneBuilder()
fun <X, Y, Z> PlaneBuilder<X, Y, Z, NO>.addTail(/*t: Tail*/): PlaneBuilder<X, Y, Z, OK> = PlaneBuilder()
fun PlaneBuilder<OK, OK, OK, OK>.build() = Airplane()

class Wheel
class Tail
class Wing
class Engine