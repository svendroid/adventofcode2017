package day12

val programsMap: MutableMap<Int, Program> = HashMap()
fun main(args: Array<String>) {

    val exampleInput = """|0 <-> 2
                        |1 <-> 1
                        |2 <-> 0, 3, 4
                        |3 <-> 2, 4
                        |4 <-> 2, 3, 6
                        |5 <-> 6
                        |6 <-> 4, 5""".trimMargin()



    for (line in input.lines()) {
        var progamId = line.substringBefore(" <-> ").toInt()
        var linkedProgramIds = line.substringAfter(" <-> ")
                .split(regex = """\s?,\s?""".toRegex()).map { it.toInt() }


        var program = getProgramFromMap(progamId)
        for (id in linkedProgramIds) {
            var linkedProgram = getProgramFromMap(id)
            program.ids.add(linkedProgram)
            linkedProgram.ids.add(program)
        }
    }


    var toIgnore: MutableSet<Program> = HashSet()
    toIgnore.add(getProgramFromMap(0))

    var resultSet: MutableSet<Program> = HashSet()
    programsMap.get(0)?.printAllLinkedPrograms(resultSet, toIgnore)

    println("PARTONE: " + resultSet.size)

    var groups: MutableList<Set<Program>> = ArrayList()
    for (entry in programsMap.entries) {
        println("" + entry.key + ": " + entry.value)

        var alreadyInGroup = false
        for(group in groups){
            if(group.contains(entry.value)){
                println("break")
                alreadyInGroup = true
                break
            }
            println("loop")
        }

        if(!alreadyInGroup) {

            println("resultsSEt")

            var toIgnore1: MutableSet<Program> = HashSet()
            toIgnore.add(getProgramFromMap(entry.key))

            var resultSet1: MutableSet<Program> = HashSet()
            entry.value.printAllLinkedPrograms(resultSet1, toIgnore1)

            groups.add(resultSet1)
        }
    }

    println("Groups Count: " + groups.size)



}

fun getProgramFromMap(key: Int): Program {

    var program = programsMap.get(key)
    if (program == null) {
        program = Program(key)
        programsMap.put(key, program)
    }

    return program

}

class Program(var id: Int) {
    var ids: MutableSet<Program> = HashSet()

    fun printAllLinkedPrograms(resultSet: MutableSet<Program>, alreadyAddedIds: Set<Program>) {

        //println(id)
        resultSet.add(this)

        for(link in ids){
            if(!alreadyAddedIds.contains(link)) {
                var idsToIgnore: MutableSet<Program> = HashSet()
                idsToIgnore.addAll(ids)
                idsToIgnore.addAll(alreadyAddedIds)
                link.printAllLinkedPrograms(resultSet, idsToIgnore)
            }
        }

    }

    override fun toString(): String {
        return ids.joinToString { it.id.toString() }
    }
}