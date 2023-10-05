package algorythm.randomproblems

/**
 * change an array into zigzag fashion in place, another implementation is by sorting first,
 */
fun zigZag(arr: IntArray) {
    // Flag true indicates relation "<" is expected,
    // else ">" is expected. The first expected relation
    // is "<"
    var flag = true
    var temp = 0
    for (i in 0..arr.size - 2) {
        if (flag) /* "<" relation expected */ {
            /* If we have a situation like A > B > C,
            we get A > C < B by swapping B and C */
            if (arr[i] > arr[i + 1]) {
                // swap
                temp = arr[i]
                arr[i] = arr[i + 1]
                arr[i + 1] = temp
            }
        } else  /* ">" relation expected */ {
            /* If we have a situation like A < B < C,
            we get A < C > B by swapping B and C */
            if (arr[i] < arr[i + 1]) {
                // swap
                temp = arr[i]
                arr[i] = arr[i + 1]
                arr[i + 1] = temp
            }
        }
        flag = !flag /* flip flag */
    }
}

/**
 * return new array form [arr] into zigzag fashion, using sort
 */
fun zigzagSort(arr: IntArray): IntArray {
    // sort and copy
    val newArr = arr.sortedArray()
    var i = 0
    while (i < newArr.size - 1) {
        val tmp = newArr[i]
        newArr[i] = newArr[i + 1]
        newArr[i + 1] = tmp
        i += 2
    }
    return newArr
}


fun main() {
    val arr = intArrayOf(1, 1, 1, 1, 1, 3, 3, 6, 2, 2, 1, 0, 7, 8, 9, 10, 10, 3, 1, 0)
    println(zigzagSort(arr).toList())
    zigZag(arr)
    println(arr.toList())
}