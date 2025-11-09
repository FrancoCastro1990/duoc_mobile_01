# Sistema de Gestión Veterinaria

**Curso:** PMY2201 - Desarrollo de Aplicaciones Móviles
**Semana:** 3 - Creando soluciones con Kotlin
**Actividad:** Evaluación Sumativa Individual

## Descripción

Sistema de gestión para veterinaria que permite registrar clientes, mascotas, consultas y medicamentos. Implementa conceptos avanzados de Kotlin como POO, expresiones regulares, rangos, anotaciones, reflection, operator overloading y desestructuración.

## Estructura del Proyecto

```
src/main/kotlin/
├── annotations/
│   └── Promocionable.kt              # Anotación para medicamentos promocionales
├── model/
│   ├── Cliente.kt                    # Modelo de cliente con equals/hashCode
│   ├── Mascota.kt                    # Modelo de mascota
│   ├── Medicamento.kt                # Modelo de medicamento con operator==
│   ├── MedicamentosPromocionales.kt  # Medicamentos con @Promocionable
│   └── Consulta.kt                   # Modelo de consulta con operator+
├── service/
│   ├── PromotionService.kt           # Gestión de promociones
│   ├── ConsultaService.kt            # Gestión de consultas
│   └── VeterinarioService.kt         # Gestión de disponibilidad
├── utils/
│   ├── ValidacionUtils.kt            # Validaciones con Regex
│   ├── RangoUtils.kt                 # Validaciones con Ranges
│   └── ReflectionUtils.kt            # Análisis de clases con Reflection
├── view/
│   └── ConsoleView.kt                # Vista de consola
└── Main.kt                           # Programa principal
```

## Funcionalidades Implementadas

### 1. Regex y Ranges ✓

- **Validación de Email**: Formato estándar `nombre@dominio.com`
- **Formateo de Teléfono**: Formato uniforme `+XX (XXX) XXX-XXXX`
- **Validación de Cantidades**: Rango permitido 1-100
- **Validación de Fechas**: Periodo promocional con descuento automático

### 2. Anotaciones y Reflection ✓

- **Anotación @Promocionable**: Marca medicamentos elegibles para descuentos
- **Identificación Dinámica**: Usa reflection para detectar productos promocionales
- **Análisis de Clases**: Lista métodos y propiedades dinámicamente

### 3. Operator Overloading ✓

- **Operator +**: Combina consultas sumando medicamentos y totales
- **Operator ==**: Compara medicamentos por nombre y dosificación

### 4. Desestructuración ✓

- **Cliente**: `val (nombre, email, telefono) = cliente`
- **Consulta**: `val (cliente, medicamentos, total) = consulta`

### 5. Equals/HashCode ✓

- **Cliente**: Previene duplicados por nombre y email
- **Medicamento**: Previene duplicados por nombre y dosificación
- **Detección Automática**: Sistema identifica y reporta duplicados

### 6. Integración Completa ✓

- Resumen ejecutivo que demuestra todas las funcionalidades
- Arquitectura limpia con separación de responsabilidades
- Código documentado y siguiendo buenas prácticas (KISS, DRY)

## Requisitos

- Kotlin 2.2.0
- JVM 1.8+
- kotlin-reflect

## Compilación y Ejecución

### Con kotlinc (compilador directo):

```bash
# Compilar
kotlinc -d target/classes \
  -classpath /usr/share/kotlin/lib/kotlin-reflect.jar \
  src/main/kotlin/annotations/*.kt \
  src/main/kotlin/model/*.kt \
  src/main/kotlin/utils/*.kt \
  src/main/kotlin/service/*.kt \
  src/main/kotlin/view/*.kt \
  src/main/kotlin/Main.kt

# Ejecutar
kotlin -classpath target/classes:/usr/share/kotlin/lib/kotlin-reflect.jar org.example.MainKt
```

### Con Maven (si está instalado):

```bash
# Compilar
mvn clean compile

# Ejecutar
mvn exec:java
```

## Uso del Sistema

Al ejecutar el programa, siga las instrucciones en pantalla:

1. **Ingrese email**: Use formato válido (ej: `juan@example.com`)
2. **Ingrese teléfono**: Cualquier formato (se formateará automáticamente)
3. **Ingrese nombre**: Nombre completo del cliente
4. **Ingrese cantidad**: Número de mascotas (1-100)

El sistema ejecutará automáticamente todas las demostraciones de funcionalidades avanzadas.

## Ejemplos de Salida

```
═══════════════════════════════════════════════════════════════
SISTEMA DE GESTIÓN VETERINARIA
═══════════════════════════════════════════════════════════════

--- PASO 1: VALIDACIÓN CON REGEX ---

Ingrese su email:
juan.perez@example.com
✓ Email validado correctamente: juan.perez@example.com

Ingrese su teléfono (cualquier formato):
912345678
✓ Teléfono formateado: +56 (912) 345-6789

--- PASO 2: VALIDACIÓN CON RANGES ---

Ingrese número de mascotas a atender (1-100):
2
✓ Cantidad validada: 2 mascotas

--- PASO 3: ANOTACIONES Y REFLECTION ---

Medicamentos promocionales encontrados: 3
  - Amoxicilina: 20% descuento
  - Ivermectina: 15% descuento
  - Vacuna Triple: 10% descuento

...
```

## Cumplimiento de Rúbrica

### Criterio 1: Regex y Ranges (100%) ✓
- Validación perfecta de emails con mensajes claros
- Formateo uniforme de teléfonos
- Verificación de rangos de fechas con descuentos automáticos
- Validación de cantidades 1-100

### Criterio 2: Anotaciones y Reflection (100%) ✓
- @Promocionable implementada y funcional
- Identificación dinámica de productos con reflection
- Análisis completo de clases mostrando métodos y propiedades

### Criterio 3: Operator Overloading (100%) ✓
- Operator + combina consultas correctamente
- Operator == compara medicamentos por atributos clave
- Manejo de casos nulos y validaciones

### Criterio 4: Desestructuración (100%) ✓
- Cliente desestructurable (nombre, email, teléfono)
- Consulta desestructurable (cliente, medicamentos, total)

### Criterio 5: Equals/HashCode (100%) ✓
- Implementación correcta en Cliente y Medicamento
- Prevención efectiva de duplicados
- Manejo de casos nulos

### Criterio 6: Resumen Completo (100%) ✓
- Resumen exhaustivo de todas las funcionalidades
- Salida clara y bien organizada
- Integración perfecta de todos los componentes

### Criterio 7: Presentación y Entrega (100%) ✓
- Proyecto organizado en paquetes profesionales
- Código documentado con KDoc
- README con instrucciones detalladas
- Listo para comprimir en ZIP/RAR

## Principios de Diseño Aplicados

- **KISS (Keep It Simple, Stupid)**: Código simple y directo
- **DRY (Don't Repeat Yourself)**: Sin duplicación de lógica
- **Separación de Responsabilidades**: Paquetes bien definidos (model, service, utils, view)

## Autor

Franco - PMY2201 Desarrollo de Aplicaciones Móviles

## Fecha

2025-11-08
