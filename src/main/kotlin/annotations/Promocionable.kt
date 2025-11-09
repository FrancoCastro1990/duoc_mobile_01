package annotations

/**
 * Anotación personalizada para marcar medicamentos/productos que califican para promociones especiales.
 *
 * Esta anotación se utiliza para identificar dinámicamente durante el procesamiento de pedidos
 * cuáles productos son elegibles para descuentos promocionales.
 *
 * @property descuento Porcentaje de descuento aplicable (0.0 a 1.0). Por ejemplo: 0.15 = 15%
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Promocionable(val descuento: Double = 0.10)
