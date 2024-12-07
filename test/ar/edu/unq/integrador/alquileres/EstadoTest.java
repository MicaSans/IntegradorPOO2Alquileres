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
	private Reservada reservada;
	private Obsoleta obsoleta;
	private Alquilada alquilada;
	
	private List<Reserva> condicionales;
	@Mock private Reserva reserva;
	@Mock private Publicacion publicacion;
	@Mock private RangoDeFechas fechasAlquiladas;
	@Mock private Usuario inquilino;
	@Mock private Reserva reserva2;
	@Spy private Pendiente pendienteSpy;
	
	@BeforeEach
	void setUp() throws Exception {
		
		//Pendiente 
		pendiente = new Pendiente(); //SUT
		reserva = mock(Reserva.class);
		pendienteSpy = spy(Pendiente.class);
		publicacion = mock(Publicacion.class);
		fechasAlquiladas = mock(RangoDeFechas.class);
		reserva2 = mock(Reserva.class);
		
		//Reservada
		reservada = new Reservada(); //SUT
		inquilino = mock(Usuario.class);
		condicionales = new ArrayList<Reserva>();
		
		//Obsoleta
		obsoleta = new Obsoleta(); //SUT
		
		//Alquilada
		alquilada = new Alquilada(); //SUT
	}

	//Test clase Pendiente
	@Test
	void testPendienteAceptarReserva() {
		when (reserva.getPublicacion()).thenReturn(publicacion);
		when (reserva.getFechas()).thenReturn(fechasAlquiladas);
		pendiente.aceptarReserva(reserva);
		verify(publicacion).agregarADiasOcupados(fechasAlquiladas);
		verify(reserva).setEstado(any(Reservada.class));
	}
	
	@Test
	void testPendienteEstaReservaOcupada() {
		//Configuración de mocks
		when (reserva.getPublicacion()).thenReturn(publicacion);
		when (reserva.getFechas()).thenReturn(fechasAlquiladas);
		when (reserva2.getPublicacion()).thenReturn(publicacion);
		when (reserva2.getFechas()).thenReturn(fechasAlquiladas);
		//Cuando no hay días ocupados en la publicacón
		when (publicacion.getDiasOcupados()).thenReturn(new ArrayList<>());
		//Ejecuto el método aceptarReserva para la primer reserva
		pendiente.aceptarReserva(reserva);
		//Verifico que se agregaron los días ocupados de la publicación
		verify(publicacion).agregarADiasOcupados(fechasAlquiladas);
		//Verifico que el estado de la reserva haya sido modificado
		verify(reserva).setEstado(any(Reservada.class));
		//Agrego una fecha ocupada en la publicación
		List<RangoDeFechas> diasOcupados = new ArrayList<>();
		diasOcupados.add(fechasAlquiladas);
		when (publicacion.getDiasOcupados()).thenReturn(diasOcupados);
		
		when (fechasAlquiladas.seSuperponenDias(fechasAlquiladas)).thenReturn(true);
		//Ejecuto el método aceptarReserva para la segunda reserva, que se superpone
		pendiente.aceptarReserva(reserva2);
		//Verifico que la segunda reserva se agregó a condicionales
	    verify(publicacion).agregarACondicionales(reserva2);
	    //Verifico que la segunda reserva pase su estado a Obsoleta
	    verify(reserva2).setEstado(any(Obsoleta.class));
	}
	
	@Test
	void testPendienteRechazarReserva() {
		when (reserva.getPublicacion()).thenReturn(publicacion);
		pendiente.rechazarReserva(reserva);
		verify(reserva).setEstado(any(Obsoleta.class));
	}
	
	//Test clase Reservada
	@Test
	void testReservadaCancelarReservaSinCondicionales() {
		when (reserva.getPublicacion()).thenReturn(publicacion);
		when (reserva.getFechas()).thenReturn(fechasAlquiladas);
		reservada.cancelarReserva(reserva);
		verify(publicacion).quitarADiasOcupados(fechasAlquiladas);
		verify(reserva).setEstado(any(Obsoleta.class));
	}
	
	@Test
	void testReservadaCancelarReservasConCondicionales() {
		when (reserva.getPublicacion()).thenReturn(publicacion);
		when (publicacion.getCondicionales()).thenReturn(condicionales);
		when (reserva.getFechas()).thenReturn(fechasAlquiladas);
		reservada.cancelarReserva(reserva);
		//verify(reserva).setNuevoInquilino(any(Usuario.class));
		verify(reserva).setEstado(any(Obsoleta.class));
		verify(publicacion).quitarADiasOcupados(fechasAlquiladas);
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
	
	//Test clase Obsoleta
	
	//Test clase Aceptada
}
