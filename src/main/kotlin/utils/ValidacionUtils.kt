package utils

/**
 * Utilidades para validación de datos usando expresiones regulares (Regex).
 *
 * Proporciona funciones para validar y formatear correos electrónicos y números telefónicos
 * siguiendo estándares de formato específicos.
 */
object ValidacionUtils {

    /**
     * Regex para validar formato de correo electrónico estándar (nombre@dominio.com)
     */
    private val EMAIL_REGEX = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")

    /**
     * Regex para extraer dígitos de un número telefónico
     */
    private val PHONE_DIGITS_REGEX = Regex("\\d+")

    /**
     * Valida si un correo electrónico cumple con el formato estándar.
     *
     * Formato esperado: nombre@dominio.com
     * - Permite letras, números, puntos, guiones bajos, porcentajes, signos más y guiones en el nombre
     * - El dominio debe tener al menos un punto
     * - La extensión debe tener al menos 2 caracteres
     *
     * @param email El correo electrónico a validar
     * @return true si el formato es válido, false en caso contrario
     */
    fun validarEmail(email: String): Boolean {
        return EMAIL_REGEX.matches(email)
    }

    /**
     * Solicita un correo electrónico al usuario y lo valida hasta que sea correcto.
     *
     * @param prompt Mensaje a mostrar al usuario
     * @return El correo electrónico validado
     */
    fun solicitarEmailValido(prompt: String = "Ingrese email"): String {
        while (true) {
            println(prompt)
            val email = readLine()?.trim() ?: ""

            if (validarEmail(email)) {
                return email
            } else {
                println("  ⚠ ERROR: Formato de email incorrecto.")
                println("  Formato esperado: nombre@dominio.com")
                println("  Por favor, ingréselo nuevamente.")
                println()
            }
        }
    }

    /**
     * Formatea un número telefónico al estilo uniforme: +XX (XXX) XXX-XXXX
     *
     * Extrae todos los dígitos del input y los formatea al patrón especificado.
     * Si el número no tiene suficientes dígitos, devuelve el original.
     *
     * @param telefono Número telefónico en cualquier formato
     * @return Número formateado o el original si no se puede formatear
     */
    fun formatearTelefono(telefono: String): String {
        // Extraer solo dígitos
        val digitos = PHONE_DIGITS_REGEX.findAll(telefono)
            .map { it.value }
            .joinToString("")

        // Verificar que haya suficientes dígitos (al menos 8)
        if (digitos.length < 8) {
            return telefono
        }

        // Formatear: +XX (XXX) XXX-XXXX
        return when {
            digitos.length >= 11 -> {
                // Formato internacional completo: +XX (XXX) XXX-XXXX
                val codigo = digitos.substring(0, 2)
                val area = digitos.substring(2, 5)
                val parte1 = digitos.substring(5, 8)
                val parte2 = digitos.substring(8, 12.coerceAtMost(digitos.length))
                "+$codigo ($area) $parte1-$parte2"
            }
            digitos.length == 10 -> {
                // Formato con código país: +56 (XXX) XXX-XXXX
                val area = digitos.substring(0, 3)
                val parte1 = digitos.substring(3, 6)
                val parte2 = digitos.substring(6, 10)
                "+56 ($area) $parte1-$parte2"
            }
            digitos.length == 9 -> {
                // Formato chileno 9 dígitos: +56 (9XX) XXX-XXX
                val area = digitos.substring(0, 3)
                val parte1 = digitos.substring(3, 6)
                val parte2 = digitos.substring(6, 9)
                "+56 ($area) $parte1-$parte2"
            }
            digitos.length == 8 -> {
                // Formato chileno 8 dígitos: +56 (XX) XXX-XXX
                val area = digitos.substring(0, 2)
                val parte1 = digitos.substring(2, 5)
                val parte2 = digitos.substring(5, 8)
                "+56 ($area) $parte1-$parte2"
            }
            else -> telefono
        }
    }

    /**
     * Solicita un número telefónico y lo formatea automáticamente.
     *
     * Valida que el input contenga al menos 8 dígitos antes de formatearlo.
     * Si no es válido, muestra un error y solicita nuevamente.
     *
     * @param prompt Mensaje a mostrar al usuario
     * @return Número telefónico formateado
     */
    fun solicitarTelefonoFormateado(prompt: String = "Ingrese teléfono"): String {
        while (true) {
            println(prompt)
            val telefono = readLine()?.trim() ?: ""

            // Extraer dígitos para validar
            val digitos = PHONE_DIGITS_REGEX.findAll(telefono)
                .map { it.value }
                .joinToString("")

            // Validar que tenga al menos 8 dígitos
            if (digitos.length < 8) {
                println("  ⚠ ERROR: Debe ingresar un número telefónico válido.")
                println("  El teléfono debe contener al menos 8 dígitos.")
                println("  Por favor, ingréselo nuevamente.")
                println()
                continue
            }

            // Formatear el teléfono
            val formateado = formatearTelefono(telefono)

            if (formateado != telefono) {
                println("  ✓ Teléfono formateado: $formateado")
            }

            return formateado
        }
    }
}
