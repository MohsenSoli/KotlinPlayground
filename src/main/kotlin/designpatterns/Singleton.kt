package designpatterns

import kotlin.concurrent.thread

class Eager private constructor() {

    companion object {
        private val instance = Eager()
        fun getInstance() = instance
    }
}

class Lazy1 private constructor() {

    companion object {
        private val instance by lazy { Lazy1() }
        fun getInstancee() = instance
    }
}

class LazyThreadDanger private constructor() {

    companion object {

        private var instance: LazyThreadDanger? = null

        fun getInstancee(): LazyThreadDanger {
            instance?.let {
                return instance!!
            }
            instance = LazyThreadDanger()
            return instance!!
        }
    }
}

class Lazy2 private constructor() {

    companion object {

        private var instance: Lazy2? = null

        fun getInstancee(): Lazy2 {
            synchronized(this) {
                instance?.let {
                    return instance!!
                }
                instance = Lazy2()
                return instance!!
            }
        }

    }
}

class LazyDoubleCheckLock private constructor() {

    companion object {

        @Volatile
        private var instance: LazyDoubleCheckLock? = null

        fun getInstancee(): LazyDoubleCheckLock {
            if (instance == null) {
                synchronized(this) {
                    instance?.let {
                        return instance!!
                    }
                    instance = LazyDoubleCheckLock()
                    return instance!!
                }
            } else {
                return instance!!
            }
        }

    }
}

class BillPugh private constructor() {

    class Instance {
        companion object {
            private val instance = BillPugh()
            fun get() = instance
        }
    }
}

enum class Singleton {
    Instance;

    val x: Int = 10
    fun foo() {}
}


fun main() {

    thread {
        thread {
            println(Lazy1.getInstancee())
        }
        println(Lazy1.getInstancee())
        thread {
            println(Lazy1.getInstancee())
        }
    }
    thread {
        println(Lazy1.getInstancee())
        thread {
            println(Lazy1.getInstancee())
        }
    }
    thread {

        thread {
            println(Lazy1.getInstancee())
        }
        println(Lazy1.getInstancee())
    }
    thread {
        println(Lazy1.getInstancee())
    }
    thread {
        println(Lazy1.getInstancee())
    }
    thread {
        println(Lazy1.getInstancee())
    }


}