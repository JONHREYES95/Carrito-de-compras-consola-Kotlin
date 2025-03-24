package models

// Clase que representa un producto disponible en el inventario de la tienda.
data class Productos (
    val id: Int,        // Identificador único del producto
    val nombre: String, // Nombre del producto
    val precio: Double, // Precio unitario del producto
    var stock: Int      // Cantidad disponible en inventario (mutable)
)