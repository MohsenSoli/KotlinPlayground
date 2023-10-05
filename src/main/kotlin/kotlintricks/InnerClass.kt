package kotlintricks

class Class {

    companion object {
        init {

        }
        val y = 10
    }

    fun foo() {
        y + x + Static().yy
    }

    val x : Int = 10

    inner class Inner {
        val y = x
    }

    class Static {
        val yy = 20
    }
}


fun main() {
    val myClass = Class()
    val inner = myClass.Inner()
    val static = Class.Static()
}