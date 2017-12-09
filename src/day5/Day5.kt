package day5

fun main(args: Array<String>) {

    val exampleInputJumps: IntArray = intArrayOf(0, 3, 0, 1, -3)
    val inputJumps: IntArray = input.lines().map { it.toInt() }.toIntArray()

    println("*** Part One ***")
    println("Steps: " + jumpRecursive(arr = exampleInputJumps))
    //leads to stackoverflow error
    // println(jumpRecursive(arr = input.lines().map { it.toInt() }.toIntArray()))

    println("Steps: " + jumpIterative(exampleInputJumps))
    println("Steps: " + jumpIterative(inputJumps))

    println("*** Part Two ***")
    println("Steps: " + jumpIterativePart2(exampleInputJumps))
    println("Steps: " + jumpIterativePart2(inputJumps))

}

fun jumpRecursive(i: Int = 0, arr: IntArray): Int {
    val jumps = arr.copyOf()

    if (i < 0 || i > jumps.size - 1) {
        return 0
    }
    //println(Arrays.toString(jumps))
    val currentOffset = jumps[i]
    jumps[i] = currentOffset + 1

    //println(Arrays.toString(jumps))
    //println("Step: $currentOffset")

    return jumpRecursive(i + currentOffset, jumps) + 1
}

fun jumpIterative(arr: IntArray): Int {
    val jumps = arr.copyOf()

    var i = 0
    var currentOffset: Int
    var steps = 0

    //println("$steps: " + Arrays.toString(jumps))
    while (i >= 0 && i < jumps.size) {
        currentOffset = jumps[i]
        jumps[i] += 1
        i += currentOffset

        //println("currentOffset $currentOffset")


        steps += 1

        //println("$steps - i:$i:" + Arrays.toString(jumps))
    }
    return steps
}

fun jumpIterativePart2(arr: IntArray): Int {
    val jumps = arr.copyOf()

    var i = 0
    var currentOffset: Int
    var steps = 0

    //println("$steps: " + Arrays.toString(jumps))
    while (i >= 0 && i < jumps.size) {
        currentOffset = jumps[i]
        jumps[i] += if (currentOffset >= 3) -1 else 1
        i += currentOffset

        //println("currentOffset $currentOffset")


        steps += 1

        //println("$steps - i:$i:" + Arrays.toString(jumps))
    }
    return steps
}