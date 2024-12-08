package ar.edu.unq.integrador.alquileres.publicacion.politicaDeCancelacion;

import java.time.LocalDate;

import ar.edu.unq.integrador.alquileres.rangoDeFechas.RangoDeFechas;

public class Intermedia implements PoliticaDeCancelacion {

	private static final int diasLimiteGratis = 20;
	private static final int diasLimite50 = 10;

	@Override
	public boolean esCancelacionGratuita(RangoDeFechas rangoDeFechas) {
		//Determina si es gratuita dependiendo de los días
		LocalDate fechaActual = LocalDate.now();
		
		if(rangoDeFechas.getInicio().minusDays(diasLimiteGratis).isAfter(fechaActual)) {
			return true; //Es gratuita si falta más de 20 días
		}
		return false; //No es gratuita en otros casos
	}
	
	@Override
	public String cancelarReserva(RangoDeFechas rangoDeFechas) {
		LocalDate fechaInicio = rangoDeFechas.getInicio();
		LocalDate fechaActual = LocalDate.now();

		if (esCancelacionGratuita(rangoDeFechas)) {
			return "No debes nada, cancelada con exito";
		}
		else if (fechaInicio.minusDays(diasLimite50).isAfter(fechaActual)){
			return "Debes abonar el 50% del alquiler";
		}
		else {
			return "Debes pagar la totalidad del alquiler";
		}
	
	}
	
}
