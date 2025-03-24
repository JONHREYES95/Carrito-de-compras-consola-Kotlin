package models
// Clase que representa un ítem dentro del carrito de compras.
// Asocia un producto con la cantidad agregada al carrito.
data class ItemCarrito (
    val producto: Productos,    // Producto seleccionado
    var cantidad: Int           // Cantidad de unidades de ese producto en el carrito
)