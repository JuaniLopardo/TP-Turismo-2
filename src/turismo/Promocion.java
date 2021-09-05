package turismo;

import java.util.HashSet;
import java.util.Set;

public abstract class Promocion {
	
	private String nombre;
	private Set<Atraccion> atracciones;
	private String tipo;
	
	public Promocion(String nombre, String tipo, Set<Atraccion> atracciones) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.atracciones = atracciones;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	/*
	 * Devuelve un nuevo Set para que quienes obtengan el set de atracciones no puedan modificar
	 * las atracciones de la instancia de Promocion.
	 */
	public Set<Atraccion> getAtracciones() {
		return new HashSet<Atraccion>(this.atracciones);
	}
	
	public abstract double getCosto();
	
	public abstract String getMensajeDePresentacionAlUsuario();

	@Override
	public String toString() {
		return "Promocion [nombre=" + nombre + ", atracciones=" + atracciones + ", tipo=" + tipo + "]";
	}
	
	

}
