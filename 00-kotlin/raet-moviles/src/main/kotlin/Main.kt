import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    println("Hola mundo");
    //Tipos de variables

    //Inmutables (No se pueden reasignar)
    val inmutable: String = "Vicente";
    //inmutable = "Adrian";

    //Mutables (Se pueden reasignar)
    var mutable: String = "Vicente";
    mutable = "Adrian";

    //val > var
    println( inmutable > mutable)

    //Duck Typing
    // entiende las variables directamente por como estas son declaradas.
    val ejemploVariable = "Ejemplo"
    ejemploVariable.trim()
    val edadEjemplo: Int = 12

    //Variables primitivas
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'S'
    val mayorEdad: Boolean = true
    //Clases
    val fechaNacimiento: Date = Date() //no se usa "new" en clases

    if(true){

    }else{

    }

    //Switch no existe
    val estadoCivilWhen = 'A'
    when (estadoCivilWhen){
        ('S')->{
            println("Soltero")
        }
        'C' -> println("Casado")
        else -> println("Esperando el partido de Argentina")
    }
    val estadoEmelecWhen = 'S'
    val coqueta = if(estadoEmelecWhen == 'S') "5-0 te acuerdas y te duele!" else "0-0"
    //println(coqueta)

    //llamada a las clases
    val sumaUno = Suma(1,2)
    val sumaDos = Suma(1, null)
    val sumaTres = Suma(null, 2)
    val sumaCuatro = Suma(null, null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.historialSumas)

    //Arreglos
    //Tipos de arreglos
        //Arreglo Estático
    val arregloEstatico: Array<Int> = arrayOf(1, 2, 3)
    println(arregloEstatico)

        //Arreglo Dinámico
    val arregloDinamico: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    //OPERADORES -> Sirven para los arreglos estáticos y dináminos
    //FOR EACH -> Unit
    //Iterar un arreglo

    val respuestaForEach: Unit = arregloDinamico
        .forEach { valorActual: Int ->
            println("Valor actual: ${valorActual}")
        }
    arregloEstatico
        .forEachIndexed { indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }

    //MAP -> Muta el arreglo (Cambia el arreglo)
    // 1) Enviamos el nuevo valor de la iteración
    // 2) Nos devuelve es un NUEVO ARREGLO con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico
        .map {valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }
    println(respuestaMap)

    val respuestaMapDos = arregloDinamico.map { it + 15}
    // .map{ valorActual: Int ->
    //      return@map valorActual +15
    //  }
    println(respuestaMapDos)

    //Filter -> Filtrar el arreglo
    // 1) Devolver una expresion (TRUE o FALSE)
    // 2) Nuevo arreglo filtrado
    val respuestaFilter: List<Int> = arregloDinamico
        .filter {
            valorActual: Int ->
            val mayoresACinco: Boolean = valorActual > 5 // Expresion condicion
            return@filter mayoresACinco
        }

    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(arregloDinamico)
    println(respuestaFilter)
    println(respuestaFilterDos)

    //OR AND
    // OR -> ANY (ALGUNO CUMPLE?)
    // AND -> ALL (TODOS CUMPLEN?)
    val respuestaAny: Boolean = arregloDinamico
        .any{ valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) //true

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll) //false

    //REDUCE -> Valor acumulado
    // Valor acumulado = 0 (Siempre 0 en lenguaje Kotlin)
    // [1, 2, 3, 4, 5] -> Sumeme todos los valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion 1
    // valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion 2
    // valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion 3
    // valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion 4
    // valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteracion 5

    val respuestaReduce: Int = arregloDinamico
        .reduce { //acumulado = 0 -> SIEMPRE EMPIEZA EN 0
            acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual) // -> Lógica negocio
        }
    println(respuestaReduce)

}

//funciones
fun imprimirNombre(nombre: String): Unit{ //Unit == void
    println("Nombre: ${nombre}")
}
fun calcularSueldo(
    sueldo: Double, //requerido
    tasa: Double = 12.00, //opcional por defecto
    bonoEspecial: Double? = null //opcional nulo
): Double{
    //String -> String?
    //Int -> Int?
    //Date -> Date?
    if(bonoEspecial != null){
        return sueldo * tasa * bonoEspecial
    }
    return sueldo * tasa
}

//Clases
abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int
    constructor(
        uno: Int, //parametro
        dos: Int //parametro
    ){
        this.numeroUno = uno;
        this.numeroDos = dos;
        println("Iniciando")
    }
}

abstract class Numeros( //Constructor Primario
    //uno: Int, //Parametro
    //publix var uno: Int // Propiedad de la clase publica
    protected  val numeroUno: Int, //Propiedad
    protected val numeroDos: Int //Propiedad
){
    init{ //bloque codigo constructor primario
        //this. numeroDos // "this" es opcional
        numeroUno
        numeroDos
        println("Iniciando")
    }
}

class Suma( //Constructor Primario Suma
    uno: Int, //Parametro
    dos: Int //Parametro
): Numeros( //Heredamos de la clase números
    //Super constructor números
    uno,
    dos
){
    init { //Bloque constructor primario
        this.numeroUno
        this.numeroDos
    }
    constructor( //Segundo Constructor
        uno: Int?, //Parametro
        dos: Int //Parametro
    ): this(
        if (uno == null) 0 else uno,
        dos
    )
    constructor( //Tercer Constructor
        uno: Int, //Parametro
        dos: Int? //Parametro
    ): this(
        uno,
        if (dos == null) 0 else dos
    )
    constructor( //Cuarto Constructor
        uno: Int?, //Parametro
        dos: Int? //Parametro
    ): this(
        if (uno == null) 0 else uno,
        if (dos == null) 0 else dos
    )

    fun sumar(): Int{
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }

    companion object {
        val pi = 3.14 // Suma.pi -> 3.14
        val historialSumas = arrayListOf<Int>() //Suma.historialSumas
        fun agregarHistorial(valorNuevaSuma: Int){ //Suma. agregarHistorial()
            historialSumas.add(valorNuevaSuma)
        }
    }
}
