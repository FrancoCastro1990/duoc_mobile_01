package model

/**
 * Representa un cliente (dueño de mascota) en el sistema veterinario.
 *
 * Esta clase implementa equals/hashCode para prevenir duplicados y
 * soporta desestructuración para acceso conveniente a sus propiedades.
 *
 * @property nombre Nombre completo del cliente
 * @property email Correo electrónico del cliente (debe estar validado)
 * @property telefono Número de teléfono del cliente (formateado)
 */
class Cliente(
    val nombre: String,
    val email: String,
    val telefono: String
) {
    /**
     * Componente 1 para desestructuración: nombre
     */
    operator fun component1(): String = nombre

    /**
     * Componente 2 para desestructuración: email
     */
    operator fun component2(): String = email

    /**
     * Componente 3 para desestructuración: telefono
     */
    operator fun component3(): String = telefono

    /**
     * Compara dos clientes por sus atributos clave: nombre y email.
     * Dos clientes son iguales si tienen el mismo nombre y email.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is Cliente) return false

        return nombre == other.nombre && email == other.email
    }

    /**
     * Genera un hash code consistente basado en nombre y email.
     */
    override fun hashCode(): Int {
        var result = nombre.hashCode()
        result = 31 * result + email.hashCode()
        return result
    }

    override fun toString(): String {
        return "Cliente(nombre='$nombre', email='$email', telefono='$telefono')"
    }
}
