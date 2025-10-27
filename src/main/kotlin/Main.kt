package org.example

/**
 * Sistema de Gestión de Consultas Veterinaria
 * Desarrollado para: PMY2201 - Desarrollo App Móviles
 * Semana 1: Mi primera app en Kotlin
 */

fun main() {
    println("=".repeat(60))
    println("    SISTEMA DE GESTIÓN VETERINARIA")
    println("=".repeat(60))
    println()

    // =========================================================================
    // PASO 1: DECLARACIÓN DE VARIABLES Y TIPOS DE DATOS
    // =========================================================================

    // Variables para MASCOTA (usando val ya que no cambiarán después de asignarse)
    val nombreMascota: String
    val especie: String
    val edadMascota: Int
    val pesoMascota: Double

    // Variables para DUEÑO (usando val para datos inmutables)
    val nombreDueño: String
    val telefono: String
    val email: String

    // Variables para CONSULTA
    val idConsulta: Int = (1000..9999).random() // ID único generado automáticamente
    val descripcionConsulta: String
    var costoConsulta: Double // var porque se actualizará con descuento
    var estadoConsulta: String // var porque cambiará según disponibilidad

    // Variables adicionales para gestión
    var numeroMascotas: Int
    val disponibilidadVeterinario: Boolean
    val fechaSolicitada: String
    val horaSolicitada: String

    // =========================================================================
    // PASO 2: ENTRADA Y SALIDA DE DATOS CON MANEJO DE ERRORES
    // =========================================================================

    println("--- REGISTRO DE MASCOTA ---")
    println()

    // Captura de nombre de mascota con validación de nulos
    print("Ingrese el nombre de la mascota: ")
    nombreMascota = readLine()?.takeIf { it.isNotBlank() } ?: "Sin nombre"

    // Captura de especie con validación
    print("Ingrese la especie (Perro/Gato/Otro): ")
    especie = readLine()?.takeIf { it.isNotBlank() } ?: "Sin especificar"

    // Captura de edad con manejo de errores y valor predeterminado
    print("Ingrese la edad de la mascota (años): ")
    edadMascota = try {
        readLine()?.toIntOrNull()?.takeIf { it >= 0 } ?: 0
    } catch (e: Exception) {
        println("  ⚠ Error en la entrada. Se asignó edad predeterminada: 0")
        0
    }

    // Captura de peso con manejo de errores y valor predeterminado
    print("Ingrese el peso de la mascota (kg): ")
    pesoMascota = try {
        readLine()?.toDoubleOrNull()?.takeIf { it > 0.0 } ?: 1.0
    } catch (e: Exception) {
        println("  ⚠ Error en la entrada. Se asignó peso predeterminado: 1.0 kg")
        1.0
    }

    println()
    println("--- REGISTRO DE DUEÑO ---")
    println()

    // Captura de datos del dueño con validación
    print("Ingrese el nombre del dueño: ")
    nombreDueño = readLine()?.takeIf { it.isNotBlank() } ?: "Sin nombre"

    print("Ingrese el teléfono de contacto: ")
    telefono = readLine()?.takeIf { it.isNotBlank() } ?: "No proporcionado"

    print("Ingrese el correo electrónico: ")
    email = readLine()?.takeIf { it.isNotBlank() } ?: "no-email@example.com"

    println()
    println("--- REGISTRO DE CONSULTA ---")
    println()

    // Captura de descripción de la consulta
    print("Ingrese el motivo de la consulta: ")
    descripcionConsulta = readLine()?.takeIf { it.isNotBlank() } ?: "Consulta general"

    // =========================================================================
    // PASO 3: CÁLCULO DEL COSTO CON DESCUENTO
    // =========================================================================

    // Captura del costo inicial de la consulta
    print("Ingrese el costo de la consulta ($): ")
    val costoInicial: Double = try {
        readLine()?.toDoubleOrNull()?.takeIf { it > 0.0 } ?: 30000.0
    } catch (e: Exception) {
        println("  ⚠ Error en la entrada. Se asignó costo predeterminado: $30000")
        30000.0
    }

    // Captura del número de mascotas para aplicar descuento
    print("Ingrese el número de mascotas a atender en esta consulta: ")
    numeroMascotas = try {
        readLine()?.toIntOrNull()?.takeIf { it > 0 } ?: 1
    } catch (e: Exception) {
        println("  ⚠ Error en la entrada. Se asignó valor predeterminado: 1")
        1
    }

    // Cálculo del descuento usando expresión if
    // Descuento del 15% si se atiende más de una mascota
    val descuentoAplicado: Double = if (numeroMascotas > 1) 0.15 else 0.0
    costoConsulta = if (numeroMascotas > 1) {
        costoInicial * (1 - descuentoAplicado)
    } else {
        costoInicial
    }

    // Mostrar información del descuento
    println()
    if (numeroMascotas > 1) {
        println("✓ Descuento aplicado: ${descuentoAplicado * 100}% por atender $numeroMascotas mascotas")
        println("  Costo original: \$${String.format("%.2f", costoInicial)}")
        println("  Costo con descuento: \$${String.format("%.2f", costoConsulta)}")
        println("  Ahorro: \$${String.format("%.2f", costoInicial - costoConsulta)}")
    } else {
        println("  No se aplicó descuento (se atiende solo 1 mascota)")
        println("  Costo de la consulta: \$${String.format("%.2f", costoConsulta)}")
    }

    // =========================================================================
    // PASO 4: VERIFICACIÓN DE DISPONIBILIDAD DEL VETERINARIO
    // =========================================================================

    println()
    println("--- VERIFICACIÓN DE DISPONIBILIDAD ---")
    println()

    // Solicitar fecha y hora deseada
    print("Ingrese la fecha deseada (DD/MM/YYYY): ")
    fechaSolicitada = readLine()?.takeIf { it.isNotBlank() } ?: "No especificada"

    print("Ingrese la hora deseada (HH:MM): ")
    horaSolicitada = readLine()?.takeIf { it.isNotBlank() } ?: "No especificada"

    // Simulación de verificación de disponibilidad
    // En una aplicación real, esto consultaría una base de datos
    print("Verificando disponibilidad del veterinario... ")

    // Simulación: El veterinario está disponible si la hora solicitada es entre 09:00 y 18:00
    disponibilidadVeterinario = try {
        val hora = horaSolicitada.substringBefore(':').toIntOrNull() ?: 0
        hora in 9..17
    } catch (e: Exception) {
        false
    }

    println()

    // Usar if como expresión para determinar el estado y mensaje
    val mensajeDisponibilidad: String = if (disponibilidadVeterinario) {
        estadoConsulta = "Confirmada"
        """
        ✓ CONSULTA CONFIRMADA
        El veterinario está disponible en la fecha y hora solicitada.
        Fecha: $fechaSolicitada
        Hora: $horaSolicitada
        La consulta ha sido registrada exitosamente.
        """.trimIndent()
    } else {
        estadoConsulta = "Pendiente"
        """
        ✗ VETERINARIO NO DISPONIBLE
        Lo sentimos, el veterinario no está disponible en el horario solicitado.

        Sugerencias de horarios disponibles:
        - Mañana: 09:00 - 12:00
        - Tarde: 14:00 - 17:00

        Por favor, contacte con recepción para reagendar su consulta.
        Estado actual: Pendiente de confirmación
        """.trimIndent()
    }

    println(mensajeDisponibilidad)

    // =========================================================================
    // PASO 5 y 6: RESUMEN DEL PEDIDO
    // =========================================================================

    println()
    println("=".repeat(60))
    println("    RESUMEN DE LA CONSULTA")
    println("=".repeat(60))
    println()

    // Información de confirmación
    println("ID de Consulta: #$idConsulta")
    println("Fecha de registro: ${java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}")
    println()

    // Datos del dueño
    println("--- DATOS DEL DUEÑO ---")
    println("Nombre: $nombreDueño")
    println("Teléfono: $telefono")
    println("Email: $email")
    println()

    // Información de la mascota
    println("--- INFORMACIÓN DE LA MASCOTA ---")
    println("Nombre: $nombreMascota")
    println("Especie: $especie")
    println("Edad: $edadMascota año(s)")
    println("Peso: ${String.format("%.2f", pesoMascota)} kg")
    println()

    // Detalles de la consulta
    println("--- DETALLES DE LA CONSULTA ---")
    println("Motivo: $descripcionConsulta")
    println("Fecha solicitada: $fechaSolicitada")
    println("Hora solicitada: $horaSolicitada")
    println("Número de mascotas: $numeroMascotas")

    if (numeroMascotas > 1) {
        println("Costo sin descuento: \$${String.format("%.2f", costoInicial)}")
        println("Descuento aplicado: ${descuentoAplicado * 100}%")
    }

    println("COSTO FINAL: \$${String.format("%.2f", costoConsulta)}")
    println("Estado: $estadoConsulta")
    println()

    // Mensaje final según el estado
    val mensajeFinal: String = if (estadoConsulta == "Confirmada") {
        """
        ✓ Su consulta ha sido confirmada exitosamente.
        Por favor, llegue 10 minutos antes de su cita.
        Recibirá un recordatorio por correo electrónico a: $email
        """.trimIndent()
    } else {
        """
        Su consulta está pendiente de confirmación.
        Nos pondremos en contacto con usted al teléfono: $telefono
        para coordinar un nuevo horario.
        """.trimIndent()
    }

    println(mensajeFinal)
    println()
    println("=".repeat(60))
    println("    Gracias por confiar en nuestra veterinaria")
    println("=".repeat(60))
}
