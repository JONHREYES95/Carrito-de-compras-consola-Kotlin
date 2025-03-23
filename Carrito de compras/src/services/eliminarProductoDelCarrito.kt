package services

import models.ItemCarrito
import models.Productos

// Función que permite eliminar productos del carrito de compras.
fun eliminarProductoDelCarrito(carrito: MutableList<ItemCarrito> ,inventario: MutableList<Productos>) {
    println("\n--- ELIMINAR PRODUCTO DEL CARRITO ---")
    // Verificar si el carrito está vacío
    if (carrito.isEmpty()) {
        println("El carrito está vacío.")
        println("¿Queres agregar un producto al carrito de compras? (s/n)")
        val confirmacion = readln().trim()
        if (confirmacion == "s" || confirmacion == "S") {
            agregarProductoAlCarrito(inventario, carrito)
        }else{
            return
        }
    }
    // Mostrar contenido del carrito con índice
    println("Carrito actual:")
    for ((index, item) in carrito.withIndex()) {
        println("*".repeat(40))
        println("${index + 1}. ${item.producto.nombre} (Cantidad: ${item.cantidad})")
        println("*".repeat(40))
    }
    println("Ingresa el número del producto que deseas eliminar del carrito:")
    val opcion = readlnOrNull()?.toIntOrNull()
    // Validar que la opción ingresada sea válida
    if (opcion == null || opcion <= 0 || opcion > carrito.size) {
        println("El item indicado no concuerda con nuestros registros")
        println("Porfavor vuelve a intentarlo correctamente ")
        return eliminarProductoDelCarrito(carrito ,inventario)
    }

    val itemSeleccionado = carrito[opcion - 1]
    println("Ingresa la cantidad que deseas eliminar (máximo ${itemSeleccionado.cantidad}):")
    val cantidadEliminar = readlnOrNull()?.toIntOrNull()
    // Validar que la cantidad sea válida y dentro del rango posible
    if (cantidadEliminar == null || cantidadEliminar <= 0 || (cantidadEliminar > itemSeleccionado.cantidad)) {
        println("La cantidad indicada no concuerda con nuestros registros de tu compra")
        println("Porfavor vuelve a intentarlo correctamente ")
        return eliminarProductoDelCarrito(carrito, inventario)
    }
    // Restaurar el stock en el inventario
    val productoInventario = inventario.find { it.nombre == itemSeleccionado.producto.nombre }

    if (productoInventario != null) {
        productoInventario.stock += cantidadEliminar // Aumentar cantidad en el inventario
    }
    // Eliminar o reducir la cantidad del ítem en el carrito
    if (cantidadEliminar == itemSeleccionado.cantidad) {
        // Si se elimina toda la cantidad, se quita el producto del carrito
        carrito.removeAt(opcion - 1)
        println("Se eliminó '${itemSeleccionado.producto.nombre}' completamente del carrito.")
    } else {
        // Reducir la cantidad del item
        itemSeleccionado.cantidad -= cantidadEliminar
        println("Se eliminaron $cantidadEliminar unidad(es) de '${itemSeleccionado.producto.nombre}'.")
    }
}