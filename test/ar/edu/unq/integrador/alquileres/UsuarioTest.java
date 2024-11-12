package ar.edu.unq.integrador.alquileres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class UsuarioTest {

	private Usuario usuario;
	@Mock private Ranking ranking;
	@Mock private Ranking otroRanking;
	
	@BeforeEach
	void setUp() throws Exception {
		usuario = new Usuario("Francisco","francisco@gmail.com","48596745"); //SUT
		ranking = mock(Ranking.class);
		otroRanking = mock(Ranking.class);
	}

	@Test
	void testAgregarCalificacionPropietario() {
		usuario.agregarCalificacionPropietario(ranking);
		assertTrue(usuario.getRankingPropietario().size() == 1);
		assertTrue(usuario.getRankingPropietario().contains(ranking));
	}
	
	@Test
	void testAgregarCalificacionInquilino() {
		usuario.agregarCalificacionInquilino(ranking);
		assertTrue(usuario.getRankingInquilino().size() == 1);
		assertTrue(usuario.getRankingInquilino().contains(ranking));
	}
	
	@Test
	void testVerPuntajePropietario() {
		usuario.agregarCalificacionPropietario(ranking);
		when(ranking.getCategoria()).thenReturn("Amabilidad");
		when(ranking.getPuntaje()).thenReturn(5);
		assertEquals(5, usuario.verPuntajePropietario("Amabilidad"));
	}
	
	@Test
	void testVerPuntajeInquilino() {
		usuario.agregarCalificacionInquilino(ranking);
		when(ranking.getCategoria()).thenReturn("Puntualidad");
		when(ranking.getPuntaje()).thenReturn(3);
		assertEquals(3, usuario.verPuntajeInquilino("Puntualidad"));
	}
	
	@Test
	void testVerPromedioPropietario() {
		usuario.agregarCalificacionPropietario(ranking);
		usuario.agregarCalificacionPropietario(otroRanking);
		when(ranking.getPuntaje()).thenReturn(4);
		when(otroRanking.getPuntaje()).thenReturn(2);
		assertEquals(3, usuario.verPromedioPropietario());
	}
	
	@Test
	void testVerPromedioInquilino() {
		usuario.agregarCalificacionInquilino(ranking);
		usuario.agregarCalificacionInquilino(otroRanking);
		when(ranking.getPuntaje()).thenReturn(3);
		when(otroRanking.getPuntaje()).thenReturn(1);
		assertEquals(2, usuario.verPromedioInquilino());
	}
	
	@Test
	void testSetFechaInicio() {
		usuario.setFechaInicioUsuario(LocalDate.now());
		assertEquals(usuario.getFechaInicioUsuario(), LocalDate.now());
	}
	
	@Test
	void testGetNombre() {
		assertEquals("Francisco", usuario.getNombre());
	}
	
	@Test
	void testGetMail() {
		assertEquals("francisco@gmail.com", usuario.getEmail());
	}
	
	@Test
	void testGetTelefono() {
		assertEquals("48596745", usuario.getTelefono());
	}
	
	@Test
	void testGetRankingPropietario() {
		//Como en el testAgregarCalificacionPropietario() ya se testea la funcionalidad del método getRankingPropietario(), testeo otra cosa (que la lista está vacía por ejemplo)
		assertTrue(usuario.getRankingPropietario().isEmpty());
	}
	
	@Test
	void testGetRankingInquilino() {
		//Como en el testAgregarCalificacionInquilino() ya se testea la funcionalidad del método getRankingInquilino(), testeo otra cosa (que la lista está vacía por ejemplo)
		assertTrue(usuario.getRankingInquilino().isEmpty());
	}
	
	@Test
	void testGetFechaInicioUsuario() {
		//Se testea de igual forma que el testSetFechaInicioUsuario()
		usuario.setFechaInicioUsuario(LocalDate.now());
		assertEquals(usuario.getFechaInicioUsuario(), LocalDate.now());
	}

}
