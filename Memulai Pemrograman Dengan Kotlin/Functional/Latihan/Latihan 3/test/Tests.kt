import org.junit.Assert
import org.junit.Test

class Test {

    private fun checkFunction(value: String) {
        val result = mapOf(
                "lowerCase" to value.toLowerCase(),
                "upperCase" to value.toUpperCase()
        )

        Assert.assertEquals("Fungsi formatText seharusnya mengembalikan nilai $result", result, formatText(value))
    }

    @Test
    fun test1() = checkFunction("Kotlin Fundamental")

    @Test
    fun test2() = checkFunction("Kotlin is Awesome")

    @Test
    fun test3() = checkFunction("Dicoding")
}