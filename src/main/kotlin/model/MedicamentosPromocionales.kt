package model

import annotations.Promocionable

/**
 * Medicamentos con promoción especial del 20%
 */

@Promocionable(descuento = 0.20)
class Antibiotico(
    nombre: String = "Amoxicilina",
    dosificacion: String = "500mg",
    precio: Double = 15000.0,
    stock: Int = 50
) : Medicamento(nombre, dosificacion, precio, stock)

@Promocionable(descuento = 0.15)
class Antiparasitario(
    nombre: String = "Ivermectina",
    dosificacion: String = "10ml",
    precio: Double = 12000.0,
    stock: Int = 30
) : Medicamento(nombre, dosificacion, precio, stock)

@Promocionable(descuento = 0.10)
class Vacuna(
    nombre: String = "Vacuna Triple",
    dosificacion: String = "1 dosis",
    precio: Double = 25000.0,
    stock: Int = 20
) : Medicamento(nombre, dosificacion, precio, stock)
