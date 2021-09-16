package algorythm.leetcode

fun convert(s: String, numRows: Int): String {
    if (numRows == 1) return s
    var i = 0
    var index = 0
    val finalString = MutableList<Char>(s.length) { ' ' }
    repeat(numRows) { row ->
        while (i * (numRows - 1) - row < s.length) {
            when(row) {
                0 -> {
                    finalString[index] = s[i * (numRows - 1)]
                    println(i * (numRows - 1))
                    index++
                }
                numRows - 1 -> {
                    if (i * (numRows - 1) + row < s.length) {
                        finalString[index] = s[i * (numRows - 1) + row]
                        println(i * (numRows - 1) + row)
                        index++
                    }
                }
                else -> {
                    if (i * (numRows - 1) - row > -1) {
                        finalString[index] = s[i * (numRows - 1) - row]
                        println(i * (numRows - 1) - row)
                        index++
                    }
                    if (i * (numRows - 1) + row < s.length) {
                        finalString[index] = s[i * (numRows - 1) + row]
                        println(i * (numRows - 1) + row)
                        index++
                    }
                }
            }
            i += 2
        }
        i = 0
    }
    return finalString.joinToString("")
}


fun convertFull(s: String, numRows: Int): String {

    val cols = calcColumns(s.length, numRows)
    val rows = numRows - 1
    val finalString = CharArray((rows + 1) * (cols + 1)) { ' ' }
    s.forEachIndexed { index, c ->
        val col = calcColumns(index, numRows)
        val row = calcRow(index, numRows)
        finalString[((cols + 1) * row + col)] = c
    }
    println(finalString.concatToString())
    return finalString.concatToString().chunked(cols + 1).joinToString("\n")
}

fun calcColumns(length: Int, numRows: Int): Int {
    val halfBox = numRows - 1
    val box = halfBox * 2
    val x = length / box
    val y = length % box
    return if (y > halfBox) {
        (x * halfBox) + y - halfBox
    } else {
        x * halfBox
    }
}

fun calcRow(length: Int, numRows: Int): Int {
    val halfBox = numRows - 1
    val box = halfBox * 2
    val y = length % box
    return if (y < halfBox) {
        y
    } else {
        box - y
    }
}


fun main() {
//    println("${calcRow(22, 7)}, ${calcColumns(22, 7)}")
//    println(convertFull("PAYPALISHIRING", 4).toString())
    println(convert("abcd", 3))
}