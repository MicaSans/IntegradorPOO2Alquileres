package ar.edu.unq.integrador.alquileres.rangoDeFechas;

import java.time.LocalDate;

public class RangoDeFechas {

	private LocalDate fechaEntrante;
	private LocalDate fechaSalida;

	public RangoDeFechas(LocalDate entrante, LocalDate salida) {
		this.fechaEntrante = entrante;
		this.fechaSalida = salida;
	
	}

	public LocalDate getInicio() {
		return this.fechaEntrante;
	
	}

	public LocalDate getFinal() {
		return this.fechaSalida;
		
	}

	public boolean esRangoCompatible() {
		return esAnteriorA(this.getFinal());
		
	}
	
	private boolean esAnteriorA(LocalDate dia) {
		return this.getInicio().isBefore(dia);
	}

	public boolean estaDentroDeLasFechas(LocalDate dia) {
		return dia.isAfter(this.getInicio())
				&& dia.isBefore(this.getFinal());
	
	}

	public boolean seSuperponenDias(RangoDeFechas rangoDeFecha) {
		return !esPosteriorA(rangoDeFecha.getFinal()) 
				&& !rangoDeFecha.esPosteriorA(this.getFinal());
	
	}

	private boolean esPosteriorA(LocalDate dia) {
		return this.getInicio().isAfter(dia);
	}
	
}
