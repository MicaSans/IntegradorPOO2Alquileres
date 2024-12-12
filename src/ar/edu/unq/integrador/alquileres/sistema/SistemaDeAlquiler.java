package ar.edu.unq.integrador.alquileres.sistema;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unq.integrador.alquileres.busqueda.Busqueda;
import ar.edu.unq.integrador.alquileres.observadorDelSistema.ObservadorDelSistema;
import ar.edu.unq.integrador.alquileres.publicacion.FormaDePago;
import ar.edu.unq.integrador.alquileres.publicacion.Foto;
import ar.edu.unq.integrador.alquileres.publicacion.Publicacion;
import ar.edu.unq.integrador.alquileres.publicacion.inmueble.Inmueble;
import ar.edu.unq.integrador.alquileres.publicacion.politicaDeCancelacion.PoliticaDeCancelacion;
import ar.edu.unq.integrador.alquileres.ranking.Ranking;
import ar.edu.unq.integrador.alquileres.reserva.Reserva;
import ar.edu.unq.integrador.alquileres.usuario.Usuario;

public class SistemaDeAlquiler {
	
	private List<Usuario> usuarios;
	private List<Publicacion> publicaciones;
	private List<Reserva> reservas;
	private List<String> categoriasDePuntuacion;
	private List<String> tiposDeInmueble;
	private List<String> servicios;
	private ObservadorDelSistema observer;
	
	public SistemaDeAlquiler() {
		usuarios = new ArrayList<Usuario>();
		publicaciones = new ArrayList<Publicacion>();
		reservas = new ArrayList<Reserva>();
		categoriasDePuntuacion = new ArrayList<String>();
		tiposDeInmueble = new ArrayList<String>();
		servicios = new ArrayList<String>();
		observer = new ObservadorDelSistema();
		
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
		this.getObserver().notificarReserva(reserva);
		
	}
	
	public ObservadorDelSistema getObserver() {
		return this.observer;
		
	}

	public List<Reserva> getReservas() {
		return this.reservas;
		
	}

	public void setCategoriaPuntuacion(String categoria) {
		this.getCategorias().add(categoria);
		
	}

	public List<String> getCategorias() {
		return this.categoriasDePuntuacion;
		
	}

	public void setTipoInmueble(String tipoInmueble) {
		this.getTipoInmuebles().add(tipoInmueble);
		
	}

	public List<String> getTipoInmuebles() {
		return this.tiposDeInmueble;
		
	}

