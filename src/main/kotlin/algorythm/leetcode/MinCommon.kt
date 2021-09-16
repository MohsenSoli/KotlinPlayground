package algorythm.leetcode

import kotlin.math.min

class MinCommon {

    fun minCommon1(arr1: IntArray, arr2: IntArray): Int? {
        arr1.sort()
        arr2.sort()
        var p1 = 0
        var p2 = 0
        while (p1 < arr1.size && p2 < arr2.size) {
            when {
                arr1[p1] == arr2[p2] -> return arr1[p1]
                arr1[p1] > arr2[p2] -> p2++
                else -> p1++
            }
        }
        return null
    }

    fun minCommon2(arr1: IntArray, arr2: IntArray): Int? {
        val arr1Hashed = arr1.toHashSet()
        var min = Int.MAX_VALUE
        arr2.forEach {
            if (arr1Hashed.contains(it))
                min = min(min, it)
        }
        return min
    }
}


fun main() {
    val arr1 = intArrayOf(8, 7, 5, 10, 3,42)
    val arr2 = intArrayOf(4, 6, 55, 42, 0)
    println(MinCommon().minCommon2(arr1, arr2))
}