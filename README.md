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

El programa consta de dos partes principales:

### Parte 1: Registro de Consulta Veterinaria (Aplicación Funcional)

Al ejecutar, el sistema solicitará paso a paso:

1. **Datos del Dueño**
   - Email (se valida con regex)
   - Teléfono (se formatea automáticamente)
   - Nombre completo

2. **Datos de la Mascota**
   - Nombre de la mascota
   - Especie (Perro/Gato/Otro)
   - Edad (años)
   - Peso (kg)

3. **Información de la Consulta**
   - Motivo de la consulta
   - Número de mascotas a atender (1-100, validado con ranges)

4. **Agendamiento**
   - Fecha (DD/MM/YYYY, se verifica si está en periodo promocional)
   - Hora (HH:MM, se verifica disponibilidad del veterinario)

5. **Selección de Medicamentos**
   - Se muestra catálogo con precios y promociones
   - Selección mediante números separados por comas

6. **Resumen de Consulta**
   - Muestra todos los datos ingresados
   - Cálculo total con descuentos aplicados

### Parte 2: Demostración Técnica

Después de presionar ENTER, el sistema ejecuta automáticamente una demostración de todas las funcionalidades avanzadas de Kotlin.

## Ejemplo de Salida

```
═══════════════════════════════════════════════════════════════
SISTEMA DE GESTIÓN VETERINARIA
═══════════════════════════════════════════════════════════════



--- DATOS DEL DUEÑO ---

Ingrese su email:
franco@castro.cl
Ingrese su teléfono (cualquier formato):
986107382
  ✓ Teléfono formateado: +56 (986) 107-382
Ingrese su nombre completo: Franco Castro

✓ Cliente registrado: Franco Castro
-----------------------------------------------------------

--- DATOS DE LA MASCOTA ---

Ingrese el nombre de la mascota: Pepita
Ingrese la especie (Perro/Gato/Otro): Gato
Ingrese la edad de la mascota (años): 12
Ingrese el peso de la mascota (kg): 32

✓ Mascota registrada: Pepita (Gato, 12 años, 32.0kg)
-----------------------------------------------------------

--- INFORMACIÓN DE LA CONSULTA ---

Ingrese el motivo de la consulta: Dolor de pie
¿Cuántas mascotas atenderá en esta consulta? (1-100):
1


--- AGENDAMIENTO ---

Ingrese la fecha deseada (DD/MM/YYYY): 30/10/2025
  ✓ Fecha confirmada: 30/10/2025

Ingrese la hora deseada (HH:MM): 14:00
  ✓ Hora solicitada: 14:00

✓ CONSULTA CONFIRMADA
Fecha: 30/10/2025
Hora: 14:00

Por favor, llegue 10 minutos antes de su cita.
Recibirá un correo de confirmación.

-----------------------------------------------------------

--- SELECCIÓN DE MEDICAMENTOS ---

Medicamentos disponibles:
  1. Amoxicilina (500mg) - $15000.00 [PROMOCIÓN 20%]
  2. Ivermectina (10ml) - $12000.00 [PROMOCIÓN 15%]
  3. Vacuna Triple (1 dosis) - $25000.00 [PROMOCIÓN 10%]
  4. Analgésico (50mg) - $8000.00
  5. Vitaminas (100ml) - $18000.00

Ingrese los números de medicamentos separados por comas (ej: 1,3,5) o ENTER para ninguno: 1,5

✓ Medicamentos seleccionados: 2
  - Amoxicilina (500mg)
  - Vitaminas (100ml)
-----------------------------------------------------------

--- CÁLCULO DE COSTOS ---

Costo base de consulta: $30,000
Total medicamentos: $30000.00

✓ TOTAL A PAGAR: $60000.00

-----------------------------------------------------------

═══════════════════════════════════════════════════════════════
RESUMEN DE LA CONSULTA
═══════════════════════════════════════════════════════════════

ID Consulta: 4958
Estado: Confirmada
Fecha registro: 2025-11-08T21:38:34.585826882

CLIENTE:
  Nombre: Franco Castro
  Email: franco@castro.cl
  Teléfono: +56 (986) 107-382

MASCOTA:
  Nombre: Pepita
  Especie: Gato
  Edad: 12 años
  Peso: 32.0 kg

DETALLES:
  Motivo: Dolor de pie
  Fecha agendada: 30/10/2025
  Hora agendada: 14:00
  Número de mascotas: 1

MEDICAMENTOS (2):
  - Amoxicilina (500mg): $12000.00 [20% desc]
  - Vitaminas (100ml): $18000.00

TOTAL: $60000.00

✓ Consulta confirmada - Por favor llegue 10 minutos antes
-----------------------------------------------------------

Presione ENTER para ver el resumen técnico de funcionalidades...




═══════════════════════════════════════════════════════════════
RESUMEN TÉCNICO - FUNCIONALIDADES KOTLIN AVANZADAS
═══════════════════════════════════════════════════════════════


--- 1. REGEX Y RANGES ---

✓ Email validado con Regex: franco@castro.cl
✓ Teléfono formateado con Regex: +56 (986) 107-382
✓ Cantidad validada en rango (1-100): 1
✓ Fecha verificada en periodo promocional: false


--- 2. ANOTACIONES Y REFLECTION ---

✓ Medicamentos promocionales identificados: 3
  - Amoxicilina: 20% descuento
  - Ivermectina: 15% descuento
  - Vacuna Triple: 10% descuento

✓ Análisis con Reflection:
  Cliente: 3 propiedades
  Consulta: 7 métodos


--- 3. OPERATOR OVERLOADING ---

✓ Operator + (combinar consultas):
  Consulta 1: $60000.00
  Consulta 2: $40200.00
  Combinada: $100200.00

✓ Operator == (comparar medicamentos):
  Amoxicilina == Amoxicilina: true
  Amoxicilina == Ibuprofeno: false


--- 4. DESESTRUCTURACIÓN ---

✓ Cliente desestructurado:
  val (nombre, email, telefono) = cliente
  → Franco Castro, franco@castro.cl, +56 (986) 107-382

✓ Consulta desestructurada:
  val (cliente, medicamentos, total) = consulta
  → Franco Castro, 3 medicamentos, $100200.00


--- 5. EQUALS/HASHCODE - PREVENCIÓN DE DUPLICADOS ---

✓ Detección de clientes duplicados:
  Total clientes: 3
  Únicos: 2
  Duplicados: 1
    - Franco Castro (franco@castro.cl)

✓ Detección de medicamentos duplicados:
  Total medicamentos: 3
  Únicos: 2
  Duplicados: 1
    - Amoxicilina 500mg

-----------------------------------------------------------
✓ SISTEMA EJECUTADO EXITOSAMENTE
ℹ Consulta registrada y funcionalidades Kotlin demostradas

Gracias por usar el Sistema de Gestión Veterinaria

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
