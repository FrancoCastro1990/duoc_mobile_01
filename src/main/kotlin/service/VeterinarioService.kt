package service

/**
 * Servicio para gestionar la disponibilidad de veterinarios.
 *
 * Verifica horarios disponibles y gestiona la agenda de atención.
 */
object VeterinarioService {

    /**
     * Rango de horas de atención (9:00 a 17:00)
     */
    private val HORARIO_ATENCION = 9..17

    /**
     * Verifica si un veterinario está disponible en una hora específica.
     *
     * El veterinario está disponible de 9:00 a 17:00 horas.
     *
     * @param hora Hora solicitada (en formato 24 horas)
     * @return true si está disponible, false en caso contrario
     */
    fun estaDisponible(hora: Int): Boolean {
        return hora in HORARIO_ATENCION
    }

    /**
     * Verifica disponibilidad parseando una hora en formato HH:MM
     *
     * @param horaStr Hora en formato "HH:MM"
     * @return true si está disponible, false en caso contrario
     */
    fun estaDisponible(horaStr: String): Boolean {
        val hora = parsearHora(horaStr)
        return hora?.let { estaDisponible(it) } ?: false
    }

    /**
     * Parsea una hora en formato HH:MM y extrae la hora.
     *
     * @param horaStr String con formato "HH:MM"
     * @return Hora parseada o null si el formato es inválido
     */
    fun parsearHora(horaStr: String): Int? {
        return try {
            horaStr.substringBefore(':').toIntOrNull()
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Obtiene los horarios disponibles como string formateado.
     *
     * @return String con los horarios disponibles
     */
    fun obtenerHorariosDisponibles(): String {
        return "Horarios disponibles: 09:00 - 17:00"
    }

    /**
     * Genera un mensaje de disponibilidad según la hora solicitada.
     *
     * @param horaStr Hora solicitada en formato HH:MM
     * @param fechaStr Fecha solicitada en formato DD/MM/YYYY
     * @return Par con (disponible: Boolean, mensaje: String)
     */
    fun verificarDisponibilidad(horaStr: String, fechaStr: String): Pair<Boolean, String> {
        val disponible = estaDisponible(horaStr)

        val mensaje = if (disponible) {
            """
            ✓ CONSULTA CONFIRMADA
            Fecha: $fechaStr
            Hora: $horaStr

            Por favor, llegue 10 minutos antes de su cita.
            Recibirá un correo de confirmación.
            """.trimIndent()
        } else {
            """
            ✗ VETERINARIO NO DISPONIBLE

            Lo sentimos, el veterinario no está disponible en el horario solicitado.
            ${obtenerHorariosDisponibles()}

            Horarios sugeridos:
            - Mañana: 09:00 - 12:00
            - Tarde: 14:00 - 17:00

            Por favor, contacte con recepción para reagendar.
            """.trimIndent()
        }

        return Pair(disponible, mensaje)
    }

    /**
     * Obtiene el estado de la consulta según disponibilidad.
     *
     * @param hora Hora solicitada
     * @return "Confirmada" si está disponible, "Pendiente" si no
     */
    fun obtenerEstadoConsulta(hora: Int): String {
        return if (estaDisponible(hora)) "Confirmada" else "Pendiente"
    }

    /**
     * Obtiene el estado de la consulta según disponibilidad (formato string).
     *
     * @param horaStr Hora en formato HH:MM
     * @return "Confirmada" si está disponible, "Pendiente" si no
     */
    fun obtenerEstadoConsulta(horaStr: String): String {
        return if (estaDisponible(horaStr)) "Confirmada" else "Pendiente"
    }
}
