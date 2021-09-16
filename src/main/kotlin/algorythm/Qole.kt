package algorythm

fun findQole(list: List<Int>) = findQole(list, 0, list.size - 1)


tailrec fun findQole(list: List<Int>, left: Int, right: Int): Int {
    val midIndex = (left + right) / 2
    if (left == right) {
        return left
    }
    return when {
        isInSoudi(list, midIndex) -> findQole(list, midIndex + 1, right)
        isInNozooli(list, midIndex) -> findQole(list, left, midIndex - 1)
        else -> midIndex
    }

}

fun nemidoonam(list: List<Int>, index: Int, fn: (Int, Int) -> Boolean): Boolean {
    return when (index) {
        0 -> {
            fn(list[index], list[index + 1])
        }
        list.size - 1 -> {
            fn(list[index - 1], list[index])
        }
        else -> {
            fn(list[index - 1], list[index]) && fn(list[index], list[index + 1])
        }
    }
}

fun isInSoudi(list: List<Int>, index: Int) =
    nemidoonam(list, index) { a: Int, b: Int ->
        a < b
    }


fun isInNozooli(list: List<Int>, index: Int) =
    nemidoonam(list, index) { a: Int, b: Int ->
        a > b
    }

fun main() {
    val list = listOf(1, 2, 3, 0)
    println(findQole(list))
}
