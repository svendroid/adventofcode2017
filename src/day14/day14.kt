package day14

import day10.hexHash


fun main(args: Array<String>) {

    println(findUsedSquares("""uugsqrei"""))

}

fun findUsedSquares(key: String): Int {

    var usedSquaresCount = 0

    for (i in 0..127) {
        val list =
                hexHash("$key-$i").map {
                    String.format("%4s",
                            Integer.toBinaryString(Integer.parseInt("" + it, 16)))
                            .replace(' ', '0')
                }
        for (x in list) {
            print("$x")
            usedSquaresCount += x.count { '1' == it }
        }
        println()
    }

    return usedSquaresCount


}