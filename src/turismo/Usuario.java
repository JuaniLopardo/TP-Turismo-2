package turismo;

public class Usuario {
	
	private String nombre;
	private double presupuesto;
	private double tiempoDisponible;
	private String tipoDeAtraccionPreferida;
	
	public Usuario(String nombre, double presupuesto, double tiempoDisponible, String tipoDeAtraccionPreferida) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoDeAtraccionPreferida = tipoDeAtraccionPreferida;
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
