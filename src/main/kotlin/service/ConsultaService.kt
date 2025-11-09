package service

import model.Cliente
import model.Consulta
import model.Mascota
import model.Medicamento
import utils.RangoUtils
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Servicio para gestionar consultas veterinarias y pedidos de medicamentos.
 *
 * Proporciona funcionalidades para crear consultas, calcular totales,
 * aplicar descuentos y combinar pedidos.
 */
object ConsultaService {

    /**
     * Costo base de una consulta veterinaria
     */
    private const val COSTO_BASE_CONSULTA = 30000.0

    /**
     * Descuento por múltiples mascotas (15%)
     */
    private const val DESCUENTO_MULTIPLES_MASCOTAS = 0.15

    /**
     * Crea una nueva consulta con cálculo automático del total.
     *
     * @param cliente Cliente que solicita la consulta
     * @param mascota Mascota a consultar
     * @param medicamentos Lista de medicamentos prescritos
     * @param descripcion Descripción de la consulta
     * @param numeroMascotas Número de mascotas atendidas (para descuento)
     * @return Consulta creada con total calculado
     */
    fun crearConsulta(
        cliente: Cliente,
        mascota: Mascota,
        medicamentos: List<Medicamento> = emptyList(),
        descripcion: String = "Consulta general",
        numeroMascotas: Int = 1
    ): Consulta {
        val totalMedicamentos = PromotionService.calcularTotalConPromociones(medicamentos)
        val costoConsulta = calcularCostoConsulta(numeroMascotas)
        val total = costoConsulta + totalMedicamentos

        return Consulta(
            id = (1000..9999).random(),
            cliente = cliente,
            mascota = mascota,
            medicamentos = medicamentos,
            descripcion = descripcion,
            fecha = LocalDateTime.now(),
            total = total
        )
    }

    /**
     * Calcula el costo de la consulta aplicando descuento por múltiples mascotas.
     *
     * @param numeroMascotas Número de mascotas atendidas
     * @return Costo de la consulta con descuento aplicado si corresponde
     */
    fun calcularCostoConsulta(numeroMascotas: Int): Double {
        val descuento = if (numeroMascotas > 1) DESCUENTO_MULTIPLES_MASCOTAS else 0.0
        return COSTO_BASE_CONSULTA * (1 - descuento)
    }

    /**
     * Aplica descuento promocional por fecha a una consulta.
     *
     * @param consulta La consulta original
     * @param fecha Fecha a evaluar
     * @return Nueva consulta con descuento aplicado si corresponde
     */
    fun aplicarDescuentoPorFecha(consulta: Consulta, fecha: LocalDate = LocalDate.now()): Consulta {
        val (totalConDescuento, descuento) = RangoUtils.aplicarDescuentoSiFechaPromocional(
            consulta.total,
            fecha
        )

        return if (descuento > 0) {
            Consulta(
                id = consulta.id,
                cliente = consulta.cliente,
                mascota = consulta.mascota,
                medicamentos = consulta.medicamentos,
                descripcion = "${consulta.descripcion} [Descuento promocional: ${(descuento * 100).toInt()}%]",
                fecha = consulta.fecha,
                total = totalConDescuento
            )
        } else {
            consulta
        }
    }

    /**
     * Genera un resumen detallado de una consulta.
     *
     * @param consulta La consulta a resumir
     * @return String formateado con el resumen
     */
    fun generarResumenConsulta(consulta: Consulta): String {
        val resultado = StringBuilder()

        resultado.appendLine("═══════════════════════════════════════")
        resultado.appendLine("RESUMEN DE CONSULTA #${consulta.id}")
        resultado.appendLine("═══════════════════════════════════════")
        resultado.appendLine()

        resultado.appendLine("FECHA: ${consulta.fecha}")
        resultado.appendLine()

        resultado.appendLine("--- CLIENTE ---")
        resultado.appendLine("Nombre: ${consulta.cliente.nombre}")
        resultado.appendLine("Email: ${consulta.cliente.email}")
        resultado.appendLine("Teléfono: ${consulta.cliente.telefono}")
        resultado.appendLine()

        resultado.appendLine("--- MASCOTA ---")
        resultado.appendLine("Nombre: ${consulta.mascota.nombre}")
        resultado.appendLine("Especie: ${consulta.mascota.especie}")
        resultado.appendLine("Edad: ${consulta.mascota.edad} años")
        resultado.appendLine("Peso: ${consulta.mascota.peso} kg")
        resultado.appendLine()

        resultado.appendLine("--- DESCRIPCIÓN ---")
        resultado.appendLine(consulta.descripcion)
        resultado.appendLine()

        if (consulta.medicamentos.isNotEmpty()) {
            resultado.appendLine("--- MEDICAMENTOS (${consulta.medicamentos.size}) ---")
            consulta.medicamentos.forEachIndexed { index, med ->
                resultado.appendLine("${index + 1}. ${med.nombre} (${med.dosificacion}) - $${String.format("%.2f", med.precio)}")
            }
            resultado.appendLine()
        }

        resultado.appendLine("--- TOTAL ---")
        resultado.appendLine("$${String.format("%.2f", consulta.total)}")
        resultado.appendLine()
        resultado.appendLine("═══════════════════════════════════════")

        return resultado.toString()
    }

    /**
     * Detecta duplicados en una lista de clientes.
     *
     * Utiliza equals/hashCode para identificar clientes duplicados.
     *
     * @param clientes Lista de clientes
     * @return Par con (clientesUnicos, duplicadosEncontrados)
     */
    fun detectarClientesDuplicados(clientes: List<Cliente>): Pair<List<Cliente>, List<Cliente>> {
        val unicos = mutableListOf<Cliente>()
        val duplicados = mutableListOf<Cliente>()

        for (cliente in clientes) {
            if (cliente in unicos) {
                duplicados.add(cliente)
            } else {
                unicos.add(cliente)
            }
        }

        return Pair(unicos, duplicados)
    }

    /**
     * Detecta medicamentos duplicados en una lista.
     *
     * Utiliza equals/hashCode para identificar medicamentos duplicados.
     *
     * @param medicamentos Lista de medicamentos
     * @return Par con (medicamentosUnicos, duplicadosEncontrados)
     */
    fun detectarMedicamentosDuplicados(medicamentos: List<Medicamento>): Pair<List<Medicamento>, List<Medicamento>> {
        val unicos = mutableListOf<Medicamento>()
        val duplicados = mutableListOf<Medicamento>()

        for (med in medicamentos) {
            if (med in unicos) {
                duplicados.add(med)
            } else {
                unicos.add(med)
            }
        }

        return Pair(unicos, duplicados)
    }
}
