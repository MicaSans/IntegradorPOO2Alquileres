package ar.edu.unq.integrador.alquileres.rangoDeFechas;

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

	public boolean estaDentroDeLasFechas(LocalDate dia) {
		return dia.isAfter(this.getInicio()) && dia.isBefore(this.getFinal());
	}

	public boolean seSuperponenDias(RangoDeFechas rangoDeFecha) {
		
		return !this.getInicio().isAfter(rangoDeFecha.getFinal()) 
				&& !rangoDeFecha.getInicio().isAfter(this.getFinal());
	}
}
