// main function
fun main() {
    val user = setUser("Fariz", 23)
    println(user)

    printUser("Fariz")
}

fun setUser(name: String, age: Int) = "Your name is $name, and you $age years old"

fun printUser(name: String) {
    println("Your name is $name")
}