package turismo;

public class Atraccion {
	
	private String nombre;
	private double costo;
	private double duracion;
	private int cupoDiario;
	private String tipo;
	
	public Atraccion(String nombre, double costo, double duracion, int cupoDiario, String tipo) {
		this.nombre = nombre;
		this.costo = costo;
		this.duracion = duracion;
		this.cupoDiario = cupoDiario;
		this.tipo = tipo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public double getCosto() {
		return costo;
	}

	public double getDuracion() {
		return duracion;
	}

	public int getCupoDiario() {
		return cupoDiario;
	}

	public String getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return "Atraccion [nombre=" + nombre + ", costo=" + costo + ", duracion=" + duracion + ", cupoDiario="
				+ cupoDiario + ", tipo=" + tipo + "]";
	}
	
}
