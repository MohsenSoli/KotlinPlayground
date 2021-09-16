import java.util.function.Function
import java.util.logging.Level

val <P1, P2, R> Function2<P1, P2, R>.curried: (P1) -> (P2) -> R
    get() = { p1 -> { p2 -> this(p1, p2) } }

val <P1, P2, P3, R> Function3<P1, P2, P3, R>.curried: (P1) -> (P2) -> (P3) -> R
    get() = { p1 ->
        val inner = object: Function2<P2, P3, R> {
            override fun invoke(p2: P2, p3: P3): R = this@curried(p1, p2, p3)
        }
        inner.curried
    }

fun logger(level: Level, appender: Appendable, msg: String) {}

fun main() {
    val cLogger = ::logger.curried(Level.FINE)(System.out)("ddd")
}

class A {
    lateinit var m: String

    fun x() {
        ::m.isInitialized
    }
}