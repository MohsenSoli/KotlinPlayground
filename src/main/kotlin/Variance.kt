
class Logger<in T> {
    fun log(message: T) = println(message)
}

class Logic<in T> (private val logger: Logger<T>) {

    fun processData(data: T) {


        logger.log(data)
    }

    fun calculateDistance(num: T) {


        logger.log(num)
    }

}

fun main() {
    val logger = Logger<Any>()
    val intLogic = Logic<Int>(logger)
    val stringLogic = Logic<String>(logger)
}