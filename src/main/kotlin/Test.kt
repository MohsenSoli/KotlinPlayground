import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

@Volatile
var counter = 0
val counterContext = newSingleThreadContext("CounterContext")

suspend fun massiveRun(action: suspend () -> Unit) {
    val n = 100  // number of coroutines to launch
    val k = 1000 // times an action is repeated by each coroutine
    val time = measureTimeMillis {
        coroutineScope { // scope for coroutines
            repeat(n) {
                launch {
                    repeat(k) { action() }
                }
            }
        }
    }
    println("Completed ${n * k} actions in $time ms")
}

fun main(): Unit = runBlocking {
//    withContext(counterContext) {
//        massiveRun {
//            counter++
//        }
//    }
//    println("Counter = $counter")
    val x = generateSequence(5) {
        it + 3
    }
        .takeWhile { it < 100 }
        .onEach { println(it) }
        .reduce { acc, i -> acc + i }
}

suspend fun x(): Int {
    return withContext(Dispatchers.IO) {
        return@withContext 10
    }
}