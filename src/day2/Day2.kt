package day2

fun main(args: Array<String>) {

    val exampleInput = """
    	|5	1	9	5
		|7	5	3
		|2	4	6	8""".trimMargin()

    println("Example Part One: " + calculateChecksum(exampleInput, ::calculateChecksumForLine))
    println("Checksum Part One: " + calculateChecksum(input, ::calculateChecksumForLine))

    val partTwoExampleInput = """
    	|5	9	2	8
		|9	4	7	3
		|3	8	6	5""".trimMargin()

    println("Example Part Two: " + calculateChecksum(partTwoExampleInput, ::calculateChecksumForLinePartTwo))
    println("Checksum Part Two: " + calculateChecksum(input, ::calculateChecksumForLinePartTwo))
}


fun calculateChecksum(input: String, body: (String) -> Int): Int {
    return input.lines().sumBy { body(it) }
}

fun calculateChecksumForLinePartTwo(line: String): Int {
    val row: List<Int> = line.split("\t").map({ it.toInt() }).sortedDescending()

    for ((i, value) in row.withIndex()) {
        for ((j, divider) in row.withIndex()) {
            if (i != j && value % divider == 0) {
                return value / divider
            }
        }
    }

    return -1

}

fun calculateChecksumForLine(line: String): Int {
    val row = line.split("\t").map({ it.toInt() })
    return (row.max() ?: 0) - (row.min() ?: 0)
}