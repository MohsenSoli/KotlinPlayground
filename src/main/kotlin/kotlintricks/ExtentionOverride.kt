package kotlintricks

open class View
open class Button : View()
open class Text : View()


abstract class ViewShower {

    fun show(view: View) {
        view.showIt()
    }

    open fun View.showIt() {
        println("showing view")
    }

    open fun Button.showIt() {
        println("showing button")
    }

    open fun Text.showIt() {
        println("showing text")
    }
}

class MyViewShower: ViewShower() {

    override fun Button.showIt() {
        println("showing button")
    }

    override fun Text.showIt() {
        println("showing text")
    }
}

fun main() {
    val viewShower = MyViewShower()
    viewShower.show(View())
    viewShower.show(Button())
    viewShower.show(Text())

    val he = NobleGas("He")
    he.react(Electron())
    he.react(Particle())
}


open class Particle
class Electron : Particle()
open class Element(val name: String) {

    fun react(particle: Particle) {
        particle.doReaction(name)
    }

    open fun Particle.doReaction(name: String) {
        println("$name is reacting with a particle")
    }

    open fun Electron.doReaction(name: String) {
        println("$name is reacting with an electron to make an isotope")
    }
}


class NobleGas(name: String) : Element(name) {

    override fun Particle.doReaction(name: String) {
        println("$name is noble, it doesn't react with particles")
    }

    override fun Electron.doReaction(name: String) {
        println("$name is noble, it doesn't react with electrons")
    }
}

