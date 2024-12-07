package ar.edu.unq.integrador.alquileres.observadorDelSistema.suscriptor;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.integrador.alquileres.publicacion.Publicacion;
import ar.edu.unq.integrador.alquileres.publicacion.inmueble.Inmueble;
import ar.edu.unq.integrador.alquileres.reserva.Reserva;

class SuscriptorTest {

	private SuscriptorBajaDePrecio suscriptorBajaDePrecio;
	@Mock private HomePagePublisher hPP;
	@Mock private Publicacion publicacion;
	@Mock private PopUpWindow pUW;
	private SuscriptorCancelacion suscriptorCancelacion;
	@Mock private Inmueble inmueble;
	@Mock private Reserva reserva;
	private SuscriptorReservaRealizada suscriptorReservaRealizada;

	@BeforeEach
	void setUp() throws Exception {
		publicacion = mock(Publicacion.class);
		inmueble = mock(Inmueble.class);
		reserva = mock(Reserva.class);
		
		// Cancelacion
		pUW = mock(PopUpWindow.class);
		suscriptorCancelacion = new SuscriptorCancelacion(pUW);
		
		// Baja de Precio
		hPP = mock (HomePagePublisher.class);
		suscriptorBajaDePrecio = new SuscriptorBajaDePrecio(hPP);
		
		// Reserva realizada
		suscriptorReservaRealizada = new SuscriptorReservaRealizada();
		
	}
	
	@Test
	void testUpdatePorCancelacion() {
		when(reserva.getPublicacion()).thenReturn(publicacion);
		when(publicacion.getInmueble()).thenReturn(inmueble);
		when(inmueble.getTipoInmueble()).thenReturn("Casa");
		suscriptorCancelacion.updatePorCancelacion(reserva);
		verify(pUW).popUp("El/la Casa que te interesa se ha liberado! Correa a reservarlo!", "Azul", 12);
		
	}
	
	@Test
	void testUpdatePorBajaDePrecio() {
		when(publicacion.getInmueble()).thenReturn(inmueble);
		when(inmueble.getTipoInmueble()).thenReturn("Casa");
		when(publicacion.getPrecioPorDia()).thenReturn(300d);
		suscriptorBajaDePrecio.updatePorBajaDePrecio(publicacion);
		verify(hPP).publish("Un inmueble Casa a tan solo 300.0 pesos");
		
		}
	
	@Test
	void testSuscriptorReservaRealizadaUpdatePorReserva() {
		suscriptorReservaRealizada.updatePorReserva(reserva);
		
		}
	
	@Test
	void testSuscriptorReservaRealizadaUpdatePorCancelacion() {
		//Acción
		assertDoesNotThrow(()->suscriptorReservaRealizada.updatePorCancelacion(reserva));
		//Verifico que no lanza excepción, ya que el método no tiene implementación
			
	}
	
	@Test
	void testSuscriptorReservaRealizadaUpdatePorBajaDePrecio() {
		//Acción
		assertDoesNotThrow(()->suscriptorReservaRealizada.updatePorBajaDePrecio(publicacion));
		//Verifico que no lanza excepción, ya que el método no tiene implementación
			
	}
	
	@Test
	void testSuscriptorCancelacionUpdatePorBajaDePrecio() {
		//Acción
		assertDoesNotThrow(()->suscriptorCancelacion.updatePorBajaDePrecio(publicacion));
		//Verifico que no lanza excepción, ya que el método no tiene implementación
			
	}
	
	@Test
	void testSuscriptorCancelacionUpdatePorReserva() {
		//Acción
		assertDoesNotThrow(()->suscriptorCancelacion.updatePorReserva(reserva));
		//Verifico que no lanza excepción, ya que el método no tiene implementación
			
	}
	
	@Test
	void testSuscriptorBajaDePrecioUpdatePorCancelacion() {
		//Acción
		assertDoesNotThrow(()->suscriptorBajaDePrecio.updatePorCancelacion(reserva));
		//Verifico que no lanza excepción, ya que el método no tiene implementación
			
	}
	
	@Test
	void testSuscriptorBajaDePrecioUpdatePorReserva() {
		//Acción
		assertDoesNotThrow(()->suscriptorBajaDePrecio.updatePorReserva(reserva));
		//Verifico que no lanza excepción, ya que el método no tiene implementación
			
	}

}
