package ar.edu.unq.integrador.alquileres;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

class EstadoTest {

	private Pendiente pendiente;
	@Mock private Reserva reserva;
	@Spy private Pendiente pendienteSpy;
	@Mock private Publicacion publicacion;
	@Mock private RangoDeFechas fechasAlquiladas;
	private Reservada reservada;
	@Mock private Usuario inquilino;
	private Obsoleta obsoleta;
	private Alquilada alquilada;
	private List<Usuario> condicionales;

	@BeforeEach
	void setUp() throws Exception {
		//Pendiente 
		pendiente = new Pendiente(); //SUT
		reserva = mock(Reserva.class);
		pendienteSpy = spy(Pendiente.class);
		publicacion = mock(Publicacion.class);
		fechasAlquiladas = mock(RangoDeFechas.class);
		//Reservada
		reservada = new Reservada(); //SUT
		inquilino = mock(Usuario.class);
		condicionales = new ArrayList<Usuario>();
		condicionales.add(inquilino);
		//Obsoleta
		obsoleta = new Obsoleta(); //SUT
		//Alquilada
		alquilada = new Alquilada();
		
	}

	@Test
	void testPendienteAceptarReserva() {
		when (reserva.getPublicacion()).thenReturn(publicacion);
		when (reserva.getFechas()).thenReturn(fechasAlquiladas);
		pendiente.aceptarReserva(reserva);
		verify(publicacion).agregarADiasOcupados(fechasAlquiladas);
		verify(reserva).setEstado(any(Estado.class));
	}
	
	@Test
	void testPendienteRechazarReserva() {
		pendiente.rechazarReserva(reserva);
		verify(reserva).setEstado(any(Estado.class));
	}
	
	@Test
	void testReservadaCancelarReservaSinCondicionales() {
		when (reserva.getPublicacion()).thenReturn(publicacion);
		when (reserva.getFechas()).thenReturn(fechasAlquiladas);
		reservada.cancelarReserva(reserva);
		verify(publicacion).quitarADiasOcupados(fechasAlquiladas);
		verify(reserva).setEstado(any(Estado.class));
	}
	
	@Test
	void testReservadaCancelarReservasConCondicionales() {
		when (reserva.getCondicionales()).thenReturn(condicionales);
		reservada.cancelarReserva(reserva);
		verify(reserva).setNuevoInquilino(any(Usuario.class));
		verify(reserva).setEstado(any(Estado.class));
	}
	
	@Test
	void testReservadaCheckOut() {
		when (reserva.getPublicacion()).thenReturn(publicacion);
		when (reserva.getFechas()).thenReturn(fechasAlquiladas);
		when (fechasAlquiladas.getFinal()).thenReturn(LocalDate.of(2024, 11, 3));
		reservada.checkOut(reserva);
		verify(publicacion).quitarADiasOcupados(fechasAlquiladas);
		verify(reserva).setEstado(any(Estado.class));
	}
	
	@Test
	void testParaLosBooleanosDelFueAlquilada() {
		assertFalse(pendiente.fueAlquilada(reserva));
		assertFalse(reservada.fueAlquilada(reserva));
		assertFalse(obsoleta.fueAlquilada(reserva));
		assertTrue(alquilada.fueAlquilada(reserva));
	}
	
	
}
