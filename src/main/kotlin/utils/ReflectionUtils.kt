package utils

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.memberFunctions

/**
 * Utilidades para trabajar con Reflection en Kotlin.
 *
 * Permite analizar dinámicamente las clases, sus propiedades y métodos,
 * facilitando el análisis de entidades del sistema en tiempo de ejecución.
 */
object ReflectionUtils {

    /**
     * Analiza una clase y retorna información detallada sobre sus componentes.
     *
     * Lista todas las propiedades y métodos de la clase usando reflection,
     * lo que permite inspeccionar dinámicamente la estructura de cualquier entidad.
     *
     * @param clase La clase a analizar (KClass)
     * @return String formateado con el análisis completo de la clase
     */
    fun analizarClase(clase: KClass<*>): String {
        val resultado = StringBuilder()

        resultado.appendLine("═══════════════════════════════════════")
        resultado.appendLine("ANÁLISIS DE CLASE: ${clase.simpleName}")
        resultado.appendLine("═══════════════════════════════════════")
        resultado.appendLine()

        // Información general
        resultado.appendLine("Nombre completo: ${clase.qualifiedName ?: "N/A"}")
        resultado.appendLine()

        // Listar propiedades
        resultado.appendLine("--- PROPIEDADES (${clase.memberProperties.size}) ---")
        if (clase.memberProperties.isEmpty()) {
            resultado.appendLine("  (ninguna)")
        } else {
            clase.memberProperties.forEachIndexed { index, prop ->
                resultado.appendLine("  ${index + 1}. ${prop.name}")
                resultado.appendLine("     Tipo: ${prop.returnType}")
            }
        }
        resultado.appendLine()

        // Listar métodos
        resultado.appendLine("--- MÉTODOS (${clase.memberFunctions.size}) ---")
        if (clase.memberFunctions.isEmpty()) {
            resultado.appendLine("  (ninguno)")
        } else {
            clase.memberFunctions.forEachIndexed { index, func ->
                val parametros = func.parameters
                    .drop(1) // Omitir el receiver (this)
                    .joinToString(", ") { "${it.name}: ${it.type}" }

                resultado.appendLine("  ${index + 1}. ${func.name}($parametros)")
                resultado.appendLine("     Retorna: ${func.returnType}")
            }
        }
        resultado.appendLine()
        resultado.appendLine("═══════════════════════════════════════")

        return resultado.toString()
    }

    /**
     * Analiza e imprime información de una clase directamente en consola.
     *
     * @param clase La clase a analizar
     */
    fun mostrarAnalisisClase(clase: KClass<*>) {
        println(analizarClase(clase))
    }

    /**
     * Analiza e imprime información de múltiples clases.
     *
     * @param clases Lista de clases a analizar
     */
    fun mostrarAnalisisClases(vararg clases: KClass<*>) {
        clases.forEach { clase ->
            mostrarAnalisisClase(clase)
            println()
        }
    }

    /**
     * Verifica si una clase tiene una propiedad específica.
     *
     * @param clase La clase a verificar
     * @param nombrePropiedad Nombre de la propiedad a buscar
     * @return true si la clase tiene la propiedad, false en caso contrario
     */
    fun tienePropiedad(clase: KClass<*>, nombrePropiedad: String): Boolean {
        return clase.memberProperties.any { it.name == nombrePropiedad }
    }

    /**
     * Verifica si una clase tiene un método específico.
     *
     * @param clase La clase a verificar
     * @param nombreMetodo Nombre del método a buscar
     * @return true si la clase tiene el método, false en caso contrario
     */
    fun tieneMetodo(clase: KClass<*>, nombreMetodo: String): Boolean {
        return clase.memberFunctions.any { it.name == nombreMetodo }
    }

    /**
     * Obtiene los nombres de todas las propiedades de una clase.
     *
     * @param clase La clase a analizar
     * @return Lista con los nombres de las propiedades
     */
    fun obtenerNombresPropiedades(clase: KClass<*>): List<String> {
        return clase.memberProperties.map { it.name }
    }

    /**
     * Obtiene los nombres de todos los métodos de una clase.
     *
     * @param clase La clase a analizar
     * @return Lista con los nombres de los métodos
     */
    fun obtenerNombresMetodos(clase: KClass<*>): List<String> {
        return clase.memberFunctions.map { it.name }
    }
}
