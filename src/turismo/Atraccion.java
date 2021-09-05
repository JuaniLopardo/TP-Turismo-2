package turismo;

public class Atraccion extends Ofertable {
	
	private int cupo;
	private double costo;
	private double duracion;
	
	public Atraccion(String nombre, double costo, double duracion, int cupoDiario, String tipo) {
		super(nombre, tipo);
		this.costo = costo;
		this.duracion = duracion;
		this.cupo = cupoDiario;
	}
	
	public boolean tieneCupo() {
		return this.cupo > 0;
	}
	
	public void reducirCupo() {
		this.cupo -= 1;
	}
	
	public int getCupoDiario() {
		return this.cupo;
	}

	@Override
	public String toString() {
		return "Atraccion [nombre=" + this.getNombre() + ", costo=" + costo + ", duracion=" + duracion + ", cupoDiario="
				+ cupo + ", tipo=" + this.getTipo() + "]";
	}

	@Override
	public double getCosto() {
		return this.costo;
	}

	@Override
	public double getDuracion() {
		return this.duracion;
	}

	@Override
	public String getMensajeDePresentacionAlUsuario() {
		return "Visita " + this.getNombre() + " (" + this.getTipo() +  ") por " + this.getCostoString() + "!";
	}
	
}
