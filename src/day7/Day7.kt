package day7

import java.util.*

fun main(args: Array<String>) {

    val exampleInput = """pbga (66)
xhth (57)
ebii (61)
havc (66)
ktlj (57)
fwft (72) -> ktlj, cntj, xhth
qoyq (66)
padx (45) -> pbga, havc, qoyq
tknk (41) -> ugml, padx, fwft
jptl (61)
ugml (68) -> gyxo, ebii, jptl
gyxo (61)
cntj (57)"""

    findBottomProgram(exampleInput)
    findBottomProgram(input)

}

fun findBottomProgram(input: String) {

    val nodes: HashMap<String, Node> = HashMap()

    for (line in input.lines()) {
        val name = line.substringBefore(" ")
        val regex: Regex = """.*\((\d*)\).*""".toRegex()
        val weight = regex.matchEntire(line)?.groups?.get(1)?.value?.toInt() ?: -1

        var node = nodes[name]
        if (node == null) {
            node = Node()
            nodes.put(name, node)
        }

        node.name = name
        node.weight = weight

        val childs = line.substringAfter("-> ", missingDelimiterValue = "").split(", ")
        for (child in childs) {
            var childNode = nodes[child]
            if (childNode == null) {
                childNode = Node()
                nodes.put(child, childNode)
            }
            childNode.name = child
            childNode.parent = node
            node.addChild(childNode)
        }


    }

    println("***")

    val rootNode: Node = nodes.values.single { it.parent == null }
    println("RootNode: " + rootNode)

    var balancedWeight = -1
    for (child in rootNode.children) {
        println(child.name + ": " + child.totalWeight() + ": " + child.isBalanced() + ":" + child.weight)

        if (child.isBalanced()) {
            balancedWeight = child.totalWeight()
        } else {
            for (u in child.children) {
                println(u.name + ": " + u.totalWeight() + ": " + u.isBalanced())
            }
        }


    }

    val findUnbalanced = rootNode.findUnbalanced()
    println("Unbalanced: " + findUnbalanced?.name + ", " + findUnbalanced?.weight + ", " + findUnbalanced?.totalWeight())

    //calculated the rest by hand 1531 - 5 to get a total weight of 2260

}


class Node {
    var name: String = ""
    var weight: Int = 0
    var children: ArrayList<Node> = ArrayList()
    var parent: Node? = null

    fun addChild(child: Node) {
        children.add(child)
    }

    override fun toString(): String {
        return "name: $name, weight: $weight, parent: " + parent?.name
    }

    fun totalWeight(): Int {
        return weight + children.sumBy { it.totalWeight() }
    }

    fun findUnbalanced(): Node? {
        println("findUnbalanced " + this)


        //child with total weight different from other children

        val uniqueChild = children.groupBy { it.totalWeight() }

        for(col in uniqueChild){
            if(col.value.size == 1){
                for (c in children){
                    println(c.name + c.totalWeight())
                }
                return col.value[0].findUnbalanced()
            }
        }
        return this

//        if(uniqueChild != null){
//            return uniqueChild.single().findUnbalanced()
//        }else{
//            return this
//        }
    }

    fun isBalanced(): Boolean {
        if (children.isNotEmpty()) {
            val childWeight = children[0].totalWeight()
            children
                    .filter { it.totalWeight() != childWeight }
                    .forEach { return false }
        }
        return true


    }
}