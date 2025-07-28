package com.example.kotlin_workspace.grammar

/**
 * 모든 함수는 Expression (식)
 * definition 이나 print의 경우 Statement (문)
 */

fun main() {
//    helloKotlin()
//    println(add(3, 1))
//    outerFunction()
//    templateStrings()
//    sayHi()
//    sayHi("Marie")
//    num()
    checkNum(95)
}

fun helloKotlin() {
    println("Hello Kotlin")
}

/**
fun 함수이름(매개변수1: 타입, 매개변수2: 타입, ...): 반환타입 {
return 반환값
}*/

fun add(a: Int, b: Int) : Int {
    return a + b
}

fun outerFunction() {
    fun innerFunction() {
        println("Inner function")
    }
    return innerFunction()
}

fun templateStrings() {
    val firstName = "Harry"
    var lastName = "Potter"
    println("My name is ${firstName + lastName}")
    println("This wallet is 4\$")
    println("Is '1==0' true? ${1==0}")
}

// variable default
fun sayHi(name: String = "Anna") {
    println("Hi, I'm ${name}!")
}

// val & var
fun num() {
    val a : Int = 1 // final
    var b : Int = 2
//    a = 10
    b = 11
    println(a + b)
}

// 조건문
fun compareBy(a: Int, b: Int):Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}

fun minBy(a: Int, b :Int) : Int = if (a < b) b else a

fun checkNum(score : Int) {
    // 실제로 어떤 값을 반환하지 않더라도 모든 함수는 값을 반환해야 한다.
    // 실제로 값을 반환하지 않는 함수의 반환 타입은 Unit이다.
    val evaluation : Unit = when(score) {
        0 -> println("This is 0")
        1 -> println("This is 1")
        // 복수 처리
        2, 3 -> println("This is  2 or 3")
        // 범위
        in 10..80 -> println("Not Bad")
        in 90..100 -> println("Genius.")
        else -> println("I don't know") // 반환값이 있을 때 생략 불가
    }
}