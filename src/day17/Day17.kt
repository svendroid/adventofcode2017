package day17

fun main(args: Array<String>) {

    var steps = 367
    partOne(steps)

    partTwo(steps)


}

fun partTwo(steps: Int) {
    var currentPos = 0
    var index0 = 0
    var nextVal = 0

    for (i in 1..50000000) {
        var x: Int
        if (i == 1) {
            x = i
        } else {
            x = (currentPos + steps + 1) % (i)
        }

        currentPos = x

        if(currentPos <= index0){
            index0 = (index0+1)%i
        }else if(currentPos == (index0+1)%i){
            nextVal = i
        }
    }
    println("0 Next: $nextVal")

}

fun partOne(steps: Int){
    var buffer: ArrayList<Int> = ArrayList(2017)
    buffer.add(0)

    var currentPos = 0

    for (i in 1..2017) {
        var x = (currentPos + steps + 1) % (i)

        buffer.add(x, i)
        currentPos = x
//        println("Inserted $i at $x - next: ${buffer.get((x+1)%buffer.size)}" + buffer.toString())


    }
    println("2017 next: " + buffer.get((buffer.indexOf(2017)+1)%buffer.size))
    println("0 next: " + buffer.get((buffer.indexOf(0)+1)%buffer.size))
}
