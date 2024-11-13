package ar.edu.unq.integrador.alquileres;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {

	
	private Usuario inquilino;
	private Publicacion publicacion;
	private RangoDeFechas fechasDeAlquiler;
	private Estado estado;
	private List<Usuario> condicionales;

	public Reserva() {
		//Creo este constructor con la finalidad de poder usar un SPY en el test
	}
	
	public Reserva(Usuario inquilino, Publicacion publicacion, RangoDeFechas fechasDeAlquiler) {
		this.inquilino = inquilino;
		this.publicacion = publicacion;
		this.fechasDeAlquiler = fechasDeAlquiler; 
		this.estado = new Pendiente();
		this.condicionales = new ArrayList<Usuario>();
	}

	public String verCiudadDeReserva() {
		
		return this.getPublicacion().getInmueble().getCiudad();
	}

	public Publicacion getPublicacion() {
		
		return this.publicacion;
	}	
	
	public Estado getEstado() {
		return this.estado;
	}
	
	public void aceptarReserva() {
		this.getEstado().aceptarReserva(this);	
	}

	public void rechazarReserva() {
		this.getEstado().rechazarReserva(this);
		
	}

	public void cancelarReserva() {
		this.getEstado().cancelarReserva(this);
		
	}

	public void realizadoDeCheckOut() {
		this.getEstado().checkOut(this);
		
	}
	
	public Boolean fueAlquilada() {
		
		return this.getEstado().fueAlquilada(this);
	}
	
	public void agregarACondicionales(Usuario usuario) {
		this.getCondicionales().add(usuario);
	}

	public List<Usuario> getCondicionales() {
		return this.condicionales;
	}

	public Usuario getInquilino() {
		return this.inquilino;
	}

	public LocalDate getFechaIngreso() {
		return this.getFechas().getInicio();
	}

	public RangoDeFechas getFechas() {
		return this.fechasDeAlquiler;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void setNuevoInquilino(Usuario inquilino) {
		this.inquilino = inquilino;
		
	}

	

	

}
