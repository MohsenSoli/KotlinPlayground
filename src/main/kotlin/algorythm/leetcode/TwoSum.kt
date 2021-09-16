package algorythm.leetcode

class TwoSum {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = mutableMapOf<Int, Int>()
        nums.forEachIndexed { index, num ->
            if (map.containsKey(num)) {
                return intArrayOf(map[num]!!, index)
            } else {
                map[target - num] = index
            }
        }
        TODO()
    }
}


fun main() {
    val x = TwoSum()
    val list = intArrayOf(2,7,11,15)
    println(list.string())
    println(x.twoSum(list, 9).string())
}

fun IntArray.string(): String {
    val s = "["
    val sb = StringBuilder()
    sb.append(s)
    this.forEach { sb.append("$it, ") }
    sb.append("]")
    return sb.toString()
}

