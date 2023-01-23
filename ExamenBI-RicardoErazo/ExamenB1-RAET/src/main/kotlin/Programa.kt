import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.text.SimpleDateFormat
import java.util.*

public class Programa(
    private var id_programa: Int,
    private var nombre_programa: String,
    private var tipo_programa: String,
    private var peso_programa: Float,
    private var fecha_instalacion_programa: Date
){
    init {
        id_programa
        nombre_programa
        tipo_programa
        peso_programa
        fecha_instalacion_programa
    }

    //setters and getters
    fun setIdPrograma (id_programa: Int){
        this.id_programa = id_programa
    }
    fun setNombrePrograma (nombre_programa: String){
        this.nombre_programa = nombre_programa
    }
    fun setTipoPrograma (tipo_programa: String){
        this.tipo_programa = tipo_programa
    }
    fun setPesoPrograma (peso_programa: Float){
        this.peso_programa = peso_programa
    }
    fun setFechaInstalacionPrograma (fecha_instalacion_programa: Date){
        this.fecha_instalacion_programa = fecha_instalacion_programa
    }

    fun getIdPrograma(): Int{
        return id_programa
    }
    fun getNombrePrograma() : String {
        return nombre_programa
    }
    fun getTipoPrograma(): String {
        return tipo_programa
    }
    fun getPesoPrograma(): Float{
        return peso_programa
    }
    fun getFechaInstalacionPrograma(): Date{
        return fecha_instalacion_programa
    }
    //función para crear (CREATE) al programa dentro del archivo
    fun crearPrograma(){
        //agregar un nuevo programa en el archivo
        var directorio = Paths.get("src/main/resources/programas.txt")
        val formato = SimpleDateFormat("yyyy-MM-dd")
        var data =
            this.id_programa.toString() +
                    "," + this.nombre_programa +
                    "," + this.tipo_programa +
                    "," + this.peso_programa +
                    "," + formato.format(this.fecha_instalacion_programa) +
                    "\n"
        try {
            Files.write(directorio, data.toByteArray(), StandardOpenOption.APPEND)
            println("\t---Programa agregado con éxito!---\t")
        } catch (e: IOException) {
            println("\t---Error al ingresar el elemento---\t")
        }
    }
    //compartir objetos
    companion object{
        //CRUD
        //función para buscar (READ) al programa dentro del archivo
        fun buscarPrograma(){
            var directorio = Paths.get("src/main/resources/programas.txt")
            Files.lines(directorio, Charsets.UTF_8).forEach {
                var arraySalida = it.split(",")
                println(
                    " * ID programa: " + arraySalida[0] + "\n\t"
                            + "Nombre programa: " + arraySalida[1] + "\n\t"
                            + "Tipo de programa: " + arraySalida[2] + "\n\t"
                            + "Peso requerido: " + arraySalida[3] + "\n\t"
                            + "Fecha de instalación: " + arraySalida[4] + "\n"
                )
            }
        }
        //función para actualizar (UPDATE) un programa dentro del archivo
        fun actualizarPrograma(id_programa: Int) {
            //Leer archivo para encontrar el programa
            var directorio = Paths.get("src/main/resources/programas.txt")
            var archivoActualizado = ""
            var bandera = false
            Files.lines(directorio, Charsets.UTF_8).forEach {
                var arraySalida = it.split(",").toMutableList()
                if (arraySalida[0] == id_programa.toString()) {
                    var actualizar = true
                    println(
                        "- ID programa: " + arraySalida[0] + "\t\n\t"
                                + " 1. Nombre del programa: " + arraySalida[1] + "\n\t"
                                + " 2. Tipo de programa: " + arraySalida[2] + "\n\t"
                                + " 3. Peso requerido: " + arraySalida[3] + "\n\t"
                                + " 4. Fecha de instalación: " + arraySalida[4] + "\n"
                    )
                    //Escoger los atributos a ser actualizados
                    var arrayCadena = arrayOf<String>("0", "0", "0", "0")
                    do {
                        println("Escoger el atributo que se desea actualizar")
                        var atributo = readln().toInt()
                        when (atributo) {
                            (1) -> {
                                print("Ingrese el nuevo nombre del programa: ")
                                var nombre = readln()
                                arrayCadena.set(0, nombre)
                            }
                            (2) -> {
                                print("Ingrese el nuevo tipo de programa: ")
                                var tipo = readln()
                                arrayCadena.set(1, tipo)
                            }
                            (3) -> {
                                print("Ingrese el nuevo peso requerido: ")
                                var peso = readln()
                                arrayCadena.set(2, peso)
                            }
                            (4) -> {
                                print("Ingrese la nueva fecha de instalación (yyyy-MM-dd): ")
                                var fecha = readln()
                                var auxFecha = fecha.split("-")
                                var newFecha: Date =
                                    Date(auxFecha[0].toInt() - 1900, auxFecha[1].toInt() - 1, auxFecha[2].toInt())
                                val formato = SimpleDateFormat("yyyy-MM-dd")
                                arrayCadena.set(3, formato.format(newFecha))
                            }
                        }
                        //Mostrar una nueva visión para seguir actualizando otro dato del programa
                        for (i in 0..arrayCadena.size - 1){
                            if (arrayCadena[i] != "0"){
                                arraySalida[i+1] = arrayCadena[i]
                            }
                        }
                        println(
                            " - ID programa: " + arraySalida[0] + "\t\n\t"
                                    + " 1. Nombre: " + arraySalida[1] + "\n\t"
                                    + " 2. Tipo: " + arraySalida[2] + "\n\t"
                                    + " 3. Peso requerido: " + arraySalida[3] + "\n\t"
                                    + " 4. Fecha de instalación: " + arraySalida[4] + "\n"
                        )
                        print("\t---¿Seguir actualizando?---\t \n \t1. Si \n \t2. No \nElección: ")
                        var auxOpcion = readln().toInt()
                        if (auxOpcion == 2) {
                            actualizar = false //Terminar la actualización del elemento
                            for (i in 0..arrayCadena.size - 1) {
                                if (arrayCadena[i] == "0") {
                                    arrayCadena[i] = arraySalida[i + 1]
                                }
                            }
                            archivoActualizado += arraySalida[0] +
                                                "," + arrayCadena[0] +
                                                "," + arrayCadena[1] +
                                                "," + arrayCadena[2] +
                                                "," + arrayCadena[3] + "\n"


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
                File("src/main/resources/programas.txt").printWriter().use {
                        out -> out.print(archivoActualizado)
                }
            }
            println("\t---Programa actualizado---\t")
        }
        //función para eliminar (DELETE) al programa dentro del archivo
        fun eliminarPrograma(id_programa: Int) {
            //Busca en el archivo el programa a ser eliminado
            var directorio = Paths.get("src/main/resources/programas.txt")
            var bandera = false
            var archivoAux = ""
            Files.lines(directorio, Charsets.UTF_8).forEach {
                var arraySalida = it.split(",")
                if (arraySalida[0] == id_programa.toString()) {
                    println("")
                    bandera = true
                } else {
                    archivoAux += it + "\n"
                }
            }
            if (!bandera) {
                println("\t---El programa no existe---\t")
            } else {
                File("src/main/resources/programas.txt").printWriter().use {
                        out -> out.print(archivoAux)
                }
            }
        }
        //obtener el id
        fun getIdPrograma(): Int {
            var directorio = Paths.get("src/main/resources/programas.txt")
            var numeroTotal = 0
            Files.lines(directorio, Charsets.UTF_8).forEach {
                numeroTotal += 1
            }
            return numeroTotal
        }
    }
}