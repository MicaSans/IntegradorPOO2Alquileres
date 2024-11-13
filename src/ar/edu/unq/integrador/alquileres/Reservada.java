package ar.edu.unq.integrador.alquileres;

import java.time.LocalDate;

public class Reservada implements Estado {

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
		if (!reserva.getCondicionales().isEmpty()) {
			reserva.setNuevoInquilino(reserva.getCondicionales().remove(0));
			reserva.setEstado(new Pendiente());
		}
		else {
			reserva.getPublicacion().quitarADiasOcupados(reserva.getFechas());
			reserva.setEstado(new Obsoleta());
		}

	}
	
	//En la front end si se realiza un checkOut, pasaria el estado de la reserva a Alquilada, siempre y cuando se cumpla que el inquilino realize el checkOut
	@Override
	public void checkOut(Reserva reserva) {
		if (LocalDate.now().isAfter(reserva.getFechas().getFinal())) {
			reserva.getPublicacion().quitarADiasOcupados(reserva.getFechas()); 
			// Realizo esto para que la lista de dias ocupados en publicacion no sea inmensa, aunque nadie va a buscar dias anteriores al presente.
			reserva.setEstado(new Alquilada());
		}
		// Y si quiere realizar checkOut antes del ultimo dia, no hace nada
	}

	@Override
	public Boolean fueAlquilada(Reserva reserva) {
		// TODO Auto-generated method stub
		return false;
	}

}
