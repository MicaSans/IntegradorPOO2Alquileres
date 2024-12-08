package ar.edu.unq.integrador.alquileres.publicacion.politicaDeCancelacion;

import java.time.LocalDate;

import ar.edu.unq.integrador.alquileres.rangoDeFechas.RangoDeFechas;

public class CancelacionGratuita implements PoliticaDeCancelacion {

	private static final int diasLimiteGratis = 10;

	@Override
	public boolean esCancelacionGratuita(RangoDeFechas rangoDeFechas) {
		return rangoDeFechas.getInicio().minusDays(diasLimiteGratis).isAfter(LocalDate.now());
		
	}
	
	@Override
	public String cancelarReserva(RangoDeFechas rangoDeFechas) {
		if (esCancelacionGratuita(rangoDeFechas)) {
			return "No debes nada, cancelada con exito";
		}
		else {
			return "Debes abonar el equivalente a dos dias de reserva";
		}
		
	}

}
