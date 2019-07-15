// TODO 1
open class Vehicle(val color: String, val wheel: Int, val maxSpeed: Int)

// TODO 2
class Car(color: String, wheel: Int, maxSpeed: Int): Vehicle(color, wheel, maxSpeed)
class Motorcycle(color: String, wheel: Int, maxSpeed: Int): Vehicle(color, wheel, maxSpeed)

fun main() {
    val car = Car("Red", 4, 250)

    // TODO 3
    println("${car.color}")
    println("${car.wheel}")
    println("${car.maxSpeed}")
    println()

    val motor = Motorcycle("Blue", 2, 120)

    // TODO 4
    println("${motor.color}")
    println("${motor.wheel}")
    println("${motor.maxSpeed}")
}