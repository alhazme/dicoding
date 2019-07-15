import org.junit.Assert
import org.junit.Test

class Test {

    private fun checkFunction(value1: Int, value2: Int) {
        val result = value1 * value2

        Assert.assertEquals("Fungsi multiple seharusnya mengembalikan nilai $result", result, multiply(value1, value2))
    }

    @Test
    fun test1() = checkFunction(100, 100)

    @Test
    fun test2() = checkFunction(200, 200)

    @Test
    fun test3() = checkFunction(300, 300)

    @Test
    fun test4() = checkFunction(400, 400)
}