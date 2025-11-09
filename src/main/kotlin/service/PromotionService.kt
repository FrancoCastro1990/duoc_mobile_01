package service

import annotations.Promocionable
import model.Medicamento
import kotlin.reflect.full.findAnnotation

/**
 * Servicio para gestionar promociones de medicamentos.
 *
 * Utiliza reflection para identificar dinámicamente qué medicamentos
 * están marcados con la anotación @Promocionable y calcular descuentos.
 */
object PromotionService {

    /**
     * Identifica los medicamentos que están marcados como promocionables.
     *
     * Usa reflection para verificar si la clase del medicamento tiene
     * la anotación @Promocionable.
     *
     * @param medicamentos Lista de medicamentos a evaluar
     * @return Lista de medicamentos promocionables
     */
    fun identificarPromocionales(medicamentos: List<Medicamento>): List<Medicamento> {
        return medicamentos.filter { esPromocionable(it) }
    }

    /**
     * Verifica si un medicamento es promocionable usando reflection.
     *
     * @param medicamento El medicamento a verificar
     * @return true si tiene la anotación @Promocionable
     */
    fun esPromocionable(medicamento: Medicamento): Boolean {
        return medicamento::class.findAnnotation<Promocionable>() != null
    }

    /**
     * Obtiene el descuento aplicable a un medicamento promocionable.
     *
     * @param medicamento El medicamento
     * @return Porcentaje de descuento (0.0 si no es promocionable)
     */
    fun obtenerDescuento(medicamento: Medicamento): Double {
        val anotacion = medicamento::class.findAnnotation<Promocionable>()
        return anotacion?.descuento ?: 0.0
    }

    /**
     * Calcula el precio con descuento de un medicamento si es promocionable.
     *
     * @param medicamento El medicamento
     * @return Par con (precioFinal, descuentoAplicado)
     */
    fun calcularPrecioConDescuento(medicamento: Medicamento): Pair<Double, Double> {
        val descuento = obtenerDescuento(medicamento)
        val precioFinal = medicamento.precio * (1 - descuento)
        return Pair(precioFinal, descuento)
    }

    /**
     * Genera un reporte de medicamentos promocionales con sus descuentos.
     *
     * @param medicamentos Lista de medicamentos
     * @return String formateado con el reporte
     */
    fun generarReportePromocionales(medicamentos: List<Medicamento>): String {
        val promocionales = identificarPromocionales(medicamentos)

        if (promocionales.isEmpty()) {
            return "No hay medicamentos promocionales disponibles."
        }

        val reporte = StringBuilder()
        reporte.appendLine("═══════════════════════════════════════")
        reporte.appendLine("MEDICAMENTOS PROMOCIONALES")
        reporte.appendLine("═══════════════════════════════════════")
        reporte.appendLine()

        promocionales.forEachIndexed { index, med ->
            val (precioFinal, descuento) = calcularPrecioConDescuento(med)
            reporte.appendLine("${index + 1}. ${med.nombre} (${med.dosificacion})")
            reporte.appendLine("   Precio normal: $${String.format("%.2f", med.precio)}")
            reporte.appendLine("   Descuento: ${(descuento * 100).toInt()}%")
            reporte.appendLine("   Precio promocional: $${String.format("%.2f", precioFinal)}")
            reporte.appendLine()
        }

        reporte.appendLine("═══════════════════════════════════════")
        return reporte.toString()
    }

    /**
     * Calcula el total de una lista de medicamentos aplicando descuentos promocionales.
     *
     * @param medicamentos Lista de medicamentos
     * @return Total calculado con descuentos aplicados
     */
    fun calcularTotalConPromociones(medicamentos: List<Medicamento>): Double {
        return medicamentos.sumOf { med ->
            val (precioFinal, _) = calcularPrecioConDescuento(med)
            precioFinal
        }
    }
}
