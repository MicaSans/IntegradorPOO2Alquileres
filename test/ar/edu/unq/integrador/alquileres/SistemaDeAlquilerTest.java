package ar.edu.unq.integrador.alquileres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class SistemaDeAlquilerTest {
	private SistemaDeAlquiler sistema;
	@Mock private Usuario usuario;
	@Mock private Publicacion publicacion;
	@Mock private Busqueda busqueda;
	private List<Publicacion> publicaciones;
	@Mock private FormaDePago efectivo;
	@Mock private Reserva reserva;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	@Mock private PoliticaDeCancelacion politicaDeCancelacion;
	@Mock private Ranking calificacion;
	@Mock private Reserva reserva2;
	@Mock private Usuario usuario2;
	
	@BeforeEach
	void setUp() throws Exception {
		// testRegistrarUsuario()
		sistema = new SistemaDeAlquiler(); // SUT
		usuario = mock(Usuario.class);
		// testGenerarPublicacion() 
		publicacion = mock(Publicacion.class);
		// testBusquedaDePublicaciones()
		busqueda = mock(Busqueda.class);
		publicaciones = Arrays.asList(publicacion);
		// testReservarPublicacion()
		efectivo = mock(FormaDePago.class);
		reserva = mock(Reserva.class);
		fechaEntrada = LocalDate.of(2024, 11, 15);
		fechaSalida =  LocalDate.of(2024, 11, 30);
		// testCambiarPoliticaDeCancelacion()
		politicaDeCancelacion = mock(PoliticaDeCancelacion.class);
		// testCalificar()
		calificacion = mock(Ranking.class);
		// testVerReservas()
		reserva2 = mock(Reserva.class);
		usuario2 = mock(Usuario.class);
		
		
	}

	@Test
	void testRegistrarUsuario() {
		sistema.registrarUsuario(usuario);
		assertTrue(sistema.getUsuarios().contains(usuario));
	}
	
	@Test
	void testGenerarPublicacion() {
		sistema.generarPublicacion(publicacion);
		assertTrue(sistema.getPublicaciones().contains(publicacion));
	}
	
	@Test 
	void testBusquedaDePublicaciones() {
		sistema.generarPublicacion(publicacion);
		when (sistema.buscarPublicaciones(busqueda)).thenReturn(publicaciones);
		List <Publicacion> publicacionesFiltradas = sistema.buscarPublicaciones(busqueda);
		assertTrue(publicacionesFiltradas.contains(publicacion));	
	}
	
	@Test
	void testReservarPublicacion() {
		sistema.reservarPublicacion(reserva);
		assertTrue(sistema.getReservas().contains(reserva));
	}
	
	@Test 
	void testSetCategoriasPuntuacion () {
		sistema.setCategoriasPuntuacion("categoria");
		assertTrue(sistema.getCategorias().contains("categoria"));
	}
	
	@Test 
	void testSetTipoInmueble () {
		sistema.setTipoInmueble("tipoInmueble");
		assertTrue(sistema.getTipoInmuebles().contains("tipoInmueble"));
	}
	
	@Test 
	void testSetServicio () {
		sistema.setServicios("servicio");
		assertTrue(sistema.getServicios().contains("servicio"));
	}
	
	@Test
	void testAceptarReserva() {
		sistema.aceptarReserva(reserva);
		verify(reserva).aceptarReserva();
	}
	
	@Test
	void testRechazarReserva() {
		sistema.rechazarReserva(reserva);
		verify(reserva).rechazarReserva();
	}
	
	@Test
	void testCancelarReserva() {
		sistema.cancelarReserva(reserva);
		verify(reserva).cancelarReserva();
	}
	
	@Test
	void testRealizarCheckOut() {
		sistema.realizarCheckOut(reserva);
		verify(reserva).realizadoDeCheckOut();
	}

	@Test
	void testCambiarPoliticaDeCancelacion() {
		sistema.cambiarPoliticaDeCancelacion(publicacion, politicaDeCancelacion);
		verify(publicacion).setPoliticaCancelacion(politicaDeCancelacion);
	}
	
	@Test
	void testReservarCondicionalmente() {
		sistema.reservarCondicionalmente(reserva, usuario);
		verify(reserva).agregarACondicionales(usuario);
	}
	
	@Test
	void testCalificarInquilino() {
		sistema.calificarInquilino(calificacion, usuario);
		verify(usuario).agregarCalificacionInquilino(calificacion);
	}
	
	@Test
	void testCalificarPropietario() {
		sistema.calificarPropietario(calificacion, usuario);
		verify(usuario).agregarCalificacionPropietario(calificacion);
	}
	
	@Test 
	void testCalificarPublicacion() {
		sistema.calificarPublicacion(calificacion, publicacion);
		verify(publicacion).agregarCalificacion(calificacion);
	}
	
	@Test
	void testVerNombreUsuario() {
		when (usuario.getNombre()).thenReturn("Sebastian");
		assertEquals(sistema.verNombreUsuario(usuario), "Sebastian");
	}
	
	@Test
	void testVerEmailUsuario() {
		when (usuario.getEmail()).thenReturn("Sebastian@gmail.com");
		assertEquals(sistema.verEmailUsuario(usuario), "Sebastian@gmail.com");
	}
	
	@Test
	void testTelefonoUsuario() {
		when (usuario.getTelefono()).thenReturn("42002247");
		assertEquals(sistema.verTelefonoUsuario(usuario), "42002247");
	}
	
	@Test
	void testFechaInicio() {
		when (usuario.getFechaInicioUsuario()).thenReturn(fechaEntrada);
		assertEquals(sistema.verFechaInicioUsuario(usuario), fechaEntrada);
	}
	
	@Test
	void testVerInquilino() {
		when (reserva.getInquilino()).thenReturn(usuario);
		assertEquals(sistema.verInquilino(reserva), usuario);
	}
	
	@Test
	void testVerReservas() {
		when (reserva.getInquilino()).thenReturn(usuario);
		when (reserva2.getInquilino()).thenReturn(usuario2);
		sistema.reservarPublicacion(reserva);
		sistema.reservarPublicacion(reserva2);
		List<Reserva> reservas = sistema.verReservas(usuario);
		assertTrue(reservas.contains(reserva));
		assertFalse(reservas.contains(reserva2));
	}
	
	@Test
	void testVerReservasFuturas() {
		when (reserva.getInquilino()).thenReturn(usuario);
		when (reserva2.getInquilino()).thenReturn(usuario);
		when (reserva.getFechaIngreso()).thenReturn(LocalDate.of(2024, 12, 10));
		when (reserva2.getFechaIngreso()).thenReturn(LocalDate.of(2024, 10, 20));
		sistema.reservarPublicacion(reserva);
		sistema.reservarPublicacion(reserva2);
		List<Reserva> reservasFuturas = sistema.verReservasFuturas(usuario);
		assertTrue(reservasFuturas.contains(reserva));
		assertFalse(reservasFuturas.contains(reserva2));
	}
	
	/* @Test
	void testVerReservasEnCiudad() {
		when (reserva.getInquilino()).thenReturn(usuario);
		when (reserva2.getInquilino()).thenReturn(usuario);
		sistema.reservarPublicacion(reserva);
		sistema.reservarPublicacion(reserva2);
		List<Reserva> reservasEnCiudad = sistema.verReservasEnCiudad(usuario, "Londres");
	}
	
	@Test
	void testVerCiudadesDeLasReservas() {
		when (reserva.getInquilino()).thenReturn(usuario);
		when (reserva2.getInquilino()).thenReturn(usuario);
		sistema.reservarPublicacion(reserva);
		sistema.reservarPublicacion(reserva2);
		List<String> ciudadesDeLasReservas = sistema.verCiudadesDeLasReservas(usuario);
	} 
	 LO TENGO QUE SEGUIR ESTOS DOS NO QUEDA MUCHO*/
}
