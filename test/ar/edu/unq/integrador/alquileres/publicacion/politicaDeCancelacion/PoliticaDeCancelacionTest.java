package ar.edu.unq.integrador.alquileres.publicacion.politicaDeCancelacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.integrador.alquileres.rangoDeFechas.RangoDeFechas;

class PoliticaDeCancelacionTest {

	private SinCancelacion sinCancelacion;
	private CancelacionGratuita cancelacionGratuita;
	private Intermedia intermedia;
	
	@Mock private RangoDeFechas rangoDeFechas;

	@BeforeEach
	void setUp() throws Exception {
		sinCancelacion = new SinCancelacion();
		cancelacionGratuita = new CancelacionGratuita();
		intermedia = new Intermedia();
		
		rangoDeFechas = mock(RangoDeFechas.class);
		
	}

	//Test SinCancelación
	@Test
	void testSinCancelacion() {
		assertEquals("Debes pagar el alquiler de los dias reservados completo", sinCancelacion.cancelarReserva(rangoDeFechas));
		assertFalse(sinCancelacion.esCancelacionGratuita(rangoDeFechas));
		
	}
	
	//Test CancelaciónGratuita
	@Test 
	void testCancelacionGratuitaAntesDeLos10Dias() {
		//Simulo que la fecha es 11 días después
		when(rangoDeFechas.getInicio()).thenReturn(LocalDate.now().plusDays(11));
		
		//Verificaciones
		assertEquals("No debes nada, cancelada con exito", cancelacionGratuita.cancelarReserva(rangoDeFechas));
		assertTrue(cancelacionGratuita.esCancelacionGratuita(rangoDeFechas));
		
	}
	
	@Test 
	void testCancelacionGratuitaDespuesDeLos10Dias() {
		//Simulo que la fecha es un día antes
		when(rangoDeFechas.getInicio()).thenReturn(LocalDate.now().minusDays(1));
		
		//Verificaciones
		assertEquals("Debes abonar el equivalente a dos dias de reserva", cancelacionGratuita.cancelarReserva(rangoDeFechas));
		assertFalse(cancelacionGratuita.esCancelacionGratuita(rangoDeFechas));

	}
	
	@Test
	void testCancelacionGratuitaConFechaActual() {
		LocalDate fechaActual = LocalDate.now();
		
		//Fecha en 11 días
		when(rangoDeFechas.getInicio()).thenReturn(fechaActual.plusDays(11));
		
		
		//Verificaciones
		assertEquals("No debes nada, cancelada con exito", cancelacionGratuita.cancelarReserva(rangoDeFechas));
		assertTrue(cancelacionGratuita.esCancelacionGratuita(rangoDeFechas));

		//Fecha en 1 día
		when(rangoDeFechas.getInicio()).thenReturn(fechaActual.minusDays(1));
		
		//Verificaciones
		assertEquals("Debes abonar el equivalente a dos dias de reserva", cancelacionGratuita.cancelarReserva(rangoDeFechas));
		assertFalse(cancelacionGratuita.esCancelacionGratuita(rangoDeFechas));

	}
	
	//Test Intermedia
	@Test 
	void testIntermediaAntesDeLos20Dias() {
		//Simulo que la fecha es en 21 días
		when(rangoDeFechas.getInicio()).thenReturn(LocalDate.now().plusDays(21));
		
		//Verificaciones
		assertEquals("No debes nada, cancelada con exito", intermedia.cancelarReserva(rangoDeFechas));
		assertTrue(intermedia.esCancelacionGratuita(rangoDeFechas));

	}
	
	@Test 
	void testIntermediaAntesDeLos10DiasYDespuesDeLos19Dias() {
		//Simulo que la fecha es en 15 días
		when(rangoDeFechas.getInicio()).thenReturn(LocalDate.now().plusDays(15));
		
		//Verificaciones
		assertEquals("Debes abonar el 50% del alquiler", intermedia.cancelarReserva(rangoDeFechas));
		assertFalse(intermedia.esCancelacionGratuita(rangoDeFechas));

	}
	
	@Test 
	void testIntermediaDespuesdeLos19Dias() {
		//Simulo que la fecha es en 5 días
		when(rangoDeFechas.getInicio()).thenReturn(LocalDate.now().plusDays(5));
		
		//Verificaciones
		assertEquals("Debes pagar la totalidad del alquiler", intermedia.cancelarReserva(rangoDeFechas));
		assertFalse(intermedia.esCancelacionGratuita(rangoDeFechas));

	}

	@Test
	void testIntermediaConFechaActual() {
		LocalDate fechaActual = LocalDate.now();
		
		//Fecha en 21 días
		when(rangoDeFechas.getInicio()).thenReturn(fechaActual.plusDays(21));
		
		//Verificaciones
		assertEquals("No debes nada, cancelada con exito", intermedia.cancelarReserva(rangoDeFechas));
		assertTrue(intermedia.esCancelacionGratuita(rangoDeFechas));

		//Fecha en 15 días
		when(rangoDeFechas.getInicio()).thenReturn(fechaActual.plusDays(15));
		
		//Verificaciones
		assertEquals("Debes abonar el 50% del alquiler", intermedia.cancelarReserva(rangoDeFechas));
		assertFalse(intermedia.esCancelacionGratuita(rangoDeFechas));

		//Fecha en 5 días
		when(rangoDeFechas.getInicio()).thenReturn(fechaActual.plusDays(5));
		
		//Verificaciones
		assertEquals("Debes pagar la totalidad del alquiler", intermedia.cancelarReserva(rangoDeFechas));
		assertFalse(intermedia.esCancelacionGratuita(rangoDeFechas));

	}
}
