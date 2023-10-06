package kotlintricks.inline

private inline fun x(
    func0: () -> Unit = {},
    crossinline func: () -> Unit,
) {
    inlineFun { func0.invoke() }

    notInlineFun {
        //func0.invoke() error
        func.invoke()
    }

    // interface cant have returns
    Interface {
        //func0.invoke()   error
        func.invoke()
    }.run()

    Runnable {
        //func0.invoke()   error
        func.invoke()
        println("runnable finished")
    }.run()

    println("didn't return")
}

private inline fun inlineFun(func: () -> Unit) {
    func()
}

private fun notInlineFun(func: () -> Unit) {
    func()
}


private fun interface Interface {
    fun run()
}


private fun main() {
    x(
        func0 = { return },
        func = { }
    )
}

