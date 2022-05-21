package com.huisam.kotlincoroutine.arrow

import arrow.optics.Lens

data class Product(val name: String)
data class OrderItem(val quantity: Long, val product: Product)
data class Order(val purchaserName: String, val orderItem: OrderItem)

// 이러한 것은 arrow kt 에서 지원해주는 kapt 기능을 통하여 자동생성이 가능하다.
private val orderOrderItem: Lens<Order, OrderItem> = Lens(
    get = { it.orderItem },
    set = { order, orderItem -> order.copy(orderItem = orderItem) }
)

private val orderItemProduct: Lens<OrderItem, Product> = Lens(
    get = { it.product },
    set = { orderItem, product -> orderItem.copy(product = product) }
)

private val productName: Lens<Product, String> = Lens(
    get = { it.name },
    set = { product, name -> product.copy(name = name) }
)


fun main() {
    val order = Order(
        purchaserName = "customer",
        orderItem = OrderItem(
            quantity = 1L,
            product = Product(name = "productA")
        )
    )

    val order2 = order.copy(
        orderItem = order.orderItem.copy(
            product = order.orderItem.product.copy(
                name = "productB"
            )
        )
    )

    val orderCopyOnlyProductName: Lens<Order, String> = orderOrderItem compose orderItemProduct compose productName
    val order3 = orderCopyOnlyProductName.modify(order) { "productB" }

    println(order2.orderItem.product.name == order3.orderItem.product.name)
}