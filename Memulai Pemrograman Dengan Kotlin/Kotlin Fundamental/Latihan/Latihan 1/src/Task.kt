fun main() {
    val now = 10
    val shopOpen = 7
    val shopClose = 17

    val shop = shopIsClose(now, shopOpen, shopClose)

    println("Shop is close : $shop" )
}

// TODO
fun shopIsClose(now: Int, shopOpen: Int, shopClose: Int) = now < shopOpen || now > shopClose