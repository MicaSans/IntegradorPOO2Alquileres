package ar.edu.unq.integrador.alquileres.publicacion.politicaDeCancelacion;

import ar.edu.unq.integrador.alquileres.rangoDeFechas.RangoDeFechas;

public interface PoliticaDeCancelacion {

	public boolean esCancelacionGratuita(RangoDeFechas rangoDeFechas);
	public String cancelarReserva(RangoDeFechas rangoDeFechas);

}
