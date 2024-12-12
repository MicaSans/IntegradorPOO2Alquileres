package ar.edu.unq.integrador.alquileres.busqueda.filtro;

import ar.edu.unq.integrador.alquileres.publicacion.Publicacion;
import ar.edu.unq.integrador.alquileres.rangoDeFechas.RangoDeFechas;

public class FiltroBase implements Filtro{

	private String ciudad;
	private RangoDeFechas rangoDeFechas;
	
	public FiltroBase(String ciudad, RangoDeFechas fechas) {
		this.ciudad = ciudad;
		this.rangoDeFechas = fechas;
	
	}

	public boolean filtrar(Publicacion publicacion) {
		return this.filtrarPorCiudad(publicacion) 
				&& this.filtrarPorFechas(publicacion);
	
	}

	private boolean filtrarPorFechas(Publicacion publicacion) {	
		return esRangoCompatible()
				&& !tieneSuperposicionCon(publicacion);
	
	}

	private RangoDeFechas getRangoDeFecha() {
		return this.rangoDeFechas;
	
	}
	
	private boolean filtrarPorCiudad(Publicacion publicacion) {
		return this.getCiudad().equals(publicacion.getCiudad());
	
	}

	private String getCiudad() {
		return this.ciudad;
	
	}
	
	private boolean esRangoCompatible() {
		return this.getRangoDeFecha().esRangoCompatible();
		
	}
	
	private boolean tieneSuperposicionCon(Publicacion publicacion) {
		return publicacion.tieneSuperposicionDeDiasCon(this.getRangoDeFecha());
	
	}
	
}
