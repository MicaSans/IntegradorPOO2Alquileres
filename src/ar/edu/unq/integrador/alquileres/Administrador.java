package ar.edu.unq.integrador.alquileres;

public class Administrador {

	private SistemaDeAlquiler sistema;

	public Administrador(SistemaDeAlquiler sistema) {
		this.sistema = sistema;
	}

	private SistemaDeAlquiler getSistema() {
		return this.sistema;
	}
	
	public void darDeAltaServicio(String servicio) {
		this.getSistema().setServicio(servicio);
	}

	public void darDeAltaCategoriaPuntuacion(String categoria) {
		this.getSistema().setCategoriaPuntuacion(categoria);
	}

	public void darDeAltaTipoInmueble(String tipoInmueble) {
		this.getSistema().setTipoInmueble(tipoInmueble);
	}

}
