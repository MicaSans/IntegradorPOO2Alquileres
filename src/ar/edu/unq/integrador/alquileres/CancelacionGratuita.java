package ar.edu.unq.integrador.alquileres;

import java.time.LocalDate;

public class CancelacionGratuita implements PoliticaDeCancelacion {

	@Override
	public String cancelarReserva(RangoDeFechas rangoDeFechas) {
		if (rangoDeFechas.getInicio().minusDays(10).isAfter(LocalDate.now())) {
			return "No debes nada, cancelada con exito";
		}
		else {
			return "Debes abonar el equivalente a dos dias de reserva";
		}
		
		
	}

}
