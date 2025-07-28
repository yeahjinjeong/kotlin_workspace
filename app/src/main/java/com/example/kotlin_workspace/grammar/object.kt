package com.example.kotlin_workspace.grammar

fun main() {

    CarFactory.makeCar(100)
    CarFactory.makeCar(200)
    CarFactory.makeCar(300)
    CarFactory.makeCar(400)
    CarFactory.makeCar(500)

    println(CarFactory.cars.size.toString())
    println(CarFactory.cars)
}

// 싱글톤 객체
// data class
object CarFactory {
    val cars = mutableListOf<Car>()

    fun makeCar(horsePower: Int) : Car {
        val car = Car(horsePower)
        cars.add(car)
        return car
    }
}

data class Car(val horsePower:Int)