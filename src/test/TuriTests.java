package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import turismo.Atraccion;
import turismo.Usuario;

public class TuriTests {

	@BeforeEach
	public void setUp() {
		Usuario tester = new Usuario("Tester", 200, 24, "Aventura");
		Atraccion aventutest = new Atraccion("Avetutest", 15, 12, 4, "Aventura");
		Atraccion degustest = new Atraccion("Degustest", 15, 12, 3, "Degustacion");
		Atraccion paisatest = new Atraccion("Paisatest", 15, 12, 4, "Paisaje");
	}

	@Test
	public void ofreceAtraccionFavorita() {

		Usuario tester = new Usuario("Tester", 200, 24, "Aventura");
		Atraccion aventutest = new Atraccion("Avetutest", 15, 12, 4, "Aventura");
		Atraccion degustest = new Atraccion("Degustest", 15, 12, 3, "Degustacion");
		Atraccion paisatest = new Atraccion("Paisatest", 15, 12, 4, "Paisaje");

		assertTrue(tester.prefiereEsteTipoDeOferta(aventutest));
		assertFalse(tester.prefiereEsteTipoDeOferta(paisatest));
		assertFalse(tester.prefiereEsteTipoDeOferta(degustest));

	}

//	@Test
//	public void AtracciónBajaCupo() {
//		
//		Usuario tester = new Usuario("Tester", 200, 24, "Aventura");
//		Atraccion aventutest = new Atraccion("Avetutest", 15, 12, 4, "Aventura");
//		tester.aceptarOferta(aventutest);
//		assertEquals(3,aventutest.getCupo());
//		
//		
//	}

	@Test
	public void UsuarioBajaPresupuesto() {

		Usuario tester = new Usuario("Tester", 200, 24, "Aventura");
		Atraccion aventutest = new Atraccion("Avetutest", 15, 12, 4, "Aventura");
		tester.aceptarOferta(aventutest);
		assertEquals(185, tester.getPresupuesto());

	}

	@Test
	public void UsuarioBajaTiempoDisponible() {

		Usuario tester = new Usuario("Tester", 200, 24, "Aventura");
		Atraccion aventutest = new Atraccion("Avetutest", 15, 12, 4, "Aventura");
		tester.aceptarOferta(aventutest);
		assertEquals(12, tester.getTiempoDisponible());

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
