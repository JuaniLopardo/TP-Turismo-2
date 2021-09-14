package turismo;

import java.util.Set;

public class PromocionAbsoluta extends Promocion {
	
	private double costoTotal;

	public PromocionAbsoluta(String nombre, String tipo, Set<Atraccion> atracciones, double costoTotal) {
		super(nombre, tipo, atracciones);
		this.costoTotal = costoTotal;
	}

	@Override
	public String getMensajeDePresentacionAlUsuario() {
		// Mensaje para presentar la promocion. Ejemplo: "Lothlorien y La Comarca a 36 monedas."
		return this.getNombre() + ": Pagando $" + this.costoTotal + ", te llevas " + this.getAtraccionesEnumeradasString(getAtraccionesQueIncluye()) + "!";
	}

	@Override
	public double getCosto() {
		return this.costoTotal;
	}

}
