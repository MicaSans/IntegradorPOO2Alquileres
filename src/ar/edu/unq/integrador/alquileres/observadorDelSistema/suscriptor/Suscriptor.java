package ar.edu.unq.integrador.alquileres.observadorDelSistema.suscriptor;

import ar.edu.unq.integrador.alquileres.publicacion.Publicacion;
import ar.edu.unq.integrador.alquileres.reserva.Reserva;

public interface Suscriptor {

	void updatePorCancelacion(Reserva reserva);

	void updatePorBajaDePrecio(Publicacion publicacion);

	void updatePorReserva(Reserva reserva);

}
