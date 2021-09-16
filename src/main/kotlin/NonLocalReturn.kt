
inline fun x(crossinline func: () -> Unit) {
    val f = Runnable { func.invoke() }

    println("didn't return")
}


fun main() {
    x {

    }
}

