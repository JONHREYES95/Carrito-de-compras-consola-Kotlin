package services

import models.ItemCarrito
import models.Productos

// Función que genera la factura y realiza el proceso de pago
fun facturar(carrito: MutableList<ItemCarrito>, inventario: MutableList<Productos>) {
    println("*".repeat(40))
    println("\n--- FACTURA CELLEXPRESS ---")
    // Validar si el carrito está vacío
    if (carrito.isEmpty()) {
        println("El carrito está vacío, no se puede facturar.")
        return
    }
    var total = 0.0
    // Recorrer los productos del carrito para calcular totales
    carrito.forEach { item ->
        val subtotal = item.producto.precio * item.cantidad
        total += subtotal
        // Mostrar información del producto
        println("*".repeat(40))
        println("Producto: ${item.producto.nombre}\n")
        println("Cantidad: ${item.cantidad}\n")
        println("Subtotal: $${String.format("%.2f", subtotal)}\n")
        println("*".repeat(40))
    }
    // Mostrar total a pagar
    println("-".repeat(40))
    println("TOTAL A PAGAR: $${String.format("%.2f", total)}")
    println("-".repeat(40))

    // Limpiar carrito después de facturar
    carrito.clear()
    // Mensaje de cierre
    println("¡Gracias por tu compra!")
    println("¡te esperamos nuevamente!")
    println("*".repeat(40))
}