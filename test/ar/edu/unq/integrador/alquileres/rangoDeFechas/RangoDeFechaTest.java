package ar.edu.unq.integrador.alquileres.rangoDeFechas;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RangoDeFechaTest {

	private RangoDeFechas rangoDeFechas;
	private RangoDeFechas rangoDeFechas2;

	@BeforeEach
	void setUp() throws Exception {
		rangoDeFechas = new RangoDeFechas(LocalDate.of(2024, 11, 12),LocalDate.of(2024, 12, 15));
		rangoDeFechas2 = new RangoDeFechas(LocalDate.of(2025, 11, 12),LocalDate.of(2024, 12, 15));
	
	}

	@Test
	void testVerificarSiElRangoEsCompatible() {
		assertTrue(rangoDeFechas.esRangoCompatible());
		
	}
	
	@Test
	void testVerificarCuandoNoElRangoEsCompatible() {
		assertFalse(rangoDeFechas2.esRangoCompatible());
	
	}
	
	@Test
	void testEstaDentroDeLasFechas() {
		//Verificar que una fecha dentro del rango sea válida
		assertTrue(rangoDeFechas.estaDentroDeLasFechas(LocalDate.of(2024, 11, 20)));
		//Verificar que una fecha fuera del rango sea incorrecta
		assertFalse(rangoDeFechas.estaDentroDeLasFechas(LocalDate.of(2024, 12, 20)));
		//Verificar que una fecha en el inicio (no incluido) sea incorrecta
		assertFalse(rangoDeFechas.estaDentroDeLasFechas(LocalDate.of(2024, 11, 12)));
		//Verificar que una fecha en el final (no incluido) sea incorrecta
		assertFalse(rangoDeFechas.estaDentroDeLasFechas(LocalDate.of(2024, 12, 15)));
	
	}
	
	@Test
	void testVerificarSiLasFechasSonCorrectas() {
		assertEquals(LocalDate.of(2024, 11, 12), rangoDeFechas.getInicio());
		assertEquals(LocalDate.of(2024, 12, 15), rangoDeFechas.getFinal());
		
	}
	
	@Test
	void testSeSuperponenDias() {
		//Se verifica que los rangos no se superponen
		assertFalse(rangoDeFechas.seSuperponenDias(rangoDeFechas2));
		//Se verifica que los rangos se superponen
		RangoDeFechas rangoSuperpuesto = new RangoDeFechas(LocalDate.of(2024, 12, 10), LocalDate.of(2024, 12, 20));
		assertTrue(rangoDeFechas.seSuperponenDias(rangoSuperpuesto));
		
	}

}
