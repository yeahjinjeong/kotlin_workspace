package com.example.practice

fun main() {
    val human = Human("Harry")
    println("${human.name} : ${human.eatingIceCream()}")

    val hum = Hum( 30)
    println("Hum(30) : ${hum.name}")
    println("Hum(30) : ${hum.age}")
//    println(hum.realAge)
    hum.noticeYourRealAge()
    // no setter
    hum.name = "Robert"
    hum.age = 50
    println("hum.name = \"Robert\" : ${hum.name}")
    println("hum.age = 50 : ${hum.age}")
    val hum2 = Hum("Sandy")
    println("Hum(\"Sandy\") : ${hum2.name}")
    println("Hum(\"Sandy\") : ${hum2.age}")
}

// 기본생성자까지 한번에!
open class Human(open val name: String = "Anonymous") {
    open fun eatingIceCream() : String = "I love it!"
}

class Hum(name: String = "Anonymous") {
    var name = name
    var age : Int? = 20
    private var realAge : Int? = null

    // 부생성자 선언
    constructor(age:Int) : this() {
        this.realAge = age
    }

    init {
        println("Init : hi!")
    }

    fun noticeYourRealAge() = println("My real age is ${realAge}.")
}