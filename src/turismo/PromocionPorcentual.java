package turismo;

import java.util.Set;

public class PromocionPorcentual extends Promocion {
	
	private double porcentajeDescuento;

	public PromocionPorcentual(String nombre, String tipo, Set<Atraccion> atracciones, double porcentajeDescuento) {
		super(nombre, tipo, atracciones);
		this.porcentajeDescuento = porcentajeDescuento;
	}

	@Override
	public String getMensajeDePresentacionAlUsuario() {
		// Mensaje para presentar la promocion. Ejemplo: "Bosque Negro y Mordor con un 20% de descuento si se compran ambas."
		return "";
	}

	@Override
	public double getCosto() {
		double total = 0;
		for (Atraccion atraccion : this.getAtraccionesQueIncluye()) {
			total += atraccion.getCosto();
		}
		return total - ((total * this.porcentajeDescuento) / 100);
	}

}
