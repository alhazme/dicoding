fun main() {

    // TODO 2
    val user = User("John", 23, "St. Petersburg")

    // TODO 3
    var newUser = user.copy()

    // TODO 4
    newUser = newUser.copy(age = 25, address = "Saint Petersburg")

    // TODO 5
    println("User")
    val userName = user.component1()
    val userAge = user.component2()
    val userAddress = user.component3()
    println("Name: $userName")
    println("Age: $userAge")
    println("Address: $userAddress")
    println("")

    println("New User")
    val newUserName = newUser.component1()
    val newUserAge = newUser.component2()
    val newUserAddress = newUser.component3()
    println("Name: $newUserName")
    println("Age: $newUserAge")
    println("Address: $newUserAddress")

}

// TODO 1
data class User(val name: String, val age: Int, val address: String)