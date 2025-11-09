package model

/**
 * Representa un medicamento o producto veterinario.
 *
 * Esta clase implementa equals/hashCode para comparar medicamentos por nombre y dosificación,
 * previniendo duplicados en el inventario.
 *
 * Los medicamentos pueden ser marcados con la anotación @Promocionable para identificarlos
 * dinámicamente durante el procesamiento de pedidos.
 *
 * @property nombre Nombre del medicamento
 * @property dosificacion Dosificación del medicamento (ej: "10mg", "5ml")
 * @property precio Precio unitario del medicamento
 * @property stock Cantidad disponible en inventario
 */
open class Medicamento(
    val nombre: String,
    val dosificacion: String,
    val precio: Double,
    val stock: Int = 0
) {
    /**
     * Compara dos medicamentos por nombre y dosificación.
     * Dos medicamentos son iguales si tienen el mismo nombre y dosificación.
     *
     * Esto permite usar el operador == para comparar medicamentos.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is Medicamento) return false

        return nombre == other.nombre && dosificacion == other.dosificacion
    }

    /**
     * Genera un hash code consistente basado en nombre y dosificación.
     */
    override fun hashCode(): Int {
        var result = nombre.hashCode()
        result = 31 * result + dosificacion.hashCode()
        return result
    }

    override fun toString(): String {
        return "Medicamento(nombre='$nombre', dosificacion='$dosificacion', precio=$$precio, stock=$stock)"
    }
}
