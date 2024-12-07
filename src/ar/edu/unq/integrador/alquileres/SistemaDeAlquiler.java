package ar.edu.unq.integrador.alquileres;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SistemaDeAlquiler {
	
	private List<Usuario> usuarios;
	private List<Publicacion> publicaciones;
	private List<Reserva> reservas;
	private List<String> categorias;
	private List<String> tipoInmuebles;
	private List<String> servicios;
	private ObservadorDelSistema observer;
	
	public SistemaDeAlquiler() {
		usuarios = new ArrayList<Usuario>();
		publicaciones = new ArrayList<Publicacion>();
		reservas = new ArrayList<Reserva>();
		categorias = new ArrayList<String>();
		tipoInmuebles = new ArrayList<String>();
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
		return this.categorias;
	}

	public void setTipoInmueble(String tipoInmueble) {
		this.getTipoInmuebles().add(tipoInmueble);
	}

	public List<String> getTipoInmuebles() {
		return this.tipoInmuebles;
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

	//public void reservarCondicionalmente(Reserva reserva, Usuario usuario) {
	//	reserva.agregarACondicionales(usuario);
	//	
	//}

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
				.filter(reserva -> reserva.getInquilino().equals(usuario)).toList();
		
		
		return reservasDeUsuario;
	}

	public List<Reserva> verReservasFuturas(Usuario usuario) {
		List<Reserva> reservasFuturas = this.verReservas(usuario).stream()
				.filter(reserva -> reserva.getFechaIngreso().isAfter(LocalDate.now()))
				.toList();
		return reservasFuturas;
	}

	public List<Reserva> verReservasEnCiudad(Usuario usuario, String ciudad) {
		List<Reserva> reservasEnCiudad = this.verReservas(usuario).stream()
				.filter(reserva -> reserva.verCiudadDeReserva().equals(ciudad)).toList();
		return reservasEnCiudad;
	}

	public List<String> verCiudadesDeLasReservas(Usuario usuario) {
		List <String> ciudadesDeReservas = new ArrayList<String>();
		this.verReservas(usuario).stream()
			.forEach(reserva -> ciudadesDeReservas.add(reserva.verCiudadDeReserva()));
		return ciudadesDeReservas;
	}

	public int verPuntajePublicacion(String categoria, Publicacion publicacion) {
		return publicacion.verPuntajeCategoria(categoria);
		
	}

	public Integer verPromedioPublicacion(Publicacion publicacion) {
		
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

	public Integer verCantidadQueFueAlquilada(Publicacion publicacion) {
		List<Reserva> reservasDePublicacion = this.getReservas().stream()
			.filter(reserva -> reserva.getPublicacion().equals(publicacion) && reserva.fueAlquilada()).toList();
		return reservasDePublicacion.size();
	}

	public Integer verCuantasVecesAlquiloInmuebles(Usuario propietario) {
		List<Reserva> reservasDePropietario = this.reservasDePropietario(propietario);
			return reservasDePropietario.size();
	}
	
	private List<Reserva> reservasDePropietario(Usuario propietario) {
		List<Reserva> reservasDePropietario = this.getReservas().stream()
				.filter(reserva -> reserva.getPublicacion().getInmueble().getPropietario().equals(propietario) && reserva.fueAlquilada()).toList();
			return reservasDePropietario;
	}
	
	public List<Inmueble> verInmueblesAlquilados(Usuario propietario) {
		List<Inmueble> inmuebles = new ArrayList<Inmueble>();
			this.reservasDePropietario(propietario).stream()
			.forEach(reserva -> inmuebles.add(reserva.getPublicacion().getInmueble()));
		Set<Inmueble> inmueblesNoRepetidos = new HashSet<Inmueble>(inmuebles);
		List<Inmueble> inmueblesAlquilados = new ArrayList<>(inmueblesNoRepetidos);
		return inmueblesAlquilados;
	}

	public Integer verPromedioPuntajePropietario(Usuario usuario) {
		return usuario.verPromedioPropietario();
	}

	public Integer verPuntajePropietario(String categoria, Usuario usuario) {
		
		return usuario.verPuntajePropietario(categoria);
	}

	public List<String> verComentariosPropietario(Usuario usuario) {
		return usuario.verComentariosPropietario();
	}

	public Integer verPromedioPuntajeInquilino(Usuario usuario) {
		
		return usuario.verPromedioInquilino();
	}

	public Integer verPuntajeInquilino(String categoria, Usuario usuario) {
		
		return usuario.verPuntajeInquilino(categoria);
	}

	public List<String> verComentariosInquilino(Usuario usuario) {
		return usuario.verComentariosInquilino();
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

	public int verCantidadDeVecesQueAlquilo(Usuario usuario) {
	return this.getReservas().stream()
		.filter(reserva -> reserva.getInquilino().equals(usuario))
		.toList().size();
	}
	
}
