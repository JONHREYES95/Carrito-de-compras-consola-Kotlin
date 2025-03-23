package services

import models.ItemCarrito
import models.Productos

// Función que permite al usuario agregar productos al carrito.
fun agregarProductoAlCarrito(inventario: MutableList<Productos>, carrito: MutableList<ItemCarrito>) {
    println("\n--- AGREGAR PRODUCTO AL CARRITO ---")
    listarProductos(inventario) // Mostrar productos disponibles
    println("Ingresa el ID del producto que deseas agregar:")
    val idProducto = readlnOrNull()?.toIntOrNull()
    if (idProducto == null) {
        println("ID no puede quedar vacio")
        println("porfavor llena correctamente este campo: ")
        return agregarProductoAlCarrito(inventario, carrito)
    }
    // Buscar producto por ID
    val productoEncontrado = inventario.find { it.id == idProducto }
    if (productoEncontrado == null) {
        println("No existe un producto con ese ID.")
        println("Revisa los ID disponibles en el catalogo e intentalo nuevamente")
        return agregarProductoAlCarrito(inventario, carrito)
    }

    println("Ingresa la cantidad que deseas agregar:")
    val cantidad = readlnOrNull()?.toIntOrNull()
    if (cantidad == null || cantidad <= 0) {
        println("Cantidad inválida.")
        println("Revisa las cantidades disponibles en el catalogo e intentalo nuevamente")
        return agregarProductoAlCarrito(inventario, carrito)
    }

    // Verificar que haya suficiente stock
    if (cantidad > productoEncontrado.stock) {
        println("No hay suficiente stock. El Stock disponible es : ${productoEncontrado.stock}")
        println("Revisa las cantidades disponibles en el catalogo e intentalo nuevamente")
        return agregarProductoAlCarrito(inventario, carrito)
    }

    // Verificar si el producto ya está en el carrito
    val itemExistente = carrito.find { it.producto.id == productoEncontrado.id }
    if (itemExistente != null) {
        // Si ya existe, aumentar la cantidad
        itemExistente.cantidad += cantidad
    } else {
        // Si no existe, agregar nuevo ítem al carrito
        carrito.add(ItemCarrito(productoEncontrado, cantidad))
    }

    // Reducir stock del inventario
    productoEncontrado.stock -= cantidad

    println("Se agregó $cantidad unidad(es) de '${productoEncontrado.nombre}' al carrito.")

    // Preguntar si desea seguir comprando
    print("¿Deseas seguir comprando?: (s/n)\n")
    val confirmacion = readln().trim()
    if (confirmacion == "s" || confirmacion == "S") {
        agregarProductoAlCarrito(inventario, carrito)
    }else{
        return
    }
}