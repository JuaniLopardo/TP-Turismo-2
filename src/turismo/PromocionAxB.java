package turismo;

import java.util.List;

public class PromocionAxB extends Promocion {
	
	private Atraccion atraccionDeRegalo;

	public PromocionAxB(String nombre, String tipo, List<Atraccion> atracciones, Atraccion atraccionDeRegalo) {
		super(nombre, tipo, atracciones);
		this.atraccionDeRegalo = atraccionDeRegalo;
	}

	@Override
	public double getCosto() {
		double total = 0;
		for (Atraccion atraccion : this.getAtracciones()) {
			if (atraccion.getNombre() == this.atraccionDeRegalo.getNombre()) {
				continue;
			}
			total += atraccion.getCosto();
		}
		return total;
	}

	@Override
	public String getMensajeDePresentacionAlUsuario() {
		// Mensaje para presentar la promocion. Ejemplo: "Comprando Minas Tirith y el Abismo de Helm, Erebor es gratis."
		return "";
	}

}
