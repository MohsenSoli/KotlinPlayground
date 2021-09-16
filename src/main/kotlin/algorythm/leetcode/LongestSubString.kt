package algorythm.leetcode

class LongestSubString {
    fun lengthOfLongestSubstring(s: String): Int {
        if (s.isEmpty()) { return 0 }
        var start = 0
        var end = 1
        var max = 1
        val currentChars = hashMapOf(Pair(s[start], start))
        while (end < s.length) {
            val endChar = s[end]
            val repeatedIndex = currentChars[endChar]
            if (repeatedIndex != null && repeatedIndex >= start) {
                start = repeatedIndex + 1
            } else {
                val currentMax = end - start + 1
                if (currentMax > max) {
                    max = currentMax
                }
            }
            currentChars[endChar] = end
            end++
        }
        return max
    }
}


fun showit(s: String) {
    val los = LongestSubString()
    println("longest substring without repeating length for -$s- -> ${los.lengthOfLongestSubstring(s)} ")
}

fun main() {
    showit("abcabcbb")
    showit("bbbbb")
    showit("")
    showit("pwwkew")
}