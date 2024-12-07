package ar.edu.unq.integrador.alquileres.ranking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RankingTest {

	private Ranking ranking;
	
	@BeforeEach
	void setUp() throws Exception {
		ranking = new Ranking(5, "Amabilidad", "Destaco su amabilidad, siempre fue muy atento con nosotros en todas nuestras necesidades"); //SUT
	}

	@Test
	void testGetPuntaje() {
		assertEquals(5, ranking.getPuntaje());
	}
	
	@Test
	void testGetCategoria() {
		assertEquals("Amabilidad", ranking.getCategoria());
	}
	
	@Test
	void testGetComentario() {
		assertEquals("Destaco su amabilidad, siempre fue muy atento con nosotros en todas nuestras necesidades", ranking.getComentario());
	}

}
