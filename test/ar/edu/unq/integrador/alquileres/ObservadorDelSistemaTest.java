package ar.edu.unq.integrador.alquileres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class ObservadorDelSistemaTest {

	private ObservadorDelSistema observer;
	@Mock private Reserva reserva;
	@Mock private Suscriptor suscriptor;
	@Mock private Publicacion publicacion;
	
	@BeforeEach
	void setUp() throws Exception {
		observer = new ObservadorDelSistema();
		reserva = mock(Reserva.class);
		suscriptor = mock(Suscriptor.class);
		publicacion = mock(Publicacion.class);
		// Agrego esto en el setup ya que lo utilizo en todos los tests
		observer.agregarSuscriptor(suscriptor);
		
	}

	@Test
	void testAgregarSuscriptor() {
		assertTrue(observer.getSuscriptores().contains(suscriptor));
	}
	
	@Test
	void testNotificarCancelacion() {
		observer.notificarCancelacion(reserva);
		verify(suscriptor).updatePorCancelacion(reserva);
	}
	
	@Test
	void testNotificarBajaDePrecio() {
		observer.notificarBajaDePrecio(publicacion);
		verify(suscriptor).updatePorBajaDePrecio(publicacion);
	}
	
	@Test
	void testNotificarReserva() {
		observer.notificarReserva(reserva);
		verify(suscriptor).updatePorReserva(reserva);
	}
	

}
