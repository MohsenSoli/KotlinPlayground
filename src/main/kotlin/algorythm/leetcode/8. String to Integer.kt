package algorythm.leetcode

import kotlin.math.abs

fun myAtoi(s: String): Int {
    var isNumbersReached = false
    var sign = 1
    var res = 0
    s.forEach { char ->
        if (!isNumbersReached) {
            if (char != ' ') {
                char.whenIsNumber(
                    onNumber = {
                        res = res * 10 + it
                        isNumbersReached = true
                    },
                    otherwise = {
                        sign = when (char) {
                            '+' -> 1
                            '-' -> -1
                            else -> return 0
                        }
                        isNumbersReached = true
                    }
                )
            }
        } else {
            char.whenIsNumber(
                onNumber = {
                    if (res.isInDangerZone(it)) {
                        if (sign == 1) {
                            return Int.MAX_VALUE
                        } else if (sign == -1) {
                            return Int.MIN_VALUE
                        }
                    }
                    res = res * 10 + it
                },

                otherwise = { return res * sign }
            )
        }
    }
    return res * sign
}



inline fun Char.whenIsNumber(onNumber: (Int) -> Unit, otherwise: (Char) -> Unit) {
    when (this) {
        '0' -> onNumber(0)
        '1' -> onNumber(1)
        '2' -> onNumber(2)
        '3' -> onNumber(3)
        '4' -> onNumber(4)
        '5' -> onNumber(5)
        '6' -> onNumber(6)
        '7' -> onNumber(7)
        '8' -> onNumber(8)
        '9' -> onNumber(9)
        else -> otherwise(this)
    }
}

fun Int.isInDangerZone(next: Int) = abs(this) > Int.MAX_VALUE / 10 ||
        (abs(this) == Int.MAX_VALUE / 10 && next > 7)


fun main() {
    println(myAtoi("2147483648"))

}

