package turismo;

import java.util.List;

public class PromocionPorcentual extends Promocion {
	
	private double porcentajeDescuento;

	public PromocionPorcentual(String nombre, String tipo, List<Atraccion> atracciones, double porcentajeDescuento) {
		super(nombre, tipo, atracciones);
		this.porcentajeDescuento = porcentajeDescuento;
	}

	@Override
	public double getCosto() {
		double total = 0;
		for (Atraccion atraccion : this.getAtracciones()) {
			total += atraccion.getCosto();
		}
		return (total * this.porcentajeDescuento) / 100;
	}

	@Override
	public String getMensajeDePresentacionAlUsuario() {
		// Mensaje para presentar la promocion. Ejemplo: "Bosque Negro y Mordor con un 20% de descuento si se compran ambas."
		return "";
	}

}
