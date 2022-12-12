import java.util.*

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
