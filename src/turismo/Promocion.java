package turismo;

import java.util.ArrayList;
import java.util.List;

public abstract class Promocion {
	
	private String nombre;
	private List<Atraccion> atracciones;
	private String tipo;
	
	public Promocion(String nombre, String tipo, List<Atraccion> atracciones) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.atracciones = atracciones;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	/*
	 * Devuelve una nueva ArrayList para que quienes obtengan la lista de atracciones no puedan modificar
	 * las atracciones de la instancia de Promocion.
	 */
	public List<Atraccion> getAtracciones() {
		return new ArrayList<Atraccion>(this.atracciones);
	}
	
	public abstract double getCosto();
	
	public abstract String getMensajeDePresentacionAlUsuario();

	@Override
	public String toString() {
		return "Promocion [nombre=" + nombre + ", atracciones=" + atracciones + ", tipo=" + tipo + "]";
	}
	
	

}
