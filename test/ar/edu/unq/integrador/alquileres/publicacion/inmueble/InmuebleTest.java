package ar.edu.unq.integrador.alquileres.publicacion.inmueble;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.integrador.alquileres.usuario.Usuario;

class InmuebleTest {

	private Inmueble inmueble;
	@Mock private Usuario usuario;
	private List<String> servicios;
	
	@BeforeEach
	void setUp() throws Exception {
		usuario = mock(Usuario.class);
		servicios = Arrays.asList("Wifi", "Desayuno");
		inmueble = new Inmueble(100, "Argentina", "Mar del Plata", "Olivar 785", 5, servicios, "Duplex", usuario); //SUT
	}
	
	@Test
	void testGetPropietario() {
		assertEquals(usuario, inmueble.getPropietario());
	}

	@Test
	void testGetSuperficie() {
		assertEquals(100, inmueble.getSuperficie());
	}
	
	@Test
	void testGetPais() {
		assertEquals("Argentina", inmueble.getPais());
	}
	
	@Test
	void testGetCiudad() {
		assertEquals("Mar del Plata", inmueble.getCiudad());
	}
	
	@Test
	void testGetDireccion() {
		assertEquals("Olivar 785", inmueble.getDireccion());
	}
	
	@Test
	void testGetCapacidad() {
		assertEquals(5, inmueble.getCapacidad());
	}
	
	@Test
	void testGetServicios() {
		assertEquals(2, inmueble.getServicios().size());
		assertTrue(inmueble.getServicios().contains("Wifi"));
	}
	
	@Test
	void testGetTipoInmueble() {
		assertEquals("Duplex", inmueble.getTipoInmueble());
	}

}