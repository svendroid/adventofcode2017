package day11

import kotlin.math.absoluteValue

//https://www.redblobgames.com/grids/hexagons/ helped a lot :)

var directions: MutableMap<String, HexElement> = HashMap()
fun main(args: Array<String>) {


    directions.put("n", HexElement(1, -1, 0))
    directions.put("ne", HexElement(1, 0, -1))
    directions.put("s", HexElement(-1, 1, 0))
    directions.put("se", HexElement(0, 1, -1))
    directions.put("nw", HexElement(0, -1, 1))
    directions.put("sw", HexElement(-1, 0, 1))

    val exampleInput1 = """ne,ne,ne"""
    val exampleInput2 = """ne,ne,sw,sw"""
    val exampleInput3 = """ne,ne,s,s"""
    val exampleInput4 = """se,sw,se,sw,sw"""

    println(getMinDistance(exampleInput1))
    println(getMinDistance(exampleInput2))
    println(getMinDistance(exampleInput3))
    println(getMinDistance(exampleInput4))
    println(getMinDistance(input))

}

fun getMinDistance(instructions: String): Int {
    println("""---""")
    println(instructions)
    var root = HexElement()
    var maxDistanceEver = 0
    for (i in instructions.split(",")) {
        directions.get(i)?.let { root.go(it) }

        if (root.getMinDistanceToCenter() > maxDistanceEver) {
            maxDistanceEver = root.getMinDistanceToCenter()
        }
    }

    println(root)

    println("Part Two maxDistanceEver $maxDistanceEver")

    return root.getMinDistanceToCenter()

}

class HexElement(var y: Int = 0, var x: Int = 0, var z: Int = 0) {

    fun go(direction: HexElement) {
        x += direction.x
        y += direction.y
        z += direction.z
    }

    fun getMinDistanceToCenter(): Int {
        return maxOf(x.absoluteValue, y.absoluteValue, z.absoluteValue)
    }

    override fun toString(): String {
        return "[$y, $x, $z]"
    }

}

