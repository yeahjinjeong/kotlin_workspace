package com.example.kotlin_workspace.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

var acquired = 0

class Resource {
    init { acquired++ }
    fun close() { acquired-- }
}

fun main() {
    runBlocking {
        repeat(10_000) {
            launch(Dispatchers.Default) {
                var resource : Resource? = null
                try {
                    resource = withTimeout(60) { delay(50)
                        Resource()
                    }
                    print("+")
                } finally {
                    resource?.close()
                    print("-")
                }
            }
        }
    }
    println()
    println(acquired)
}