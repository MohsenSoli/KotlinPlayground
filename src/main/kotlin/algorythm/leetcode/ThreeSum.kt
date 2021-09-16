package algorythm.leetcode

class ThreeSum {

    fun threeSum(numbers: IntArray): List<List<Int>> {
        val ans = mutableListOf<MutableList<Int>>()
        numbers.sort()
        numbers.forEachIndexed { i, v ->
            if (i > 0 && numbers[i - 1] == v) return@forEachIndexed
            if (i < numbers.size - 1) {
                val two = twoSum(numbers, -v, i, i + 1).toMutableList()
                if (two.isNotEmpty()) {
                    two.forEach {
                        ans.add(mutableListOf(v, numbers[it[0]], numbers[it[1]]))
                    }
                }
            }
        }
        return ans
    }

    private fun twoSum(
        numbers: IntArray,
        target: Int,
        ignore: Int,
        start: Int = 0,
        end: Int = numbers.size - 1
    ): List<IntArray> {
        val ans = mutableListOf<IntArray>()
        var left = start
        var right = end
        while (left < right) {
            if (left == ignore) {
                left++
                continue
            }
            if (right == ignore) {
                right--
                continue
            }
            if (left > start && numbers[left] == numbers[left - 1]) {
                left++
                continue
            }
            if (right < end && numbers[right] == numbers[right + 1]) {
                right--
                continue
            }
            val sum = numbers[left] + numbers[right]
            when {
                sum == target -> {
                    ans.add(intArrayOf(left, right))
                    left++
                    right--
                }
                sum > target -> right--
                else -> left++
            }
        }
        return ans
    }

}

fun main() {
    val threeSum = ThreeSum()
    //[-1,0,1,2,-1,-4]
    val arr = intArrayOf(-1, 0, 1, 2, -1, 4)
    println(threeSum.threeSum(arr))
}