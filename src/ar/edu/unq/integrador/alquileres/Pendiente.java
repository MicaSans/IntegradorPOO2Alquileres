package ar.edu.unq.integrador.alquileres;

public class Pendiente implements Estado {

	@Override
	public void aceptarReserva(Reserva reserva) {
		reserva.getPublicacion().agregarADiasOcupados(reserva.getFechas());
		reserva.setEstado(new Reservada());
	}

	@Override
	public void rechazarReserva(Reserva reserva) {
		reserva.setEstado(new Obsoleta());
		
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
		
		return false;
	}

}
