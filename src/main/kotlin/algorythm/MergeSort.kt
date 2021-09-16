package algorythm

class MergeSort {

    fun mergeSort(arr: IntArray) {
        mergeSort(arr, 0, arr.size - 1)
    }

    private fun mergeSort(arr: IntArray, left: Int, right: Int) {
        if (left >= right) return
        mergeSort(arr, left, (left + right) / 2)
        mergeSort(arr, (left + right) / 2 + 1, right)
        merge(arr, left, right)
    }

    private fun merge(arr: IntArray, start1: Int, end2: Int) {
        val end1 = (start1 + end2) / 2
        val start2 = end1 + 1
        var p1 = start1
        var p2 = start2
        var index = 0
        val newArr = IntArray(end2 - start1 + 1)
        while (p1 <= end1 && p2 <= end2) {
            if (arr[p1] < arr[p2]) {
                newArr[index] = arr[p1]
                p1++
            } else {
                newArr[index] = arr[p2]
                p2++
            }
            index++
        }
        for (i in p1..end1) {
            newArr[index] = arr[i]
            index++
        }
        for (i in p2..end2) {
            newArr[index] = arr[i]
            index++
        }
        for (i in start1..end2) {
            arr[i] = newArr[i - start1]
        }
    }
}

fun IntArray.mergeSort() = MergeSort().mergeSort(this)