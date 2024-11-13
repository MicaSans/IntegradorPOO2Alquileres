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

	@Mock private FiltroBase filtro;
	private Busqueda busqueda;
	private List<Publicacion> publicaciones;
	@Mock private Publicacion publicacion;
	@Mock private Filtro filtroPrecio;
	@Mock private Filtro filtroPrecio2;

	@BeforeEach
	void setUp() throws Exception {
		//1er Test
		filtro = mock(FiltroBase.class);
		busqueda = new Busqueda(filtro); //SUT
		publicacion = mock(Publicacion.class);
		publicaciones = Arrays.asList(publicacion);
		//Test agrego filtros
		filtroPrecio = mock(Filtro.class);
		filtroPrecio2 = mock(Filtro.class);
		
	}

	@Test
	void testFiltrarPublicaciones() {
		when(filtro.filtrar(publicacion)).thenReturn(true);
		assertTrue(busqueda.filtrarPublicaciones(publicaciones).contains(publicacion));
	}
	
	@Test
	void testFiltrosExtras() {
		busqueda.agregarFiltro(filtroPrecio);
		busqueda.agregarFiltro(filtroPrecio2);
		assertTrue(busqueda.getFiltros().contains(filtroPrecio));
		assertFalse(busqueda.getFiltros().contains(filtroPrecio2));
	}

}
