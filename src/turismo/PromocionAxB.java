package turismo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PromocionAxB extends Promocion {
	
	private List<Atraccion> atraccionesDeRegalo;
	private List<Atraccion> atraccionesQueSeCobran;

	public PromocionAxB(String nombre, String tipo, Set<Atraccion> atracciones, Set<Atraccion> atraccionesDeRegalo) {
		super(nombre, tipo, atracciones);
		this.atraccionesQueSeCobran = new ArrayList<Atraccion>(atracciones);
		this.atraccionesDeRegalo = new ArrayList<Atraccion>(atraccionesDeRegalo);
		this.atraccionesQueSeCobran.removeAll(atraccionesDeRegalo);
	}

	@Override
	public String getMensajeDePresentacionAlUsuario() {
		// Mensaje para presentar la promocion. Ejemplo: "Comprando Minas Tirith y el Abismo de Helm, Erebor es gratis."
		return this.getNombre() + ": Comprando " + this.getAtraccionesEnumeradasString(atraccionesQueSeCobran) + ", te llevas " + this.getAtraccionesEnumeradasString(atraccionesDeRegalo) + " gratis!";
	}

	@Override
	public double getCosto() {
		double total = 0;
		for (Atraccion atraccion : this.atraccionesQueSeCobran) {
			total += atraccion.getCosto();
		}
		return total;
	}

}
