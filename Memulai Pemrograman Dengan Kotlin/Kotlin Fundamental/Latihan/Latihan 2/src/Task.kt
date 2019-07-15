fun main() {

    val resultMultiple = multiple("10", "10")
    val resultSum = sum(10, 10)

    // TODO 3
    println("$resultMultiple")
    println("$resultSum")
}

// TODO 1
fun multiple(value1: String, value2: String) = value1.toInt() * value2.toInt()

// TODO 2
fun sum(value1: Int, value2: Int) = value1 + value2