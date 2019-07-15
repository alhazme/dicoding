import org.junit.Assert
import org.junit.Test

class Test {

    private fun checkFunction(value: Int, range: IntRange) {
        val result = when (value) {
            in range -> true
            !in range -> false
            else -> false
        }

        Assert.assertEquals("Fungsi inRange() seharusnya mengembalikan nilai $result", result, inRange(value, range))
    }

    @Test
    fun testTrue() = checkFunction(10 , 1..100)

    @Test
    fun testFalse() = checkFunction(1000 , 1..100)
}