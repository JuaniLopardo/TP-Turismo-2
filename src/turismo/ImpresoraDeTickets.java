package turismo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ImpresoraDeTickets {

	private List<Usuario> usuarios;
	private List<String> ticketsParaSalida;
	
	private static String headerCSV = "Nombre, Total a Pagar, Horas Necesarias, Atracciones a Visitar";
	
	public ImpresoraDeTickets(List<Usuario> usuarios, List<String> ticketsParaSalida) {
		this.usuarios = usuarios;
		this.ticketsParaSalida = ticketsParaSalida;
	}
	
	public void imprimirTickets() {
		for (int i = 0; i < this.usuarios.size(); i++) {
			Usuario usuario = this.usuarios.get(i);
			String ticketUsuario = this.ticketsParaSalida.get(i);
			BufferedWriter bufferedWriter;
			try {
				bufferedWriter = new BufferedWriter(new FileWriter("Salida/" + usuario.getNombre() + ".csv"));
				bufferedWriter.append(headerCSV);
				bufferedWriter.newLine();
				bufferedWriter.append(ticketUsuario);
				bufferedWriter.newLine();
				bufferedWriter.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}

}
