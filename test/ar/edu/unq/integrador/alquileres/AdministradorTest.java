package ar.edu.unq.integrador.alquileres;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class AdministradorTest {

	private Administrador administrador;
	@Mock private SistemaDeAlquiler sistema;
	
	@BeforeEach
	void setUp() throws Exception {
		sistema = mock(SistemaDeAlquiler.class);
		administrador = new Administrador(sistema); //SUT
	}

	@Test
	void testDarDeAltaServicio() {
		administrador.darDeAltaServicio("Wifi");
		verify(sistema).setServicio("Wifi");
	}
	
	@Test
	void testDarDeAltaCategoriaPuntuacion() {
		administrador.darDeAltaCategoriaPuntuacion("Limpieza");
		verify(sistema).setCategoriaPuntuacion("Limpieza");
	}
	
	@Test
	void testDarDeAltaTipoInmueble() {
		administrador.darDeAltaTipoInmueble("Duplex");
		verify(sistema).setTipoInmueble("Duplex");
	}
}
