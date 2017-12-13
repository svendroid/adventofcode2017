package day13

fun main(args: Array<String>) {

    val exampleInput = """  |0: 3
                                    |1: 2
                                    |4: 4
                                    |6: 4""".trimMargin()

    println("Part one - example " + partOne(exampleInput,0))
    println("Part one - input " +partOne(input, 0))
    println("Part two - example delay:" + partTwoGetDelay(exampleInput))
    println("Part two - input:" + partTwoGetDelay(input))
}

fun partTwoGetDelay(input: String): Int {

    var layers: MutableList<Int> = ArrayList()
    for (i in input.lines().map { it.split(": ").map { it.toInt() } }) {
        while (layers.size < i[0]) {
            layers.add(0)
        }
        layers.add(i[1])
    }

    var delay = 0
    var caught = true
    delayLoop@ while (caught) {
        for ((picosecond, range) in layers.withIndex()) {
            if(isCaught(picosecond+delay, range)){
                delay += 1
                continue@delayLoop
            }
        }
        caught = false
    }
    return delay

}

fun partOne(input: String, delay: Int): Int {

    var layers: MutableList<Int> = ArrayList()

    for (i in input.lines().map { it.split(": ").map { it.toInt() } }) {
        while (layers.size < i[0]) {
            layers.add(0)
        }
        layers.add(i[1])
    }

    var severity = 0
    for ((picosecond, range) in layers.withIndex()) {

        var pos = getPosition(picosecond + delay, range)
        if (isCaught(picosecond+delay,range)) {
            severity += picosecond * range
        }
    }
    return severity

}

fun getPosition(sec: Int, range: Int): Int {
    var pos = 0
    var x = 1

    if (range == 0) {
        return -1
    }

    var i = 0
    while (i < sec) {
        pos += x
        if (pos % (range - 1) == 0) {
            x *= -1
        }
        i++
    }

    return pos
}

fun isCaught(sec: Int, range: Int):Boolean{
    return range != 0 && (sec)%(range*2-2)==0
}