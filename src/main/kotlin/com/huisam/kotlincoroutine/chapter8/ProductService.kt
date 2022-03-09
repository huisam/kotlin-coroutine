package com.huisam.kotlincoroutine.chapter8

import kotlinx.coroutines.delay
import java.math.BigDecimal

data class Product(
    val id: String,
    val price: BigDecimal
)

class ProductService {
    suspend fun findProductById(productId: String): Product {
        delay(500)
        return Product(productId, 1000.toBigDecimal())
    }
}