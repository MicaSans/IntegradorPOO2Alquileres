package ar.edu.unq.integrador.alquileres.publicacion.politicaDeCancelacion;

import java.time.LocalDate;

import ar.edu.unq.integrador.alquileres.rangoDeFechas.RangoDeFechas;

public class Intermedia implements PoliticaDeCancelacion {

	@Override
	public String cancelarReserva(RangoDeFechas rangoDeFechas) {
		if (rangoDeFechas.getInicio().minusDays(20).isAfter(LocalDate.now())) {
			return "No debes nada, cancelada con exito";
		}
			else if (rangoDeFechas.getInicio().minusDays(10).isAfter(LocalDate.now())){
				return "Debes abonar el 50% del alquiler";
			}
				else {
					return "Debes pagar la totalidad del alquiler";
		}
	
	}
}
