package com.huisam.kotlincoroutine.arrow

import arrow.core.Either
import arrow.core.left
import arrow.core.right

fun parse(s: String): Either<NumberFormatException, Int> =
    if (s.matches(Regex("-?\\d+"))) s.toInt().right()
    else NumberFormatException("$s is not a valid integer.").left()

fun main() {
    val notNumber = parse("Not a number")
    val number = parse("2")

    println(notNumber) // Either.Left(java.lang.NumberFormatException: Not a number is not a valid integer.)
    println(number) // Either.Right(2)
}