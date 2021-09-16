package algorythm.leetcode

import java.lang.StringBuilder
import java.util.*
import kotlin.collections.HashMap

fun longestCommonPrefix(strs: Array<String>): String {
    var index = 0
    val result = StringBuilder()
    while (true) {
        var char: Char? = null
        strs.forEach {
            if (index > it.length - 1) return result.toString()
            if (char == null) {
                char = it[index]
            } else if (char != it[index]) {
                return result.toString()
            }
        }
        result.append(strs[0][index])
        index++
    }
}

data class Node(
    val value: Char,
    val isEnd: Boolean
) {
    private val _neighbor = hashMapOf<Char, Node>()
    val neighbors: HashMap<Char, Node> = _neighbor
    fun addNeighbor(node: Node) {
        _neighbor[node.value] = node
    }
}

fun longestCommonPrefixTrie(strs: Array<String>): String {
    val trie = createTrie(strs)

}

fun bfs(root: Node): String {
    val queue: Queue<Node> = ArrayDeque()
    queue.add(root)
    val seen = mutableSetOf<Node>()
    while (queue.isNotEmpty()) {
        val element = queue.poll()
        element.neighbors.values.forEach {
            queue.add(it)
        }
    }
}

fun createTrie(strs: Array<String>): Node {
    val trieRoot = Node('N', false)
    var currentNode = trieRoot
    strs.forEach { word ->
        word.forEach {
            currentNode = if (currentNode.neighbors.contains(it)) {
                currentNode.neighbors[it]!!
            } else {
                val node = Node(it, false)
                currentNode.addNeighbor(node)
                node
            }
        }
    }
    return trieRoot
}

fun main() {
    val m = arrayOf("flower", "flow")
    println(longestCommonPrefix(m))
}