package turismo;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class InteraccionConUsuarios {
	
	private List<Usuario> usuarios;
	private List<Ofertable> ofertables;
	
	private List<String> ticketsParaSalida;
	
	private ComparadorDeOfertablesConsigna comparadorDeOfertablesConsigna = new ComparadorDeOfertablesConsigna();
	
	private DecimalFormat decimalFormat = new DecimalFormat("0.##", DecimalFormatSymbols.getInstance(Locale.ENGLISH));

	public InteraccionConUsuarios(List<Usuario> usuarios, List<Atraccion> atracciones, List<Promocion> promociones) {
		this.ticketsParaSalida = new ArrayList<String>();
		
		this.usuarios = usuarios;
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
				
				// Recolecta datos de la compra.
				double costoItinerario = 0;
				double horasNecesarias = 0;
				StringBuilder stringBuilderTicket = new StringBuilder();
				for (Ofertable oferta : usuario.getOfertasAceptadas()) {
					// Pasar a Usuario?
					costoItinerario += oferta.getCosto();
					horasNecesarias += oferta.getDuracion();
					stringBuilderTicket.append(oferta.getNombre());
					stringBuilderTicket.append(" + ");
				}
				stringBuilderTicket.delete(stringBuilderTicket.length() - 3, stringBuilderTicket.length());
				stringBuilderTicket.insert(0, usuario.getNombre() + ", " + decimalFormat.format(costoItinerario) + ", " + decimalFormat.format(horasNecesarias) + ", ");
				
				// Guarda la informacion para el archivo de salida.
				this.ticketsParaSalida.add(stringBuilderTicket.toString());
				
				// Mostrar resumen del itinerario.
				System.out.println("Tu itinerario es: ");
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
		scanner.close();
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
	
	public List<String> getTicketsParaArchivosDeSalida() {
		return this.ticketsParaSalida;
	}

}
