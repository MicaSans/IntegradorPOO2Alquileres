package ar.edu.unq.integrador.alquileres;

import java.util.List;

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

	public List<Publicacion> obtenerInmueblesLibres() {
		List<Publicacion> publicaciones = this.getSistema().getPublicaciones().stream()
			.filter(publi -> publi.getDiasOcupados().size() == 0)
			.toList();
		return publicaciones;
		
	}

	public Double obtenerTasaOcupacion() {
		Double tasa = Double.valueOf(obtenerInmueblesAlquilados().size()) / this.getSistema().getPublicaciones().size();
		return  tasa;
	}

	private List<Publicacion> obtenerInmueblesAlquilados() {
		List<Publicacion> publicaciones = this.getSistema().getPublicaciones().stream()
				.filter(publi -> publi.getDiasOcupados().size() != 0)
				.toList();
			return publicaciones;
	}

	
}
