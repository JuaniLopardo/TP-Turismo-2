package turismo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LectorArchivosEntrada {
	
	private List<Usuario> usuarios;
	private List<Atraccion> atracciones;
	private List<Promocion> promociones;
	
	private Map<String, Atraccion> mapaDeAtraccionesPorSuNombre;
	
	static String PROMOCION_PORCENTUAL = "Porcentual";
	static String PROMOCION_ABSOLUTA = "Absoluta";
	static String PROMOCION_AxB = "AxB";
	
	public LectorArchivosEntrada(String nombreDeArchivoDeUsuarios, String nombreDeArchivoDeAtracciones, String nombreDeArchivoDePromociones) throws IOException {
		List<List<String>> listaDeDatosDeUsuarios = getContenidoDeCSV(nombreDeArchivoDeUsuarios);
		List<List<String>> listaDeDatosDeAtracciones = getContenidoDeCSV(nombreDeArchivoDeAtracciones);
		List<List<String>> listaDeDatosDePromociones = getContenidoDeCSV(nombreDeArchivoDePromociones);
		
		construirUsuariosCon(listaDeDatosDeUsuarios);
		construirAtraccionesCon(listaDeDatosDeAtracciones);
		construirPromocionesCon(listaDeDatosDePromociones);
	}
	
	private List<List<String>> getContenidoDeCSV(String nombreDeArchivo) throws IOException {
		List<List<String>> listaDeLineas = new ArrayList<List<String>>();
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(nombreDeArchivo));
		String linea = bufferedReader.readLine();
		// Ignorar la primer linea CSV
		linea = bufferedReader.readLine();
		
		while (linea != null) {
			if (linea.isBlank()) {
				linea = bufferedReader.readLine();
				continue;
			} 
			
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
			
			String nombre = datosDePromocion.get(1);
			String tipo = datosDePromocion.get(2);
			Set<Atraccion> atraccionesEnPromocion = getSetDeAtraccionesDeString(datosDePromocion.get(4), tipo);
			
			String tipoDePromocion = datosDePromocion.get(0);
			if (tipoDePromocion.equals(PROMOCION_PORCENTUAL)) {
				double porcentajeDescuento = Double.parseDouble(datosDePromocion.get(3));
				promocion = new PromocionPorcentual(nombre, tipo, atraccionesEnPromocion, porcentajeDescuento);
			} else if (tipoDePromocion.equals(PROMOCION_ABSOLUTA)) {
				double costoTotal = Double.parseDouble(datosDePromocion.get(3));
				promocion = new PromocionAbsoluta(nombre, tipo, atraccionesEnPromocion, costoTotal);
			} else if (tipoDePromocion.equals(PROMOCION_AxB)) {
				Set<Atraccion> atraccionesDeRegalo = getSetDeAtraccionesDeString(datosDePromocion.get(3), tipo);
				promocion = new PromocionAxB(nombre, tipo, atraccionesEnPromocion, atraccionesDeRegalo);
			} else {
				throw new RuntimeException("El tipo de promocion \"" + tipoDePromocion + "\" no existe.");
			}
			
			this.promociones.add(promocion);
		}
	}
	
	private Set<Atraccion> getSetDeAtraccionesDeString(String listaDeAtraccionesString, String tipo) {
		Set<Atraccion> atraccionesEnPromocion = new HashSet<Atraccion>();

		// Se usa "\\+" para decir "el signo +" porque el signo + es un caracter reservado en Regex
		for (String nombreDeAtraccion : listaDeAtraccionesString.split("\\+")) {
			Atraccion atraccion = getAtraccionPorSuNombre(nombreDeAtraccion.trim()); // .trim() Le saca espacios en blanco al principio y al final del String.
			
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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	public List<Promocion> getPromociones() {
		return promociones;
	}

}