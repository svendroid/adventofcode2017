package day18

import java.util.*

fun main(args: Array<String>) {

    val exampleInput = """set a 1
add a 2
mul a a
mod a 5
snd a
set a 0
rcv a
jgz a -1
set a 1
jgz a -2"""

//    runInstructions(exampleInput)
    //runInstructions(input)

    var exampleInputPart2 = """snd 1
snd 2
snd p
rcv a
rcv b
rcv c
rcv d"""

    var program0 = Program(exampleInputPart2, 0)
    var program1 = Program(exampleInputPart2, 1)
    program0.otherProg = program1
    program1.otherProg = program0

    program0.runInstructions()


    println("SendCount Prog1: ${program1.sendCount}")
    println("SendCount Prog0: ${program0.sendCount}")

    program0 = Program(input, 0)
    program1 = Program(input, 1)
    program0.otherProg = program1
    program1.otherProg = program0

    program0.runInstructions()


    println("SendCount Prog1: ${program1.sendCount}")
    println("SendCount Prog0: ${program0.sendCount}")

}


class Program(input: String, private var pid: Long) {

    var msgIn: LinkedList<Long> = LinkedList()
    private var registers = HashMap<String, Long>()
    private var instructions = input.lines()
    private var i = 0
    lateinit var otherProg: Program
    var sendCount = 0

    init{
        registers.set("p", pid)
    }

    private var waiting: Boolean = false

    fun runInstructions() {
        println("PID:${pid} runInstr with i $i: ${instructions[i]}")


        loop@ while (i < instructions.size) {
            var instr = instructions[i]

//            println("PID${pid}: $instr")


            var cmd = instr.substring(0, 3)
            var register: String = instr[4].toString()
            var param = getValue(if (instr.length >= 7) instr.substring(6) else "0")

//            println("PID:$pid, i: $i, cmd:$cmd, reg:$register, par:$param")

            when (cmd) {
                "snd" -> {
//                    println("PID:$pid SEND " + getValue(register))
                    sendCount++
                    otherProg.msgIn.add(getValue(register))
                }
                "set" -> registers.set(register, param)
                "add" -> registers.set(register, (registers.get(register) ?: 0) + param)
                "mul" -> registers.set(register, (registers.get(register) ?: 0) * param)
                "mod" -> registers.set(register, (registers.get(register) ?: 0) % param)
                "rcv" -> {
                    if(msgIn.isEmpty() && otherProg.msgIn.isEmpty()){
                        println("PID:${pid} DEADLOCK")
                        return
                    }
                    if(msgIn.isEmpty()){
                        waiting = true
                        otherProg.runInstructions()
                        return
                    }else{
                        var reg = register
                        var msginnew = msgIn.removeFirst()
//                        println("PID-$pid: RECV REg:$reg, $msginnew" + registers.toString())
                        registers.set(register, msginnew)

                    }
                }
                "jgz" -> {
                    if ((registers.get(register) ?: 0) > 0) {
                        i += param.toInt()
//                        println(registers.toString())
                        continue@loop
                    }
                }

            }
//            println("PID:${pid}" +registers.toString())
            i++
        }
    }

    fun getValue(value: String): Long {
        try {
            return value.toLong()
        } catch (e: NumberFormatException) {
            return registers.get(value) ?: 0
        }
    }


}


//fun runInstructions(input: String) {
//    var instructions = input.lines()
//
//    var registers = HashMap<String, Long>()
//
//    var i = 0
//    var playedSound: Long = 0
//    loop@ while (i < instructions.size) {
//        var instr = instructions[i]
//
//        var cmd = instr.substring(0, 3)
//        var register: String = instr[4].toString()
//        var param = getValue(registers, if (instr.length >= 7) instr.substring(6) else "0")
//
//
//        println("i: $i, cmd:$cmd, reg:$register, par:$param, sound:$playedSound")
//
//        when (cmd) {
//            "snd" -> {
//                playedSound = registers.get(register) ?: 0
//            }
//            "set" -> registers.set(register, param)
//            "add" -> registers.set(register, (registers.get(register) ?: 0) + param)
//            "mul" -> registers.set(register, (registers.get(register) ?: 0) * param)
//            "mod" -> registers.set(register, (registers.get(register) ?: 0) % param)
//            "rcv" -> {
//                if ((registers.get(register) ?: 0) != 0L) {
//                    println("Recoverd $playedSound")
//                    return
//                }
//            }
//            "jgz" -> {
//                if ((registers.get(register) ?: 0) > 0) {
//                    i += param.toInt()
//                    println(registers.toString())
//                    continue@loop
//                }
//            }
//
//        }
//        println(registers.toString())
//        i++
//    }
//
//
//}
//
//fun getValue(registers: Map<String, Long>, value: String): Long {
//    try {
//        return value.toLong()
//    } catch (e: NumberFormatException) {
//        return registers.get(value) ?: 0
//    }
//}




