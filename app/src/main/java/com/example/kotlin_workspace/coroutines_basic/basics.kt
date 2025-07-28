package com.example.kotlin_workspace.coroutines_basic

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay // suspending function
import kotlinx.coroutines.job
import kotlinx.coroutines.launch // coroutine builder
import kotlinx.coroutines.runBlocking // Set the coroutine scope

fun main() = runBlocking {
    doName("yejin")
    doWorld()
    val job1 = launch {
        println("(wait1...)") // 8
        delay(10000L) // 9
        println("!") // 10
    }
    val job2 = launch {
        println("(wait2...)")
        delay(5000L)
        println("haha..")
    }
    job2.join() // launch 되자마자 시작하지만, 여기까지는 완료를 보장함
    println("Hello") // 3 - job2 끝날 때까지 대기 (원래라면, doWorld() 끝나면 다른 launch들과 동시에 시작)
    repeat(3) {
        launch { // 같은 블럭 내 다른 launch들과 동시 시작
            print(".")
        }
    }
}

suspend fun doName(name: String) {
    println("(wait3...)") // 1
    delay(1000L)
    println(name) // 2
}

suspend fun doWorld() = coroutineScope {
    launch { // in CoroutineScop
        println("(wait4...)") // 5
        delay(1000L) // 6
        println("World") // 7
    }
    println("?") // 4
}

/**
 * delay는 runBlocking이나 laynch, suspend function내에서 호출 가능하고,
 * launch는 runBlocking, coroutineScope내에서만 호출 가능하고,
 * coroutineScope는 suspend function에서만 호출 가능하고,
 * suspend function이 순서를 보장해준다
 *
 * 메소드 우선 > launch
 * launch#join()의 경우 launch의 종료를 보장하고, 메소드의 실행을 미룸
 */

/**
 * If you write the same program using threads (remove runBlocking, replace launch with thread, and replace delay with Thread.sleep), it will consume a lot of memory.
 */