package ar.edu.unq.integrador.alquileres;

import java.time.LocalDate;

public class RangoDeFechas {

	private LocalDate fechaInicio;
	private LocalDate fechaFinal;

	public RangoDeFechas(LocalDate fechaInicio, LocalDate fechaFinal) {
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
	}

	public LocalDate getInicio() {
		
		return this.fechaInicio;
	}

	public LocalDate getFinal() {
		// TODO Auto-generated method stub
		return this.fechaFinal;
	}

	public boolean esRangoCompatible() {
		return this.getInicio().isBefore(this.getFinal());
	}

}
