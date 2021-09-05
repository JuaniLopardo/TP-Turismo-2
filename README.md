# TP-Turismo
Trabajo práctico en grupo.

## Formato de Entrada

Los archivos de entrada para cargar datos de usuarios, atracciones y promociones estan en una especie de formato [CSV](https://es.wikipedia.org/wiki/Valores_separados_por_comas) donde no se aceptan comas para nada mas que separar los valores.

### Promociones.csv

En las promociones, los ultimos valores separados por coma de una linea se toman como las atracciones de esa promocion, y no hay limite.

Formato para PromocionPorcentual: tipoDePromocion, nombre, tipoDeAtraccion, porcentajeDeDescuento, promocion1, promocion2, promocion3, (etc)
Formato para PromocionAbsoluta: tipoDePromocion, nombre, tipoDeAtraccion, costo, promocion1, promocion2, promocion3, (etc)
Formato para PromocionAxB: tipoDePromocion, nombre, tipoDeAtraccion, promocionGratuita, promocion1, promocion2, promocion3 (etc)

Ejemplo:

```
Porcentual, Pack aventura, Aventura, 20, Bosque Negro, Mordor
Absoluta, Pack degustacion, Degustación, 36, Lothlórien, La Comarca
AxB, Pack paisajes, Paisaje, Erebor, Minas Tirith, Abismo de Helm
```

### Usuarios.csv

Formato para Usuario: nombre, tipoDeAtraccionFavorita, presupuesto, tiempoDisponible

Ejemplo:

```
Eowyn, Aventura, 10, 8
```

### Atracciones.csv

Formato para Atraccion: nombre, costo, duracion, cupo, tipo

Ejemplo:

```
La Comarca, 3, 6.5, 150, Degustación
```
