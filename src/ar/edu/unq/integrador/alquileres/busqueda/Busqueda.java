package ar.edu.unq.integrador.alquileres.busqueda;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ar.edu.unq.integrador.alquileres.busqueda.filtro.Filtro;
import ar.edu.unq.integrador.alquileres.busqueda.filtro.FiltroBase;
import ar.edu.unq.integrador.alquileres.publicacion.Publicacion;

public class Busqueda {
	
	private List<Filtro> filtros;
	private Set<Class<? extends Filtro>> clasesDeFiltros;

	public Busqueda(FiltroBase filtro) {
		// El filtro base no puede ser nulo
		this.filtros = new ArrayList<Filtro>();  // Aca se resuelve que si o si este el filtro base
		filtros.add(filtro);
		this.clasesDeFiltros = new HashSet<>();  // Esto lo instancio para que no se repitan filtros del mismo tipo
		clasesDeFiltros.add(filtro.getClass());
		
	}

	public List<Publicacion> filtrarPublicaciones(List<Publicacion> publicaciones) {
		//List<Publicacion> filtrados = publicaciones.stream()
		//		.filter(publicacion -> this.comprobacionDeFiltrado(publicacion))
		//		.toList();
		//return filtrados;
		return publicaciones.stream()
				.filter(this::comprobacionDeFiltrado)
				.toList();
	
	}

	private Boolean comprobacionDeFiltrado(Publicacion publicacion) {
		return  this.getFiltros().stream()
				.allMatch(f -> f.filtrar(publicacion));
	
	}

	public List<Filtro> getFiltros() {
		return this.filtros;
	
	}

	public void agregarFiltro(Filtro filtro) {
		//El filtro no puede ser nulo
		if (clasesDeFiltros.add(filtro.getClass())) {
			filtros.add(filtro);
		} 		
	
	}

}
