package answers.session_types

sealed interface ChannelState
object Full : ChannelState
object Empty : ChannelState

class SingleMessageChannel<S : ChannelState, T> internal constructor(internal val message: T?) {
  companion object {
    operator fun <T> invoke(msg: T): SingleMessageChannel<Full, T> = SingleMessageChannel(message = msg)
  }
}

fun <T> SingleMessageChannel<Full, T>.consume(): Pair<T, SingleMessageChannel<Empty, T>> =
  requireNotNull(this.message) { "In a Full channel, the message should never be null!" } to
      SingleMessageChannel<Empty, T>(message = null)

fun main() {
  val ch = SingleMessageChannel<String>("Hello")
//  val ch_copy = ch // <- In Rust this would be illegal?

  val (msg, emptyCh) = ch.consume()

  println("Got message: $msg")

// emptyCh.consume() // <-- Compile error!

  val c0: Connection<Disconnected> = Connection()
  val c1: Connection<Connected> = c0.connect()
  val c2: Connection<AuthInProgress> = c1.startAuth()
  val c3: Connection<Authenticated> = c2.completeAuth()
  val c4: Connection<Terminated> = c3.disconnect()

// c2.disconnect() // <-- Compile error!
}

sealed interface ConnState

object Disconnected   : ConnState
object Connected      : ConnState
object AuthInProgress : ConnState
object Authenticated  : ConnState
object Terminated     : ConnState
class Connection<S    : ConnState> internal constructor(private val info: String? = null) {
  companion object {
    operator fun invoke(): Connection<Disconnected> =
      Connection(info = "Just created; not yet connected.")
  }
}

fun Connection<Disconnected>.connect(): Connection<Connected> =
  Connection(info = "Connection established.")

fun Connection<Connected>.startAuth(): Connection<AuthInProgress> =
  Connection(info = "Auth is in progress...")

fun Connection<AuthInProgress>.completeAuth(): Connection<Authenticated> =
  Connection(info = "Connection fully authenticated.")

fun Connection<Connected>.disconnect(): Connection<Terminated> =
  Connection(info = "Terminated.")

fun Connection<Authenticated>.disconnect(): Connection<Terminated> =
  Connection(info = "Terminated.")

