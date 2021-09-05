package turismo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private String nombre;
	private double presupuesto;
	private double tiempoDisponible;
	private String tipoDeAtraccionPreferida;
	
	private List<Atraccion> atraccionesAdquiridas;
	private List<Ofertable> ofertasAceptadas;
	
	public Usuario(String nombre, double presupuesto, double tiempoDisponible, String tipoDeAtraccionPreferida) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoDeAtraccionPreferida = tipoDeAtraccionPreferida;
		this.atraccionesAdquiridas = new ArrayList<Atraccion>();
	}
	
	public String getNombre() {
		return nombre;
	}

	public boolean podriaAceptarOferta(Ofertable oferta) {
		return oferta.getCosto() < this.presupuesto && oferta.getDuracion() < this.tiempoDisponible;
	}
	
	public boolean prefiereEsteTipoDeOferta(Ofertable oferta) {
		return oferta.getTipo().equals(this.getTipoDeAtraccionPreferida());
	}
	
	public void aceptarOferta(Ofertable oferta) {
		this.presupuesto -= oferta.getCosto();
		this.tiempoDisponible -= oferta.getDuracion();
		if (oferta instanceof Promocion) {
			for (Atraccion atraccion: ((Promocion) oferta).getAtraccionesQueIncluye()) {
				this.atraccionesAdquiridas.add(atraccion);
			}
		} else {
			this.atraccionesAdquiridas.add((Atraccion) oferta);
		}
		this.ofertasAceptadas.add(oferta);
	}
	
	public boolean yaTieneAtraccion(Atraccion atraccion) {
		return this.atraccionesAdquiridas.contains(atraccion);
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	public String getTipoDeAtraccionPreferida() {
		return tipoDeAtraccionPreferida;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", presupuesto=" + presupuesto + ", tiempoDisponible=" + tiempoDisponible
				+ ", tipoDeAtraccionPreferida=" + tipoDeAtraccionPreferida + "]";
	}

}
