package algorythm.leetcode


 class ListNode(var `val`: Int) {
    var next: ListNode? = null
 }

fun ListNode?.print() {
    var x: ListNode? = this
    while (x != null) {
        print("${x.`val`} ,")
        x = x.next
    }
    println()
}

fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    l1?.let { list1 ->
        l2?.let { list2 ->
            var one: ListNode? = list1.next
            var two: ListNode? = list2.next
            var acc = (list1.`val` + list2.`val`) / 10
            val res = ListNode((list1.`val` + list2.`val`) % 10)
            var prev: ListNode = res
            while (one != null && two != null) {
                val current = ListNode((one.`val` + two.`val` + acc) % 10)
                acc = if (one.`val` + two.`val` + acc > 9) {
                    (one.`val` + two.`val` + acc) / 10
                } else { 0 }
                prev.next = current
                prev = current
                one = one.next
                two = two.next
            }
            while (one != null) {
                val current = ListNode((one.`val` + acc) % 10)
                acc = if (one.`val` + acc > 9) {
                    (one.`val` + acc) / 10
                } else { 0 }
                prev.next = current
                prev = current
                one = one.next
            }
            while (two != null) {
                val current = ListNode((two.`val` + acc) % 10)
                acc = if (two.`val` + acc > 9) {
                    (two.`val` + acc) / 10
                } else { 0 }
                prev.next = current
                prev = current
                two = two.next
            }
            if (acc != 0) {
                prev.next = ListNode(acc)
            }
            return res
        }
        return l1
    }
    return l2
}

private fun add(l: ListNode?, r: ListNode?): Int {
    return (l?.`val` ?: 0) + (r?.`val` ?: 0)
}

fun addTwoNumbersBetter(l1: ListNode?, l2: ListNode?): ListNode? {
    var curLeft = l1
    var curRight = l2
    var root: ListNode? = null
    var cur: ListNode? = null
    var carry = 0
    while (curLeft != null || curRight != null || carry > 0) {
        val added = add(curLeft, curRight) + carry
        carry = if (added > 9) 1 else 0
        val node = ListNode(added % 10)
        if (root == null) {
            root = node
            cur = node
        } else {
            cur?.next = node
            cur = node
        }
        curLeft = curLeft?.next
        curRight = curRight?.next
    }
    return root
}

fun main() {
    val l1 = ListNode(9).apply { next = ListNode(9).apply { next = ListNode(9) } }
    val l2 = ListNode(9)
    l1.print()
    l2.print()
    addTwoNumbers(l1, l2).print()
}