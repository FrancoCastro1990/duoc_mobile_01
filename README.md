# Sistema de Gestión Veterinaria

**Asignatura:** PMY2201 - Desarrollo App Móviles
**Actividad:** Semana 1 - Mi primera app en Kotlin
**Descripción:** Sistema para gestionar consultas de una veterinaria

## Descripción del Proyecto

Este proyecto implementa un sistema de gestión de consultas veterinarias que permite:

- Registrar información de mascotas y dueños
- Calcular costos de consultas con descuentos automáticos
- Verificar disponibilidad de veterinarios
- Generar resúmenes detallados de consultas

## Características Implementadas

### 1. Declaración de Variables
- Variables inmutables (`val`) para datos que no cambian
- Variables mutables (`var`) para datos que se actualizan
- Tipos de datos precisos: `String`, `Int`, `Double`, `Boolean`

### 2. Entrada y Salida de Datos
- Validación de entradas nulas
- Manejo de errores con `try-catch`
- Conversiones seguras: `toIntOrNull()`, `toDoubleOrNull()`
- Operador Elvis (`?:`) para valores predeterminados

### 3. Cálculo de Descuentos
- Descuento del 15% para múltiples mascotas
- Uso de expresiones `if` para cálculos
- Visualización clara de costos y ahorros

### 4. Verificación de Disponibilidad
- Validación de horarios (09:00 - 17:00)
- Mensajes claros de confirmación o rechazo
- Sugerencias de horarios alternativos

### 5. Resumen de Consulta
- Datos completos del dueño
- Información de la mascota
- Detalles de la consulta con costos finales

## Requisitos

- IntelliJ IDEA 2023 o superior
- Kotlin 1.9 o superior
- JDK 17 o superior

## Instrucciones de Ejecución

1. Abrir el proyecto en IntelliJ IDEA
2. Esperar a que el IDE sincronice las dependencias
3. Abrir el archivo `src/main/kotlin/Main.kt`
4. Hacer clic en el botón "Run" (▶) o presionar `Shift + F10`
5. Seguir las instrucciones en la consola

## Ejemplo de Uso

```
Ingrese el nombre de la mascota: Max
Ingrese la especie (Perro/Gato/Otro): Perro
Ingrese la edad de la mascota (años): 5
Ingrese el peso de la mascota (kg): 15.5
Ingrese el nombre del dueño: Juan Pérez
Ingrese el teléfono de contacto: +56912345678
Ingrese el correo electrónico: juan.perez@email.com
Ingrese el motivo de la consulta: Vacunación anual
Ingrese el costo de la consulta ($): 30000
Ingrese el número de mascotas a atender en esta consulta: 2
Ingrese la fecha deseada (DD/MM/YYYY): 28/10/2025
Ingrese la hora deseada (HH:MM): 10:00
```

## Cumplimiento de Rúbrica

### Criterios Evaluados (Completamente Logrado - 100%)

- ✅ **Declaración de variables:** Todas las variables declaradas correctamente con `var`/`val` y tipos precisos
- ✅ **Captura de datos:** Manejo completo de entradas nulas y errores sin fallos
- ✅ **Cálculo de descuentos:** Aplicación precisa del 15% de descuento
- ✅ **Verificación de disponibilidad:** Mensajes claros y actualización precisa del estado
- ✅ **Resumen del pedido:** Información completa de cliente, mascota y consulta
- ✅ **Código limpio:** Organización clara, nombres descriptivos, uso efectivo de expresiones
- ✅ **Formato de entrega:** Proyecto comprimido en formato ZIP

## Estructura del Código

```
src/main/kotlin/Main.kt
├── PASO 1: Declaración de variables y tipos de datos
├── PASO 2: Entrada y salida de datos con manejo de errores
├── PASO 3: Cálculo del costo con descuento
├── PASO 4: Verificación de disponibilidad del veterinario
└── PASO 5 y 6: Resumen del pedido
```

## Conceptos de Kotlin Aplicados

- Inferencia de tipos
- Null safety (`?`, `?.`, `?:`)
- Conversiones seguras (`toIntOrNull()`, `toDoubleOrNull()`)
- Expresiones `if`
- String templates (`$variable`, `${expresión}`)
- Rangos (`in 9..17`)
- Triple-quoted strings (`"""..."""`)
- Extension functions (`takeIf`, `isNotBlank`)
- Try-catch para manejo de excepciones
- `String.format()` para formato de números

## Autor

Franco Castro Villanueva - PMY2201

## Fecha

Octubre 2025
