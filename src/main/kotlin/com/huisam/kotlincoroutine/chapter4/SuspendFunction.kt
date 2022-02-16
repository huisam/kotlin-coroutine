package com.huisam.kotlincoroutine.chapter4

import kotlinx.coroutines.runBlocking

data class Profile(
    val id: Long,
    val name: String
)

interface ProfileServiceRepository {
    suspend fun fetchByName(name: String): Profile
}

class ProfileServiceClient : ProfileServiceRepository {
    override suspend fun fetchByName(name: String): Profile {
        return Profile(1, name)
    }
}

fun main() = runBlocking {
    val repository = ProfileServiceClient()
    val profile = repository.fetchByName("Susan")
    println(profile)
}