package ar.edu.unq.integrador.alquileres.reserva.estado;

import java.util.List;

import ar.edu.unq.integrador.alquileres.rangoDeFechas.RangoDeFechas;
import ar.edu.unq.integrador.alquileres.reserva.Reserva;

public class Pendiente implements Estado {

	@Override
	public void aceptarReserva(Reserva reserva) {
		if(!this.estaOcupado(reserva.getPublicacion().getDiasOcupados(), reserva.getFechas())){
			reserva.getPublicacion().agregarADiasOcupados(reserva.getFechas());
			reserva.setEstado(new Reservada());
		}else {
			reserva.getPublicacion().agregarACondicionales(reserva);
			reserva.setEstado(new Obsoleta());
		}
		
	}
	
	public boolean estaOcupado(List<RangoDeFechas> fechasOcupadas, RangoDeFechas rangoDeFechas) {
		return fechasOcupadas.stream()
				.anyMatch(fecha -> fecha.seSuperponenDias(rangoDeFechas));
	
	}

	@Override
	public void rechazarReserva(Reserva reserva) {
		if (!reserva.getPublicacion().getCondicionales().isEmpty()) {
			Reserva nuevaReserva = reserva.getPublicacion().getCondicionales().remove(0);
			nuevaReserva.setEstado(new Pendiente());
			reserva.getPublicacion().getCondicionales().stream()
				.forEach(condicional -> nuevaReserva.getPublicacion().agregarACondicionales(condicional));
		}
		reserva.setEstado(new Obsoleta());
	
	}

	@Override
	public void cancelarReserva(Reserva reserva) {
		reserva.setEstado(new Obsoleta());
	
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
