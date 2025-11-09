package view

/**
 * Vista para la interacción con el usuario a través de consola.
 *
 * Proporciona funciones de utilidad para mostrar información formateada
 * y separar la lógica de presentación del resto del sistema.
 */
object ConsoleView {

    private const val LINEA_SEPARADORA = "═══════════════════════════════════════════════════════════════"
    private const val LINEA_SIMPLE = "-----------------------------------------------------------"

    /**
     * Muestra un encabezado principal.
     */
    fun mostrarEncabezado(titulo: String) {
        println()
        println(LINEA_SEPARADORA)
        println(titulo.uppercase())
        println(LINEA_SEPARADORA)
        println()
    }

    /**
     * Muestra un subtítulo.
     */
    fun mostrarSubtitulo(subtitulo: String) {
        println()
        println("--- $subtitulo ---")
        println()
    }

    /**
     * Muestra un separador de sección.
     */
    fun mostrarSeparador() {
        println(LINEA_SIMPLE)
    }

    /**
     * Muestra un mensaje de éxito.
     */
    fun mostrarExito(mensaje: String) {
        println("✓ $mensaje")
    }

    /**
     * Muestra un mensaje de error.
     */
    fun mostrarError(mensaje: String) {
        println("✗ ERROR: $mensaje")
    }

    /**
     * Muestra un mensaje de advertencia.
     */
    fun mostrarAdvertencia(mensaje: String) {
        println("⚠ ADVERTENCIA: $mensaje")
    }

    /**
     * Muestra información general.
     */
    fun mostrarInfo(mensaje: String) {
        println("ℹ $mensaje")
    }

    /**
     * Solicita presionar Enter para continuar.
     */
    fun esperarEnter() {
        println()
        println("Presione ENTER para continuar...")
        readLine()
    }

    /**
     * Limpia visualmente la consola agregando líneas en blanco.
     */
    fun limpiarPantalla() {
        repeat(2) { println() }
    }
}
