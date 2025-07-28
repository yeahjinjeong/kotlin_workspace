package com.example.kotlin_workspace.grammar

fun main() {

    val maxResult = compareBy{ a, b -> if (a>b) a else b}
    val minResult = compareBy{ a, b -> if (a<b) a else b}

    println("${maxResult}")
    println("${minResult}")

    val lambda = invokeLamda { number:Double -> number == 4.321 }
    val lambdaIt = invokeLamda { it > 5.4 }

    println(lambda)
    println(lambdaIt)

    println("Pepperoni ".pizzaIsGreat())
    println("Hawaiian ".pizzaIsGreat())
    println("YeRin".introduceMyself(25))
}

// 고차 함수
fun compareBy(operation: (a:Int, b:Int) -> Int): Int {
    return operation(6, 3)
}

fun invokeLamda(lambda: (Double) -> Boolean) : Boolean {
    return lambda(5.4321)
}

/**
 * operation은 a, b 인자를 받아서 정수를 반환하는 expression인데
 * maxBy는 그 expression을 인자로 받아서 정수를 반환하는 놈이고
 * 반환하는 내용은 그 expression에 인자를 전달하는 것?
 */

val pizzaIsGreat : String.() -> String = {
    this + "Pizza is yummy!" + this + "is the best."
}

// 단일 파라미터 it으로 받기
val introduceMyself : String.(Int) -> String = {
    "I am ${this} and ${it} years old."
}

