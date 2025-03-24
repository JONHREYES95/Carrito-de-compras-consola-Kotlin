package services

import models.Productos
// Función que muestra por consola la lista de productos disponibles en el inventario.
fun listarProductos(inventario: List<Productos>) {
    println("\n--- LISTA DE PRODUCTOS ---")
    // Verifica si el inventario está vacío
    if (inventario.isEmpty()) {
        println("No hay productos disponibles.")
    } else {
        // Imprime cada producto con su información formateada
        inventario.forEach { prod ->
            println("ID: ${prod.id}, Nombre: ${prod.nombre}, Precio: $${String.format("%.2f", prod.precio)}, Stock: ${prod.stock}")
        }
    }
}