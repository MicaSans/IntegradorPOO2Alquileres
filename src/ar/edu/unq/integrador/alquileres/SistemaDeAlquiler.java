package ar.edu.unq.integrador.alquileres;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public void reservarPublicacion(Reserva reserva) {
		this.getReservas().add(reserva);
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

	public void aceptarReserva(Reserva reserva) {
		reserva.aceptarReserva();
	}

	public void rechazarReserva(Reserva reserva) {
		reserva.rechazarReserva();
	}

	public void cancelarReserva(Reserva reserva) {
		reserva.cancelarReserva();	
	}

	public void realizarCheckOut(Reserva reserva) {
		reserva.realizadoDeCheckOut();
	}

	public void cambiarPoliticaDeCancelacion(Publicacion publicacion, PoliticaDeCancelacion politicaDeCancelacion) {
		publicacion.setPoliticaCancelacion(politicaDeCancelacion);
	}

	public void reservarCondicionalmente(Reserva reserva, Usuario usuario) {
		reserva.agregarACondicionales(usuario);
		
	}

	public void calificarInquilino(Ranking calificacion, Usuario usuario) {
		usuario.agregarCalificacionInquilino(calificacion);
	}

	public void calificarPropietario(Ranking calificacion, Usuario usuario) {
		usuario.agregarCalificacionPropietario(calificacion);
	}

	public void calificarPublicacion(Ranking calificacion, Publicacion publicacion) {
		publicacion.agregarCalificacion(calificacion);
		
	}

	public String verNombreUsuario(Usuario usuario) {
		
		return usuario.getNombre();
	}

	public String verEmailUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuario.getEmail();
	}

	public String verTelefonoUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuario.getTelefono();
	}

	public LocalDate verFechaInicioUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuario.getFechaInicioUsuario();
	}

	public Usuario verInquilino(Reserva reserva) {
		// TODO Auto-generated method stub
		return reserva.getInquilino();
	}

	public List<Reserva> verReservas(Usuario usuario) {
		List<Reserva> reservasDeUsuario = this.getReservas().stream()
				.filter(reserva -> reserva.getInquilino().equals(usuario))
				.collect(Collectors.toList());
		
		
		return reservasDeUsuario;
	}

	public List<Reserva> verReservasFuturas(Usuario usuario) {
		List<Reserva> reservasFuturas = this.verReservas(usuario).stream()
				.filter(reserva -> reserva.getFechaIngreso().isAfter(LocalDate.now()))
				.toList();
		return reservasFuturas;
	}

	public List<Reserva> verReservasEnCiudad(Usuario usuario, String string) {
		List<Reserva> reservasEnCiudad = this.verReservas(usuario).stream()
				.filter(reserva -> reserva.getFechaIngreso().isAfter(LocalDate.now()))
				.toList();
		return reservasEnCiudad;
	}

	
	
	
	
}
