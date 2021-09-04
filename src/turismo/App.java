package turismo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
	
	private List<Usuario> usuarios;
	private List<Atraccion> atracciones;
	private List<Promocion> promociones;
	
	private Map<String, Atraccion> mapaDeAtraccionesPorSuNombre;
	
	static String PROMOCION_PORCENTUAL = "Porcentual";
	static String PROMOCION_ABSOLUTA = "Absoluta";
	static String PROMOCION_AxB = "AxB";
	
	public App(List<List<String>> listaDeDatosDeUsuarios, List<List<String>> listaDeDatosDeAtracciones, List<List<String>> listaDeDatosDePromociones) {
		construirUsuariosCon(listaDeDatosDeUsuarios);
		construirAtraccionesCon(listaDeDatosDeAtracciones);
		construirPromocionesCon(listaDeDatosDePromociones);
	}
	
	private void construirUsuariosCon(List<List<String>> listaDeDatosDeUsuarios) {
		this.usuarios = new ArrayList<Usuario>();
		
		for (List<String> datosDeUsuario : listaDeDatosDeUsuarios) {
			String nombre = datosDeUsuario.get(0);
			String tipoDeAtraccionPreferida = datosDeUsuario.get(1);
			double presupuesto = Double.parseDouble(datosDeUsuario.get(2));
			double tiempoDisponible = Double.parseDouble(datosDeUsuario.get(3));
			
			Usuario nuevoUsuario = new Usuario(nombre, presupuesto, tiempoDisponible, tipoDeAtraccionPreferida);
			
			this.usuarios.add(nuevoUsuario);
		}
	}
	
	private void construirAtraccionesCon(List<List<String>> listaDeDatosDeAtracciones) {
		this.atracciones = new ArrayList<Atraccion>();
		this.mapaDeAtraccionesPorSuNombre = new HashMap<String, Atraccion>();
		
		for (List<String> datosDeAtraccion : listaDeDatosDeAtracciones) {
			String nombre = datosDeAtraccion.get(0);
			double costo = Double.parseDouble(datosDeAtraccion.get(1));
			double duracion = Double.parseDouble(datosDeAtraccion.get(2));
			int cupoDiario = Integer.parseInt(datosDeAtraccion.get(3));
			String tipo = datosDeAtraccion.get(4);
			
			Atraccion nuevaAtraccion = new Atraccion(nombre, costo, duracion, cupoDiario, tipo);
			
			this.mapaDeAtraccionesPorSuNombre.put(nombre, nuevaAtraccion);
			
			this.atracciones.add(nuevaAtraccion);
		}
	}
	
	private void construirPromocionesCon(List<List<String>> listaDeDatosDePromociones) {
		this.promociones = new ArrayList<Promocion>();
		
		for (List<String> datosDePromocion : listaDeDatosDePromociones) {
			Promocion promocion;
			
			String tipoDePromocion = datosDePromocion.get(0);
			if (tipoDePromocion.equals(PROMOCION_PORCENTUAL)) {
				promocion = getNuevaPromocionPorcentual(datosDePromocion);
			} else if (tipoDePromocion.equals(PROMOCION_ABSOLUTA)) {
				promocion = getNuevaPromocionAbsoluta(datosDePromocion);
			} else if (tipoDePromocion.equals(PROMOCION_AxB)) {
				promocion = getNuevaPromocionAxB(datosDePromocion);
			} else {
				throw new RuntimeException("El tipo de promocion \"" + tipoDePromocion + "\" no existe.");
			}
			
			this.promociones.add(promocion);
		}
	}

    // Ejemplo datosDePromocion: Porcentual, Pack aventura, Aventura, 20, Bosque Negro, Mordor
	private PromocionPorcentual getNuevaPromocionPorcentual(List<String> datosDePromocion) {
		String nombre = datosDePromocion.get(1);
		String tipo = datosDePromocion.get(2);
		double porcentajeDescuento = Double.parseDouble(datosDePromocion.get(3));
		List<Atraccion> atraccionesEnPromocion = getAtraccionesEnDatosDePromocion(4, datosDePromocion, tipo);
		
		return new PromocionPorcentual(nombre, tipo, atraccionesEnPromocion, porcentajeDescuento);
	}
	
	// Ejemplo datosDePromocion: Absoluta, Pack degustacion, Degustacion, 36, Lothlorien, La Comarca
	private PromocionAbsoluta getNuevaPromocionAbsoluta(List<String> datosDePromocion) {
		String nombre = datosDePromocion.get(1);
		String tipo = datosDePromocion.get(2);
		double costoTotal = Double.parseDouble(datosDePromocion.get(3));
		List<Atraccion> atraccionesEnPromocion = getAtraccionesEnDatosDePromocion(4, datosDePromocion, tipo);
		
		return new PromocionAbsoluta(nombre, tipo, atraccionesEnPromocion, costoTotal);
	}
	
	// Ejemplo datosDePromocion: AxB, Pack paisajes, Paisajes, Erebor, Minas Tirith, Abismo de Helm
	private PromocionAxB getNuevaPromocionAxB(List<String> datosDePromocion) {
		String nombre = datosDePromocion.get(1);
		String tipo = datosDePromocion.get(2);
		Atraccion atraccionDeRegalo = getAtraccionPorSuNombre(datosDePromocion.get(3));
		List<Atraccion> atraccionesEnPromocion = getAtraccionesEnDatosDePromocion(4, datosDePromocion, tipo);
		
		return new PromocionAxB(nombre, tipo, atraccionesEnPromocion, atraccionDeRegalo);
	}
	
	private List<Atraccion> getAtraccionesEnDatosDePromocion(int indiceComienzo, List<String> datosDePromocion, String tipo) {
		List<Atraccion> atraccionesEnPromocion = new ArrayList<Atraccion>();
		for (int i = indiceComienzo; i < datosDePromocion.size(); i++) {
			Atraccion atraccion = getAtraccionPorSuNombre(datosDePromocion.get(i));
			
			if (!atraccion.getTipo().equals(tipo)) {
				throw new RuntimeException("Todas las atracciones de una promocion deben ser del mismo tipo. Esperado: \"" + atraccion.getTipo() +  "\". Obtenido: \"" + tipo +  "\"");
			}
			
			atraccionesEnPromocion.add(atraccion);
		}
		return atraccionesEnPromocion;
	}
	
	private Atraccion getAtraccionPorSuNombre(String nombre) {
		if (mapaDeAtraccionesPorSuNombre.containsKey(nombre)) {
			return mapaDeAtraccionesPorSuNombre.get(nombre);
		} else {
			throw new RuntimeException("No existe ninguna atraccion llamada \"" + nombre + "\"");
		}
	}
	
	private void iniciarInteraccionConUsuarios() {
		// TODO hacer el metodo real
		// Por ahora solo muestra los usuarios, las atracciones, y las promociones a la consola.
		
		for (Usuario usuario : this.usuarios) {
			System.out.println(usuario);
		}
		for (Atraccion atraccion : this.atracciones) {
			System.out.println(atraccion);
		}
		for (Promocion promocion : this.promociones) {
			System.out.println(promocion);
		}
	}
	
	public static void main(String[] args) {

		try {
			List<List<String>> listaDeDatosDeUsuarios = getContenidoDeCSV("Entrada/Usuarios.csv");
			List<List<String>> listaDeDatosDeAtracciones = getContenidoDeCSV("Entrada/Atracciones.csv");
			List<List<String>> listaDeDatosDePromociones = getContenidoDeCSV("Entrada/Promociones.csv");
			
			App app = new App(listaDeDatosDeUsuarios, listaDeDatosDeAtracciones, listaDeDatosDePromociones);
			app.iniciarInteraccionConUsuarios();
		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}

	private static List<List<String>> getContenidoDeCSV(String nombreDeArchivo) throws IOException {
		List<List<String>> listaDeLineas = new ArrayList<List<String>>();
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(nombreDeArchivo));
		String linea = bufferedReader.readLine();
		while (linea != null) {
			List<String> valoresEnLinea = new ArrayList<String>();
			
			for (String valor : linea.split(",")) {
				valoresEnLinea.add(valor.trim()); // .trim() Le saca espacios en blanco al principio y al final del String.
			}
			listaDeLineas.add(valoresEnLinea);
			
			linea = bufferedReader.readLine();
		}
		bufferedReader.close();
		
		return listaDeLineas;
	}

}