	public void setServicio(String servicio) {
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

	public String cancelarReserva(Reserva reserva) {
		this.getObserver().notificarCancelacion(reserva);
		return reserva.cancelarReserva();	
		
	}

	public void realizarCheckOut(Reserva reserva) {
		reserva.realizadoDeCheckOut();
		
	}

	public void cambiarPoliticaDeCancelacion(Publicacion publicacion, PoliticaDeCancelacion politicaDeCancelacion) {
		publicacion.setPoliticaCancelacion(politicaDeCancelacion);
		
	}

	public void calificarInquilino(Ranking calificacion, Usuario inquilino) {
		inquilino.agregarCalificacionInquilino(calificacion);
		
	}

	public void calificarPropietario(Ranking calificacion, Usuario propietario) {
		propietario.agregarCalificacionPropietario(calificacion);
		
	}

	public void calificarPublicacion(Ranking calificacion, Publicacion publicacion) {
		publicacion.agregarCalificacion(calificacion);
		
	}

	public String verNombreUsuario(Usuario usuario) {
		return usuario.getNombre();
		
	}

	public String verEmailUsuario(Usuario usuario) {
		return usuario.getEmail();
		
	}

	public String verTelefonoUsuario(Usuario usuario) {
		return usuario.getTelefono();
		
	}

	public LocalDate verFechaInicioUsuario(Usuario usuario) {
		return usuario.getFechaInicioUsuario();
		
	}

	public Usuario verInquilino(Reserva reserva) {
		return reserva.getInquilino();
		
	}

	public List<Reserva> verReservas(Usuario usuario) {
		List<Reserva> reservasDeUsuario = this.getReservas().stream()
				.filter(reserva -> reserva.getInquilino().equals(usuario)).toList();
		return reservasDeUsuario;
		
	}

	public List<Reserva> verReservasFuturas(Usuario inquilino) {
		List<Reserva> reservasFuturas = this.verReservas(inquilino).stream()
				.filter(reserva -> reserva.getFechaIngreso().isAfter(LocalDate.now()))
				.toList();
		return reservasFuturas;
		
	}

	public List<Reserva> verReservasEnCiudad(Usuario inquilino, String ciudad) {
		List<Reserva> reservasEnCiudad = this.verReservas(inquilino).stream()
				.filter(reserva -> reserva.verCiudadDeReserva().equals(ciudad)).toList();
		return reservasEnCiudad;
		
	}

	public List<String> verCiudadesDeLasReservas(Usuario inquilino) {
		List <String> ciudadesDeReservas = new ArrayList<String>();
		this.verReservas(inquilino).stream()
			.forEach(reserva -> ciudadesDeReservas.add(reserva.verCiudadDeReserva()));
		return ciudadesDeReservas;
		
	}

	public int verPuntajePublicacion(String categoria, Publicacion publicacion) {
		return publicacion.verPuntajeCategoria(categoria);
		
	}

	public int verPromedioPublicacion(Publicacion publicacion) {
		return publicacion.verPromedioTotal();
		
	}

	public Inmueble verInmueblePublicacion(Publicacion publicacion) {
		return publicacion.getInmueble();
		
	}

	public List<Foto> verFotosPublicacion(Publicacion publicacion) {
		return publicacion.getFotos();
		
	}

	public List<FormaDePago> verFormasDePagoPublicacion(Publicacion publicacion) {
		return publicacion.getFormasDePago();
		
	}

	public List<String> verComentariosPublicacion(Publicacion publicacion) {
		return publicacion.verComentarios();
		
	}

	public int verCantidadQueFueAlquilada(Publicacion publicacion) {
		List<Reserva> reservasDePublicacion = this.getReservas().stream()
			.filter(reserva -> reserva.getPublicacion().equals(publicacion) && reserva.fueAlquilada()).toList();
		return reservasDePublicacion.size();
		
	}

	public int verCuantasVecesAlquiloInmuebles(Usuario propietario) {
		List<Reserva> reservasDePropietario = this.reservasDePropietario(propietario);
			return reservasDePropietario.size();
			
	}
	
	private List<Reserva> reservasDePropietario(Usuario propietario) {
		List<Reserva> reservasDePropietario = this.getReservas().stream()
				.filter(reserva -> reserva.getPropietarioReserva().equals(propietario) && reserva.fueAlquilada()).toList();
			return reservasDePropietario;
			
	}
	
	public List<Inmueble> verInmueblesAlquilados(Usuario propietario) {
		List<Inmueble> inmuebles = new ArrayList<Inmueble>();
			this.reservasDePropietario(propietario).stream()
			.forEach(reserva -> inmuebles.add(reserva.getInmuebleReserva()));
		Set<Inmueble> inmueblesNoRepetidos = new HashSet<Inmueble>(inmuebles);
		List<Inmueble> inmueblesAlquilados = new ArrayList<>(inmueblesNoRepetidos);
		return inmueblesAlquilados;
		
	}

	public int verPromedioPuntajePropietario(Usuario propietario) {
		return propietario.verPromedioPropietario();
		
	}

	public int verPuntajePropietario(String categoria, Usuario propietario) {
		return propietario.verPuntajePropietario(categoria);
		
	}

	public List<String> verComentariosPropietario(Usuario propietario) {
		return propietario.verComentariosPropietario();
		
	}

	public int verPromedioPuntajeInquilino(Usuario inquilino) {
		return inquilino.verPromedioInquilino();
		
	}

	public int verPuntajeInquilino(String categoria, Usuario inquilino) {
		return inquilino.verPuntajeInquilino(categoria);
		
	}

	public List<String> verComentariosInquilino(Usuario inquilino) {
		return inquilino.verComentariosInquilino();
		
	}

	public void cambiarPrecioPorDia(Publicacion publicacion, double precio) {
		if (publicacion.getPrecioPorDia() > precio) {
			publicacion.cambiarPrecioPorDia(precio);
			this.getObserver().notificarBajaDePrecio(publicacion);
		}
		else {
			publicacion.cambiarPrecioPorDia(precio);
		}
		
	}

	public int verCantidadDeVecesQueAlquilo(Usuario inquilino) {
	return this.getReservas().stream()
		.filter(reserva -> reserva.getInquilino().equals(inquilino))
		.toList().size();
	
	}
	
}
