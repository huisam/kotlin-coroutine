package com.huisam.kotlincoroutine.chapter4

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    println("Starting a coroutine block...")
    runBlocking {
        println(" Coroutine block started")
        launch {
            println("  1/ First coroutine start")
            delay(100)
            println("  1/ First coroutine end")
        }
        launch {
            println("  2/ Second coroutine start")
            delay(50)
            println("  2/ Second coroutine end")
        }
        println(" Two coroutines have been launched")
    }
    println("Back from the coroutine block")
}