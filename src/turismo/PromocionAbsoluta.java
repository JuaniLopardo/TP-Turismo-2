package turismo;

import java.util.List;

public class PromocionAbsoluta extends Promocion {
	
	private double costoTotal;

	public PromocionAbsoluta(String nombre, String tipo, List<Atraccion> atracciones, double costoTotal) {
		super(nombre, tipo, atracciones);
		this.costoTotal = costoTotal;
	}

	@Override
	public double getCosto() {
		return this.costoTotal;
	}

	@Override
	public String getMensajeDePresentacionAlUsuario() {
		// Mensaje para presentar la promocion. Ejemplo: "Lothlórien y La Comarca a 36 monedas."
		return "";
	}

}
