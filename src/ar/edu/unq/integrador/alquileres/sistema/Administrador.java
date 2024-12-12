package ar.edu.unq.integrador.alquileres.sistema;

import java.util.List;

import ar.edu.unq.integrador.alquileres.publicacion.Publicacion;

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
		List<Publicacion> publicaciones = getPublicacionesDelSistema().stream()
			.filter(publi -> publi.getDiasOcupados().size() == 0)
			.toList();
		return publicaciones;
		
	}

	private List<Publicacion> getPublicacionesDelSistema() {
		return this.getSistema().getPublicaciones();
	
	}

	public Double obtenerTasaOcupacion() {
		Double tasa = Double.valueOf(obtenerInmueblesAlquilados().size()) / getPublicacionesDelSistema().size();
		return  tasa;
	
	}

	private List<Publicacion> obtenerInmueblesAlquilados() {
		List<Publicacion> publicaciones = getPublicacionesDelSistema().stream()
				.filter(publi -> publi.getDiasOcupados().size() != 0)
				.toList();
			return publicaciones;
	
	}
	
}
