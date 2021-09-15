package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import turismo.Atraccion;
import turismo.InteraccionConUsuarios;
import turismo.LectorArchivosEntrada;
import turismo.Ofertable;
import turismo.Promocion;
import turismo.Usuario;

public class TuriTests {
	
	static LectorArchivosEntrada lectorArchivosEntrada;

	@BeforeClass
	public static void setUp() {
		try {
			lectorArchivosEntrada = new LectorArchivosEntrada("EntradaParaJUnit/Usuarios.csv", "EntradaParaJUnit/Atracciones.csv", "EntradaParaJUnit/Promociones.csv");
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		if (lectorArchivosEntrada == null) {
			fail("No se pudo cargar los archivos de entrada.");
		}
	}
	
	@Test
	public void queCargaBienLosArchivosDeEntrada() {
		Usuario eowyn = lectorArchivosEntrada.getUsuarios().get(0);
		assertEquals(eowyn.getNombre(), "Eowyn");
		assertEquals(eowyn.getTipoDeAtraccionPreferida(), "Aventura");
		assertEquals(eowyn.getPresupuesto(), 10, 0.01);
		assertEquals(eowyn.getTiempoDisponible(), 8, 0.01);
		
		Usuario galadriel = lectorArchivosEntrada.getUsuarios().get(3);
		assertEquals(galadriel.getNombre(), "Galadriel");
		assertEquals(galadriel.getTipoDeAtraccionPreferida(), "Paisaje");
		assertEquals(galadriel.getPresupuesto(), 120, 0.01);
		assertEquals(galadriel.getTiempoDisponible(), 6, 0.01);
		
		Atraccion mordor = lectorArchivosEntrada.getAtracciones().get(3);
		assertEquals(mordor.getNombre(), "Mordor");
		assertEquals(mordor.getCosto(), 25, 0.01);
		assertEquals(mordor.getDuracion(), 3, 0.01);
		assertEquals(mordor.getCupo(), 4, 0.01);
		assertEquals(mordor.getTipo(), "Aventura");
		
		Atraccion abismoDeHelm = lectorArchivosEntrada.getAtracciones().get(4);
		assertEquals(abismoDeHelm.getNombre(), "Abismo de Helm");
		assertEquals(abismoDeHelm.getCosto(), 5, 0.01);
		assertEquals(abismoDeHelm.getDuracion(), 2, 0.01);
		assertEquals(abismoDeHelm.getCupo(), 15, 0.01);
		assertEquals(abismoDeHelm.getTipo(), "Paisaje");
		
		// AxB, Pack paisajes,  Paisaje, Erebor, Minas Tirith + Abismo de Helm
		
		Promocion packPaisajes = lectorArchivosEntrada.getPromociones().get(2);
		
		assertEquals(packPaisajes.getNombre(), "Pack paisajes");
		assertEquals(packPaisajes.getTipo(), "Paisaje");
		assertEquals(packPaisajes.getDuracion(), 4.5, 0.01);
		
		
	}

	@Test
	public void queRespetaElOrdenDeOfertasQueDiceLaConsigna() {
		
		/*
"Deberá priorizarse la oferta de promociones, las atracciones más
caras y que requieran mayor tiempo, en ese orden."
		 */
		
		InteraccionConUsuarios interaccionConUsuario = new InteraccionConUsuarios(
				lectorArchivosEntrada.getUsuarios(),
				lectorArchivosEntrada.getAtracciones(),
				lectorArchivosEntrada.getPromociones()
		);
		
		Usuario eowyn = lectorArchivosEntrada.getUsuarios().get(0);
		
		List<Ofertable> ofertasParaEowyn = interaccionConUsuario.getOfertasParaUsuario(eowyn);

		assertEquals(ofertasParaEowyn.get(0).getTipo(), eowyn.getTipoDeAtraccionPreferida());
		

		Ofertable ultimoOfertable = ofertasParaEowyn.get(0);
		
		for (Ofertable oferta : ofertasParaEowyn) {
			// Primero vienen las promociones y atracciones del tipo preferido por el usuario, pero despues
			// las atracciones pueden ser mas caras o durar mas
			if (oferta.getCosto() > ultimoOfertable.getCosto()) {
				if (!(ultimoOfertable instanceof Promocion && oferta instanceof Atraccion)) {
					if (!ultimoOfertable.getTipo().equals(eowyn.getTipoDeAtraccionPreferida())) {
						fail("Error: Las ofertas no estan ordenadas por mayor a menor costo.");
					}
				}
			}
			if (oferta.getDuracion() > ultimoOfertable.getDuracion()) {
				if (!(ultimoOfertable instanceof Promocion && oferta instanceof Atraccion)) {
					if (ultimoOfertable.getCosto() < oferta.getCosto()) {
						if (!ultimoOfertable.getTipo().equals(eowyn.getTipoDeAtraccionPreferida())) {
							fail("Error: Las ofertas no estan ordenadas por mayor a menor duracion.");
						}
					}

				}
			}
			ultimoOfertable = oferta;
		}



	}


	@Test
	public void UsuarioBajaPresupuesto() {

		Usuario tester = new Usuario("Tester", 200, 24, "Aventura");
		Atraccion aventutest = new Atraccion("Avetutest", 15, 12, 4, "Aventura");
		tester.aceptarOferta(aventutest);
		assertEquals(185, tester.getPresupuesto(), 0.01);

	}

	@Test
	public void UsuarioBajaTiempoDisponible() {

		Usuario tester = new Usuario("Tester", 200, 24, "Aventura");
		Atraccion aventutest = new Atraccion("Avetutest", 15, 12, 4, "Aventura");
		tester.aceptarOferta(aventutest);
		assertEquals(12, tester.getTiempoDisponible(), 0.01);

	}

	@Test
	public void yaTieneAtraccion() {

		Usuario tester = new Usuario("Tester", 200, 24, "Aventura");
		Atraccion aventutest = new Atraccion("Avetutest", 15, 12, 4, "Aventura");
		tester.aceptarOferta(aventutest);
		assertTrue(tester.yaTieneAtraccion(aventutest));
	}
	

	@Test
	public void podriaAceptarOferta() {

		Usuario tester = new Usuario("Tester", 200, 24, "Aventura");
		Atraccion aventutest = new Atraccion("Avetutest", 15, 12, 4, "Aventura");
		assertTrue(tester.podriaAceptarOferta(aventutest));
	}

}
