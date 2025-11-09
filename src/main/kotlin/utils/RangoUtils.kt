package utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

/**
 * Utilidades para trabajar con rangos (Ranges) en validaciones.
 *
 * Proporciona funciones para verificar rangos de fechas (periodo promocional)
 * y validar cantidades dentro de rangos permitidos.
 */
object RangoUtils {

    /**
     * Rango de cantidades permitidas para productos
     */
    private val RANGO_CANTIDADES = 1..100

    /**
     * Descuento aplicable durante periodo promocional (15%)
     */
    const val DESCUENTO_PROMOCIONAL = 0.15

    /**
     * Periodo promocional predeterminado (ejemplo: todo diciembre 2024)
     */
    private val FECHA_INICIO_PROMO = LocalDate.of(2024, 12, 1)
    private val FECHA_FIN_PROMO = LocalDate.of(2024, 12, 31)

    /**
     * Verifica si una fecha está dentro del periodo promocional.
     *
     * @param fecha La fecha a verificar
     * @param inicio Fecha de inicio del periodo (opcional, usa predeterminado)
     * @param fin Fecha de fin del periodo (opcional, usa predeterminado)
     * @return true si la fecha está en el rango promocional
     */
    fun estaEnPeriodoPromocional(
        fecha: LocalDate,
        inicio: LocalDate = FECHA_INICIO_PROMO,
        fin: LocalDate = FECHA_FIN_PROMO
    ): Boolean {
        return fecha in inicio..fin
    }

    /**
     * Calcula el descuento a aplicar si la fecha está en periodo promocional.
     *
     * @param fecha La fecha de la consulta/pedido
     * @return El porcentaje de descuento (0.0 si no aplica, 0.15 si aplica)
     */
    fun calcularDescuentoPromocional(fecha: LocalDate): Double {
        return if (estaEnPeriodoPromocional(fecha)) {
            DESCUENTO_PROMOCIONAL
        } else {
            0.0
        }
    }

    /**
     * Aplica descuento promocional a un monto si la fecha está en el periodo.
     *
     * @param monto Monto original
     * @param fecha Fecha de la transacción
     * @return Par con (montoFinal, descuentoAplicado)
     */
    fun aplicarDescuentoSiFechaPromocional(monto: Double, fecha: LocalDate): Pair<Double, Double> {
        val descuento = calcularDescuentoPromocional(fecha)
        val montoFinal = monto * (1 - descuento)
        return Pair(montoFinal, descuento)
    }

    /**
     * Valida si una cantidad está dentro del rango permitido (1-100).
     *
     * @param cantidad La cantidad a validar
     * @return true si está en el rango, false en caso contrario
     */
    fun validarCantidad(cantidad: Int): Boolean {
        return cantidad in RANGO_CANTIDADES
    }

    /**
     * Valida y solicita una cantidad hasta que esté en el rango permitido.
     *
     * @param prompt Mensaje a mostrar
     * @return Cantidad validada
     */
    fun solicitarCantidadValida(prompt: String = "Ingrese cantidad (1-100)"): Int {
        while (true) {
            println(prompt)
            val input = readLine()?.trim() ?: ""
            val cantidad = input.toIntOrNull()

            when {
                cantidad == null -> {
                    println("  ⚠ ERROR: Debe ingresar un número válido.")
                }
                !validarCantidad(cantidad) -> {
                    println("  ⚠ ERROR: La cantidad debe estar entre ${RANGO_CANTIDADES.first} y ${RANGO_CANTIDADES.last}.")
                    println("  Usted ingresó: $cantidad")
                }
                else -> {
                    return cantidad
                }
            }
            println()
        }
    }

    /**
     * Parsea una fecha en formato DD/MM/YYYY
     *
     * @param fechaStr String con la fecha
     * @return LocalDate parseada o null si el formato es inválido
     */
    fun parsearFecha(fechaStr: String): LocalDate? {
        return try {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            LocalDate.parse(fechaStr, formatter)
        } catch (e: DateTimeParseException) {
            null
        }
    }

    /**
     * Muestra información sobre el periodo promocional actual.
     */
    fun mostrarInfoPeriodoPromocional() {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        println("  ℹ PERIODO PROMOCIONAL ACTIVO:")
        println("    Desde: ${FECHA_INICIO_PROMO.format(formatter)}")
        println("    Hasta: ${FECHA_FIN_PROMO.format(formatter)}")
        println("    Descuento: ${(DESCUENTO_PROMOCIONAL * 100).toInt()}%")
    }
}
