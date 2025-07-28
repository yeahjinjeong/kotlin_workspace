package com.example.kotlin_workspace.grammar

fun main() {
    val array = arrayOf(1, 2, 3) // Array<Int>
    array[0] = 3 // 값 변경 가능, 크기는 불가
//    array[1] = "a" // 불가능

    val array2 = arrayOf(1, "b", 3.4f, "d", 5L) // Array<{Comparable<*> & java.io.Serializable}>

    val list = listOf(1, 2, 3) // List<Int>
//    list[0] = 2 // 값 변경 불가

    val list2 = listOf(1, "b") // List<{Comparable<*> & java.io.Serializable}>

    val mutableList = mutableListOf("a", "b", "c") // MutableList<String>
    mutableList.add("d") // 값 변경 가능, 크기 변경 가능
//    mutableList.add(2) // 불가능

    val mutableList2 = mutableListOf("a", 1) // MutableList<{Comparable<*> & java.io.Serializable}>
    mutableList2.add("b")
    mutableList2.add(2)

    var arrayList = arrayListOf<Int>() // kotlin.collections.ArrayList<Int>
    arrayList.add(1)
    arrayList.add(2)
    arrayList[0] = 20 // 값 변경 가능

    val arrayList2 = arrayListOf("1", 2) // kotlin.collections.ArrayList<{Comparable<*> & java.io.Serializable}>
    arrayList2.add(3f)

    val arrayList3 = arrayListOf(1, 2) // kotlin.collections.ArrayList<Int>
    arrayList = arrayList3 // 변수 재할당

    for (e in array2) {
        println(e)
    }

    forAndStep(array)
    forAndStep(array2)
}

fun <T> forAndStep(a:Array<T>) {
    print("[")
    for (i in a.indices step 2) { // a.indices = 0..a.size-1 = 0 until a.size
        print("${a[i]}, ")
    }
    print("] [")
    for (i in a.size - 1 downTo 0) {
        print("${a[i]}, ")
    }
    print("] [")
    for ((i, v) in a.withIndex()) {
        print("a[${i}] = ${v}, ")
    }
    print("]")
    println()
}