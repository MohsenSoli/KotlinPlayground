package algorythm

class BinarySearch {

    fun binarySearch(
        numbers: IntArray,
        target: Int,
        start: Int = 0,
        end: Int = numbers.size - 1
    ): Int? {
        val midIndex = (end + start) / 2
        val mid = numbers[midIndex]
        return when {
            mid == target -> midIndex
            start == end -> { if (target == mid) midIndex else null }
            mid > target -> binarySearch(numbers, target, start, midIndex - 1)
            mid < target -> binarySearch(numbers, target, midIndex + 1, end)
            else -> null
        }
    }

    fun binarySearchAll(numbers: IntArray, target: Int): Pair<Int?, Int?> {
        val index = binarySearch(numbers, target)
        val found = if (index == null) return Pair(null, null) else numbers[index]
        val right = findRight(numbers, found, index + 1, numbers.size - 1)
        val left = findLeft(numbers, found, 0, index - 1)
        return Pair(left, right)
    }

    private fun findRight(
        numbers: IntArray,
        target: Int,
        start: Int,
        end: Int
    ): Int? {
        val midIndex = (end + start) / 2
        val mid = numbers[midIndex]
        return when {
            numbers[end] == target -> end
            start == end -> {
                if (mid == target) midIndex
                else midIndex - 1
            }
            mid == target -> findRight(numbers, target, midIndex + 1, end)
            mid != target -> findRight(numbers, target, start, midIndex - 1)
            else -> null
        }
    }

    private fun findLeft(
        numbers: IntArray,
        target: Int,
        start: Int,
        end: Int
    ): Int? {
        val midIndex = (end + start) / 2
        val mid = numbers[midIndex]
        return when {
            numbers[start] == target -> start
            start == end -> {
                if (mid == target) midIndex
                else midIndex + 1
            }
            mid == target -> findLeft(numbers, target, start, midIndex - 1)
            mid != target -> findLeft(numbers, target, midIndex + 1, end)
            else -> null
        }
    }
}

fun main() {
    val numbers = intArrayOf(5, 5, 5, 5, 5 , 5, 5, 5, 5, 5, 5)
    println(BinarySearch().binarySearch(numbers, 5))
    println(BinarySearch().binarySearchAll(numbers, 5))
}