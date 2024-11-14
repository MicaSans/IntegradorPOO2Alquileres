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
	@Mock private PoliticaDeCancelacion politicaDeCancelacion;
	@Mock private Ranking calificacion;
	@Mock private Reserva reserva2;
	@Mock private Usuario usuario2;
	@Mock private Inmueble inmueble;
	@Mock private Foto foto;
	private List<Foto> fotos;
	private List<FormaDePago> formasDePago;
	@Mock private ObservadorDelSistema observer;
	

	
	@BeforeEach
	void setUp() throws Exception {
		// testRegistrarUsuario()
		sistema = new SistemaDeAlquiler(); // SUT
		usuario = mock(Usuario.class);
		// testGenerarPublicacion() 
		publicacion = mock(Publicacion.class);
		publicaciones = Arrays.asList(publicacion);
		// testBusquedaDePublicaciones()
		busqueda = mock(Busqueda.class);
		publicaciones = Arrays.asList(publicacion);
		// testReservarPublicacion()
		efectivo = mock(FormaDePago.class);
		reserva = mock(Reserva.class);
		fechaEntrada = LocalDate.of(2024, 11, 15);
		// testCambiarPoliticaDeCancelacion()
		politicaDeCancelacion = mock(PoliticaDeCancelacion.class);
		// testCalificar()
		calificacion = mock(Ranking.class);
		// testVerReservas()
		reserva2 = mock(Reserva.class);
		usuario2 = mock(Usuario.class);
		// testVerInmueblePublicacion()
		inmueble = mock(Inmueble.class);
		// testVerFotosPublicacion()
		foto = mock(Foto.class);
		fotos = Arrays.asList(foto);
		// testVerFormasDePagoPublicacion(
		formasDePago = Arrays.asList(efectivo);
		
 		observer = mock(ObservadorDelSistema.class);
		
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
	
	//
	@Test 
	void testBusquedaDePublicaciones() {
		sistema.generarPublicacion(publicacion);
		when (busqueda.filtrarPublicaciones(sistema.getPublicaciones())).thenReturn(publicaciones);

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
		sistema.setCategoriaPuntuacion("categoria");
		assertTrue(sistema.getCategorias().contains("categoria"));
	}
	
	@Test 
	void testSetTipoInmueble () {
		sistema.setTipoInmueble("tipoInmueble");
		assertTrue(sistema.getTipoInmuebles().contains("tipoInmueble"));
	}
	
	@Test 
	void testSetServicio () {
		sistema.setServicio("servicio");
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
		when(reserva.cancelarReserva()).thenReturn("Paga lo que debes rata");
		assertEquals(sistema.cancelarReserva(reserva),"Paga lo que debes rata");
		
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
	
	@Test
	void testVerReservasEnCiudad() {
		when (reserva.getInquilino()).thenReturn(usuario);
		when (reserva2.getInquilino()).thenReturn(usuario);
		when (reserva.verCiudadDeReserva()).thenReturn("Londres");
		when (reserva2.verCiudadDeReserva()).thenReturn("Berlin");
		sistema.reservarPublicacion(reserva);
		sistema.reservarPublicacion(reserva2);
		List<Reserva> reservasEnCiudad = sistema.verReservasEnCiudad(usuario, "Londres");
		assertTrue(reservasEnCiudad.contains(reserva));
		assertFalse(reservasEnCiudad.contains(reserva2));
	}
	
	@Test
	void testVerCiudadesDeLasReservas() {
		when (reserva.getInquilino()).thenReturn(usuario);
		when (reserva2.getInquilino()).thenReturn(usuario);
		when (reserva.verCiudadDeReserva()).thenReturn("Londres");
		when (reserva2.verCiudadDeReserva()).thenReturn("Berlin");
		sistema.reservarPublicacion(reserva);
		sistema.reservarPublicacion(reserva2);
		List<String> ciudadesDeLasReservas = sistema.verCiudadesDeLasReservas(usuario);
		assertTrue(ciudadesDeLasReservas.contains("Londres"));
		assertTrue(ciudadesDeLasReservas.contains("Berlin"));
	} 
	
	@Test
	void testVerPuntajePublicacion() {
		when (publicacion.verPuntajeCategoria("Comodidad")).thenReturn(4);
		assertEquals(sistema.verPuntajePublicacion("Comodidad", publicacion),4);
	}
	
	@Test
	void testVerPromedioPublicacion() {
		when (publicacion.verPromedioTotal()).thenReturn(20);
		assertEquals(sistema.verPromedioPublicacion(publicacion), 20);

	}

	@Test
	void testVerInmueblePublicacion() {
		when (publicacion.getInmueble()).thenReturn(inmueble);
		assertEquals(sistema.verInmueblePublicacion(publicacion), inmueble);
	}
	
	@Test
	void testVerFotosPublicacion() {
		when (publicacion.getFotos()).thenReturn(fotos);
		assertEquals(sistema.verFotosPublicacion(publicacion), fotos);
	}
	
	@Test
	void testVerFormasDePagoPublicacion() {
		
		when (publicacion.getFormasDePago()).thenReturn(formasDePago);
		assertEquals(sistema.verFormasDePagoPublicacion(publicacion), formasDePago);
	}
	
	@Test
	void testVerComentariosPublicacion() {
		List <String> comentarios = Arrays.asList("La pase muy bien");
		when (publicacion.verComentarios()).thenReturn(comentarios);
		assertEquals(sistema.verComentariosPublicacion(publicacion), comentarios);
	}
	
	@Test
	void testVerCantidadQueFueAlquilada() {
		when (reserva.getPublicacion()).thenReturn(publicacion);
		when (reserva2.getPublicacion()).thenReturn(publicacion);
		when (reserva.fueAlquilada()).thenReturn(true);
		when (reserva2.fueAlquilada()).thenReturn(false);
		sistema.reservarPublicacion(reserva);
		sistema.reservarPublicacion(reserva2);
		assertEquals(sistema.verCantidadQueFueAlquilada(publicacion), 1);
	}
	
	@Test
	void testVerCuantasVecesAlquiloInmuebles() {
		when (reserva.getPublicacion()).thenReturn(publicacion);
		when (reserva2.getPublicacion()).thenReturn(publicacion);
		when (reserva.fueAlquilada()).thenReturn(true);
		when (reserva2.fueAlquilada()).thenReturn(true);
		when (publicacion.getInmueble()).thenReturn(inmueble);
		when (inmueble.getPropietario()).thenReturn(usuario);
		sistema.reservarPublicacion(reserva);
		sistema.reservarPublicacion(reserva2);
		assertEquals(sistema.verCuantasVecesAlquiloInmuebles(usuario), 2);
	}
	
	@Test 
	void testVerInmueblesAlquilados() {
		when (reserva.getPublicacion()).thenReturn(publicacion);
		when (reserva2.getPublicacion()).thenReturn(publicacion);
		when (reserva.fueAlquilada()).thenReturn(true);
		when (reserva2.fueAlquilada()).thenReturn(true);
		when (publicacion.getInmueble()).thenReturn(inmueble);
		when (inmueble.getPropietario()).thenReturn(usuario);
		sistema.reservarPublicacion(reserva);
		sistema.reservarPublicacion(reserva2);
		assertTrue(sistema.verInmueblesAlquilados(usuario).contains(inmueble));
	}
	
	@Test
	void testVerPromedioPuntajePropietario() {
		when (usuario.verPromedioPropietario()).thenReturn(3);
		assertEquals(sistema.verPromedioPuntajePropietario(usuario),3);
	}
	
	@Test
	void testVerPuntajePropietario() {
		when (usuario.verPuntajePropietario("Cuidado")).thenReturn(5);
		assertEquals(sistema.verPuntajePropietario("Cuidado", usuario),5);
	}
	
	@Test
	void testVerComentariosPropietario() {
		List<String> comentarios = Arrays.asList("Muy amable el propietario");
		when(usuario.verComentariosPropietario()).thenReturn(comentarios);
		assertEquals(comentarios, sistema.verComentariosPropietario(usuario));
	}
	
	@Test
	void testVerPromedioPuntajeInquilino() {
		when (usuario.verPromedioInquilino()).thenReturn(4);
		assertEquals(sistema.verPromedioPuntajeInquilino(usuario),4);
	}
	
	@Test
	void testVerPuntajeInquilino() {
		when (usuario.verPuntajeInquilino("Conducta")).thenReturn(4);
		assertEquals(sistema.verPuntajeInquilino("Conducta", usuario),4);
	}
	
	@Test
	void testVerComentariosInquilino() {
		List<String> comentarios = Arrays.asList("Excelentes inquilinos");
		when(usuario.verComentariosInquilino()).thenReturn(comentarios);
		assertEquals(comentarios, sistema.verComentariosInquilino(usuario));
	}
	
	@Test
	void testcambiarPrecioPorDia() {
		when(publicacion.getPrecioPorDia()).thenReturn(400d);
		sistema.cambiarPrecioPorDia(publicacion, 300d);
		verify(publicacion).cambiarPrecioPorDia(300d);
	}
	
}
