package ar.edu.unq.integrador.alquileres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class PoliticaDeCancelacionTest {

	private SinCancelacion sinCancelacion;
	@Mock private RangoDeFechas rangoDeFechas;
	private CancelacionGratuita cancelacionGratuita;
	private Intermedia intermedia;

	@BeforeEach
	void setUp() throws Exception {
		rangoDeFechas = mock(RangoDeFechas.class);
		sinCancelacion = new SinCancelacion();
		cancelacionGratuita = new CancelacionGratuita();
		intermedia = new Intermedia();
		
	}

	@Test
	void testSinCancelacion() {
		assertEquals("Debes pagar el alquiler de los dias reservados completo", sinCancelacion.cancelarReserva(rangoDeFechas));
	}
	
	@Test 
	void testCancelacionGratuitaAntesdeLos10Dias() {
		when(rangoDeFechas.getInicio()).thenReturn(LocalDate.of(2024, 12, 10));
		assertEquals("No debes nada, cancelada con exito", cancelacionGratuita.cancelarReserva(rangoDeFechas));
	}
	
	@Test 
	void testCancelacionGratuitaDespuesdeLos10Dias() {
		when(rangoDeFechas.getInicio()).thenReturn(LocalDate.of(2024, 11, 20));
		assertEquals("Debes abonar el equivalente a dos dias de reserva", cancelacionGratuita.cancelarReserva(rangoDeFechas));
	}
	
	@Test 
	void testIntermediaAntesdeLos20Dias() {
		when(rangoDeFechas.getInicio()).thenReturn(LocalDate.of(2024, 12, 10));
		assertEquals("No debes nada, cancelada con exito", intermedia.cancelarReserva(rangoDeFechas));
	}
	
	@Test 
	void testIntermediaAntesdelos10YdespuesdeLos19() {
		when(rangoDeFechas.getInicio()).thenReturn(LocalDate.of(2024, 11, 25));
		assertEquals("Debes abonar el 50% del alquiler", intermedia.cancelarReserva(rangoDeFechas));
	}
	
	@Test 
	void testIntermediaDespuesdeLos19dias() {
		when(rangoDeFechas.getInicio()).thenReturn(LocalDate.of(2024, 11, 20));
		assertEquals("Debes pagar la totalidad del alquiler", intermedia.cancelarReserva(rangoDeFechas));
	}

}
