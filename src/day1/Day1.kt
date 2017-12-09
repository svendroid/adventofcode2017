package day1

fun main(args: Array<String>) {
    //your input stored in the `input` variable
    println("--- Part One ---")
    partOne("1122")
    partOne("1111")
    partOne("1234")
    partOne("91212129")
    partOne(input)

    println("--- Part Two ---")
    partTwo("1212")
    partTwo("1221")
    partTwo("123425")
    partTwo("123123")
    partTwo("12131415")
    partTwo(input)
}

fun partTwo(input: String) {
    var result = 0
    for (i in input.indices) {
        var nextIndex: Int = i + input.length / 2
        nextIndex %= input.length

        if (input[i] == input[nextIndex]) {
            result += Integer.parseInt("" + input[i])
        }
    }
    println(result)
}

fun partOne(input: String) {
    var result = 0
    for (i in input.indices) {
        var nextIndex: Int = i + 1
        if (nextIndex == input.length) {
            nextIndex = 0
        }
        if (input[i] == input[nextIndex]) {
            result += Integer.parseInt("" + input[i])
        }
    }
    println(result)
}