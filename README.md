# CELLEXPRESS – Carrito de Compras en Kotlin (Consola)

## Descripción General

CELLEXPRESS es una aplicación de consola desarrollada en Kotlin, diseñada para simular una experiencia completa de compra y administración de inventario desde un entorno de texto. El usuario puede interactuar con un menú intuitivo, gestionar productos, realizar compras y generar facturas detalladas.

Este proyecto fue creado como parte de la asignatura DSM104-CI-2025, aplicando programación orientada a objetos (POO), estructuras de datos y persistencia con archivos planos.

## Tecnologías Utilizadas

- Lenguaje: Kotlin
- Tipo de aplicación: Consola interactiva
- Persistencia de datos: Archivo de texto plano (inventario.txt)
- Paradigma: Programación orientada a objetos
- IDE sugerido: IntelliJ IDEA o Android Studio

## Cómo Ejecutar el Programa

Requisitos:

- Kotlin instalado (kotlinc y java)
- Archivo inventario.txt (se crea automáticamente si no existe)

Pasos para ejecutar:

1. Compilar:
   kotlinc src/*.kt -include-runtime -d cellexpress.jar

2. Ejecutar:
   java -jar cellexpress.jar

Sugerencia: también puedes abrir el proyecto en IntelliJ y ejecutar Main.kt directamente.

## Funcionalidades Implementadas

- Ver productos disponibles
- Agregar productos al carrito (con validación de stock)
- Eliminar productos parcial o totalmente del carrito
- Visualizar el contenido del carrito con subtotales
- Facturación clara y detallada con total general
- Agregar productos nuevos al inventario
- Eliminar productos o reducir stock del inventario
- Guardado automático de inventario en archivo plano
- Validaciones de entrada y manejo de errores
- Experiencia de consola amigable y guiada

## Autores del Proyecto

- Henry Vladimir Najera Guerra
- Jonathan Rafael Señora Reyes
- Jarly Leonel Vigil Velasquez
- Nelson Vicente Luna Hernandez

Cada integrante trabajó desde su propia rama de Git, con commits y participación activa en el repositorio.

## Notas y Recomendaciones

- El archivo inventario.txt se guarda automáticamente tras cada cambio.
- Al salir del programa, si el carrito no está vacío, se ofrece la opción de pagar antes de salir.
- Toda entrada del usuario es validada para evitar errores y mejorar la experiencia.

## Estructura del Proyecto
```
src/
├── Main.kt                      # Lógica principal del programa y menú
├── models/
│   ├── Productos.kt             # Clase Producto
│   └── ItemCarrito.kt          # Elemento en el carrito
├── services/
│   ├── Servicios.kt             # CRUD del inventario y manejo de archivo
│   ├── agregarProductoAlCarrito.kt
│   ├── eliminarProductoDelCarrito.kt
│   ├── verCarrito.kt
│   ├── listarProductos.kt
│   └── pagar.kt
inventario.txt                   # Archivo de datos persistente
```
## Lógica y Flujo General

1. El programa carga productos desde inventario.txt.
2. El usuario interactúa mediante un menú que permite navegar por las funciones.
3. Al realizar una compra, se muestra una factura y se actualiza el stock.
4. Todos los cambios se reflejan en el archivo plano automáticamente.
5. El carrito se limpia tras el pago o salida confirmada.

## Documentación del Código

Cada archivo está dividido en fragmentos comentados con explicaciones claras. 
Puedes encontrar esta documentación directamente en el código fuente o visitar el siguiente enlace:
https://drive.google.com/file/d/13Fap0dnZHzlvVGkce8OTXypnozsw98qW/view?usp=sharing

## Conclusión

Este proyecto permitió poner en práctica los fundamentos de la programación orientada a objetos en Kotlin, así como el uso de estructuras de datos y persistencia básica con archivos. A través del desarrollo de CELLEXPRESS, se logró construir un sistema funcional que simula una experiencia de compra real, incluyendo la administración de inventario, validaciones, manejo de errores y generación de facturas.

La experiencia fue enriquecedora, ya que no solo se profundizó en el uso del lenguaje Kotlin, sino también en la organización modular del código, buenas prácticas de desarrollo y la interacción con el usuario desde una interfaz de consola.

Como resultado, CELLEXPRESS no solo cumple con los requisitos funcionales del proyecto, sino que también demuestra un enfoque sólido hacia la construcción de software mantenible, escalable y bien documentado.
