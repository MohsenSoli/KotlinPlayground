import java.util.concurrent.ConcurrentHashMap

fun <A, R> Function1<A, R>.memoized(): (A) -> R {
    val map = ConcurrentHashMap<A, R>()
    return { a ->
        map.getOrPut(a) {
            val res = this.invoke(a)
            println("put $a -> $res")
            res
        }
    }
}

object Cache {
    val func1s = ConcurrentHashMap<String, Function1<*, *>>()
}

@Suppress("UNCHECKED_CAST")
class None {
    var x = 2
    private val memNothing = Cache.func1s
        .getOrPut("None_nothing") { ::_nothing.memoized() } as (Int) -> Int
    private fun _nothing(a: Int) = a * x
    fun nothing(a: Int) = memNothing(a)
}

fun main() {
    val x = None()
    x.nothing(10)
    x.nothing(10)
    x.nothing(1)
    x.nothing(1)
    val y = None()
    y.x = 5
    y.nothing(10)
    y.nothing(10)
    y.nothing(1)
    y.nothing(2)
}