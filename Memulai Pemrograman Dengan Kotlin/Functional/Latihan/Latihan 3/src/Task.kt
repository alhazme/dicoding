fun main() {

    // TODO 2
    val text = formatText("Halo Android")

    val lowerCase = text["lowerCase"]
    val upperCase = text["upperCase"]

    // TODO 3
    println("lowerCase: $lowerCase")
    println("upperCase: $upperCase")

}

// TODO 1
fun formatText(text: String) = with(text) {
    mapOf<String, String>(
        "lowerCase" to this.toLowerCase(),
        "upperCase" to this.toUpperCase()
    )
}