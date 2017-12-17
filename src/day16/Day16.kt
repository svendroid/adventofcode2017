package day16

fun main(args: Array<String>) {

//    var arr = "abcde".toCharArray()
//    Dance(arr).move("s1,x3/4,pe/b")
//    Dance().move(input)
//

    var programs = CharArray(16, { (it + 97).toChar() })
    var dance = Dance(programs)

    dance.move(1000000000, input)

    println("Final: " + String(programs))

}

class Dance(var programs: CharArray = CharArray(16, { (it + 97).toChar() })) {

    var spinPattern = ("""s(?<x>\d*)""").toRegex().toPattern()
    var exchangePattern = ("""x(?<x>\d*)\/(?<y>\d*)""".toRegex()).toPattern()
    var partnerPattern = ("""p(?<x>\w)\/(?<y>\w)""".toRegex()).toPattern()

    var seen = ArrayList<String>()

    fun move(rounds: Int, instructions: String) {

        for(i in 0..rounds) {
            if (seen.contains(String(programs))) {
                println("HIT SEEN $i")
                println("HIT SEEN " + seen.get(rounds % i))//cycle of dance repeats every i times
                return
            }

            seen.add(String(programs))
            for (instr in instructions.split(",")) {

//            println("$instr " + String(programs))

                var spinMatcher = spinPattern.matcher(instr)
                val exchangeMatcher = exchangePattern.matcher(instr)
                val partnerMatcher = partnerPattern.matcher(instr)

                if (spinMatcher.matches()) {
                    var startIdx = programs.size - spinMatcher.group("x").toInt()

                    var tempPrograms = programs.copyOf()
                    for (i in 0..programs.size - 1) {
                        programs[i] = tempPrograms[(i + startIdx) % programs.size]
                    }
                } else if (exchangeMatcher.matches()) {
                    var x = exchangeMatcher.group("x").toInt()
                    var y = exchangeMatcher.group("y").toInt()

                    swap(x, y)
                } else if (partnerMatcher.matches()) {
                    var x = partnerMatcher.group("x").toCharArray()[0]
                    var y = partnerMatcher.group("y").toCharArray()[0]

                    var xIdx = programs.indexOf(x)
                    var yIdx = programs.indexOf(y)

                    swap(xIdx, yIdx)
                }

//            println(programs)

            }

        }

    }

    fun swap(x: Int, y: Int) {
        var temp = programs[x]
        programs[x] = programs[y]
        programs[y] = temp
    }


}