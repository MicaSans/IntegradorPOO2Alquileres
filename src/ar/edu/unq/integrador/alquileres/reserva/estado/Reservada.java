package ar.edu.unq.integrador.alquileres.reserva.estado;

import java.time.LocalDate;
import ar.edu.unq.integrador.alquileres.reserva.Reserva;

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
		// Primero nos fijamos si en la lista de condicionales hay alguno que coincida con las fechas o quizas esta vacia
		if (verificacionParaSacarDeCondicional(reserva)) {
			// Y ahora buscamos la primer reserva que coincide con las fechas
			Reserva nuevaReserva = primerCondicional(reserva);
			nuevaReserva.setEstado(new Pendiente());
			reserva.getCondicionalesDePublicacion().remove(nuevaReserva);
			//reserva.getPublicacion().getCondicionales().remove(nuevaReserva);
			
			// Saco estos porque no es necesario, era para cuando estaban es reservas las condicionales
			//reserva.getPublicacion().getCondicionales().stream()
				//.forEach(condicional -> nuevaReserva.getPublicacion().agregarACondicionales(condicional));
		}
		reserva.getPublicacion().quitarADiasOcupados(reserva.getFechas());
		reserva.setEstado(new Obsoleta());
		
	}
	
	//En la front end si se realiza un checkOut, pasaria el estado de la reserva a Alquilada, siempre y cuando se cumpla que el inquilino realize el checkOut
	@Override
	public void checkOut(Reserva reserva) {
		if (LocalDate.now().isAfter(reserva.getFechaFinal())) {
			reserva.getPublicacion().quitarADiasOcupados(reserva.getFechas()); 
			// Realizo esto para que la lista de dias ocupados en publicacion no sea inmensa, aunque nadie va a buscar dias anteriores al presente.
			reserva.setEstado(new Alquilada());
		}
		// Y si quiere realizar checkOut antes del ultimo dia, no hace nada
		
	}

	@Override
	public boolean fueAlquilada(Reserva reserva) {
		return false;
		
	}
	
	private Reserva primerCondicional(Reserva reserva) {
		Reserva nuevaReserva = reserva.getCondicionalesDePublicacion().stream()
			.filter(condicional -> reserva.getFechas().seSuperponenDias(condicional.getFechas()))
			.findFirst()
			.get();
		return nuevaReserva;
	
	}

	private boolean verificarSiHayCondicionalDeFechaSimilar(Reserva reserva) {
		return reserva.getCondicionalesDePublicacion().stream()
		//return reserva.getPublicacion().getCondicionales().stream()
				.anyMatch(condicional -> reserva.getFechas().seSuperponenDias(condicional.getFechas()));
	
	}
	
	private boolean verificacionParaSacarDeCondicional(Reserva reserva) {
		return !reserva.getCondicionalesDePublicacion().isEmpty()
		//return !reserva.getPublicacion().getCondicionales().isEmpty()
				&& verificarSiHayCondicionalDeFechaSimilar(reserva);
	
	}

}