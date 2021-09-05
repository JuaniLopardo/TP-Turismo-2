package turismo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InteraccionConUsuarios {
	
	private List<Usuario> usuarios;
	private List<Atraccion> atracciones;
	private List<Promocion> promociones;
	private List<Ofertable> ofertables;
	
	private ComparadorDeOfertablesConsigna comparadorDeOfertablesConsigna = new ComparadorDeOfertablesConsigna();

	public InteraccionConUsuarios(List<Usuario> usuarios, List<Atraccion> atracciones, List<Promocion> promociones) {
		this.usuarios = usuarios;
		this.atracciones = atracciones;
		this.promociones = promociones;
		
		ofertables = new ArrayList<Ofertable>();
		ofertables.addAll(promociones);
		ofertables.addAll(atracciones);
		
		Collections.sort(ofertables, comparadorDeOfertablesConsigna);
	}
	
	
	public void hacerLoop() {
		for (Usuario usuario : this.usuarios) {
			List<Ofertable> ofertasParaUsuario = getOfertasParaUsuario(usuario);
			
			for (Ofertable oferta : ofertasParaUsuario) {
				
				// Si la oferta es una atraccion y el usuario ya la adquirio aceptando una promocion que la incluye, ignora
				// este ciclo y pasa a la siguiente oferta.
				if ((oferta instanceof Atraccion) && usuario.yaTieneAtraccion((Atraccion) oferta)) {
					continue;
				}
				
				// Solo presenta ofertas que el usuario podria llegar a aceptar por tener suficiente tiempo y dinero.
				if (usuario.podriaAceptarOferta(oferta)) {
					
					System.out.println(usuario.getNombre() + " podria llegar a aceptar comprar " + oferta);
					
				}
				
			}
			
		}
	}
	
	private List<Ofertable> getOfertasParaUsuario(Usuario usuario) {
		List<Ofertable> ofertasParaUsuario = new ArrayList<Ofertable>();
		List<Ofertable> ofertasPreferidasDeUsuario = new ArrayList<Ofertable>();
		
		// Conservando el orden de ofertables, separa las ofertas del tipo preferido del usuario.
		for (Ofertable oferta : ofertables) {
			if (usuario.prefiereEsteTipoDeOferta(oferta)) {
				ofertasPreferidasDeUsuario.add(oferta);
			} else {
				ofertasParaUsuario.add(oferta);
			}
		}
		
		// Primero van las ofertas del tipo preferido por el usuario.
		ofertasParaUsuario.addAll(0, ofertasPreferidasDeUsuario);
		
		return ofertasParaUsuario;
	}


}
