package day6

import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {

    val exampleInput: IntArray = intArrayOf(0, 2, 7, 0)
    val input: IntArray = """14	0	15	12	11	11	3	5	1	6	8	4	9	1	8	4""".split("\t").map { it.toInt() }.toIntArray()

    println("Example: " + calcCycles(exampleInput))
    println("Input: " + calcCycles(input).first)

}

fun calcCycles(banks: IntArray): Pair<Int, Int> {

    var count = 0
    val results: MutableMap<String, Int> = HashMap()
    while (true) {
        var indexMostBlocks = 0
        var mostBlocks = 0
        for ((i, blocks) in banks.withIndex()) {
            if (mostBlocks < blocks) {
                mostBlocks = blocks
                indexMostBlocks = i
            }
        }

        banks[indexMostBlocks] = 0

        println("$indexMostBlocks $mostBlocks")

        for (i in 1..mostBlocks) {
            val index = (indexMostBlocks + i) % banks.size
            println(index)
            banks[index] += 1
            println(Arrays.toString(banks))
        }
        println(Arrays.toString(banks))
        count += 1

        val result = Arrays.toString(banks)
        if (results.contains(result)) {
            return Pair(count, count - (results[result] ?: 0))
        } else {
            results.put(result, count)
        }

    }


}

