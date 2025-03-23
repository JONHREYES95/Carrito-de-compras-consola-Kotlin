import models.ItemCarrito
import services.*

fun main() {
    // Cargar productos desde el archivo de inventario
    val inventario = cargarInventario()
    // Inicializar el carrito de compras vacío
    val carrito = mutableListOf<ItemCarrito>()

    // Función para mostrar el menú principal
    fun mostrarMenu() {
        print("\n======= Bienvenido a CELLEXPRESS =======")
        println("\nSelecciona una de las siguientes opciones:\n")
        println("1. Ver productos en la tienda")
        println("2. Agregar producto al carrito")
        println("3. Eliminar producto del carrito")
        println("4. Ver carrito")
        println("5. Pagar")
        println("6. Agregar producto al inventario")
        println("7. Eliminar producto del inventario")
        println("8. Salir")
        print("Selecciona una opción: \n")
    }

    var opcion: String?
    do {
        mostrarMenu()
        opcion = readlnOrNull()?.trim()
        when (opcion) {
            "1" -> listarProductos(inventario)  // Ver productos
            "2" -> agregarProductoAlCarrito(inventario, carrito)    // Agregar al carrito
            "3" -> eliminarProductoDelCarrito(carrito, inventario)  // Eliminar del carrito
            "4" -> verCarrito(carrito)  // Ver carrito
            "5" ->
                // Confirmación antes de facturar
            {print("¿Deseas hacer el pago de tu factura? (s/n)\n")
                val confirmacion = readln().trim()
                if (confirmacion == "s" || confirmacion == "S") {
                    facturar(carrito, inventario)
                }else{
                    mostrarMenu()
                }
            }
            "6" -> {
                listarProductos(inventario) // Mostrar antes de agregar
                agregarProductoInventario(inventario)
            }
            "7" -> eliminarProductoInventario(inventario)
            "8" -> {
                // Si hay productos en el carrito, confirmar antes de salir
                if (carrito.isNotEmpty()) {
                    while (true) { // Bucle para el menú de salida
                        println("Tienes productos en el carrito. ¿Qué deseas hacer?")
                        println("1. Pagar")
                        println("2. Salir sin realizar cambios")
                        val opcionSalir = readlnOrNull()?.trim()
                        when (opcionSalir) {
                            "1" -> {
                                facturar(carrito, inventario)
                                carrito.clear() // Vaciar el carrito después de pagar
                                break
                            }
                            "2" -> {
                                println("Saliendo del programa sin realizar cambios.")
                                break // Salir del bucle del menú de salida
                            }
                            else -> println("Opción no válida. Intenta de nuevo.")
                        }
                    }
                } else {
                    println("Saliendo del programa...\n" +
                            "¡¡¡Muchas Gracias Por Su Visita!!!")
                }
            }
            else -> println("Opción no válida, intenta de nuevo.")
        }
    } while (opcion != "8")
}