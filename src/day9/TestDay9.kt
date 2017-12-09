package day9

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


class TestDay9{

    private lateinit var day9: Day9

    @Before
    fun setUp(){
        day9 = Day9()
    }


    @Test
    fun testExample1(){
        assertEquals(1, day9.getScore("""{}"""))
    }

    @Test
    fun testExample2(){
        assertEquals(6, day9.getScore("""{{{}}}"""))
    }

    @Test
    fun testExample3(){
        assertEquals(5, day9.getScore("""{{},{}}"""))
    }

    @Test
    fun testExample4(){
        assertEquals(16, day9.getScore("""{{{},{},{{}}}}"""))
    }

    @Test
    fun testExample5(){
        assertEquals(1, day9.getScore("""{<a>,<a>,<a>,<a>}"""))
    }

    @Test
    fun testExample6(){
        assertEquals(9, day9.getScore("""{{<ab>},{<ab>},{<ab>},{<ab>}}"""))
    }

    @Test
    fun testExample7(){
        assertEquals(9, day9.getScore("""{{<!!>},{<!!>},{<!!>},{<!!>}}"""))
    }

    @Test
    fun testExample8(){
        assertEquals(3, day9.getScore("""{{<a!>},{<a!>},{<a!>},{<ab>}}"""))
    }

}