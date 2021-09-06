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
	
	@Override
	public void reducirCupo() {
		this.cupo -= 1;
	}
	
	@Override
	public int getCupo() {
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
		return "Visitar " + this.getNombre() + " (" + this.getTipo() +  ") por " + this.getCostoString() + "!";
	}
	
	@Override
	public String getMensajeDeDetallesParaElUsuario() {
		return "La atraccion es de tipo " + this.getTipo() + " y vas a necesitar " + this.getDuracion() + " horas para visitarla.";
	}

	@Override
	public String getTipoDeOfertable() {
		return "atraccion";
	}
	
}
