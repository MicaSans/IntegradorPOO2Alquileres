package ar.edu.unq.integrador.alquileres.busqueda.filtro;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.integrador.alquileres.publicacion.Publicacion;
import ar.edu.unq.integrador.alquileres.rangoDeFechas.RangoDeFechas;

class FiltroTest {

	private FiltroBase filtroBase;
	@Mock private RangoDeFechas rangoDeFechas;
	@Mock private Publicacion publicacion;
	@Mock private RangoDeFechas dias1;
	@Mock private RangoDeFechas dias2;
	private List<RangoDeFechas> diasOcupados;
	@Mock private Filtro filtro;
	private FiltroPrecio filtroPrecio;
	private FiltroHuespedes filtroHuespedes;
	private FiltroPrecio filtroPrecioIncorrecto;
	

	@BeforeEach
	void setUp() throws Exception {
		// FiltroBase
		rangoDeFechas = mock(RangoDeFechas.class);
		filtro = mock(Filtro.class);
		filtroBase = new FiltroBase("Londres", rangoDeFechas);
		publicacion = mock(Publicacion.class);
		dias1 = mock(RangoDeFechas.class);
		dias2 = mock(RangoDeFechas.class);
		diasOcupados = Arrays.asList(dias1,dias2);
		// FiltroPrecio
		filtroPrecio = new FiltroPrecio(200d, 300d, rangoDeFechas);
		filtroPrecioIncorrecto = new FiltroPrecio(300d, 200d, rangoDeFechas);
		// FiltroHuespedes
		filtroHuespedes = new FiltroHuespedes(5);
	}

	//Test filtro base
	@Test
	void testFiltrarFechasYCiudad() {
		//Configuracion
		when(publicacion.getDiasOcupados()).thenReturn(diasOcupados);
		when(dias2.getInicio()).thenReturn(LocalDate.of(2025, 1, 16));
		when(dias2.getFinal()).thenReturn(LocalDate.of(2025, 1, 30));
		when(dias1.getInicio()).thenReturn(LocalDate.of(2025, 1, 1));
		when(dias1.getFinal()).thenReturn(LocalDate.of(2025, 1, 15));
		when(rangoDeFechas.getInicio()).thenReturn(LocalDate.of(2025, 2, 1));
		when(rangoDeFechas.getFinal()).thenReturn(LocalDate.of(2025, 2, 15));
		when(publicacion.getCiudad()).thenReturn("Londres");
		when(filtro.filtrar(publicacion)).thenReturn(true);
		when(rangoDeFechas.esRangoCompatible()).thenReturn(true);
		
		//Accion y verificacion
		assertTrue(filtroBase.filtrar(publicacion));	
	
	}
	
	@Test
	void testFiltrarFechasYCiudadFalso() {
		//Configuracion
		when(publicacion.getDiasOcupados()).thenReturn(diasOcupados);
		when(dias2.getInicio()).thenReturn(LocalDate.of(2025, 1, 16));
		when(dias2.getFinal()).thenReturn(LocalDate.of(2025, 2, 10));
		when(dias1.getInicio()).thenReturn(LocalDate.of(2025, 1, 1));
		when(dias1.getFinal()).thenReturn(LocalDate.of(2025, 1, 15));
		when(rangoDeFechas.getInicio()).thenReturn(LocalDate.of(2025, 2, 1));
		when(rangoDeFechas.getFinal()).thenReturn(LocalDate.of(2025, 2, 15));
		when(publicacion.getCiudad()).thenReturn("Londres");
		when(filtro.filtrar(publicacion)).thenReturn(true);
		
		//Accion y verificacion
		assertFalse(filtroBase.filtrar(publicacion));	
	
	}
	
	//Test filtro precio
	@Test
	void testFiltrarPorPrecioCorrecto() {
		//Configuracion
		when(rangoDeFechas.esRangoCompatible()).thenReturn(true);
		when(publicacion.getPrecio(rangoDeFechas)).thenReturn(250d);
		
		//Accion y verificacion
		assertTrue(filtroPrecio.filtrar(publicacion));
	
	}
	
	@Test
	void testFiltrarConPreciosIncorrectos() {
		//Configuracion
		when(rangoDeFechas.esRangoCompatible()).thenReturn(true);
		
		//Accion y verificacion
		assertFalse(filtroPrecioIncorrecto.filtrar(publicacion));	
	
	}
	
	
	@Test
	void testFiltrarPorPrecioExcede() {
		//Configuracion
		when(rangoDeFechas.esRangoCompatible()).thenReturn(true);
		when(publicacion.getPrecio(rangoDeFechas)).thenReturn(350d);
		
		//Accion y verificacion
		assertFalse(filtroPrecio.filtrar(publicacion));	
	
	}
	
	@Test
	void testFiltrarPorPrecioPorDebajo() {
		//Configuracion
		when(rangoDeFechas.esRangoCompatible()).thenReturn(true);
		when(publicacion.getPrecio(rangoDeFechas)).thenReturn(150d);
		
		//Accion y verificacion
		assertFalse(filtroPrecio.filtrar(publicacion));	
	
	}
	
	@Test
	void testFiltrarPorPrecioRangoNoCompatible() {
		//Configuracion
		when(rangoDeFechas.esRangoCompatible()).thenReturn(false);
		when(publicacion.getPrecio(rangoDeFechas)).thenReturn(250d);

		//Accion y verificacion
		assertFalse(filtroPrecio.filtrar(publicacion));	
	
	}
	
	//Test filtro huéspedes
	@Test
	void testFiltrarPorHuespedesConCapacidad() {
		//Configuración
		when(publicacion.tieneCapacidadPara(5)).thenReturn(true);
		
		//Acción y verificación
		assertTrue(filtroHuespedes.filtrar(publicacion));
		verify(publicacion, times(1)).tieneCapacidadPara(5);
		
	}
	
	@Test
	void testFiltrarPorHuespedesSinCapacidad() {
		//Configuración
		when(publicacion.tieneCapacidadPara(5)).thenReturn(false);
				
		//Acción y verificación
		assertFalse(filtroHuespedes.filtrar(publicacion));
		verify(publicacion, times(1)).tieneCapacidadPara(5);
				
	}
	
}