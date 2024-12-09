package ar.edu.unq.integrador.alquileres.busqueda.filtro;

import ar.edu.unq.integrador.alquileres.publicacion.Publicacion;

public class FiltroHuespedes implements Filtro {

	private Integer cantHuespedes;

	public FiltroHuespedes(int cantHuespedes) {
		this.cantHuespedes = cantHuespedes;
	
	}

	@Override
	public boolean filtrar(Publicacion publicacion) {
		return tieneCapacidadSuficiente(publicacion);
	
	}

	private int getCantidadHuespedes() {	
		return this.cantHuespedes;
	
	}
	
	private boolean tieneCapacidadSuficiente(Publicacion publicacion) {
		return publicacion.tieneCapacidadPara(this.getCantidadHuespedes());
	}

}
