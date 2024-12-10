package ar.edu.unq.integrador.alquileres.ranking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class GestorDeRankingTest {

	private GestorDeRanking gestor;
	@Mock private Ranking calificacion;
	@Mock private Ranking otraCalificacion;

	@BeforeEach
	void setUp() throws Exception {
		gestor = new GestorDeRanking();
		calificacion = mock (Ranking.class);
		otraCalificacion = mock(Ranking.class);
	}
	
	
	@Test
	void testAgregarCalificacion() {
		gestor.agregarCalificacion(calificacion);
		assertTrue(gestor.getRanking().contains(calificacion));
	}
	
	@Test
	void testVerPuntaje() {
		when(calificacion.getCategoria()).thenReturn("Limpieza");
		when(calificacion.getPuntaje()).thenReturn(8);
		gestor.agregarCalificacion(calificacion);
		assertEquals(gestor.verPuntaje("Limpieza"), 8);
	}

	@Test
	void testVerPromedio() {
		gestor.agregarCalificacion(calificacion);
		gestor.agregarCalificacion(otraCalificacion);
		when(calificacion.getPuntaje()).thenReturn(4);
		when(otraCalificacion.getPuntaje()).thenReturn(2);
		assertEquals(3, gestor.verPromedioRanking());
	}
	
	@Test
	void testVerComentarios() {
		gestor.agregarCalificacion(calificacion);
		when(calificacion.getComentario()).thenReturn("Excelente atención por parte de los dueños");
		assertEquals(1, gestor.verComentarios().size());
		assertTrue(gestor.verComentarios().contains("Excelente atención por parte de los dueños"));
		gestor.agregarCalificacion(otraCalificacion);
		when(otraCalificacion.getComentario()).thenReturn("Buena atencion");
		assertEquals(2, gestor.verComentarios().size());
		assertTrue(gestor.verComentarios().contains("Buena atencion"));
	}
	
}
