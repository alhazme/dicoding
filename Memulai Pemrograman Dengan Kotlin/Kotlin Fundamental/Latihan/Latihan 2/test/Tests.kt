import org.junit.Assert
import org.junit.Test

class Test {

    private fun functionMultiple(value1: String, value2: String) {
        val result = value1.toInt() * value2.toInt()
        Assert.assertEquals("Fungsi multiple() seharusnya mengembalikan nilai $result", result, multiple(value1, value2))
    }

    private fun functionSum(value1: Int, value2: Int) {
        val result = value1 + value2
        Assert.assertEquals("Fungsi sum() seharusnya mengembalikan nilai $result", result, sum(value1, value2))
    }


    @Test
    fun testMultiple1() = functionMultiple("10", "10")

    @Test
    fun testMultiple2() = functionMultiple("20", "20")

    @Test
    fun testMultiple3() = functionMultiple("30", "30")

    @Test
    fun testSum1() = functionSum(10, 10)

    @Test
    fun testSum2() = functionSum(20, 20)

    @Test
    fun testSum3() = functionSum(30, 30)
}