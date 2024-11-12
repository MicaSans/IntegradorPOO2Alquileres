package ar.edu.unq.integrador.alquileres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class FiltroTest {

	private FiltroBase filtroBase;
	@Mock private RangoDeFechas rangoDeFechas;
	@Mock private Publicacion publicacion;
	@Mock private RangoDeFechas dias1;
	@Mock private RangoDeFechas dias2;
	private List<RangoDeFechas> diasOcupados;
	@Mock private Inmueble inmueble;
	@Mock private Filtro filtro;
	private FiltroPrecio filtroPrecio;
	private FiltroHuespedes filtroHuespedes;
	

	@BeforeEach
	void setUp() throws Exception {
		// FiltroBase
		rangoDeFechas = mock(RangoDeFechas.class);
		filtro= mock(Filtro.class);
		filtroBase = new FiltroBase("Londres", rangoDeFechas);
		publicacion = mock(Publicacion.class);
		dias1 = mock(RangoDeFechas.class);
		dias2 = mock(RangoDeFechas.class);
		diasOcupados = Arrays.asList(dias1,dias2);
		inmueble = mock(Inmueble.class);	
		// FiltroPrecio
		filtroPrecio = new FiltroPrecio(200d, 300d, rangoDeFechas);
		// FiltroHuespedes
		filtroHuespedes = new FiltroHuespedes(5);
	}

	@Test
	void testFiltrarFechasYCiudad() {
		when(publicacion.getDiasOcupados()).thenReturn(diasOcupados);
		when(dias2.getInicio()).thenReturn(LocalDate.of(2025, 1, 16));
		when(dias2.getFinal()).thenReturn(LocalDate.of(2025, 1, 30));
		when(dias1.getInicio()).thenReturn(LocalDate.of(2025, 1, 1));
		when(dias1.getFinal()).thenReturn(LocalDate.of(2025, 1, 15));
		when(rangoDeFechas.getInicio()).thenReturn(LocalDate.of(2025, 2, 1));
		when(rangoDeFechas.getFinal()).thenReturn(LocalDate.of(2025, 2, 15));
		when(publicacion.getInmueble()).thenReturn(inmueble);
		when(inmueble.getCiudad()).thenReturn("Londres");
		when(filtro.filtrar(publicacion)).thenReturn(true);
		when(rangoDeFechas.esRangoCompatible()).thenReturn(true);
		assertTrue(filtroBase.filtrar(publicacion));	
	}
	
	@Test
	void testFiltrarFechasYCiudadFalso() {
		when(publicacion.getDiasOcupados()).thenReturn(diasOcupados);
		when(dias2.getInicio()).thenReturn(LocalDate.of(2025, 1, 16));
		when(dias2.getFinal()).thenReturn(LocalDate.of(2025, 2, 10));
		when(dias1.getInicio()).thenReturn(LocalDate.of(2025, 1, 1));
		when(dias1.getFinal()).thenReturn(LocalDate.of(2025, 1, 15));
		when(rangoDeFechas.getInicio()).thenReturn(LocalDate.of(2025, 2, 1));
		when(rangoDeFechas.getFinal()).thenReturn(LocalDate.of(2025, 2, 15));
		when(publicacion.getInmueble()).thenReturn(inmueble);
		when(inmueble.getCiudad()).thenReturn("Londres");
		when(filtro.filtrar(publicacion)).thenReturn(true);
		assertFalse(filtroBase.filtrar(publicacion));	
	}
	
	@Test
	void testFiltrarPorPrecios() {
		when(rangoDeFechas.esRangoCompatible()).thenReturn(true);
		when(publicacion.getPrecio(rangoDeFechas)).thenReturn(250d);
		assertTrue(filtroPrecio.filtrar(publicacion));	
	}
	
	@Test
	void testFiltrarPorPrecioExcede() {
		when(rangoDeFechas.esRangoCompatible()).thenReturn(true);
		when(publicacion.getPrecio(rangoDeFechas)).thenReturn(350d);
		assertFalse(filtroPrecio.filtrar(publicacion));	
	}
	
	@Test
	void testFiltrarPorPrecioPorDebajo() {
		when(rangoDeFechas.esRangoCompatible()).thenReturn(true);
		when(publicacion.getPrecio(rangoDeFechas)).thenReturn(150d);
		assertFalse(filtroPrecio.filtrar(publicacion));	
	}
	
	@Test
	void testFiltrarPorHuespedes() {
		when(publicacion.getInmueble()).thenReturn(inmueble);
		when(inmueble.getCantHuespedes()).thenReturn(5);
		assertTrue(filtroHuespedes.filtrar(publicacion));
	}
	
}