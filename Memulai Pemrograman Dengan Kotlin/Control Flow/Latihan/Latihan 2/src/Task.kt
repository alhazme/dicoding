fun main() {
    // TODO 1
    val range = 1..100 step 10


    // TODO 2
    for (i in range) {
        val result = i * range.step
        println("$i x ${range.step} = $result")
    }
}