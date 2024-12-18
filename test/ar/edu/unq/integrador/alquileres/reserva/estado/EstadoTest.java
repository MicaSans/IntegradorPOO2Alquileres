package ar.edu.unq.integrador.alquileres.reserva.estado;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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

import ar.edu.unq.integrador.alquileres.publicacion.Publicacion;
import ar.edu.unq.integrador.alquileres.rangoDeFechas.RangoDeFechas;
import ar.edu.unq.integrador.alquileres.reserva.Reserva;
import ar.edu.unq.integrador.alquileres.usuario.Usuario;

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
	@Mock private RangoDeFechas otraFecha;
	@Mock private Reserva reserva3;
	
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
		
		otraFecha = mock(RangoDeFechas.class);
		reserva3 = mock(Reserva.class);
		
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
		when (reserva.getDiasOcupadosPublicacion()).thenReturn(new ArrayList<>());
		
		//Ejecuto el método aceptarReserva para la primer reserva
		pendiente.aceptarReserva(reserva);
		
		//Verifico que se agregaron los días ocupados de la publicación
		verify(publicacion).agregarADiasOcupados(fechasAlquiladas);
		
		//Verifico que el estado de la reserva haya sido modificado
		verify(reserva).setEstado(any(Reservada.class));
		
		//Agrego una fecha ocupada en la publicación
		List<RangoDeFechas> diasOcupados = new ArrayList<>();
		diasOcupados.add(fechasAlquiladas);
		when (reserva2.getDiasOcupadosPublicacion()).thenReturn(diasOcupados);
		
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
		pendiente.rechazarReserva(reserva);
		verify(reserva).setEstado(any(Obsoleta.class));
	}
	
	
	/* 
	
	@Test
	void testPendienteRechazarReservaSinCondicionales() {
		//Configuración sin condicionales en la lista
		when(publicacion.getCondicionales()).thenReturn(condicionales);
		when(reserva.getPublicacion()).thenReturn(publicacion);
		
		//Llamo al método
		pendiente.rechazarReserva(reserva);
		
		//Verifico que se accedió a la lista vacía
		verify(publicacion).getCondicionales();
		
		//Verifico que no se alteró la lista de condicionales
		assertTrue(condicionales.isEmpty());
		
		//Verifico que la reserva haya pasado a estado Obsoleta
		verify(reserva).setEstado(any(Obsoleta.class));
		
	}
	
	@Test
	void testPendienteRechazarReservaConCondicionales() {
		//Condifuración de la lista de condicionales con una reserva
		condicionales.add(reserva2);
		when(publicacion.getCondicionales()).thenReturn(condicionales);
		when(reserva.getPublicacion()).thenReturn(publicacion);
		
		//Llamo al método
		pendiente.rechazarReserva(reserva);
		
		//Verifico que la nueva reserva pasa a estado Pendiente
		verify(reserva2).setEstado(any(Pendiente.class));
	
		//Verifico que la lista de condicionales queda vacía
		assertTrue(condicionales.isEmpty());
		
		//Verifico que la reserva original pasa a estado Obsoleta
		verify(reserva).setEstado(any(Obsoleta.class));
		
	}
	*/ 
	@Test
	void testPendienteCancelarReserva() {
		//Llamo al método
		pendiente.cancelarReserva(reserva);
		
		//Verifico que el estado de la reserva haya cambiado a Obsoleta
		verify(reserva).setEstado(any(Obsoleta.class));
		
	}
	
	@Test
	void testPendienteCheckOut() {
		//Acción
		assertDoesNotThrow(()->pendiente.checkOut(reserva));
		//Verifico que no lanza excepción, ya que el método no tiene implementación
	
	}
	
	@Test
	void testPendienteFueAlquilada() {
		assertFalse(pendiente.fueAlquilada(reserva));
	
	}
	
	//Test clase Reservada
	@Test
	void testReservadaAceptarReserva() {
		//Acción
		assertDoesNotThrow(()->reservada.aceptarReserva(reserva));
		//Verifico que no lanza excepción, ya que el método no tiene implementación
	
	}
	
	@Test
	void testReservadaRechazarReserva() {
		//Acción
		assertDoesNotThrow(()->reservada.rechazarReserva(reserva));
		//Verifico que no lanza excepción, ya que el método no tiene implementación
	
	}
	
	//Borrar?¿
	@Test
	void testReservadaCancelarReserva() {
		when(reserva.getCondicionalesDePublicacion()).thenReturn(condicionales);
		when (reserva.getPublicacion()).thenReturn(publicacion);
		//when (publicacion.getCondicionales()).thenReturn(condicionales);
		when (reserva.getFechas()).thenReturn(fechasAlquiladas);
		reservada.cancelarReserva(reserva);
		verify(reserva).setEstado(any(Obsoleta.class));
		verify(publicacion).quitarADiasOcupados(fechasAlquiladas);
	
	}
	
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
		//Configuración inicial
		when(reserva.getCondicionalesDePublicacion()).thenReturn(condicionales);
		when (reserva.getPublicacion()).thenReturn(publicacion);
		//when (publicacion.getCondicionales()).thenReturn(condicionales);
		condicionales.add(reserva2);
		//Agrego fechas
		when (reserva.getFechas()).thenReturn(fechasAlquiladas);
		when (reserva2.getFechas()).thenReturn(fechasAlquiladas);
		when (fechasAlquiladas.seSuperponenDias(fechasAlquiladas)).thenReturn(true);
		
		//Acción
		reservada.cancelarReserva(reserva);
		
		//Verifico que el primer condicional pase a estado Pendiente
		verify(reserva2).setEstado(any(Pendiente.class));
		
		//Verifico que se quiten las fechas ocupadas de la reserva
		verify(publicacion).quitarADiasOcupados(reserva.getFechas());
		
		//Verifico que la reserva original pase a estado Obsoleta
		verify(reserva).setEstado(any(Obsoleta.class));
		
	}

	@Test
	void testReservadaCancelarReservaPeroConFechasDistintas() {
		//Quizas no se entiende el nombre, pero lo que testeo aca es cuando en 
		//los condicionales, el primero es de otra fecha al que se cancela.
		when(reserva.getCondicionalesDePublicacion()).thenReturn(condicionales);
		when (reserva.getPublicacion()).thenReturn(publicacion);
		//when (publicacion.getCondicionales()).thenReturn(condicionales);
		when (reserva.getFechas()).thenReturn(fechasAlquiladas);
		when (reserva2.getFechas()).thenReturn(otraFecha);
		when (reserva3.getFechas()).thenReturn(fechasAlquiladas);
		when (fechasAlquiladas.seSuperponenDias(otraFecha)).thenReturn(false);
		when (fechasAlquiladas.seSuperponenDias(fechasAlquiladas)).thenReturn(true);
		condicionales.add(reserva2);
		condicionales.add(reserva3);
		
		
		//Acción
		reservada.cancelarReserva(reserva);
		
		//Verifico que el segundo condicional pase a estado Pendiente
		verify(reserva3).setEstado(any(Pendiente.class));
		
		//Verifico que se quiten las fechas ocupadas de la reserva
		verify(publicacion).quitarADiasOcupados(reserva.getFechas());
		
		//Verifico que la reserva original pase a estado Obsoleta
		verify(reserva).setEstado(any(Obsoleta.class));
		
		assertTrue(condicionales.contains(reserva2));
		assertFalse(condicionales.contains(reserva3));
	
	}
	
	
	@Test
	void testReservadaCheckOut() {
		when (reserva.getPublicacion()).thenReturn(publicacion);
		when (reserva.getFechaFinal()).thenReturn(LocalDate.of(2024, 11, 3));
		reservada.checkOut(reserva);
		verify(publicacion).quitarADiasOcupados(reserva.getFechas());
		verify(reserva).setEstado(any(Estado.class));
		
	}
	
	@Test
	void testReservadaFueAlquilada() {
		assertFalse(reservada.fueAlquilada(reserva));
	
	}
	
	//Test clase Obsoleta
	@Test
	void testObsoletaAceptarReserva() {
		//Acción
		assertDoesNotThrow(()->obsoleta.aceptarReserva(reserva));
		//Verifico que no lanza excepción, ya que el método no tiene implementación
	
	}
	
	@Test
	void testObsoletaRechazarReserva() {
		//Acción
		assertDoesNotThrow(()->obsoleta.rechazarReserva(reserva));
		//Verifico que no lanza excepción, ya que el método no tiene implementación
	
	}
	
	@Test
	void testObsoletaCancelarReserva() {
		//Acción
		assertDoesNotThrow(()->obsoleta.cancelarReserva(reserva));
		//Verifico que no lanza excepción, ya que el método no tiene implementación
	
	}
	
	@Test
	void testObsoletaCheckOut() {
		//Acción
		assertDoesNotThrow(()->obsoleta.checkOut(reserva));
		//Verifico que no lanza excepción, ya que el método no tiene implementación
	
	}
	
	@Test
	void testObsoletaFueAlquilada() {
		assertFalse(obsoleta.fueAlquilada(reserva));
	
	}
	
	//Test clase Alquilada
	@Test
	void testAlquiladaAceptarReserva() {
		//Acción
		assertDoesNotThrow(()->alquilada.aceptarReserva(reserva));
		//Verifico que no lanza excepción, ya que el método no tiene implementación
	
	}
	
	@Test
	void testAlquiladaRechazarReserva() {
		//Acción
		assertDoesNotThrow(()->alquilada.rechazarReserva(reserva));
		//Verifico que no lanza excepción, ya que el método no tiene implementación
	
	}
	
	@Test
	void testAlquiladaCancelarReserva() {
		//Acción
		assertDoesNotThrow(()->alquilada.cancelarReserva(reserva));
		//Verifico que no lanza excepción, ya que el método no tiene implementación
	
	}
	
	@Test
	void testAlquiladaCheckOut() {
		//Acción
		alquilada.checkOut(reserva);
		//Verifico que el estado de la reserva cambió a Obsoleta
		verify(reserva).setEstado(any(Obsoleta.class));
	
	}
	
	@Test
	void testParaLosBooleanosDelFueAlquilada() {
		assertTrue(alquilada.fueAlquilada(reserva));
	
	}
	
}
