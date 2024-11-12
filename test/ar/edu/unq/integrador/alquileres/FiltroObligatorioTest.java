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

class FiltroObligatorioTest {

	private FiltroObligatorio filtroObligatorio;
	@Mock private RangoDeFechas rangoDeFechas;
	@Mock private Publicacion publicacion;
	@Mock private RangoDeFechas dias1;
	@Mock private RangoDeFechas dias2;
	private List<RangoDeFechas> diasOcupados;
	@Mock private Inmueble inmueble;
	@Mock private FiltroExtra filtroExtra;
	private List<FiltroExtra> filtrosExtras;
	private FiltroObligatorio filtroObligatorio2;
	

	@BeforeEach
	void setUp() throws Exception {
		// FiltroObligatorio
		rangoDeFechas = mock(RangoDeFechas.class);
		filtroExtra = mock(FiltroExtra.class);
		filtrosExtras = Arrays.asList(filtroExtra);
		filtroObligatorio = new FiltroObligatorio("Londres", rangoDeFechas, filtrosExtras);
		publicacion = mock(Publicacion.class);
		dias1 = mock(RangoDeFechas.class);
		dias2 = mock(RangoDeFechas.class);
		diasOcupados = Arrays.asList(dias1);
		inmueble = mock(Inmueble.class);	
	}

	@Test
	void testFiltrarFechasYCiudad() {
		when(publicacion.getDiasOcupados()).thenReturn(diasOcupados);
		when(dias2.getInicio()).thenReturn(LocalDate.of(2025, 1, 16));
		when(dias2.getFinal()).thenReturn(LocalDate.of(2025, 2, 10));
		when(dias1.getInicio()).thenReturn(LocalDate.of(2025, 1, 1));
		when(dias1.getFinal()).thenReturn(LocalDate.of(2025, 1, 15));
		when(rangoDeFechas.getInicio()).thenReturn(LocalDate.of(2025, 2, 1));
		when(rangoDeFechas.getFinal()).thenReturn(LocalDate.of(2025, 2, 15));
		when(publicacion.getInmueble()).thenReturn(inmueble);
		when(inmueble.getCiudad()).thenReturn("Londres");
		when(filtroExtra.filtrar(publicacion, rangoDeFechas)).thenReturn(true);
		assertTrue(filtroObligatorio.filtrar(publicacion));	
	}
	
	@Test
	void testFiltrarFechasYCiudadFalso() {
		when(publicacion.getDiasOcupados()).thenReturn(diasOcupados);
		when(dias2.getInicio()).thenReturn(LocalDate.of(2025, 1, 16));
		when(dias2.getFinal()).thenReturn(LocalDate.of(2025, 2, 10));
		when(dias1.getInicio()).thenReturn(LocalDate.of(2025, 1, 1));
		when(dias1.getFinal()).thenReturn(LocalDate.of(2025, 2, 15));
		when(rangoDeFechas.getInicio()).thenReturn(LocalDate.of(2025, 2, 1));
		when(rangoDeFechas.getFinal()).thenReturn(LocalDate.of(2025, 2, 15));
		when(publicacion.getInmueble()).thenReturn(inmueble);
		when(inmueble.getCiudad()).thenReturn("Londres");
		when(filtroExtra.filtrar(publicacion, rangoDeFechas)).thenReturn(true);
		assertFalse(filtroObligatorio.filtrar(publicacion));	
	}
	
	@Test
	void testFiltrarSinExtras() {
		filtroObligatorio2 = new FiltroObligatorio("Londres", rangoDeFechas);
		when(publicacion.getDiasOcupados()).thenReturn(diasOcupados);
		when(dias2.getInicio()).thenReturn(LocalDate.of(2025, 1, 16));
		when(dias2.getFinal()).thenReturn(LocalDate.of(2025, 1, 30));
		when(dias1.getInicio()).thenReturn(LocalDate.of(2025, 1, 1));
		when(dias1.getFinal()).thenReturn(LocalDate.of(2025, 1, 15));
		when(rangoDeFechas.getInicio()).thenReturn(LocalDate.of(2025, 2, 1));
		when(rangoDeFechas.getFinal()).thenReturn(LocalDate.of(2025, 2, 15));
		when(publicacion.getInmueble()).thenReturn(inmueble);
		when(inmueble.getCiudad()).thenReturn("Londres");
		assertTrue(filtroObligatorio2.filtrar(publicacion));	
	}
	
	
}