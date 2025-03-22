package services

import models.Productos
import java.io.File

// Funciones CRUD en archivo de texto
private const val ARCHIVO_INVENTARIO = "inventario.txt"

fun cargarInventario(): MutableList<Productos> {
    val inventario = mutableListOf<Productos>()

    try {
        val file = File(ARCHIVO_INVENTARIO)
        if (file.exists()) {
            file.forEachLine { line ->
                val partes = line.split(",")
                if (partes.size == 4) {
                    val id = partes[0].toInt()
                    val nombre = partes[1]
                    val precio = partes[2].toDouble()
                    val stock = partes[3].toInt()
                    inventario.add(Productos(id, nombre, precio, stock))
                }
            }
        } else {
            file.createNewFile() // Crea el archivo si no existe
            println("Archivo inventario.txt creado.")
        }
    } catch (e: Exception) {
        println("Error al cargar el inventario: ${e.message}")
    }
    return inventario
}

fun guardarInventario(inventario: List<Productos>) {
    try {
        File(ARCHIVO_INVENTARIO).printWriter().use { out ->
            inventario.forEach { producto ->
                out.println("${producto.id},${producto.nombre},${producto.precio},${producto.stock}")
            }
        }
    } catch (e: Exception) {
        println("Error al guardar el inventario: ${e.message}")
    }
}

// Funciones de la aplicación

fun agregarProductoInventario(inventario: MutableList<Productos>) {
    println("Ingresa el ID del producto:")
    val id = readlnOrNull()?.toIntOrNull() ?: return

    val productoExistente = inventario.find { it.id == id }

    if (productoExistente != null) {
        // El producto ya existe, aumentar el stock
        println("Producto existente. ¿Cuánto stock deseas agregar?")
        val stockAgregar = readlnOrNull()?.toIntOrNull() ?: return
        productoExistente.stock += stockAgregar
        guardarInventario(inventario)
        println("Stock de '${productoExistente.nombre}' aumentado en $stockAgregar unidades.")
    } else {
        // El producto no existe, agregarlo como nuevo
        println("Producto no encontrado. ¿Deseas agregarlo como nuevo? (s/n)")
        val confirmacion = readlnOrNull()?.trim()?.lowercase()
        if (confirmacion == "s") {
            println("Ingresa el nombre del nuevo producto:")
            val nombre = readlnOrNull() ?: return
            println("Ingresa el precio del nuevo producto:")
            val precio = readlnOrNull()?.toDoubleOrNull() ?: return
            println("Ingresa el stock inicial del nuevo producto:")
            val stock = readlnOrNull()?.toIntOrNull() ?: return

            inventario.add(Productos(id, nombre, precio, stock))
            guardarInventario(inventario)
            println("Producto agregado al inventario.")
        } else {
            println("Operación cancelada.")
        }
    }
}

fun eliminarProductoInventario(inventario: MutableList<Productos>) {
    listarProductos(inventario)
    println("Ingresa el ID del producto:")
    val id = readlnOrNull()?.toIntOrNull() ?: return

    val productoExistente = inventario.find { it.id == id }

    if (productoExistente != null) {
        println("¿Qué deseas hacer?")
        println("1. Eliminar completamente el producto (incluyendo ID)")
        println("2. Eliminar solo el stock (marcar como agotado)")
        println("3. Eliminar una cantidad parcial del producto")
        val opcion = readlnOrNull()?.toIntOrNull()

        when (opcion) {
            1 -> {
                inventario.remove(productoExistente)
                // Reordenar IDs
                val inventarioActualizado = inventario.mapIndexed { index, producto ->
                    producto.copy(id = index + 1)
                }.toMutableList()
                inventario.clear()
                inventario.addAll(inventarioActualizado)
                guardarInventario(inventario)
                println("Producto '${productoExistente.nombre}' eliminado completamente.")
            }
            2 -> {
                productoExistente.stock = 0
                guardarInventario(inventario)
                println("Stock de '${productoExistente.nombre}' marcado como agotado.")
            }
            3 -> {
                println("¿Cuántas unidades deseas reducir?")
                val cantidadReducir = readlnOrNull()?.toIntOrNull() ?: return
                if (cantidadReducir > productoExistente.stock) {
                    println("No puedes reducir más unidades de las que hay en stock.")
                    return
                }
                productoExistente.stock -= cantidadReducir
                guardarInventario(inventario)
                println("Stock de '${productoExistente.nombre}' reducido en $cantidadReducir unidades.")
            }
            else -> println("Opción no válida.")
        }
    } else {
        println("No se encontró un producto con ese ID.")
    }
}