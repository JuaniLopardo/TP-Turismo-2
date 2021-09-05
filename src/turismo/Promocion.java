package turismo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class Promocion extends Ofertable {
	
	private List<Atraccion> atraccionesQueIncluye;
	private boolean agotada = false;
	
	public Promocion(String nombre, String tipo, Set<Atraccion> atracciones) {
		super(nombre, tipo);
		this.atraccionesQueIncluye = new ArrayList<Atraccion>();
		this.atraccionesQueIncluye.addAll(atracciones);
	}
	
	public List<Atraccion> getAtraccionesQueIncluye() {
		return this.atraccionesQueIncluye;
	}
	
	public void actualizarEstado( ) {
		for (Atraccion atraccion : this.atraccionesQueIncluye) {
			if (!atraccion.tieneCupo()) {
				this.agotada = true;
			}
		}
	}
	
	public boolean estaAgotada() {
		return this.agotada;
	}

	@Override
	public String toString() {
		return "Promocion [nombre=" + getNombre() + ", tipo=" + getTipo() + ", costo=" + getCosto()
				+ ", duracion=" + getDuracion() + ", atraccionesQueIncluye=" + getAtraccionesQueIncluye()
				+ "]";
	}

	@Override
	public double getDuracion() {
		double total = 0;
		
		for (Atraccion atraccion : this.atraccionesQueIncluye) {
			total += atraccion.getDuracion();
		}
		
		return total;
	}
	
}
