import org.junit.Assert
import org.junit.Test

class Test {

    private fun checkFunction(now: Int, shopOpen: Int, shopClose: Int) {
        val result = now < shopOpen || now > shopClose

        Assert.assertEquals("Fungsi shopIsClose() seharusnya mengembalikan nilai $result", result, shopIsClose(now, shopOpen, shopClose))
    }

    @Test
    fun testClose() = checkFunction(5, 7, 17)

    @Test
    fun testOpen() = checkFunction(10, 8, 20)
}