package turismo;

import java.io.IOException;

public class App {
	
	public static void main(String[] args) {
		LectorArchivosEntrada lectorArchivosEntrada = null;
		
		try {
			lectorArchivosEntrada = new LectorArchivosEntrada("Entrada/Usuarios.csv", "Entrada/Atracciones.csv", "Entrada/Promociones.csv");
			
			
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		
		if (lectorArchivosEntrada != null) {
			InteraccionConUsuarios interaccionConUsuario = new InteraccionConUsuarios(
					lectorArchivosEntrada.getUsuarios(),
					lectorArchivosEntrada.getAtracciones(),
					lectorArchivosEntrada.getPromociones()
			);
			interaccionConUsuario.hacerLoop();
		}
	}

}
