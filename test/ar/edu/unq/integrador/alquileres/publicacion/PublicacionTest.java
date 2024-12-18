package ar.edu.unq.integrador.alquileres.publicacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import ar.edu.unq.integrador.alquileres.publicacion.inmueble.Inmueble;
import ar.edu.unq.integrador.alquileres.publicacion.politicaDeCancelacion.PoliticaDeCancelacion;
import ar.edu.unq.integrador.alquileres.rangoDeFechas.FechaEspecial;
import ar.edu.unq.integrador.alquileres.rangoDeFechas.RangoDeFechas;
import ar.edu.unq.integrador.alquileres.ranking.Ranking;
import ar.edu.unq.integrador.alquileres.reserva.Reserva;

class PublicacionTest {

	private Publicacion publicacion;
	@Mock private Inmueble inmueble;
	@Mock private Foto foto;
	@Mock private Foto foto1;
	@Mock private Foto foto2;
	@Mock private Foto foto3;
	@Mock private Foto foto4;
	@Mock private Foto foto5;
	@Mock private Foto foto6;
	//private List<Foto> fotos;
	@Mock private PoliticaDeCancelacion politicaDeCancelacion;
	@Mock private PoliticaDeCancelacion politicaDeCancelacion2;
	@Mock private Ranking ranking;
	@Mock private FormaDePago formaDePago;
	private List<FormaDePago> formasDePago;
	@Mock private RangoDeFechas rangoDeFechas;
	@Mock private RangoDeFechas diasNormales;
	@Mock private RangoDeFechas diasEspeciales;
	@Spy private Reserva reserva;
	@Mock private FechaEspecial navidad;
	@Mock private FechaEspecial finDeAño;

