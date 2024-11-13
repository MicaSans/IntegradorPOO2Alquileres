package ar.edu.unq.integrador.alquileres;

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
	public Boolean fueAlquilada(Reserva reserva) {
		// TODO Auto-generated method stub
		return false;
	}

}
