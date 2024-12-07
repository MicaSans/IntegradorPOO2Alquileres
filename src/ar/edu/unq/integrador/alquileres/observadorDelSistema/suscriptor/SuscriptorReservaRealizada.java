package ar.edu.unq.integrador.alquileres.observadorDelSistema.suscriptor;

import ar.edu.unq.integrador.alquileres.publicacion.Publicacion;
import ar.edu.unq.integrador.alquileres.reserva.Reserva;

public class SuscriptorReservaRealizada implements Suscriptor {

	@Override
	public void updatePorCancelacion(Reserva reserva) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePorBajaDePrecio(Publicacion publicacion) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePorReserva(Reserva reserva) {
		// Por ahora no realiza nada

	}

}
