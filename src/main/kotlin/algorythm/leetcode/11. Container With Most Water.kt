package algorythm.leetcode

/*
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0).
Find two lines, which, together with the kotlin.x-axis forms a container, such that the container contains the most water.
Notice that you may not slant the container.

Example:
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
In this case, the max area of water (blue section) the container can contain is 49.
 */


fun maxArea(height: IntArray): Int {
    var max = 0
    var leftPointer = 0
    var rightPointer = height.size - 1
    while (leftPointer < rightPointer) {
        val left = height[leftPointer]
        val right = height[rightPointer]
        max = maxOf(max, (rightPointer -leftPointer) * minOf(left, right))
        if (left <= right) {
            while (height[leftPointer] <= left
                && leftPointer < rightPointer) { leftPointer++ }
        }
        else {
            while (height[rightPointer] <= right
                && leftPointer < rightPointer) { rightPointer-- }
        }
    }
    return max
}

fun main() {
    println(maxArea(intArrayOf(1,8,6,2,5,4,8,3,7)))
}