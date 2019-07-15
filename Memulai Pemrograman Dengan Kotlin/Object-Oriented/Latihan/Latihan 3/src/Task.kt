// TODO 1
fun sum(valueA: Double, valueB: Double) = valueA + valueB
fun sum(valueA: Int, valueB: Int) = valueA + valueB
fun sum(valueA: String, valueB: String) = valueA.toInt() + valueB.toInt()

fun main() {
    val resultA = sum(10.9, 19.1)
    val resultB = sum(19, 28)
    val resultC = sum("27", "19")

    println(resultA)
    println(resultB)
    println(resultC)
}