package algorythm.leetcode

fun longestPalindrome(s: String): String {

    var maxLength = 1
    var maxRange = Pair(0,1)

    fun find(left: Int, right: Int, startLength: Int): Pair<Int, Pair<Int, Int>> {
        var i = left
        var j = right
        var current = startLength
        while (i >= 0 && j < s.length) {
            if (s[i] == s[j]) {
                current += 2
                i--
                j++
            } else {
                break
            }
        }
        return Pair(current, Pair(++i, j))
    }

    s.forEachIndexed { index, _ ->
        val resOdd = find(index - 1, index + 1, 1)
        if (resOdd.first > maxLength) {
            maxLength = resOdd.first
            maxRange = resOdd.second
        }

        val resEven = find(index, index + 1, 0)
        if (resEven.first > maxLength) {
            maxLength = resEven.first
            maxRange = resEven.second
        }
    }
    return s.substring(maxRange.first, maxRange.second)
}


fun main() {
    println(longestPalindrome("abcbd"))
}