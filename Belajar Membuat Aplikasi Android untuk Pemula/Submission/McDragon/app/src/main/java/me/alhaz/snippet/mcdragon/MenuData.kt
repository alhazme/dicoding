package me.alhaz.snippet.mcdragon

object MenuData {

    private var data = arrayOf(

        // Burger
        arrayOf(
            "https://mcdonalds.co.id/uploads/55OlgpzhBWeDj5XVCQL3.png",
            "Big Mac",
            "3 tumpuk roti burger dengan taburan wijen di atasnya, 2 lapis daging sapi, selada segar, keju, acar, saus Big Mac, potongan bawang.",
            34000
        ),
        arrayOf(
            "https://mcdonalds.co.id/uploads/6ZDzhChKnawUgijjRTcq.png",
            "Triple Burger with Cheese",
            "Dijamin puas dengan 3 lapisan daging ditambah keju.",
            39000
        ),
        arrayOf(
            "https://mcdonalds.co.id/uploads/9NXUDsj69OtNljR3PnP9.png",
            "Double Cheeseburger",
            "Roti burger, daging sapi, keju, saus tomat, acar, potongan bawang dan mustard",
            32500
        ),
        arrayOf(
            "https://mcdonalds.co.id/uploads/jIFNIQEF2h4q7LTyV5AM.png",
            "Cheeseburger with Egg",
            "Roti burger lezat dengan daging sapi, telur, keju, saus tomat, acar, potongan bawang dan mustard",
            26500
        ),
        arrayOf(
            "https://mcdonalds.co.id/uploads/1nxXyzG8BcF6XFg4q6nI.png",
            "Cheeseburger",
            "Roti burger, daging sapi, keju, saus tomat, acar, potongan bawang dan mustard",
            26000
        ),
        arrayOf(
            "https://mcdonalds.co.id/uploads/urfF5wstCNoKtlms1cv0.png",
            "McDouble",
            "Roti burger, 2 daging sapi, keju, saus tomat, acar, potongan bawang dan mustard",
            26000
        ),
        arrayOf(
            "https://mcdonalds.co.id/uploads/PO8H6Tu6KM4zcX7qOLvd.png",
            "Chicken Burger with Cheese",
            "Roti burger, daging ayam renyah, potongan selada segar dan satu lembar keju bernutrisi.",
            25000
        ),
        arrayOf(
            "https://mcdonalds.co.id/uploads/szT76Vman7bRvJbBjVJJ.png",
            "McChicken",
            "Roti burger dengan taburan wijen diatasnya, daging ayam, selada segar, saus McChicken.",
            28000
        ),
        arrayOf(
            "https://mcdonalds.co.id/uploads/ABytZXl0HufKWtMwykBw.png",
            "McSpicy",
            "Burger dengan daging paha ayam yang empuk, renyah dan pedas, dilengkapi dengan selada segar dan saus istimewa di dalam roti berwijen.",
            35000
        ),
        arrayOf(
            "https://mcdonalds.co.id/uploads/4B8SJHaTz72RZgxclHM3.png",
            "Fish Fillet Burger",
            "Nikmatnya daging ikan pilihan, dengan saus tartar dan keju istimewa dalam setangkup roti lembut, menjadikan Fish Fillet Burger paduan yang sempurna.",
            26500
        ),

        //
        arrayOf(
            "https://mcdonalds.co.id/uploads/FPjkTkxHYDxFS5zqXxHG.png",
            "PaNas 1 Spicy",
            "1 potong ayam, 1 nasi putih dan 1 Fruit Tea.",
            31500
        ),
        arrayOf(
            "https://mcdonalds.co.id/uploads/9GG0U23wfrZYUvNCmOXv.png",
            "PaNas 2 Spicy",
            "2 potong ayam, 1 nasi putih dan 1 Fruit Tea.",
            43500
        ),
        arrayOf(
            "https://mcdonalds.co.id/uploads/cxjDNJ5hvlWP6qF80IDe.png",
            "PaNas Spesial Spicy",
            "1 potong ayam, 1 telur dadar, 1 nasi putih dan 1 Fruit Tea.",
            37500
        )
    )

    val listData: ArrayList<Menu>
        get() {
            val list = ArrayList<Menu>()
            for (aData in data) {
                val hero = Menu()
                hero.photo = aData[0] as String
                hero.name = aData[1] as String
                hero.description = aData[2] as String
                hero.price = aData[3] as Int
                list.add(hero)
            }
            return list
        }
}