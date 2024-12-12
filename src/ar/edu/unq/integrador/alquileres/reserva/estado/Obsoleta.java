package ar.edu.unq.integrador.alquileres.reserva.estado;

import ar.edu.unq.integrador.alquileres.reserva.Reserva;

public class Obsoleta implements Estado {

	@Override
	public void aceptarReserva(Reserva reserva) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rechazarReserva(Reserva reserva) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelarReserva(Reserva reserva) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkOut(Reserva reserva) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean fueAlquilada(Reserva reserva) {
		return false;
		
	}

}
