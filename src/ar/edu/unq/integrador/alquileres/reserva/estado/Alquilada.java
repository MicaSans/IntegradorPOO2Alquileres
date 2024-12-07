package ar.edu.unq.integrador.alquileres.reserva.estado;

import ar.edu.unq.integrador.alquileres.reserva.Reserva;

public class Alquilada implements Estado {

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
		reserva.setEstado(new Obsoleta());
	}

	@Override
	public Boolean fueAlquilada(Reserva reserva) {
		return true;
	}

}
