package ar.edu.unq.integrador.alquileres.publicacion.politicaDeCancelacion;

import ar.edu.unq.integrador.alquileres.rangoDeFechas.RangoDeFechas;

public class SinCancelacion implements PoliticaDeCancelacion {

	@Override
	public boolean esCancelacionGratuita(RangoDeFechas rangoDeFechas) {
		return false;
		
	}

	@Override
	public String cancelarReserva(RangoDeFechas rangoDeFechas) {
		return "Debes pagar el alquiler de los dias reservados completo";
	
	}

}
