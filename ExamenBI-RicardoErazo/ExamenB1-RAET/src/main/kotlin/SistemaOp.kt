import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.text.SimpleDateFormat
import java.util.*

class SistemaOp (
    private var id_sistema_op: Int,
    private var nombre_sistema_op: String,
    private var tipo_sistema_op: String,
    private var version_sistema_op: String,
    private var fecha_lanzamiento_sistema_op: Date,
    private var programas_sistema_op: ArrayList<Programa>
) {
    init {
        id_sistema_op
        nombre_sistema_op
        tipo_sistema_op
        version_sistema_op
        fecha_lanzamiento_sistema_op
        programas_sistema_op
    }
    //getters and setters
    fun setIdSistemaOp(id_sistema_op: Int){
        this.id_sistema_op = id_sistema_op
    }
    fun setNombreSistemaOp(nombre_sistema_op: String){
        this.nombre_sistema_op = nombre_sistema_op
    }
    fun setFechaLanzamientoSistemaOp(fecha_lanzamiento_sistema_op: Date){
        this.fecha_lanzamiento_sistema_op = fecha_lanzamiento_sistema_op
    }
    fun setTipoSistemaOp(tipo_sistema_op: String){
        this.tipo_sistema_op = tipo_sistema_op
    }
    fun setVersionSistemaOp(version_sistema_op: String){
        this.version_sistema_op = version_sistema_op
    }

    fun getIdSistemaOp(): Int {
        return id_sistema_op
    }
    fun getNombreSistemaOp(): String {
        return nombre_sistema_op
    }
    fun getFechaLanzamientoSistemaOp(): Date {
        return fecha_lanzamiento_sistema_op
    }
    fun getTipoSistemaOp(): String {
        return tipo_sistema_op
    }
    fun getVersionSistemaOp(): String {
        return version_sistema_op
    }

    //función para crear (CREATE) al Sistema operativo dentro del archivo
    fun crearSistemaOp(sizeArray: Int){
        //agregar un nuevo Sistema operativo en el archivo
        var directorio = Paths.get("src/main/resources/sistemasop.txt")
        val formato = SimpleDateFormat("yyyy-MM-dd")
        var data =
            this.id_sistema_op.toString() +
                    "," + this.nombre_sistema_op +
                    "," + this.tipo_sistema_op +
                    "," + this.version_sistema_op +
                    "," + formato.format(this.fecha_lanzamiento_sistema_op) +
                    ","
        var contador = 1
        for (item in this.programas_sistema_op!!){
            if(contador < sizeArray){
                data += item.getIdPrograma().toString() + ","
            } else {
                data += item.getIdPrograma().toString()
            }
            contador++
        }
        data += "\n"
        try {
            Files.write(directorio, data.toByteArray(), StandardOpenOption.APPEND)
            println("\t---Sistema operativo agregado con éxito!---\t")
        } catch (e: IOException) {
            println("\t---Error al ingresar el elemento---\t")
        }
    }
    //compartir objetos
    companion object{
        //CRUD
        //función para buscar (READ) al Sistema operativo dentro del archivo
        fun buscarSistemaOp(){
            var directorio = Paths.get("src/main/resources/sistemasop.txt")
            Files.lines(directorio, Charsets.UTF_8).forEach {
                var arraySalida = it.split(",")
                println(
                    " * ID sistema operativo: " + arraySalida[0] + "\n\t"
                            + "Nombre: " + arraySalida[1] + "\n\t"
                            + "Tipo: " + arraySalida[2] + "\n\t"
                            + "Versión: " + arraySalida[3] + "\n\t"
                            + "Fecha de lanzamiento: " + arraySalida[4] + "\n"
                )
                var directorio2 = Paths.get("src/main/resources/programas.txt")
                println("\tProgramas que posee")
                Files.lines(directorio2, Charsets.UTF_8).forEach {
                    var arrayProgramas = it.split(",")
                    var id_programa = arrayProgramas[0]
                    for (i in 5 .. arraySalida.size -1){
                        if (id_programa == arraySalida[i]){
                            println(
                                "\t - Nombre del programa: " + arrayProgramas[1] + "\n\t\t"
                                        + "Tipo: " + arrayProgramas[2] + "\n\t\t"
                                        + "Peso requerido: " + arrayProgramas[3] + "\n\t\t"
                                        + "Fecha de instalación: " + arrayProgramas[4] + "\n"
                            )
                        }
                    }
                }
            }
            println("")
        }
        //función para actualizar (UPDATE) un Sistema operativo dentro del archivo
        fun actualizarSistemaOp(id_sistema_op: Int) {
            //Leer archivo para encontrar el sistema operativo
            var directorio = Paths.get("src/main/resources/sistemasop.txt")
            var archivoActualizado = ""
            var bandera = false
            Files.lines(directorio, Charsets.UTF_8).forEach {
                var arraySalida = it.split(",").toMutableList()
                if (arraySalida[0] == id_sistema_op.toString()) {
                    var actualizar = true
                    println(
                        "- ID Sistema operativo: " + arraySalida[0] + "\t\n\t"
                                + " 1. Nombre: " + arraySalida[1] + "\n\t"
                                + " 2. Tipo: " + arraySalida[2] + "\n\t"
                                + " 3. Versión: " + arraySalida[3] + "\n\t"
                                + " 4. Fecha de lanzamiento: " + arraySalida[4]
                    )
                    var directorio2 = Paths.get("src/main/resources/programas.txt")
                    println("\t 5. Agregar o eliminar un programa del sistema")
                    println("\t  Programas que posee")
                    Files.lines(directorio2, Charsets.UTF_8).forEach {
                        var arrayProgramas = it.split(",")
                        var id_programa = arrayProgramas[0]
                        for (i in 5 .. arraySalida.size -1){
                            if (id_programa == arraySalida[i]){
                                println(
                                    "\t - Nombre del programa: " + arrayProgramas[1] + "\n\t\t\t"
                                            + "Tipo: " + arrayProgramas[2] + "\n\t\t\t"
                                            + "Peso requerido: " + arrayProgramas[3] + "\n\t\t\t"
                                            + "Fecha de instalación: " + arrayProgramas[4] + "\n"
                                )
                            }
                        }
                    }
                    //Escoger los atributos a ser actualizados
                    var arrayCadena = arrayOf<String>("0", "0", "0", "0", "0")
                    do {
                        print("Escoger el atributo que se desea actualizar: ")
                        var atributo = readln().toInt()
                        when (atributo) {
                            (1) -> {
                                print("Ingrese el nuevo nombre del sistema operativo: ")
                                var nombre = readln()
                                arrayCadena.set(0, nombre)
                            }
                            (2) -> {
                                print("Ingrese el nuevo tipo de sistema operativo: ")
                                var tipo = readln()
                                arrayCadena.set(1, tipo)
                            }
                            (3) -> {
                                print("Ingrese la nueva versión del sistema operativo: ")
                                var peso = readln()
                                arrayCadena.set(2, peso)
                            }
                            (4) -> {
                                print("Ingrese la nueva fecha de lanzamiento (yyyy-MM-dd): ")
                                var fecha = readln()
                                var auxFecha = fecha.split("-")
                                var newFecha: Date =
                                    Date(auxFecha[0].toInt() - 1900, auxFecha[1].toInt() - 1, auxFecha[2].toInt())
                                val formato = SimpleDateFormat("yyyy-MM-dd")
                                arrayCadena.set(3, formato.format(newFecha))
                            }
                            (5) -> {
                                print("Opciones sobre el programa:\n" +
                                        "\t 1. Agregar un programa al sistema operativo" +
                                        "\n\t 2. Eliminar un programa del sistema operativo " +
                                        "\n Elección: ")
                                var opcionLista = readln().toInt()
                                if (opcionLista == 1) {
                                    println("- Programas disponibles son:")
                                    Programa.buscarPrograma()
                                    print("Seleccione los programas que se desea agregar al sistema operativo separándolos con comas(,): ")
                                    var nuevosProgramas = readln()
                                    arrayCadena.set(4, actualizarListaProgramas(nuevosProgramas, arraySalida[0].toInt()))
                                } else {
                                    print("Seleccione los programas que se desea eliminar del sistema operativo separándolos con comas(,): ")
                                    var listaEliminar = readln()
                                    var auxLista = eliminarDeListaProgramas(listaEliminar, arraySalida[0].toInt())
                                    arrayCadena.set(4, auxLista)
                                }
                            }
                        }
                        print("\t---¿Seguir actualizando?---\t \n \t1. Si \t\n 2. No \nElección: ")
                        var auxOpcion = readln().toInt()
                        if (auxOpcion == 2) {
                            actualizar = false //Terminar la actualización del elemento
                            for (i in 0..arrayCadena.size - 1) {
                                if (arrayCadena[i] == "0") {
                                    if (i == 4) { // Tomando lista original de programas
                                        for (j in 5..arraySalida.size - 1) {
                                            if (j == arraySalida.size - 1) {
                                                arrayCadena[i] += arraySalida[j]
                                            } else {
                                                arrayCadena[i] += arraySalida[j] + ","
                                            }
                                        }
                                    } else {
                                        arrayCadena[i] = arraySalida[i + 1]
                                    }
                                }
                            }
                            archivoActualizado += arraySalida[0] +
                                    "," + arrayCadena[0] +
                                    "," + arrayCadena[1] +
                                    "," + arrayCadena[2] +
                                    "," + arrayCadena[3] +
                                    "," + arrayCadena[4] + "\n"
                        }
                    } while (actualizar == true)
                    bandera = true
                } else {
                    archivoActualizado += it + "\n"
                }
            }
            if (!bandera) {
                println("\t---El programa no existe---\t")
            } else {
                File("src/main/resources/sistemasop.txt").printWriter().use {
                        out -> out.print(archivoActualizado)
                }
            }
            println("\t---Sistema operativo actualizado---\t")
        }
        //función para eliminar (DELETE) al Sistema operativo dentro del archivo
        fun eliminarPrograma(id_programa: Int) {
            //Busca en el archivo el elemento a ser eliminado
            var directorio = Paths.get("src/main/resources/sistemasop.txt")
            var bandera = false
            var archivoAux = ""
            Files.lines(directorio, Charsets.UTF_8).forEach {
                var arraySalida = it.split(",")
                if (arraySalida[0] == id_programa.toString()) {
                    bandera = true
                    println("\t---El sistema operativo fue eliminado con éxito---\t")
                } else {
                    archivoAux += it + "\n"
                }
            }
            if (!bandera) {
                println("\t---El sistema operativo no existe---\t")
            } else {
                File("src/main/resources/sistemasop.txt").printWriter().use {
                        out -> out.print(archivoAux)
                }
            }
        }

        fun actualizarListaProgramas(lista: String, id: Int): String {
            var nuevaLista = ""
            var directorio = Paths.get("src/main/resources/sistemasop.txt")
            Files.lines(directorio, Charsets.UTF_8).forEach {
                var arraySalida = it.split(",")
                if (arraySalida[0].toInt() == id) {
                    for (i in 5..arraySalida.size - 1) {
                        if (i == arraySalida.size - 1) {
                            nuevaLista += arraySalida[i]
                        } else {
                            nuevaLista += arraySalida[i] + ","
                        }
                    }
                }
            }
            return nuevaLista + "," + lista
        }

        fun eliminarDeListaProgramas(lista: String, id: Int): String {
            var nuevaLista = ""
            var directorio = Paths.get("src/main/resources/sistemasop.txt")
            var splitListaParam = lista.split(",")
            Files.lines(directorio, Charsets.UTF_8).forEach {
                var arraySalida = it.split(",")
                if (arraySalida[0].toInt() == id) {
                    for (i in 5..arraySalida.size - 1) {
                        var bandera = false
                        for (j in 0..splitListaParam.size - 1) {
                            if (arraySalida[i] != splitListaParam[j]) {
                                bandera = true
                            } else {
                                bandera = false
                                break
                            }
                        }
                        if (bandera == true) {
                            nuevaLista += arraySalida[i] + ","
                        }
                    }
                }
            }
            return removeLastNchars(nuevaLista, 1)
        }

        fun removeLastNchars(str: String, n: Int): String {
            return str.substring(0, str.length - n)
        }

        fun setArrayListProgramas(arrayProgramas: Array<Int>): ArrayList<Programa> {
            var directorio = Paths.get("src/main/resources/programas.txt")
            var listaProgramas: ArrayList<Programa> = ArrayList()
            var i = 0
            Files.lines(directorio, Charsets.UTF_8).forEach {
                var stringSplit = it.split(",")
                if (i < arrayProgramas.size) {
                    if (stringSplit[0] == arrayProgramas[i].toString()) {
                        var splitFecha = stringSplit[4].split("-")
                        var aux = Programa(
                            stringSplit[0].toInt(),
                            stringSplit[1],
                            stringSplit[2],
                            stringSplit[3].toFloat(),
                            Date(splitFecha[0].toInt(), splitFecha[1].toInt(), splitFecha[2].toInt())
                        )
                        listaProgramas.add(aux)
                        i++
                    }
                }
            }
            return listaProgramas
        }
        fun getIdSistemaOp(): Int {
            var directorio = Paths.get("src/main/resources/sistemasop.txt")
            var numeroTotal = 0
            Files.lines(directorio, Charsets.UTF_8).forEach {
                numeroTotal += 1
            }
            return numeroTotal
        }

    }

}