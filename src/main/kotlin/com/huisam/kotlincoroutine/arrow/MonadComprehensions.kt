package com.huisam.kotlincoroutine.arrow

import arrow.core.Either
import arrow.core.continuations.either
import arrow.core.left
import arrow.core.right

object NotFound
data class Name(val value: String)
data class UniversityId(val value: String)
data class University(val name: Name, val deanName: Name)
data class Student(val name: Name, val universityId: UniversityId)
data class Dean(val name: Name)

private val students = mapOf(
    Name("Alice") to Student(Name("Alice"), UniversityId("UCA"))
)

private val universities = mapOf(
    UniversityId("UCA") to University(Name("UCA"), Name("James"))
)

private val deans = mapOf(
    Name("James") to Dean(Name("James"))
)

fun student(name: Name): Either<NotFound, Student> {
    println("called student, $name")
    return students[name]?.right() ?: NotFound.left()
}

fun university(id: UniversityId): Either<NotFound, University> {
    println("called university, $id")
    return universities[id]?.right() ?: NotFound.left()
}

fun dean(name: Name): Either<NotFound, Dean> {
    println("called dean, $name")
    return deans[name]?.right() ?: NotFound.left()
}

suspend fun main() {
    val dean = either<NotFound, Dean> {
        val alice = student(Name("Alice")).bind()
        val uca = university(alice.universityId).bind()
        val james = dean(uca.deanName).bind()
        james
    }

    val notFound = either<NotFound, Dean> {
        val paul = student(Name("Paul")).bind()
        val uca = university(paul.universityId).bind()
        val james = dean(uca.deanName).bind()
        james
    }

    println(dean)
    println(notFound)
}