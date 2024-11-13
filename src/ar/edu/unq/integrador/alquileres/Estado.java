package ar.edu.unq.integrador.alquileres;

public interface Estado {

	void aceptarReserva(Reserva reserva);

	void rechazarReserva(Reserva reserva);

	void cancelarReserva(Reserva reserva);

	void checkOut(Reserva reserva);

	Boolean fueAlquilada(Reserva reserva);

}
