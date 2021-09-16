package algorythm.leetcode

import kotlin.math.max
import kotlin.math.min

fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    val min = (nums1.size + nums2.size)/2 - nums2.size -1
    val start = if (min > -2) min else -1
    val max = (nums1.size + nums2.size)/2 - 1
    val end = if (max < nums1.size) max else nums1.size - 1
    return findMedianSortedArrays(nums1, nums2, start, end)
}

tailrec fun findMedianSortedArrays(
    nums1: IntArray,
    nums2: IntArray,
    start1: Int = -1,
    end1: Int = nums1.size - 1
): Double {
    if (nums1.isEmpty()) {
        return nums2.mid()
    }
    if (nums2.isEmpty()) {
        return nums1.mid()
    }
    val size = nums1.size + nums2.size
    val midIndex1 = (start1 + end1) / 2
    val midIndex2 = (size)/2 - (midIndex1 + 1) - 1
    val left1 = if (midIndex1 < 0 ) { Int.MIN_VALUE } else { nums1[midIndex1] }
    val left2 = if (midIndex2 >= 0) { nums2[midIndex2] } else { Int.MIN_VALUE }
    val right1 = if (midIndex1 + 1 < nums1.size) { nums1[midIndex1 + 1] } else { Int.MAX_VALUE }
    val right2 = if (midIndex2 + 1 < nums2.size) { nums2[midIndex2 + 1] } else { Int.MAX_VALUE }
    return if (left1 <= right2) {
        if (left2 <= right1) {
            if (size % 2 == 0) {
                ((max(left1, left2).toDouble() + min(right1, right2)) / 2)
            } else {
                min(right1, right2).toDouble()
            }
        } else {
            findMedianSortedArrays(nums1, nums2, midIndex1 + 1, end1)
        }
    } else {
        val newMid = if (midIndex1 - 1 < start1) { start1 } else { midIndex1 - 1 }
        findMedianSortedArrays(nums1, nums2, start1, newMid)
    }
}

private fun IntArray.mid() = if (this.size % 2 == 0) {
    (this[(size - 1)/2].toDouble() + this[(size - 1)/2 + 1]) /2
} else {
    this[(size) / 2].toDouble()
}

fun main() {
    val nums1 = intArrayOf(2,3,4,5,6,7)
    val nums2 = intArrayOf(1)
    println(findMedianSortedArrays(nums1, nums2))
}
