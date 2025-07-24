package com.example.practice

fun main() {
    var name : String? = null
    nullCheck(name)
    name = "joo"
    nullCheck(name)
    ignoreNull(name)
}

fun nullCheck(name: String?) {
    val nullNameInUpperCase = (name?.toUpperCase()?:"Nothing")
    name?.let {
        println("My name is  $nullNameInUpperCase")
    }
}

fun ignoreNull(name: String?) {
    val notNull : String = name!! // 실제 null인 경우 NPE 처리
    val notNullNameInUpperCase = (notNull.toUpperCase())

    println(notNullNameInUpperCase)
}