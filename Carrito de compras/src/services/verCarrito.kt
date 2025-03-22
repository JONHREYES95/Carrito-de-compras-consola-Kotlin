package services

import models.ItemCarrito

// Función que muestra el contenido actual del carrito de compras
fun verCarrito(carrito: List<ItemCarrito>) {
    println("\n--- CARRITO DE COMPRAS ---")
    // Validar si el carrito está vacío
    if (carrito.isEmpty()) {
        println("El carrito está vacío.")
        return
    }

    var total = 0.0
    // Recorrer y mostrar los ítems del carrito
    for ((index, item) in carrito.withIndex()) {
        val subtotal = item.producto.precio * item.cantidad
        total += subtotal
        // Mostrar datos del producto
        println("${index + 1}")
        println(item.producto.nombre)
        println("Cantidad: ${item.cantidad}")
        println("Precio: $ ${item.producto.precio}")
        println("Subtotal: $${String.format("%.2f", subtotal)}")
        println("*".repeat(40))
    }
    // Mostrar el total acumulado del carrito
    println("TOTAL: $${String.format("%.2f", total)}")
    println("*".repeat(40))
}