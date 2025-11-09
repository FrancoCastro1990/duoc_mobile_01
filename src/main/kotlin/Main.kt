package org.example

import model.*
import service.*
import utils.*
import view.ConsoleView
import java.time.LocalDate

/**
 * Sistema de Gestión de Consultas Veterinaria
 * Desarrollado para: PMY2201 - Desarrollo App Móviles
 * Semana 3: Creando soluciones con Kotlin
 */

fun main() {
    ConsoleView.mostrarEncabezado("SISTEMA DE GESTIÓN VETERINARIA")
    println()

    // =========================================================================
    // PARTE 1: REGISTRO DE CONSULTA (APP FUNCIONAL)
    // =========================================================================

    // --- DATOS DEL CLIENTE (DUEÑO) ---
    ConsoleView.mostrarSubtitulo("DATOS DEL DUEÑO")

    val email = ValidacionUtils.solicitarEmailValido("Ingrese su email:")
    val telefono = ValidacionUtils.solicitarTelefonoFormateado("Ingrese su teléfono (cualquier formato):")

    print("Ingrese su nombre completo: ")
    val nombreCliente = readLine()?.trim() ?: "Cliente Anónimo"
    println()

    val cliente = Cliente(nombreCliente, email, telefono)
    ConsoleView.mostrarExito("Cliente registrado: $nombreCliente")
    ConsoleView.mostrarSeparador()

    // --- DATOS DE LA MASCOTA ---
    ConsoleView.mostrarSubtitulo("DATOS DE LA MASCOTA")

    print("Ingrese el nombre de la mascota: ")
    val nombreMascota = readLine()?.takeIf { it.isNotBlank() } ?: "Sin nombre"

    print("Ingrese la especie (Perro/Gato/Otro): ")
    val especie = readLine()?.takeIf { it.isNotBlank() } ?: "Sin especificar"

    print("Ingrese la edad de la mascota (años): ")
    val edad = readLine()?.toIntOrNull()?.takeIf { it >= 0 } ?: 0

    print("Ingrese el peso de la mascota (kg): ")
    val peso = readLine()?.toDoubleOrNull()?.takeIf { it > 0 } ?: 1.0

    println()
    val mascota = Mascota(nombreMascota, especie, edad, peso)
    ConsoleView.mostrarExito("Mascota registrada: $nombreMascota ($especie, $edad años, ${peso}kg)")
    ConsoleView.mostrarSeparador()

    // --- CONSULTA ---
    ConsoleView.mostrarSubtitulo("INFORMACIÓN DE LA CONSULTA")

    print("Ingrese el motivo de la consulta: ")
    val motivoConsulta = readLine()?.takeIf { it.isNotBlank() } ?: "Consulta general"

    val numeroMascotas = RangoUtils.solicitarCantidadValida(
        "¿Cuántas mascotas atenderá en esta consulta? (1-100):"
    )

    println()

    // --- FECHA Y HORA ---
    ConsoleView.mostrarSubtitulo("AGENDAMIENTO")

    print("Ingrese la fecha deseada (DD/MM/YYYY): ")
    val fechaStr = readLine()?.trim() ?: ""
    val fechaConsulta = RangoUtils.parsearFecha(fechaStr) ?: LocalDate.now()

    // Mostrar cómo quedó parseada la fecha
    val fechaFormateada = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy").format(fechaConsulta)
    println("  ✓ Fecha confirmada: $fechaFormateada")

    // Verificar si está en periodo promocional
    if (RangoUtils.estaEnPeriodoPromocional(fechaConsulta)) {
        println("  ℹ Esta fecha está en periodo promocional (15% descuento)")
    }
    println()

    print("Ingrese la hora deseada (HH:MM): ")
    val horaStr = readLine()?.trim() ?: "10:00"

    // Mostrar hora confirmada
    println("  ✓ Hora solicitada: $horaStr")
    println()

    // Verificar disponibilidad
    val (disponible, mensajeDisponibilidad) = VeterinarioService.verificarDisponibilidad(horaStr, fechaStr)
    println(mensajeDisponibilidad)
    println()

    val estadoConsulta = if (disponible) "Confirmada" else "Pendiente"

    ConsoleView.mostrarSeparador()

    // --- MEDICAMENTOS ---
    ConsoleView.mostrarSubtitulo("SELECCIÓN DE MEDICAMENTOS")

    // Mostrar catálogo
    val catalogoMedicamentos = listOf(
        Antibiotico(),
        Antiparasitario(),
        Vacuna(),
        Medicamento("Analgésico", "50mg", 8000.0, 100),
        Medicamento("Vitaminas", "100ml", 18000.0, 40)
    )

    println("Medicamentos disponibles:")
    catalogoMedicamentos.forEachIndexed { index, med ->
        val promocion = if (PromotionService.esPromocionable(med)) {
            val desc = PromotionService.obtenerDescuento(med)
            " [PROMOCIÓN ${(desc * 100).toInt()}%]"
        } else ""
        println("  ${index + 1}. ${med.nombre} (${med.dosificacion}) - $${String.format("%.2f", med.precio)}$promocion")
    }
    println()

    print("Ingrese los números de medicamentos separados por comas (ej: 1,3,5) o ENTER para ninguno: ")
    val seleccionStr = readLine()?.trim() ?: ""

    val medicamentosSeleccionados = if (seleccionStr.isBlank()) {
        emptyList()
    } else {
        seleccionStr.split(",")
            .mapNotNull { it.trim().toIntOrNull() }
            .filter { it in 1..catalogoMedicamentos.size }
            .map { catalogoMedicamentos[it - 1] }
    }

    println()
    if (medicamentosSeleccionados.isNotEmpty()) {
        ConsoleView.mostrarExito("Medicamentos seleccionados: ${medicamentosSeleccionados.size}")
        medicamentosSeleccionados.forEach { println("  - ${it.nombre} (${it.dosificacion})") }
    } else {
        ConsoleView.mostrarInfo("No se seleccionaron medicamentos")
    }

    ConsoleView.mostrarSeparador()

    // --- CÁLCULO DE COSTOS ---
    ConsoleView.mostrarSubtitulo("CÁLCULO DE COSTOS")

    val consulta1 = ConsultaService.crearConsulta(
        cliente = cliente,
        mascota = mascota,
        medicamentos = medicamentosSeleccionados,
        descripcion = motivoConsulta,
        numeroMascotas = numeroMascotas
    )

    // Aplicar descuento por fecha si corresponde
    val consultaFinal = ConsultaService.aplicarDescuentoPorFecha(consulta1, fechaConsulta)

    println("Costo base de consulta: $30,000")
    if (numeroMascotas > 1) {
        println("Descuento por múltiples mascotas (15%): -$4,500")
    }

    val descuentoFecha = RangoUtils.calcularDescuentoPromocional(fechaConsulta)
    if (descuentoFecha > 0) {
        println("Descuento promocional por fecha (${(descuentoFecha * 100).toInt()}%): Aplicado")
    }

    if (medicamentosSeleccionados.isNotEmpty()) {
        val totalMedicamentos = PromotionService.calcularTotalConPromociones(medicamentosSeleccionados)
        println("Total medicamentos: $${String.format("%.2f", totalMedicamentos)}")
    }

    println()
    ConsoleView.mostrarExito("TOTAL A PAGAR: $${String.format("%.2f", consultaFinal.total)}")
    println()

    ConsoleView.mostrarSeparador()

    // --- RESUMEN DE LA CONSULTA ---
    ConsoleView.mostrarEncabezado("RESUMEN DE LA CONSULTA")

    println("ID Consulta: ${consultaFinal.id}")
    println("Estado: $estadoConsulta")
    println("Fecha registro: ${consultaFinal.fecha}")
    println()

    println("CLIENTE:")
    println("  Nombre: ${cliente.nombre}")
    println("  Email: ${cliente.email}")
    println("  Teléfono: ${cliente.telefono}")
    println()

    println("MASCOTA:")
    println("  Nombre: ${mascota.nombre}")
    println("  Especie: ${mascota.especie}")
    println("  Edad: ${mascota.edad} años")
    println("  Peso: ${mascota.peso} kg")
    println()

    println("DETALLES:")
    println("  Motivo: $motivoConsulta")
    println("  Fecha agendada: $fechaStr")
    println("  Hora agendada: $horaStr")
    println("  Número de mascotas: $numeroMascotas")
    println()

    if (medicamentosSeleccionados.isNotEmpty()) {
        println("MEDICAMENTOS (${medicamentosSeleccionados.size}):")
        medicamentosSeleccionados.forEach { med ->
            val (precioFinal, desc) = PromotionService.calcularPrecioConDescuento(med)
            val descStr = if (desc > 0) " [${(desc * 100).toInt()}% desc]" else ""
            println("  - ${med.nombre} (${med.dosificacion}): $${String.format("%.2f", precioFinal)}$descStr")
        }
        println()
    }

    println("TOTAL: $${String.format("%.2f", consultaFinal.total)}")
    println()

    if (disponible) {
        ConsoleView.mostrarExito("Consulta confirmada - Por favor llegue 10 minutos antes")
    } else {
        ConsoleView.mostrarAdvertencia("Consulta pendiente - Nos comunicaremos para confirmar horario")
    }

    ConsoleView.mostrarSeparador()

    // =========================================================================
    // PARTE 2: DEMOSTRACIÓN TÉCNICA DE FUNCIONALIDADES AVANZADAS
    // =========================================================================

    println()
    println("Presione ENTER para ver el resumen técnico de funcionalidades...")
    readLine()
    println()

    ConsoleView.mostrarEncabezado("RESUMEN TÉCNICO - FUNCIONALIDADES KOTLIN AVANZADAS")

    // --- Crear datos de ejemplo para demostración ---
    val consulta2 = ConsultaService.crearConsulta(
        cliente = cliente,
        mascota = mascota,
        medicamentos = listOf(Antiparasitario()),
        descripcion = "Control de seguimiento",
        numeroMascotas = 1
    )

    val cliente2 = Cliente(nombreCliente, email, "+56 (912) 345-6789")
    val cliente3 = Cliente("María González", "maria@example.com", "+56 (987) 654-3210")

    val med1 = Medicamento("Amoxicilina", "500mg", 15000.0, 50)
    val med2 = Medicamento("Amoxicilina", "500mg", 12000.0, 30)
    val med3 = Medicamento("Ibuprofeno", "200mg", 5000.0, 100)

    // --- DEMOSTRACIÓN 1: REGEX Y RANGES ---
    ConsoleView.mostrarSubtitulo("1. REGEX Y RANGES")

    println("✓ Email validado con Regex: $email")
    println("✓ Teléfono formateado con Regex: $telefono")
    println("✓ Cantidad validada en rango (1-100): $numeroMascotas")
    println("✓ Fecha verificada en periodo promocional: ${RangoUtils.estaEnPeriodoPromocional(fechaConsulta)}")
    println()

    // --- DEMOSTRACIÓN 2: ANOTACIONES Y REFLECTION ---
    ConsoleView.mostrarSubtitulo("2. ANOTACIONES Y REFLECTION")

    val promocionales = PromotionService.identificarPromocionales(catalogoMedicamentos)
    println("✓ Medicamentos promocionales identificados: ${promocionales.size}")
    promocionales.forEach { med ->
        val desc = PromotionService.obtenerDescuento(med)
        println("  - ${med.nombre}: ${(desc * 100).toInt()}% descuento")
    }
    println()

    println("✓ Análisis con Reflection:")
    println("  Cliente: ${ReflectionUtils.obtenerNombresPropiedades(Cliente::class).size} propiedades")
    println("  Consulta: ${ReflectionUtils.obtenerNombresMetodos(Consulta::class).size} métodos")
    println()

    // --- DEMOSTRACIÓN 3: OPERATOR OVERLOADING ---
    ConsoleView.mostrarSubtitulo("3. OPERATOR OVERLOADING")

    val consultaCombinada = consultaFinal + consulta2
    println("✓ Operator + (combinar consultas):")
    println("  Consulta 1: $${String.format("%.2f", consultaFinal.total)}")
    println("  Consulta 2: $${String.format("%.2f", consulta2.total)}")
    println("  Combinada: $${String.format("%.2f", consultaCombinada.total)}")
    println()

    println("✓ Operator == (comparar medicamentos):")
    println("  ${med1.nombre} == ${med2.nombre}: ${med1 == med2}")
    println("  ${med1.nombre} == ${med3.nombre}: ${med1 == med3}")
    println()

    // --- DEMOSTRACIÓN 4: DESESTRUCTURACIÓN ---
    ConsoleView.mostrarSubtitulo("4. DESESTRUCTURACIÓN")

    val (nombre, emailDest, telefonoDest) = cliente
    println("✓ Cliente desestructurado:")
    println("  val (nombre, email, telefono) = cliente")
    println("  → $nombre, $emailDest, $telefonoDest")
    println()

    val (clienteDest, medicamentosDest, totalDest) = consultaCombinada
    println("✓ Consulta desestructurada:")
    println("  val (cliente, medicamentos, total) = consulta")
    println("  → ${clienteDest.nombre}, ${medicamentosDest.size} medicamentos, $${String.format("%.2f", totalDest)}")
    println()

    // --- DEMOSTRACIÓN 5: EQUALS/HASHCODE ---
    ConsoleView.mostrarSubtitulo("5. EQUALS/HASHCODE - PREVENCIÓN DE DUPLICADOS")

    val listaClientes = listOf(cliente, cliente2, cliente3)
    val (unicos, duplicados) = ConsultaService.detectarClientesDuplicados(listaClientes)

    println("✓ Detección de clientes duplicados:")
    println("  Total clientes: ${listaClientes.size}")
    println("  Únicos: ${unicos.size}")
    println("  Duplicados: ${duplicados.size}")
    if (duplicados.isNotEmpty()) {
        duplicados.forEach { println("    - ${it.nombre} (${it.email})") }
    }
    println()

    val listaMedicamentos = listOf(med1, med2, med3)
    val (medicamentosUnicos, medicamentosDuplicados) = ConsultaService.detectarMedicamentosDuplicados(listaMedicamentos)

    println("✓ Detección de medicamentos duplicados:")
    println("  Total medicamentos: ${listaMedicamentos.size}")
    println("  Únicos: ${medicamentosUnicos.size}")
    println("  Duplicados: ${medicamentosDuplicados.size}")
    if (medicamentosDuplicados.isNotEmpty()) {
        medicamentosDuplicados.forEach { println("    - ${it.nombre} ${it.dosificacion}") }
    }
    println()

    ConsoleView.mostrarSeparador()
    ConsoleView.mostrarExito("SISTEMA EJECUTADO EXITOSAMENTE")
    ConsoleView.mostrarInfo("Consulta registrada y funcionalidades Kotlin demostradas")
    println()
    println("Gracias por usar el Sistema de Gestión Veterinaria")
    println()
}
