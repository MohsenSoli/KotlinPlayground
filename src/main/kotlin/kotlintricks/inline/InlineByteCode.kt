package kotlintricks.inline

private inline fun execLambdaInline(lambda: () -> Unit) {
    lambda()
}

private fun execLambda(lambda: () -> Unit) {
    lambda()
}

private fun main() {
    var counter: Int = 0
    execLambdaInline {
        // no capturing because of inline
        println(++counter)
    }
    execLambda {
        // captures in [IntRef]
        println(++counter)
    }
    execLambda {
        // no capturing
        println("++counter")
    }
}

private class Captured {
    var counter: Int = 0

    inline val prop: String
        inline get() {
            println("getter will be inline in caller")
            return "inlined"
        }

    fun yes() {
        Y().saveLambda {
            // captures th class -> implicit reference to class
            // anonymous inner class
            println(++counter)
        }
    }

    fun no() {
        Y().saveLambda {
            // no reference to class
            // singleton object
            println("counter")
        }
    }
}

private class Y {
    lateinit var lambda: () -> Unit
    inline fun saveLambda(noinline lambda: () -> Unit) {
        // storing lambda -> use noinline
        this.lambda = lambda
    }
}
