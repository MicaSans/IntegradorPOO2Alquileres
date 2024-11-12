package ar.edu.unq.integrador.alquileres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class BusquedaTest {

	@Mock private FiltroObligatorio filtro;
	private Busqueda busqueda;
	private List<FiltroObligatorio> filtros;
	private List<Publicacion> publicaciones;
	@Mock private Publicacion publicacion;

	@BeforeEach
	void setUp() throws Exception {
		filtro = mock(FiltroObligatorio.class);
		filtros = Arrays.asList(filtro);
		busqueda = new Busqueda(filtros); //SUT
		publicacion = mock(Publicacion.class);
		publicaciones = Arrays.asList(publicacion);
		
	}

	@Test
	void testFiltrarPublicaciones() {
		when(filtro.filtrar(publicacion)).thenReturn(true);
		assertTrue(busqueda.filtrarPublicaciones(publicaciones).contains(publicacion));
	}

}
