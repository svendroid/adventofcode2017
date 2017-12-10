package day3

import java.util.*

fun main(args: Array<String>) {
    //PART ONE
    /*println("All: " + calcAll(6))
    println("All: " + calcAll(12))
    println("All: " + calcAll(23))
    println("All: " + calcAll(45))
    println("All: " + calcAll(1024))*/

    println("Part One: " + calcAll(289326))

    partTwo(289326)

}

fun partTwo(n: Int) {

    val width = Math.sqrt(n.toDouble())
    println(width)

    val m = 5001
    val board = Array(m, { IntArray(m) })

    //print2D(board)

    var x = m / 2
    var y = m / 2

    board[y][x] = 1
    x += 1



    calcArroundCoord(y, x, board)

    //print2D(board)

//    calcArroundCoord(2, 4, board)


    while (board[y][x] < n) {
        if (board[y][x - 1] != 0 && board[y - 1][x] == 0) {
            y -= 1
        } else if (board[y][x - 1] == 0 && board[y + 1][x - 1] != 0) {
            x -= 1
        } else if (board[y][x - 1] == 0 && board[y + 1][x] != 0) {
            x -= 1
        } else if (board[y + 1][x] == 0 && board[y + 1][x + 1] != 0) {
            y += 1
        } else if (board[y + 1][x] == 0 && board[y + 1][x + 1] != 0) {
            y += 1
        } else if (board[y][x + 1] != 0 && board[y + 1][x] == 0) {
            y += 1
        } else if (board[y][x + 1] == 0 && board[y - 1][x - 1] != 0) {
            x += 1
        } else {
            x += 1
        }

        //print2D(board)
        calcArroundCoord(y, x, board)

    }
    println("PART TWO: $y-$x: " + board[y][x])

//    print2D(board)

//    print2DSimple(board)
}

fun calcArroundCoord(y: Int, x: Int, arr: Array<IntArray>) {
    arr[y][x] = arr[y][x - 1] +
            arr[y - 1][x - 1] +
            arr[y + 1][x - 1] +
            arr[y - 1][x] +
            arr[y + 1][x] +
            arr[y][x + 1] +
            arr[y - 1][x + 1] +
            arr[y + 1][x + 1]
}

fun print2D(arr: Array<IntArray>) {
    for ((i, line) in arr.withIndex()) {
        for ((j, element) in line.withIndex()) {
            print("$i-$j($element)")
        }
        println()
    }
    println("---")
}

fun print2DSimple(arr: Array<IntArray>) {
    for (intArr in arr) {
        println(Arrays.toString(intArr))
    }

}


fun calcAll(x: Int): Int {
    return findCircleFor(x) + calc(findCircleFor(x), x)
}

fun calc(n: Int, x: Int): Int {
    val max = calcMax(n)
    val min = calcMin(n)

    val diff = x - min

    val modulo = n * 2
    val reminder = diff % modulo

    val y = reminder - (n - 1)

    println("x: $x:   min: $min, max: $max, cicle: $n,  diff: $diff, rem: $reminder, modulo: $modulo")
    return y
}

fun findCircleFor(x: Int): Int {
    var i = 1
    while (true) {
        val min = calcMin(i)
        val max = calcMax(i)
        if (x in min..max) {
            return i
        }
        i++
    }
}

fun calcMax(n: Int): Int {

    if (n <= 0) {
        return 1
    }
    return (n * 8) + calcMax(n - 1)

}

fun calcMin(n: Int): Int {
    return calcMax(n - 1) + 1
}