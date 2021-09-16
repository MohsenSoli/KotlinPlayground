package algorythm.leetcode

fun isPalindrome(x: Int): Boolean {
    if (x < 0) return false
    val digits = x.toList()
    var left = 0
    var right = digits.size - 1
    while (right > left) {
        if (digits[right] != digits[left])
            return false
        right--
        left++
    }
    return true
}

fun Int.toList(): List<Int> {
    var num = this
    val list = mutableListOf<Int>()
    while (num > 0) {
        list.add(num % 10)
        num /= 10
    }
    return list
}

fun main() {
    println(isPalindrome(-121))
}