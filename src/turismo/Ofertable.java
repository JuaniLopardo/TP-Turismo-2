package turismo;

public abstract class Ofertable {

	private String nombre;
	private String tipo;
	
	public Ofertable(String nombre, String tipo) {
		this.nombre = nombre;
		this.tipo = tipo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public abstract double getCosto();
	
	public abstract double getDuracion();
	
	public abstract String getMensajeDePresentacionAlUsuario();
	
	public String getCostoString() {
		return "$" + this.getCosto();
	}
	
}
