package day10

fun main(args: Array<String>) {

    val exampleInput = """3,4,1,5"""

    println("--- Part One ---")
    println("3,4,1,5 with valueRange 5: " + hash(exampleInput, 5))
    println("input: " + hash(input))

    println("--- Part Two ---")
    println("\"\": " + hexHash(""))
    println("AoC 2017: " + hexHash("AoC 2017"))
    println("1,2,3: " + hexHash("1,2,3"))
    println("1,2,4: " + hexHash("1,2,4"))
    println("input: " + hexHash(input))

}

fun hexHash(input: String, valueRange: Int = 256): String {
    val lenghts: MutableList<Int> = convertToASCII(input)
    lenghts.addAll(arrayListOf(17, 31, 73, 47, 23))

    var currentPos = 0
    var skip = 0
    val range: IntArray = IntArray(valueRange, { it })
    var round = 0
    while (round < 64) {
        for (length in lenghts) {
            val sublist: IntArray = IntArray(length)
            for ((i, value) in sublist.withIndex()) {
                val pos = (currentPos + i) % range.size
                sublist[i] = range[pos]
            }
            sublist.reverse()
            for ((i, value) in sublist.withIndex()) {
                val pos = (currentPos + i) % range.size
                range[pos] = sublist[i]
            }

            currentPos += length + skip
            skip++

        }
        round++
    }

    val denseHash = IntArray(16)

    for (i in 0..15) {
        var n = i * 16
        val nEnd = n + 16
        var block = 0
        while (n < nEnd) {
            block = block.xor(range[n])
            n++
        }
        denseHash[i] = block
    }

    val sb = StringBuilder()
    for (i in denseHash) {
        sb.append(String.format("%02x",i))
    }

    return sb.toString()
}

fun hash(input: String, valueRange: Int = 256): Int {
    val lenghts: IntArray = input.split(",").map { it.toInt() }.toIntArray()

    var currentPos = 0
    var skip = 0
    val range: IntArray = IntArray(valueRange, { it })

    for (length in lenghts) {
        val sublist: IntArray = IntArray(length)
        for ((i, value) in sublist.withIndex()) {
            val pos = (currentPos + i) % range.size
            sublist[i] = range[pos]
        }
        sublist.reverse()
        for ((i, value) in sublist.withIndex()) {
            val pos = (currentPos + i) % range.size
            range[pos] = sublist[i]
        }

        currentPos += length + skip
        skip++
    }

    return range[0] * range[1]

}

fun convertToASCII(input: String): MutableList<Int> {

    val asciis = input.toCharArray().map { it.toInt() }
    return asciis.toMutableList()

}