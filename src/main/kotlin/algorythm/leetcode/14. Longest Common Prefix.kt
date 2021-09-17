package algorythm.leetcode

import kotlinx.coroutines.yield
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.HashMap
import kotlin.system.measureNanoTime

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


fun longestCommonPrefixFastestFunctional(strs: Array<String>) = strs.reduce { acc, s ->
        generateSequence(acc) { it.substring(0, it.length - 1) }
            .takeWhileInclusive { !s.startsWith(it) }
            .last()
    }


fun longestCommonPrefixFastest(strs: Array<String>): String {
    var res = strs.first()
    strs.forEach {
        var i = res.length
        while (i > -1) {
            if (it.startsWith(res.substring(0, i))) {
                res = res.substring(0, i)
                break
            } else {
                i--
            }
        }
    }
    return res
}

data class TrieNode(
    val value: Char?,
    var isEnd: Boolean
) {
    private val _neighbor = hashMapOf<Char, TrieNode>()
    val neighbors: HashMap<Char, TrieNode> = _neighbor
    fun addNeighbor(trieNode: TrieNode) {
        _neighbor[trieNode.value!!] = trieNode
    }
}

fun longestCommonPrefixTrie(strs: Array<String>): String {
    val trie = buildTrie(strs)
    return generateSequence(trie) { it.neighbors.values.first() }
        .takeWhileInclusive { it.neighbors.size == 1 && !it.isEnd}
        .map { it.value?.toString() ?: "" }
        .reduce { acc, c -> acc + c }
}

fun <T> Sequence<T>.takeWhileInclusive(function: (T) -> Boolean): Sequence<T> {
    return TakeWhileSequenceInclusive(this, function)
}

internal class TakeWhileSequenceInclusive<T>
constructor(
    private val sequence: Sequence<T>,
    private val predicate: (T) -> Boolean
) : Sequence<T> {
    override fun iterator(): Iterator<T> = object : Iterator<T> {
        val iterator = sequence.iterator()
        var nextState: Int = -1 // -1 for unknown, 0 for done, 1 for continue
        var nextItem: T? = null
        var ending = false

        private fun calcNext() {
            if (ending) {
                nextState = 0
                return
            }
            if (iterator.hasNext()) {
                val item = iterator.next()
                if (!predicate(item)) ending = true
                nextState = 1
                nextItem = item
                return
            }
            nextState = 0
        }

        override fun next(): T {
            if (nextState == -1)
                calcNext() // will change nextState
            if (nextState == 0)
                throw NoSuchElementException()
            @Suppress("UNCHECKED_CAST")
            val result = nextItem as T

            // Clean next to avoid keeping reference on yielded instance
            nextItem = null
            nextState = -1
            return result
        }

        override fun hasNext(): Boolean {
            if (nextState == -1)
                calcNext() // will change nextState
            return nextState == 1
        }
    }
}


fun buildTrie(strs: Array<String>): TrieNode {
    val trieRoot = TrieNode(null, false)
    strs.forEach { word ->
        word.foldIndexed(trieRoot) { index, acc, char ->
            acc.neighbors[char]?.apply { isEnd = index == word.lastIndex } ?:
            TrieNode(char, index == word.lastIndex).also { acc.addNeighbor(it) }
        }
    }
    return trieRoot
}

fun main() {
    val m = arrayOf("flower", "flow","flowtruyrit",)

    val time1 = measureNanoTime {  println(longestCommonPrefixFastest(m)) }
//    val time2 = measureNanoTime {  println(longestCommonPrefix(m)) }
//    val time3 = measureNanoTime {  println(longestCommonPrefixTrie(m)) }
//    println(time1)
//    println(time2)
//    println(time3)
}