package ar.edu.unq.integrador.alquileres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

class ReservaTest {

	@Mock private Usuario inquilino;
	@Mock private Publicacion publicacion;
	@Mock private RangoDeFechas fechasDeAlquiler;
	private Reserva reserva;
	@Mock private Inmueble inmueble;
	@Mock private Estado estadoMock;
	@Spy private Reserva reservaSpy;

	@BeforeEach
	void setUp() throws Exception {
		inquilino = mock(Usuario.class);
		publicacion = mock(Publicacion.class);
		fechasDeAlquiler = mock(RangoDeFechas.class);
		reserva = new Reserva(inquilino, publicacion, fechasDeAlquiler); // SUT	
		inmueble = mock(Inmueble.class);
		estadoMock = mock(Estado.class);
		reservaSpy = spy(Reserva.class);
	}

	@Test
	void testVerCiudadDeReserva() {
		when(publicacion.getInmueble()).thenReturn(inmueble);
		when(inmueble.getCiudad()).thenReturn("Londres");
		assertEquals("Londres", reserva.verCiudadDeReserva());
	}
	
	@Test 
	void testAceptarReserva() {
		when(reservaSpy.getEstado()).thenReturn(estadoMock);
		reservaSpy.aceptarReserva();
		verify(estadoMock).aceptarReserva(reservaSpy);
	}
	
	@Test
	void testRechazarReserva() {
		when(reservaSpy.getEstado()).thenReturn(estadoMock);
		reservaSpy.rechazarReserva();
		verify(estadoMock).rechazarReserva(reservaSpy);
	}
	
	@Test
	void testCancelarReserva() {
		when(reservaSpy.getEstado()).thenReturn(estadoMock);
		when(reservaSpy.getPublicacion()).thenReturn(publicacion);
		when(reservaSpy.getFechas()).thenReturn(fechasDeAlquiler);
		when(publicacion.cancelarReserva(fechasDeAlquiler)).thenReturn("Paga por cancelar");
		assertEquals(reservaSpy.cancelarReserva(), "Paga por cancelar");
		verify(estadoMock).cancelarReserva(reservaSpy);
	}
	
	@Test
	void testRealizadoDeCheckOut() {
		when(reservaSpy.getEstado()).thenReturn(estadoMock);
		reservaSpy.realizadoDeCheckOut();
		verify(estadoMock).checkOut(reservaSpy);
	}
	
	@Test
	void testFueAlquilada() {
		when(reservaSpy.getEstado()).thenReturn(estadoMock);
		reservaSpy.fueAlquilada();
		verify(estadoMock).fueAlquilada(reservaSpy);
	}
	
	//@Test
	//void testAgregarACondicionales() {
	//	reserva.agregarACondicionales(reservaSpy);
	//	assertTrue(reserva.getCondicionales().contains(reservaSpy));
	//	assertEquals(reservaSpy.getEstado(), any(Obsoleta.class));
	//}
	
	@Test 
	void testDeGetters() {
		when(fechasDeAlquiler.getInicio()).thenReturn(LocalDate.of(2024, 12, 15));
		assertEquals(reserva.getFechaIngreso(), LocalDate.of(2024, 12, 15));
		assertEquals(reserva.getInquilino(), inquilino);
		assertEquals(reserva.getPublicacion(), publicacion);
	}
	
	@Test
	void testSetters() {
		reserva.setEstado(estadoMock);
		reserva.setNuevoInquilino(inquilino);
		assertEquals(estadoMock, reserva.getEstado());
		assertEquals(inquilino, reserva.getInquilino());
	}

}
