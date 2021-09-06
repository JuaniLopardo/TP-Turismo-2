package turismo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
		
		Scanner scanner = new Scanner(System.in);
		
		for (Usuario usuario : this.usuarios) {
			List<Ofertable> ofertasParaUsuario = getOfertasParaUsuario(usuario);
			
			System.out.println("¡Bienvenide " + usuario.getNombre() + "! Hoy podemos ofrecerte...");
			
			boolean realizoUnaCompra = false;
			
			for (Ofertable oferta : ofertasParaUsuario) {
				
				// Si la oferta es una atraccion y el usuario ya la adquirio aceptando una promocion que la incluye, ignora
				// esta oferta y pasa a la siguiente.
				if ((oferta instanceof Atraccion) && usuario.yaTieneAtraccion((Atraccion) oferta)) {
					continue;
				}
				
				// Solo presentar la oferta si tiene cupo.
				if (!oferta.tieneCupo()) {
					continue;
				}
				
				
				// Solo presentar ofertas que el usuario podria llegar a aceptar por tener suficiente tiempo y dinero.
				if (usuario.podriaAceptarOferta(oferta)) {

					String respuesta;
					boolean si = false;
					boolean no = false;
					
					while (!si && !no) {
						System.out.println(oferta.getMensajeDePresentacionAlUsuario());
						System.out.println(oferta.getMensajeDeDetallesParaElUsuario());
						System.out.println("¿Queres comprar esta " + oferta.getTipoDeOfertable() + "? (Si/No)");
						respuesta = scanner.nextLine();
						si = respuesta.toLowerCase().contains("si");
						no = respuesta.toLowerCase().contains("no");
						if (!si && !no) {
							System.out.println("El sistema no pudo entender tu respuesta. Recorda escribir \"si\" o \"no\".");
						}
					}
					
					if (si) {
						usuario.aceptarOferta(oferta);
						oferta.reducirCupo();
						realizoUnaCompra = true;
					} else if (no) {
						System.out.println("En tal caso, tal vez pueda interesarte...");
					}
					
				}
			}
			
			if (realizoUnaCompra) {
				System.out.println("****************************************************");
				System.out.println("¡Gracias por tu compra, " + usuario.getNombre() + "!");
				System.out.println("****************************************************");
				
				// Mostrar resumen del itinerario
				System.out.println("Tu itinerario es: ");
				
				double costoItinerario = 0;
				double horasNecesarias = 0;
				for (Ofertable oferta : usuario.getOfertasAceptadas()) {
					costoItinerario += oferta.getCosto();
					horasNecesarias += oferta.getDuracion();
				}
				for (Atraccion atraccion : usuario.getAtraccionesAdquiridas()) {
					System.out.println(atraccion.getNombre());
				}
				
				System.out.println("Horas totales: " + horasNecesarias);
				System.out.println("Costo total:   $" + costoItinerario);
				System.out.println("");
				
			} else {
				// TODO mensaje de despedida cuando no compra nada?
				
			}
		}
		
		// TODO ver que compro cada usuario y hacer los "tickets" de salida para cada uno
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
