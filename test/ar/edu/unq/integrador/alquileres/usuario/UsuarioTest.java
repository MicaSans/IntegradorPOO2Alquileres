package ar.edu.unq.integrador.alquileres.usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.integrador.alquileres.ranking.GestorDeRanking;
import ar.edu.unq.integrador.alquileres.ranking.Ranking;

class UsuarioTest {

	private Usuario usuario;
	@Mock private Ranking ranking;
	@Mock private Ranking otroRanking;
	@Mock private GestorDeRanking gestor;
	
	@BeforeEach
	void setUp() throws Exception {
		gestor = mock(GestorDeRanking.class);
		usuario = new Usuario("Francisco","francisco@gmail.com","48596745"); //SUT
		ranking = mock(Ranking.class);
		otroRanking = mock(Ranking.class);
	}

	@Test
	void testAgregarCalificacionPropietario() {
		usuario.agregarCalificacionPropietario(ranking);
		assertTrue(usuario.gestorPropietarioContiene(ranking));
	}
	  //
	@Test
	void testVerPuntajePropietario() {
		usuario.agregarCalificacionPropietario(ranking);
		when(ranking.getCategoria()).thenReturn("Amabilidad");
		when(ranking.getPuntaje()).thenReturn(5);
		assertEquals(5, usuario.verPuntajePropietario("Amabilidad"));
	}  //
	
	@Test
	void testVerPromedioPropietario() {
		usuario.agregarCalificacionPropietario(ranking);
		usuario.agregarCalificacionPropietario(otroRanking);
		when(ranking.getPuntaje()).thenReturn(4);
		when(otroRanking.getPuntaje()).thenReturn(2);
		assertEquals(3, usuario.verPromedioPropietario());
	}  //
	
	@Test
	void testVerComentariosPropietario() {
		usuario.agregarCalificacionPropietario(ranking);
		when(ranking.getComentario()).thenReturn("Excelente atención por parte de los dueños");
		assertEquals(1, usuario.verComentariosPropietario().size());
		assertTrue(usuario.verComentariosPropietario().contains("Excelente atención por parte de los dueños"));
		usuario.agregarCalificacionPropietario(otroRanking);
		when(otroRanking.getComentario()).thenReturn("Buena atencion");
		assertEquals(2, usuario.verComentariosPropietario().size());
		assertTrue(usuario.verComentariosPropietario().contains("Buena atencion"));
	} //
	
	@Test
	void testAgregarCalificacionInquilino() {
		usuario.agregarCalificacionInquilino(ranking);
		assertTrue(usuario.gestorInquilinoContiene(ranking));
	} //
	
	@Test
	void testVerPuntajeInquilino() {
		usuario.agregarCalificacionInquilino(ranking);
		when(ranking.getCategoria()).thenReturn("Puntualidad");
		when(ranking.getPuntaje()).thenReturn(3);
		assertEquals(3, usuario.verPuntajeInquilino("Puntualidad"));
	} //
	
	@Test
	void testVerPromedioInquilino() {
		usuario.agregarCalificacionInquilino(ranking);
		usuario.agregarCalificacionInquilino(otroRanking);
		when(ranking.getPuntaje()).thenReturn(3);
		when(otroRanking.getPuntaje()).thenReturn(1);
		assertEquals(2, usuario.verPromedioInquilino());
	} //
	
	@Test
	void testVerComentariosInquilino() {
		usuario.agregarCalificacionInquilino(ranking);
		usuario.agregarCalificacionInquilino(otroRanking);
		when(ranking.getComentario()).thenReturn("Inquilinos muy limpios");
		when(otroRanking.getComentario()).thenReturn("Dejaron la propiedad en buen estado");
		assertEquals(2, usuario.verComentariosInquilino().size());
		assertTrue(usuario.verComentariosInquilino().contains("Inquilinos muy limpios"));
		assertTrue(usuario.verComentariosInquilino().contains("Dejaron la propiedad en buen estado"));
	} //
	
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
		usuario.agregarCalificacionPropietario(ranking);
		assertTrue(usuario.getRankingPropietario().contains(ranking));
		
	}  //
	
	@Test
	void testGetRankingInquilino() {
		//Como en el testAgregarCalificacionInquilino() ya se testea la funcionalidad del método getRankingInquilino(), testeo otra cosa (que la lista está vacía por ejemplo)
		assertTrue(usuario.getRankingInquilino().isEmpty());
		usuario.agregarCalificacionInquilino(ranking);
		assertTrue(usuario.getRankingInquilino().contains(ranking));
	} //
	
	@Test
	void testGetFechaInicioUsuario() {
		//Se testea de igual forma que el testSetFechaInicioUsuario()
		usuario.setFechaInicioUsuario(LocalDate.now());
		assertEquals(usuario.getFechaInicioUsuario(), LocalDate.now());
	}

}