	@BeforeEach
	void setUp() throws Exception {
		inmueble = mock(Inmueble.class);
		foto = mock(Foto.class);
		foto1 = mock(Foto.class);
		foto2 = mock(Foto.class);
		foto3 = mock(Foto.class);
		foto4 = mock(Foto.class);
		foto5 = mock(Foto.class);
		foto6 = mock(Foto.class);
		//fotos = new ArrayList<Foto>();
		//fotos.add(foto);
		politicaDeCancelacion = mock(PoliticaDeCancelacion.class);
		politicaDeCancelacion2 = mock(PoliticaDeCancelacion.class);
		formaDePago = mock(FormaDePago.class);
		formasDePago = Arrays.asList(formaDePago);
		publicacion = new Publicacion(inmueble, LocalTime.of(10, 0), LocalTime.of(20, 0), 1000d, politicaDeCancelacion, formasDePago); //SUT
		ranking = mock(Ranking.class);
		rangoDeFechas = mock(RangoDeFechas.class);
		reserva = spy(Reserva.class);
		// Test precio en un rango de dias
		diasNormales = mock(RangoDeFechas.class);
		diasEspeciales = mock(RangoDeFechas.class);
		navidad = mock(FechaEspecial.class);
		finDeAño = mock(FechaEspecial.class);
		
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
	void testNoAgregarFoto() {
		publicacion.agregarFoto(foto1);
		publicacion.agregarFoto(foto2);
		publicacion.agregarFoto(foto3);
		publicacion.agregarFoto(foto4);
		publicacion.agregarFoto(foto5);
		publicacion.agregarFoto(foto6);
		publicacion.agregarFoto(foto);
		assertFalse(publicacion.getFotos().contains(foto));
		
	}
	
	@Test
	void testAgregarCalificacion() {
		publicacion.agregarCalificacion(ranking);
		assertTrue(publicacion.gestorContiene(ranking));
	
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
	
	//@Test 
	//void testQuitarAgregarADiasOcupados() {
	//	publicacion.agregarADiasOcupados(rangoDeFechas);
	//	publicacion.quitarADiasOcupados(rangoDeFechas);
	//	assertFalse(publicacion.getDiasOcupados().contains(rangoDeFechas));
	//
	//}
	
	@Test
	void testQuitarADiasOcupados() {
		//Configuro lista de días ocupados
		publicacion.agregarADiasOcupados(diasEspeciales);
		publicacion.agregarADiasOcupados(diasNormales);
		
		//Ejecuto el método
		publicacion.quitarADiasOcupados(diasNormales);
		
		//Verifico que los dias normales hayan sido eliminados
		assertFalse(publicacion.getDiasOcupados().contains(diasNormales));
		
		//Verifico que los dias especiales sigan en la lista
		assertTrue(publicacion.getDiasOcupados().contains(diasEspeciales));
		
	}
	
	@Test
	void testCambiarPrecioPorDia() {
		publicacion.cambiarPrecioPorDia(900d);
		assertEquals(900d, publicacion.getPrecioPorDia());
	
	}
	
	@Test
	void testGetInmueble() {
		assertEquals(publicacion.getInmueble(), inmueble);
	
	}
	
	@Test
	void testGetFormasDePago() {
		assertEquals(publicacion.getFormasDePago(), formasDePago);
		assertTrue(formasDePago.contains(formaDePago));
		
	}
	
	@Test
	void testGetHorarioCheckIn() {
		assertEquals(publicacion.getHorarioCheckIn(), LocalTime.of(10, 0));
		
	}
	
	@Test
	void testGetHorarioCheckOut() {
		assertEquals(publicacion.getHorarioCheckOut(), LocalTime.of(20, 0));
	
	}
	/*
	@Test
	void testSetPorcentajeDiaEspecial() {
		publicacion.setPorcentajeDiaEspecial(2);
		assertEquals(publicacion.getPorcentajeDiaEspecial(), 2);
	
	}
	*/
	@Test
	void testAgregarDiasEspeciales() {
		publicacion.agregarDiasEspeciales(navidad);
		assertTrue(publicacion.getDiasEspeciales().contains(navidad));	
	}
	
	@Test
	void testAgregarFechasSimilaresEspeciales() {
		when(finDeAño.getFecha()).thenReturn(diasEspeciales);
		when(navidad.getFecha()).thenReturn(diasEspeciales);
		when(diasEspeciales.seSuperponenDias(diasEspeciales)).thenReturn(true);
		publicacion.agregarDiasEspeciales(navidad);
		publicacion.agregarDiasEspeciales(finDeAño);
		assertTrue(publicacion.getDiasEspeciales().contains(navidad));
		assertFalse(publicacion.getDiasEspeciales().contains(finDeAño));	
	}
	
	
	@Test
	void testGetPrecioDiasNormales() {
		when(diasNormales.getInicio()).thenReturn(LocalDate.of(2024, 12, 1));
		when(diasNormales.getFinal()).thenReturn(LocalDate.of(2024, 12, 5));
		assertEquals(5000d, publicacion.getPrecio(diasNormales));
	
	}
	
	@Test
	void testGetPrecioDiasEspeciales() {
		when(diasNormales.getInicio()).thenReturn(LocalDate.of(2024, 12, 20));
		when(diasNormales.getFinal()).thenReturn(LocalDate.of(2024, 12, 23));
		when(navidad.coincideConLaFecha(LocalDate.of(2024, 12, 23))).thenReturn(true);
		when(navidad.coincideConLaFecha(LocalDate.of(2024, 12, 22))).thenReturn(true);
		when(navidad.getPrecio()).thenReturn(2000d);
		//publicacion.setPorcentajeDiaEspecial(20);
		publicacion.agregarDiasEspeciales(navidad);
		assertNotEquals(4000d, publicacion.getPrecio(diasNormales));
		assertEquals(6000d, publicacion.getPrecio(diasNormales));
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
	
	@Test
	void testTieneCapacidadPara() {
		//Configuración del mock para que devuelva true
		when(inmueble.tieneCapacidadPara(4)).thenReturn(true);
		
		//Llamo al método y verifico el resultado
		assertTrue(publicacion.tieneCapacidadPara(4));
		
		//Verifico que el método haya delegado correctamente
		verify(inmueble).tieneCapacidadPara(4);
		
	}
	
	@Test
	void testNoTieneCapacidadPara() {
		//Configuración del mock para que devuelva false
		when(inmueble.tieneCapacidadPara(6)).thenReturn(false);
				
		//Llamo al método y verifico el resultado
		assertFalse(publicacion.tieneCapacidadPara(6));
				
		//Verifico que el método haya delegado correctamente
		verify(inmueble).tieneCapacidadPara(6);
				
	}
	
	@Test
	void testTieneSuperposicionCon() {
		//Configuracion del mock para el rango de fechas y su comportamiento
		when(rangoDeFechas.esRangoCompatible()).thenReturn(true);
		when(diasNormales.seSuperponenDias(rangoDeFechas)).thenReturn(true); //Simulo que los días se superponen
		
		//Configuro el día ocupado de la publicación para que se superponga con el rango
		publicacion.agregarADiasOcupados(diasNormales);
		
		//Verifico la superposición de días
		assertTrue(publicacion.tieneSuperposicionDeDiasCon(rangoDeFechas));
		
		//Verifico que el mock interactuó con el método de superposición de días
		verify(diasNormales, times(1)).seSuperponenDias(rangoDeFechas);
		
	}
	
	@Test
	void testGetCiudad() {
		//Configuracion
		when(inmueble.getCiudad()).thenReturn("Buenos Aires");
		
		//Acción
		String ciudad = publicacion.getCiudad();
		
		//Verificación
		assertEquals("Buenos Aires", ciudad);
		
	}

	@Test
	void testGetTipoInmueble() {
		//Configuracion
		when(inmueble.getTipoInmueble()).thenReturn("Casa");
				
		//Acción
		String tipoInmueble = publicacion.getTipoInmueble();
				
		//Verificación
		assertEquals("Casa", tipoInmueble);
				
	}
	
}
