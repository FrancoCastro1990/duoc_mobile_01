package model

import java.time.LocalDateTime

/**
 * Representa una consulta veterinaria o pedido de medicamentos.
 *
 * Esta clase soporta:
 * - Sobrecarga del operador + para combinar consultas
 * - Desestructuración para acceso conveniente a sus componentes
 *
 * @property id Identificador único de la consulta
 * @property cliente Cliente asociado a la consulta
 * @property mascota Mascota que recibe la consulta
 * @property medicamentos Lista de medicamentos prescritos/comprados
 * @property descripcion Motivo o descripción de la consulta
 * @property fecha Fecha y hora de la consulta
 * @property total Costo total de la consulta (calculado)
 */
class Consulta(
    val id: Int,
    val cliente: Cliente,
    val mascota: Mascota,
    val medicamentos: List<Medicamento> = emptyList(),
    val descripcion: String = "",
    val fecha: LocalDateTime = LocalDateTime.now(),
    val total: Double = 0.0
) {
    /**
     * Componente 1 para desestructuración: cliente
     */
    operator fun component1(): Cliente = cliente

    /**
     * Componente 2 para desestructuración: medicamentos
     */
    operator fun component2(): List<Medicamento> = medicamentos

    /**
     * Componente 3 para desestructuración: total
     */
    operator fun component3(): Double = total

    /**
     * Sobrecarga del operador + para combinar dos consultas.
     *
     * Combina dos consultas sumando los medicamentos y recalculando el total.
     * El cliente y mascota se toman de la primera consulta (this).
     *
     * @param otra La otra consulta a combinar
     * @return Una nueva consulta con los medicamentos combinados y total recalculado
     */
    operator fun plus(otra: Consulta): Consulta {
        val medicamentosCombinados = this.medicamentos + otra.medicamentos
        val totalRecalculado = this.total + otra.total

        return Consulta(
            id = (1000..9999).random(),
            cliente = this.cliente,
            mascota = this.mascota,
            medicamentos = medicamentosCombinados,
            descripcion = "${this.descripcion} + ${otra.descripcion}",
            fecha = LocalDateTime.now(),
            total = totalRecalculado
        )
    }
}
