package algorythm.leetcode

fun romanToInt(roman: String): Int {
    val numMap = hashMapOf(
        "M"  to 1000,
        "CM" to 900,
        "D"  to 500,
        "CD" to 400,
        "C"  to 100 ,
        "XC" to 90,
        "L"  to 50,
        "XL" to 40,
        "X"  to 10,
        "IX" to 9,
        "V"  to 5,
        "IV" to 4,
        "I"  to 1
    )

    var right = roman.length - 1
    var res = 0
    while (right > -1) {

        val rom = if (right == 0) {
            roman[right].toString()
        } else {
            val box = "${roman[right - 1]}${roman[right]}"
            if (numMap.contains(box)) box
            else roman[right].toString()
        }
        res += numMap[rom]!!
        right -= rom.length
    }
    return res
}


fun main() {
    println(romanToInt("LVIII"))
}