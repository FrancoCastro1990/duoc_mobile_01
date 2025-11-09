package model

/**
 * Representa una mascota (paciente) en el sistema veterinario.
 *
 * @property nombre Nombre de la mascota
 * @property especie Especie de la mascota (perro, gato, etc.)
 * @property edad Edad de la mascota en años
 * @property peso Peso de la mascota en kilogramos
 */
data class Mascota(
    val nombre: String,
    val especie: String,
    val edad: Int,
    val peso: Double
)
