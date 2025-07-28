package com.example.kotlin_workspace.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

fun main() = runBlocking { // 메인 스레드(혹은 현재 스레드)를 블로킹
//    doCancellation()
    doNonCancellable()
}

suspend fun doCancellation() = coroutineScope {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) { // CPU 집약 작업을 백그라운드에서 효율적으로 실행하기 위해
        var nextPrintTime = startTime
        var i = 0
        while (isActive && i < 100) { // cancel()의 취소 요청을 수신할 기회가 없어요.
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
                delay(10000L) // 취소 확인 가능
            }
        }
        // 혹은 isActive yield() 사용
    }
    delay(1300L)
    println("main: I'm tired of waiting!")
    job.cancelAndJoin()
    println("main: Now I can quit.")
}

suspend fun doNonCancellable() = coroutineScope {
    val job = launch {
        try {
            println("first job")
            repeat(5) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
            println("second job")
            withTimeout(2000L) {
                repeat(1000) {  // 반복문의 경우 취소 요청을 수신할 기회가 없지만, repeat의 경우 즉시 수신한다!
                        i -> println("I'm sleeping $i ...")
                    delay(500L)
                }
            }
        } finally {
            withContext(NonCancellable) {
                println("job: I'm running finally")
                delay(1000L)
                println("job: And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(3500L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
    val job2 = launch {
        val result = withTimeoutOrNull(2000L) {
            repeat(1000) {  // 반복문의 경우 취소 요청을 수신할 기회가 없지만, repeat의 경우 즉시 수신한다!
                    i -> println("I'm sleeping $i ...")
                delay(500L)
            }
        }
        println("Result is $result")
    }
}