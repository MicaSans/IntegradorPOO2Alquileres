package ar.edu.unq.integrador.alquileres;

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
		assertTrue(rangoDeFechas.estaDentroDeLasFechas(LocalDate.of(2024, 11, 20)));
		assertFalse(rangoDeFechas.estaDentroDeLasFechas(LocalDate.of(2024, 12, 20)));
	}

}
