package day8

fun main(args: Array<String>) {

    val exampleInput = """|b inc 5 if a > 1
        |a inc 1 if b < 5
        |c dec -10 if a >= 1
        |c inc -20 if c == 10""".trimMargin()


    partOne(exampleInput)

    partOne(input)

}

fun partOne(input: String) {
    val registers: MutableMap<String, Int> = HashMap()

    var maxRegisterValue = 0

    for (line in input.lines()) {
        val regex: Regex = """(\w*)\s(\w*)\s(-?\d+)\sif\s(\w*)\s([!<>=][=]?)\s(-?\d+)""".toRegex()

        val value = regex.matchEntire(line)?.groups

//        println(line)
//        println(value)

        try {
            val registerName: String = value?.get(1)?.value!!
            val operation = value[2]?.value!!
            val n: Int = value[3]?.value?.toInt()!!
            val compRegister = value[4]?.value
            val comparator = value[5]?.value
            val compVal = value[6]?.value!!.toInt()

            val compVal1 = registers[compRegister] ?: 0
            var result = true
            when (comparator) {
                "==" -> result = compVal1 == compVal
                "!=" -> result = compVal1 != compVal
                "<=" -> result = compVal1 <= compVal
                ">=" -> result = compVal1 >= compVal
                "<" -> result = compVal1 < compVal
                ">" -> result = compVal1 > compVal
            }
            var registerValue = registers[registerName] ?: 0
            if (result) {
                when (operation) {
                    "inc" -> registerValue += n
                    "dec" -> registerValue -= n
                }
            }
            if (registerValue > maxRegisterValue) {
                maxRegisterValue = registerValue
            }
            registers.put(registerName, registerValue)

            println(registers)
        } catch (e: KotlinNullPointerException) {
            println(line)
            println(value)
        }


    }

    println(registers.maxBy { it.value })
    println("Max: $maxRegisterValue")
}