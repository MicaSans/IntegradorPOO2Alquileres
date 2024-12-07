package ar.edu.unq.integrador.alquileres.sistema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.integrador.alquileres.publicacion.Publicacion;
import ar.edu.unq.integrador.alquileres.rangoDeFechas.RangoDeFechas;

class AdministradorTest {

	private Administrador administrador;
	@Mock private SistemaDeAlquiler sistema;
	@Mock private Publicacion publicacion;
	@Mock private RangoDeFechas diasOcupados;
	@Mock private Publicacion publicacion2;
	@Mock private Publicacion publicacion3;
	@Mock private Publicacion publicacion4;
	
	@BeforeEach
	void setUp() throws Exception {
		sistema = mock(SistemaDeAlquiler.class);
		administrador = new Administrador(sistema); //SUT
		publicacion = mock(Publicacion.class);
		diasOcupados = mock(RangoDeFechas.class);
		publicacion2 = mock(Publicacion.class);
		publicacion3 = mock(Publicacion.class);
		publicacion4 = mock(Publicacion.class);
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
	
	@Test
	void testObtenerInmueblesLibres() {
		when(sistema.getPublicaciones()).thenReturn(Arrays.asList(publicacion));
		assertEquals(administrador.obtenerInmueblesLibres().size(), 1);
		assertTrue(administrador.obtenerInmueblesLibres().contains(publicacion));
	}
	
	@Test
	void testObtenerInmueblesLibresSinLibres() {
		when(publicacion.getDiasOcupados()).thenReturn(Arrays.asList(diasOcupados));
		when(sistema.getPublicaciones()).thenReturn(Arrays.asList(publicacion));
		assertEquals(administrador.obtenerInmueblesLibres().size(), 0);
		assertFalse(administrador.obtenerInmueblesLibres().contains(publicacion));
	}
	
	@Test
	void testObtenerTasaOcupacion() {
		when(publicacion.getDiasOcupados()).thenReturn(Arrays.asList(diasOcupados));
		when(publicacion2.getDiasOcupados()).thenReturn(Arrays.asList(diasOcupados));
		when(sistema.getPublicaciones()).thenReturn(Arrays.asList(publicacion, publicacion2, publicacion3, publicacion4));
		Double esperado = 0.5;
		assertEquals(administrador.obtenerTasaOcupacion(), esperado);
	}
	
}
