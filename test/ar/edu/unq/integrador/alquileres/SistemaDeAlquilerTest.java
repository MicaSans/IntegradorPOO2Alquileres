package ar.edu.unq.integrador.alquileres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
	@Mock private List<Publicacion> publicaciones;
	
	@BeforeEach
	void setUp() throws Exception {
		// testRegistrarUsuario()
		sistema = new SistemaDeAlquiler();
		usuario = mock(Usuario.class);
		// testGenerarPublicacion() 
		publicacion = mock(Publicacion.class);
		publicaciones = Arrays.asList(publicacion);
		// testBusquedaDePublicaciones()
		busqueda = mock(Busqueda.class);
		
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
		List <Publicacion >publicacionesFiltradas = sistema.buscarPublicaciones(busqueda);
		assertTrue(publicacionesFiltradas.contains(publicacion));
		
	}

	
}
