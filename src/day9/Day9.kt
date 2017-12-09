package day9

fun main(args: Array<String>) {

    val day9 = Day9()
    println("PART1: Score" + day9.getScore(input))
}


class Day9 {
    fun getScore(stream: String): Int {
        var score = 0
        var depth = 0
        var garbage = false
        var ignoreNext = false
        var notCanceledCharsInsideGarbage = 0

        stream.asSequence()
                .forEach {
                    if (ignoreNext) {
                        ignoreNext = false
                    } else if (it == '!') {
                        ignoreNext = true
                    } else if (garbage) {
                        if (it == '>') {
                            garbage = false
                        } else {
                            notCanceledCharsInsideGarbage++
                        }
                    } else if (it == '{') {
                        depth += 1
                        score += depth
                    } else if (it == '}') {
                        depth -= 1
                    } else if (it == '<') {
                        garbage = true
                    }
                }
        println("PART 2: NotCanceldCharsInGarbage: " + notCanceledCharsInsideGarbage)
        return score
    }

}