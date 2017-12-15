package day15

import java.math.BigInteger


fun main(args: Array<String>) {

    var generatorA = Generator(BigInteger("873"), BigInteger("16807"))
    var generatorB = Generator(BigInteger("583"), BigInteger("48271"))


    var exampleA = Generator(BigInteger("65"), factor = BigInteger("16807"))
    var exampleB = Generator(BigInteger("8921"), factor = BigInteger("48271"))

    var judge = Judge(exampleA, exampleB)

    for (i in 0..4) {
        judge.nextRound()
    }
    println(judge.total)

    judge = Judge(generatorA, generatorB)

    // PART ONE
//    for (i in 1..40000000){
//        println(i)
//        judge.nextRound()
//    }
//
//    println(judge.total)

    //PART TWO
    judge = Judge(Generator(BigInteger("65"), factor = BigInteger("16807"), divider = 4),
            Generator(BigInteger("8921"), factor = BigInteger("48271"), divider = 8))

    for (i in 0..4) {
        judge.nextRound()
    }
    println(judge.total)

    var pickyGeneratorA = Generator(BigInteger("873"), BigInteger("16807"), divider = 4)
    var pickyGeneratorB = Generator(BigInteger("583"), BigInteger("48271"), divider = 8)
    judge = Judge(pickyGeneratorA,pickyGeneratorB)

    for(i in 1..5000000){
        println("$i")
        judge.nextRound()
    }
    println("*** " + judge.total)

}

class Judge(var a: Generator, var b: Generator) {

    var total = 0

    fun nextRound() {

        var aNextValue = a.nextValue()
        var bNextValue = b.nextValue()

        while (aNextValue == BigInteger("-1")){
            aNextValue = a.nextValue()
        }

        while (bNextValue == BigInteger("-1")){
            bNextValue = b.nextValue()
        }

//        println(String.format("%12d %12d", aNextValue, bNextValue))

        var aValue = String.format("%32s", aNextValue.toString(2)).replace(' ', '0')
        var bValue = String.format("%32s", bNextValue.toString(2)).replace(' ', '0')

        if (aValue.substring(16) == bValue.substring(16)) {
            total++
        }

    }

}

class Generator(var prevValue: BigInteger, var factor: BigInteger, var divider: Int = -1) {

    fun nextValue(): BigInteger {
        var nextValue = (prevValue * factor).remainder(BigInteger("" + Integer.MAX_VALUE))
        prevValue = nextValue
        if (divider == -1) {
            return nextValue
        } else {
            if (nextValue.remainder(BigInteger("$divider")) == BigInteger("0")) {
                return nextValue
            } else {
                return BigInteger("-1")
            }
        }
    }


}