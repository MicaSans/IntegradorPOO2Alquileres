package ar.edu.unq.integrador.alquileres;

public class SinCancelacion implements PoliticaDeCancelacion {

	@Override
	public String cancelarReserva(RangoDeFechas rangoDeFechas) {
		
		return "Debes pagar el alquiler de los dias reservados completo";
	}

}
