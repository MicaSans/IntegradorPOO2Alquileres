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

class FiltroExtraTest {

	
	@Mock private RangoDeFechas rangoDeFechas;
	@Mock private Publicacion publicacion;
	@Mock private RangoDeFechas dias1;
	@Mock private RangoDeFechas dias2;
	private List<RangoDeFechas> diasOcupados;
	@Mock private Inmueble inmueble;
	private FiltroPrecio filtroPrecio;
	

	@BeforeEach
	void setUp() throws Exception {
		rangoDeFechas = mock(RangoDeFechas.class);
		filtroPrecio = new FiltroPrecio(200d, 300d, rangoDeFechas);
		
		
		
	}

	

}
