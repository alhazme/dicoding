fun main() {
    val value = 1
    val range = 1..100

    // TODO 2
    println(inRange(value, range))
}

// TODO 1
fun inRange(value: Int, range: IntRange): Boolean {
    if (value in range) { return true }
    return false
}