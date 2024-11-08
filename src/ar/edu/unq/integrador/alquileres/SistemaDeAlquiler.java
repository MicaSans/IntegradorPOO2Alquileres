package ar.edu.unq.integrador.alquileres;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SistemaDeAlquiler {
	
	private List<Usuario> usuarios;
	private List<Publicacion> publicaciones;
	private List<Reserva> reservas;
	private List<String> categorias;
	private List<String> tipoInmuebles;
	private List<String> servicios;
	
	public SistemaDeAlquiler() {
		usuarios = new ArrayList<Usuario>();
		publicaciones = new ArrayList<Publicacion>();
		reservas = new ArrayList<Reserva>();
		categorias = new ArrayList<String>();
		tipoInmuebles = new ArrayList<String>();
		servicios = new ArrayList<String>();
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

	public void reservarPublicacion(Usuario usuario, Publicacion publicacion, FormaDePago efectivo,
			LocalDate fechaIngreso, LocalDate fechaSalida) {
		this.getReservas().add(new Reserva(usuario, publicacion, efectivo, fechaIngreso, fechaSalida));
	}
	
	public List<Reserva> getReservas() {
		return this.reservas;
	}

	public void setCategoriasPuntuacion(String categoria) {
		this.getCategorias().add(categoria);
	}

	public List<String> getCategorias() {
		return this.categorias;
	}

	public void setTipoInmueble(String tipoInmueble) {
		this.getTipoInmuebles().add(tipoInmueble);
	}

	public List<String> getTipoInmuebles() {
		return this.tipoInmuebles;
	}

	public void setServicios(String servicio) {
		this.getServicios().add(servicio);
		
	}

	public List<String> getServicios() {
		return this.servicios;
	}
	
	
}
