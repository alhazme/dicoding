// main function
fun main() {
    val value: Int? = null

    println(value.slice)
}

val Int?.slice: Int
    get() = this ?: 0 / 2