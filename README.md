# TP-Turismo
Trabajo práctico en grupo.

## Formato de Entrada

Los archivos de entrada para cargar datos de usuarios, atracciones y promociones estan en una especie de formato [CSV](https://es.wikipedia.org/wiki/Valores_separados_por_comas) donde no se aceptan comas para nada mas que separar los valores.

### Promociones.csv

En las promociones, el ultimo valor separado por coma es la lista de atracciones que incluye la promocion, separadas por un signo `+` en lugar de una coma. 

El cuarto valor depende del tipo de promocion. 

En `PromocionPorcentual`, el cuarto valor es el porcentaje de descuento. 

En `PromocionAbsoluta`, el cuarto valor es el costo total. 

En `PromocionAxB`, el cuarto valor es la atraccion que se obtiene gratis al comprar las otras.

Ejemplo:

```
Porcentual, Pack aventura, Aventura, 20, Bosque Negro + Mordor
Absoluta, Pack degustacion, Degustación, 36, Lothlórien + La Comarca
AxB, Pack paisajes, Paisaje, Erebor, Minas Tirith + Abismo de Helm + Erebor
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
