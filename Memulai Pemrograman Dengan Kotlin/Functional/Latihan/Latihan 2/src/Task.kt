fun main() {
    val result = multiply(300, 300)

    // TODO 2
    println(result)
}

// TODO 1
typealias Arithmetic = (Int, Int) -> Int
val multiply: Arithmetic = { valueA, valueB -> valueA * valueB}