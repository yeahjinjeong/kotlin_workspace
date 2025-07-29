package com.example.kotlin_workspace.coroutines

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    // runBlocking = "동시성"이 아니라 "차단"
    runBlocking {
        val time = measureTimeMillis {
            val one = doSomethingUsefulOne()
            val two = doSomethingUsefulTwo()
            println("The answer is ${one + two}")
        }
        println("Completed in $time ms")

        /**
         * async await : 비동기처리 문법
         * async가 호출되는 즉시 동기적으로 실행됨 await 전까지!
         * await을 만나면 실행 정지, Promise가 해결될 때까지 기다림
         * await를 쓰지 않으면 변수에 Promise가 저장됨
         */
        val time2 = measureTimeMillis {
            val one = async { doSomethingUsefulOne() }
            val two = async { doSomethingUsefulTwo() }
            println("The answer is ${one.await() + two.await()}")
        }
        println("Completed in $time2 ms")

        // Lazily started async
        val time3 = measureTimeMillis {
            val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
            val two = async(start =CoroutineStart.LAZY ) { doSomethingUsefulTwo() }
            delay(1000L)
            println("ready?")
            one.start()
            two.start()
            println("The answer is ${one.await() + two.await()}")
        }
        println("Completed in $time3 ms")
    }

    val time4 = measureTimeMillis {
        // we can initiate async actions "outside" of a coroutine
        val one = somethingUsefulOneAsync()
        val two = somethingUsefulTwoAsync()
        // but waiting for a result must involve either suspending or blocking.
        // here we use `runBlocking { ... }` to block the main thread while waiting for the result
        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }
    }
    println("Completed in $time4 ms")

    runBlocking {
        val time5 = measureTimeMillis {
            println("The answer is ${concurrentSum()}")
        }
        println("Completed in $time5 ms")
    }

    runBlocking {
        try {
            failedConcurrentSum()
        } catch (e: ArithmeticException) {
            println("Computation failed with ArithmeticException")
        }
    }

}

// Sequential by default
suspend fun doSomethingUsefulOne() : Int {
    delay(1000L)
    return 13
}

suspend fun doSomethingUsefulTwo() : Int {
    delay(1000L)
    return 29
}

// Global scope is used to launch top-level coroutines which are operating on the whole application lifetime and are not cancelled prematurely.
// They can be used from anywhere.
@OptIn(DelicateCoroutinesApi::class)
fun somethingUsefulOneAsync() = GlobalScope.async {
    doSomethingUsefulOne()
}

@OptIn(DelicateCoroutinesApi::class)
fun somethingUsefulTwoAsync() = GlobalScope.async {
    doSomethingUsefulTwo()
}

// Structured concurrency with async
suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}

suspend fun failedConcurrentSum(): Int = coroutineScope {
    val one = async<Int> {
        try {
            delay(Long.MAX_VALUE)
            42 // try 의 마지막 값이 return 값
        }  finally {
            println("First child was cancelled")
        }
    }
    val two = async<Int> {
        println("Second child throws an exception")
        throw ArithmeticException()
    }
    one.await() + two.await()
}