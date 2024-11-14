package ar.edu.unq.integrador.alquileres;

public interface Suscriptor {

	void updatePorCancelacion(Reserva reserva);

	void updatePorBajaDePrecio(Publicacion publicacion);

	void updatePorReserva(Reserva reserva);

}
