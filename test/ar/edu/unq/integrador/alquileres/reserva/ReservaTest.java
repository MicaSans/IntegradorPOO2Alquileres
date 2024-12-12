package ar.edu.unq.integrador.alquileres.reserva;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import ar.edu.unq.integrador.alquileres.publicacion.Publicacion;
//import ar.edu.unq.integrador.alquileres.publicacion.inmueble.Inmueble;
import ar.edu.unq.integrador.alquileres.rangoDeFechas.RangoDeFechas;
import ar.edu.unq.integrador.alquileres.reserva.estado.Estado;
import ar.edu.unq.integrador.alquileres.usuario.Usuario;

class ReservaTest {

	@Mock private Usuario inquilino;
	@Mock private Publicacion publicacion;
	@Mock private RangoDeFechas fechasDeAlquiler;
	private Reserva reserva;
	//@Mock private Inmueble inmueble;
	@Mock private Estado estadoMock;
	@Spy private Reserva reservaSpy;

	@BeforeEach
	void setUp() throws Exception {
		inquilino = mock(Usuario.class);
		publicacion = mock(Publicacion.class);
		fechasDeAlquiler = mock(RangoDeFechas.class);
		reserva = new Reserva(inquilino, publicacion, fechasDeAlquiler); // SUT	
		//inmueble = mock(Inmueble.class);
		estadoMock = mock(Estado.class);
		reservaSpy = spy(Reserva.class);
	
	}

	@Test
	void testVerCiudadDeReserva() {
		//Configuracion
		when(publicacion.getCiudad()).thenReturn("Londres");

		//Verificacion
		assertEquals("Londres", reserva.verCiudadDeReserva());
	
	}
	
	@Test 
	void testAceptarReserva() {
		//Configuracion
		when(reservaSpy.getEstado()).thenReturn(estadoMock);

		//Llamo al método
		reservaSpy.aceptarReserva();

		//Verificacion
		verify(estadoMock).aceptarReserva(reservaSpy);
	
	}
	
	@Test
	void testRechazarReserva() {
		//Configuracion
		when(reservaSpy.getEstado()).thenReturn(estadoMock);

		//Llamo al método
		reservaSpy.rechazarReserva();

		//Verificacion
		verify(estadoMock).rechazarReserva(reservaSpy);
	
	}
	
	@Test
	void testCancelarReserva() {
		//Configuracion
		when(reservaSpy.getEstado()).thenReturn(estadoMock);
		when(reservaSpy.getPublicacion()).thenReturn(publicacion);
		when(reservaSpy.getFechas()).thenReturn(fechasDeAlquiler);
		when(publicacion.cancelarReserva(fechasDeAlquiler)).thenReturn("Paga por cancelar");

		//Verificacion
		assertEquals(reservaSpy.cancelarReserva(), "Paga por cancelar");
		verify(estadoMock).cancelarReserva(reservaSpy);
	
	}
	
	@Test
	void testRealizadoDeCheckOut() {
		//Configuracion
		when(reservaSpy.getEstado()).thenReturn(estadoMock);

		//Llamo al método
		reservaSpy.realizadoDeCheckOut();

		//Verificacion
		verify(estadoMock).checkOut(reservaSpy);
	
	}
	
	@Test
	void testFueAlquilada() {
		//Configuracion
		when(reservaSpy.getEstado()).thenReturn(estadoMock);

		//Llamo al método
		reservaSpy.fueAlquilada();

		//Verificacion
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
		//Configuracion
		when(fechasDeAlquiler.getInicio()).thenReturn(LocalDate.of(2024, 12, 15));

		//Verificacion
		assertEquals(reserva.getFechaIngreso(), LocalDate.of(2024, 12, 15));
		assertEquals(reserva.getInquilino(), inquilino);
		assertEquals(reserva.getPublicacion(), publicacion);
	
	}
	
	@Test
	void testSetters() {
		//Configuracion
		reserva.setEstado(estadoMock);
		reserva.setNuevoInquilino(inquilino);

		//Verificacion
		assertEquals(estadoMock, reserva.getEstado());
		assertEquals(inquilino, reserva.getInquilino());
	
	}
	
	@Test
	void testGetTipoInmueble() {
		//Configuracion
		when(publicacion.getTipoInmueble()).thenReturn("Casa");
		
		//Verificacion
		assertEquals("Casa", reserva.getTipoInmueble());
		
	}
	
	@Test
	void testGetDiasOcupadosPublicacion() {
		//Datos de testeo
		RangoDeFechas rango1 = mock(RangoDeFechas.class);
		RangoDeFechas rango2 = mock(RangoDeFechas.class);
		List<RangoDeFechas> diasOcupados = Arrays.asList(rango1, rango2);
		
		//Configuración
		when(publicacion.getDiasOcupados()).thenReturn(diasOcupados);
		
		//Acción
		List<RangoDeFechas> resultado = reserva.getDiasOcupadosPublicacion();
		
		//Assert
		assertNotNull(resultado);
		assertEquals(diasOcupados.size(), resultado.size());
		assertEquals(diasOcupados, resultado);
		
		//Verificación
		verify(publicacion, times(1)).getDiasOcupados();
		
	}

}
