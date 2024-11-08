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
		sistema.reservarPublicacion(usuario, publicacion, efectivo, fechaEntrada, fechaSalida);
		assertTrue(sistema.getReservas().size() != 0);
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

	
}
