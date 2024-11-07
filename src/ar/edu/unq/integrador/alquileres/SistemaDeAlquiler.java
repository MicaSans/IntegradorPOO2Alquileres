package ar.edu.unq.integrador.alquileres;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SistemaDeAlquiler {
	
	private List<Usuario> usuarios;
	private List<Publicacion> publicaciones;
	
	public SistemaDeAlquiler() {
		usuarios = new ArrayList<Usuario>();
		publicaciones = new ArrayList<Publicacion>();
	}
	
	public void registrarUsuario(Usuario usuario) {
		this.getUsuarios().add(usuario);
		usuario.setFechaInicioUsuario(LocalDate.now());
	}

	public List<Usuario> getUsuarios() {
		
		return this.usuarios;
	}

	public void generarPublicacion(Publicacion publicacion) {
		this.getPublicaciones().add(publicacion);
		
	}

	public List<Publicacion> getPublicaciones() {
		
		return this.publicaciones;
	}

	public List<Publicacion> buscarPublicaciones(Busqueda busqueda) {
		return busqueda.filtrarPublicaciones(this.getPublicaciones());
	}

}
