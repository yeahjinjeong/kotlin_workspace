package com.example.practice

fun main() {
    val korean = Korean("Joo")
    println("${korean.originName} > ${korean.name} : ${korean.eatingIceCream()}")

    val human : Human = korean
    println(human.eatingIceCream())
    println(korean.originFun)
}

final class FinalClass {

}

class Korean(name : String = "JooHyun") : Human() {
    override val name = name
    val originName : String = super.name
    val originFun : String = super.eatingIceCream()
    init {
        println("How about Korean?")
    }
    override fun eatingIceCream() : String = "It's too sweet"
}