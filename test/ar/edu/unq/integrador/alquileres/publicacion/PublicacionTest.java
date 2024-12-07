package ar.edu.unq.integrador.alquileres.publicacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import ar.edu.unq.integrador.alquileres.publicacion.inmueble.Inmueble;
import ar.edu.unq.integrador.alquileres.publicacion.politicaDeCancelacion.PoliticaDeCancelacion;
import ar.edu.unq.integrador.alquileres.rangoDeFechas.RangoDeFechas;
import ar.edu.unq.integrador.alquileres.ranking.Ranking;
import ar.edu.unq.integrador.alquileres.reserva.Reserva;

class PublicacionTest {

	private Publicacion publicacion;
	@Mock private Inmueble inmueble;
	@Mock private Foto foto;
	private List<Foto> fotos;
	@Mock private PoliticaDeCancelacion politicaDeCancelacion;
	@Mock private PoliticaDeCancelacion politicaDeCancelacion2;
	@Mock private Ranking ranking;
	@Mock private FormaDePago formaDePago;
	private List<FormaDePago> formasDePago;
	@Mock private RangoDeFechas rangoDeFechas;
	@Mock private RangoDeFechas diasNormales;
	@Mock private RangoDeFechas diasEspeciales;
	@Spy private Reserva reserva;

	@BeforeEach
	void setUp() throws Exception {
		inmueble = mock(Inmueble.class);
		foto = mock(Foto.class);
		fotos = new ArrayList<Foto>();
		fotos.add(foto);
		politicaDeCancelacion = mock(PoliticaDeCancelacion.class);
		politicaDeCancelacion2 = mock(PoliticaDeCancelacion.class);
		formaDePago = mock(FormaDePago.class);
		formasDePago = Arrays.asList(formaDePago);
		publicacion = new Publicacion(inmueble, LocalTime.of(10, 0), LocalTime.of(20, 0), 1000d, politicaDeCancelacion, formasDePago);
		ranking = mock(Ranking.class);
		rangoDeFechas = mock(RangoDeFechas.class);
		reserva = spy(Reserva.class);
		// Test precio en un rango de dias
		diasNormales = mock(RangoDeFechas.class);
		diasEspeciales =mock(RangoDeFechas.class);
	}

	@Test
	void testSetPoliticaDeCancelacion() {
		publicacion.setPoliticaCancelacion(politicaDeCancelacion2);
		assertEquals(politicaDeCancelacion2, publicacion.getPoliticaDeCancelacion());
		assertNotEquals(politicaDeCancelacion, publicacion.getPoliticaDeCancelacion());
	}
	
	@Test
	void testAgregarFoto() {
		publicacion.agregarFoto(foto);
		assertTrue(publicacion.getFotos().contains(foto));	
	}
	
	@Test
	void testAgregarCalificacion() {
		publicacion.agregarCalificacion(ranking);
		assertTrue(publicacion.getRanking().contains(ranking));
	}
	
	@Test
	void testVerPuntajeCategoria() {
		when(ranking.getCategoria()).thenReturn("Comodidad");
		when(ranking.getPuntaje()).thenReturn(5);
		publicacion.agregarCalificacion(ranking);
		assertEquals(5, publicacion.verPuntajeCategoria("Comodidad"));
	}
	
	@Test
	void testVerPromedioTotal() {
		when(ranking.getPuntaje()).thenReturn(5);
		publicacion.agregarCalificacion(ranking);
		assertEquals(5, publicacion.verPromedioTotal());
	}
	
	@Test 
	void testVerComentarios() {
		when(ranking.getComentario()).thenReturn("Muy linda casa");
		publicacion.agregarCalificacion(ranking);
		assertTrue(publicacion.verComentarios().contains("Muy linda casa"));
	}
	
	@Test 
	void testAgregarADiasOcupados() {
		publicacion.agregarADiasOcupados(rangoDeFechas);
		assertTrue(publicacion.getDiasOcupados().contains(rangoDeFechas));
	}
	
	@Test 
	void testQuitarAgregarADiasOcupados() {
		publicacion.agregarADiasOcupados(rangoDeFechas);
		publicacion.quitarADiasOcupados(rangoDeFechas);
		assertFalse(publicacion.getDiasOcupados().contains(rangoDeFechas));
	}
	
	@Test
	void testCambiarPrecioPorDia() {
		publicacion.cambiarPrecioPorDia(900d);
		assertEquals(900d, publicacion.getPrecioPorDia());
	}
	
	@Test
	void testSetPorcentajeDiaEspecial() {
		publicacion.setPorcentajeDiaEspecial(2);
		assertEquals(publicacion.getPorcentajeDiaEspecial(), 2);
	}
	
	@Test
	void testAgregarDiasEspeciales() {
		publicacion.agregarDiasEspeciales(rangoDeFechas);
		assertTrue(publicacion.getDiasEspeciales().contains(rangoDeFechas));	
	}
	
	@Test
	void testGetPrecioDiasNormales() {
		when(diasNormales.getInicio()).thenReturn(LocalDate.of(2024, 12, 1));
		when(diasNormales.getFinal()).thenReturn(LocalDate.of(2024, 12, 5));
		assertEquals(5000d, publicacion.getPrecio(diasNormales));
	}
	
	@Test
	void testGetPrecioDiasEspeciales() {
		when(diasNormales.getInicio()).thenReturn(LocalDate.of(2024, 12, 1));
		when(diasNormales.getFinal()).thenReturn(LocalDate.of(2024, 12, 5));
		when(diasEspeciales.estaDentroDeLasFechas(LocalDate.of(2024, 12, 1))).thenReturn(true);
		publicacion.setPorcentajeDiaEspecial(20);
		publicacion.agregarDiasEspeciales(diasEspeciales);
		assertNotEquals(5000d, publicacion.getPrecio(diasNormales));
		assertEquals(5200d, publicacion.getPrecio(diasNormales));
	}
	
	@Test
	void testCancelarReserva() {
		publicacion.cancelarReserva(rangoDeFechas);
		verify(politicaDeCancelacion).cancelarReserva(rangoDeFechas);
	}
	
	@Test
	void testAgregarACondicionales() {
		publicacion.agregarACondicionales(reserva);
		assertTrue(publicacion.getCondicionales().contains(reserva));
	}

}