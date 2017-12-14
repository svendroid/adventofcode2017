package day10

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class TestDay10{

    @Test
    fun testConvertToASCII(){
        assert(listOf<Int>(49,44,50,44,51).equals(convertToASCII("""1,2,3""")))
    }


}