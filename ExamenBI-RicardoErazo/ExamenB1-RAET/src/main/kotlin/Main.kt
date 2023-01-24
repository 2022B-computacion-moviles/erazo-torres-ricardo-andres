import java.util.*

fun main(args: Array<String>) {
    do {
        var seguimiento = false
        print(
            "*** Aplicaciones Móviles ***" +
                    "\nSistemas operativos y Programas" +
                    "\n1. Programas" +
                    "\n2. Sistemas operativos" +
                    "\n3. Salir\n" +
                    "Elección: "
        )
        var opcionReady = readln().toInt()
        when(opcionReady) {
            (1) -> {
                var aux = false
                var message = "Ingrese un número según la acción que se desea hacer: " +
                        "\n1. Agregar un nuevo programa" +
                        "\n2. Ver lista de programas" +
                        "\n3. Actualizar un programa existente" +
                        "\n4. Eliminar un programa" +
                        "\n5. Atrás\n"+
                        "Elección: "
                while (!aux) {
                    print(message)
                    var escogerCRUD = readln().toInt()
                    when (escogerCRUD) {
                        (1) -> {
                            print("Nombre del nuevo programa: ")
                            var nombre = readln()
                            print("Tipo de programa: ")
                            var tipo = readln()
                            print("Peso: ")
                            var peso = readln().toFloat()
                            print("Fecha de instalación (yyyy-MM-dd): ")
                            var fecha = readln()
                            var fechaSplit = fecha.split("-")
                            var fechaAux: Date = Date(fechaSplit[0].toInt()-1900, fechaSplit[1].toInt() - 1, fechaSplit[2].toInt())
                            var nuevoPrograma = Programa(Programa.getIdPrograma() + 1, nombre, tipo, peso, fechaAux)
                            nuevoPrograma.crearPrograma()
                        }
                        (2) -> {
                            println("\nLista de programas")
                            Programa.buscarPrograma()
                        }
                        (3) -> {
                            print("Ingrese el ID del programa que se desea actualizar: ")
                            var idProgramaBuscado = readln().toInt()
                            Programa.actualizarPrograma(idProgramaBuscado)
                        }
                        (4) -> {
                            print("Ingrese el ID del programa que se desea eliminar: ")
                            var idProgramaBuscado = readln().toInt()
                            Programa.eliminarPrograma(idProgramaBuscado)
                        }
                        (5) -> {
                            aux = true
                        }
                    }
                }
            }
            (2) -> {
                var aux = false
                var message = "Ingrese un número según la acción que se desea hacer: " +
                        "\n1. Agregar un nuevo sistema operativo" +
                        "\n2. Ver lista de sistemas operativos" +
                        "\n3. Actualizar un sistema operativo existente" +
                        "\n4. Eliminar un sistema operativo" +
                        "\n5. Atrás\n"+
                        "Elección: "
                while (!aux) {
                    print(message)
                    var escogerCRUD = readln().toInt()
                    when (escogerCRUD) {
                        (1) -> {
                            print("Nombre del nuevo sistema operativo: ")
                            var nombre = readln()
                            print("Tipo: ")
                            var tipo = readln()
                            print("Versión: ")
                            var version = readln()
                            print("Fecha publicación (yyyy-MM-dd): ")
                            var fecha = readln()
                            var fechaSplit = fecha.split("-")
                            var fechaAux: Date = Date(fechaSplit[0].toInt()-1900, fechaSplit[1].toInt() - 1, fechaSplit[2].toInt())
                            println("\nLista de programas del sistema operativo")
                            Programa.buscarPrograma()
                            print("Seleccione los programas a agregar al sistema operativo seleccionado: ")
                            var stringProgramas = readLine().toString()
                            var splitProgramas = stringProgramas.split(",")
                            var arrayProgramas = splitProgramas.map { it.toInt() }.toTypedArray()
                            var listaProgramas: ArrayList<Programa> = SistemaOp.setArrayListProgramas(arrayProgramas)
                            var nuevoSistemaOp = SistemaOp(SistemaOp.getIdSistemaOp() + 1, nombre, tipo,version, fechaAux, listaProgramas)
                            nuevoSistemaOp.crearSistemaOp(arrayProgramas.size)
                        }
                        (2) -> {
                            println("\nLista de sistemas operativos")
                            SistemaOp.buscarSistemaOp()
                        }
                        (3) -> {
                            print("Ingrese el ID del sistema operativo que se desea actualizar: ")
                            var idSistemaOpBuscado = readln().toInt()
                            SistemaOp.actualizarSistemaOp(idSistemaOpBuscado)
                        }
                        (4) -> {
                            print("Ingrese el ID del sistema operativo que se desea eliminar: ")
                            var idSistemaOpBuscado = readln().toInt()
                            SistemaOp.eliminarPrograma(idSistemaOpBuscado)
                        }
                        (5) -> {
                            aux = true
                        }
                    }
                }
            }
            (3) -> {
                seguimiento = true
                print("\t---Gracias por su visita!---\t")
            }
        }
    } while (!seguimiento)
}