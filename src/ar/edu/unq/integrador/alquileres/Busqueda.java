package ar.edu.unq.integrador.alquileres;


import java.util.List;


public class Busqueda {
	
	private List<FiltroObligatorio> filtros;

	public Busqueda(List<FiltroObligatorio> filtros) {
		this.filtros = filtros;
	}

	public List<Publicacion> filtrarPublicaciones(List<Publicacion> publicaciones) {
		List<Publicacion> filtrados = publicaciones.stream()
				.filter(publicacion -> this.comprobacionDeFiltrado(publicacion))
				.toList();
		return filtrados;
	}

	private Boolean comprobacionDeFiltrado(Publicacion publicacion) {
		
		return this.getFiltros().stream().allMatch(f -> f.filtrar(publicacion));
	}

	private List<FiltroObligatorio> getFiltros() {
		
		return this.filtros;
	}

}
