package algorythm.leetcode

import kotlin.math.abs
import kotlin.math.sign

fun reverse(x: Int): Int {
    var num = abs(x)
    var res: Int = 0
    while (num > 0) {
        val rem = num % 10
        if (res > (Int.MAX_VALUE - rem) / 10) return 0
        res = res * 10 + rem
        num /= 10
    }
    return res * x.sign
}

fun main() {
    println(reverse(1539))
}