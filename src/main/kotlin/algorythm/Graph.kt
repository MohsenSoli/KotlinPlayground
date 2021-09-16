package algorythm

import algorythm.leetcode.Stack
import algorythm.leetcode.hasMore
import algorythm.leetcode.pop
import java.util.*

class Graph<T>(private val adjacencyList: Map<T, List<T>>) {

    data class DfsNode<T>(
        val from: T?,
        val element: T
    )

    @Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
    inline fun dfs(start: T, process: (T) -> Unit = {}) {

        println(
            """
            starting dfs ...
        """.trimIndent()
        )

        val seen = hashSetOf<T>()
        doDfs(start, seen, process)
        adjacencyList.keys.forEach {
            if (!seen.contains(it)) {
                doDfs(it, seen, process)
            }
        }

    }

    private inline fun doDfs(start: T, seen: MutableSet<T>, process: (T) -> Unit) {
        val stack: Stack<DfsNode<T>> = mutableListOf()
        stack.add(DfsNode(null, start))
        while (stack.hasMore()) {
            val element = stack.pop()!!
            if (!seen.contains(element.element)) {
                seen.add(element.element)
                println(" ${element.from} -> ${element.element}")
                process(element.element)
                adjacencyList[element.element]?.forEach {
                    if (!seen.contains(it))
                        stack.add(DfsNode(element.element, it))
                }
            }
        }
    }


    data class BfsNode<T>(
        val element: T,
        val level: Int = 0,
    )

    @Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
    inline fun bfs(start: T, process: (T) -> Unit = {}) {

        println(
            """
            
            starting bfs ...
        """.trimIndent()
        )

        val seen = hashSetOf<T>()
        doBfs(start, seen, process)
        adjacencyList.keys.forEach {
            if (!seen.contains(it)) {
                doBfs(it, seen, process)
            }
        }
    }

    private inline fun doBfs(start: T, seen: MutableSet<T>, process: (T) -> Unit = {}) {
        val queue: Queue<BfsNode<T>> = ArrayDeque()
        queue.add(BfsNode(start))
        while (queue.isNotEmpty()) {
            val element = queue.poll()
            if (!seen.contains(element.element)) {
                seen.add(element.element)
                println("${element.element} level -> ${element.level}")
                process(element.element)
                adjacencyList[element.element]?.forEach {
                    if (!seen.contains(it))
                        queue.add(BfsNode(it, element.level + 1))
                }
            }
        }
    }
}


fun main() {
    val adjacencyList = mapOf(
        0 to listOf(1, 3),
        1 to listOf(0, 2),
        2 to listOf(3),
        4 to listOf(5, 6)
    )
    val graph = Graph(adjacencyList).apply {
        dfs(0)
        bfs(0)
    }
}