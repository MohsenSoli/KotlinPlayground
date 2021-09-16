package algorythm.leetcode

import kotlin.math.abs
import kotlin.math.sign

class Divide {

    fun divide(dividend: Int, divisor: Int) =
        divide(dividend.toLong(), divisor.toLong(), 31, 0) * dividend.sign * divisor.sign

    private tailrec fun divide(dividend: Long, divisor: Long, bits: Int, quo: Long = 0): Int {
        if (dividend == Int.MIN_VALUE.toLong() && divisor == -1L) return Int.MAX_VALUE
        if (bits < 0) return quo.toInt()
        val uDivisor = abs(divisor)
        val uDividend = abs(dividend)
        val shiftedSubtrahend = uDivisor shl bits
        val adjustedMinuend = if (uDividend < shiftedSubtrahend) uDividend
        else uDividend - shiftedSubtrahend
        val adjustedQuotient = if (uDividend < shiftedSubtrahend) quo
        else quo + (1 shl bits)
        return divide(adjustedMinuend, divisor, bits - 1, adjustedQuotient)
    }
}


fun main() {
    println(Divide().divide(25, 7))
    println(
        Divide().divide(
            -2147483648,
            -1
        )
    )
    println(Divide().divide(-25, 7))
    println(Divide().divide(100, 3))
    println(Divide().divide(100, -3))
    println(Divide().divide(5, 4))
    println(Divide().divide(-5, 4))
    println(Divide().divide(3, 4))
    println(Divide().divide(3, -4))
}