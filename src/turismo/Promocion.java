package turismo;

import java.util.ArrayList;
import java.util.Comparator;
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
	
	public String getAtraccionesEnumeradasString(List<Atraccion> atracciones) {
		String atraccionesEnumeradas = atracciones.get(0).getNombre() + "(" + atracciones.get(0).getCostoString() + ")";
		int limit = atracciones.size();

		for (int i = 1; i < limit; i++) {
			Atraccion atraccion = atracciones.get(i);
			if (i + 2 < limit) {
				atraccionesEnumeradas = atraccionesEnumeradas + ", " + atraccion.getNombre() + "(" + atraccion.getCostoString() + ")";
			} else {
				atraccionesEnumeradas = atraccionesEnumeradas + " y " + atraccion.getNombre() + "(" + atraccion.getCostoString() + ")";
			}
		}
		return atraccionesEnumeradas;
	}

	@Override
	public String getTipoDeOfertable() {
		return "promocion";
	}
	
	@Override
	public int getCupo() {
		// El cupo de una promocion es el cupo mas chico entre las atracciones que ofrece.
		int cupo = this.atraccionesQueIncluye.get(0).getCupo();
		for (Atraccion atraccion : this.atraccionesQueIncluye) {
			if (atraccion.getCupo() < cupo) {
				cupo = atraccion.getCupo();
			}
		}
		return cupo;
	}
	
	@Override
	public void reducirCupo() {
		for (Atraccion atraccion : this.atraccionesQueIncluye) {
			atraccion.reducirCupo();
		}
	}
	
	@Override
	public String getMensajeDeDetallesParaElUsuario() {
		return "Las atracciones son de tipo " + this.getTipo() + " y vas a necesitar " + this.getDuracion() + " horas para visitarlas.";
	}
	
}
