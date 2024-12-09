package ar.edu.unq.integrador.alquileres.reserva;

import java.time.LocalDate;

import ar.edu.unq.integrador.alquileres.publicacion.Publicacion;
import ar.edu.unq.integrador.alquileres.rangoDeFechas.RangoDeFechas;
import ar.edu.unq.integrador.alquileres.reserva.estado.Estado;
import ar.edu.unq.integrador.alquileres.reserva.estado.Pendiente;
import ar.edu.unq.integrador.alquileres.usuario.Usuario;

public class Reserva {
	
	private Usuario inquilino;
	private Publicacion publicacion;
	private RangoDeFechas fechasDeAlquiler;
	private Estado estado;

	public Reserva() {
		//Creo este constructor con la finalidad de poder usar un SPY en el test
	
	}
	
	public Reserva(Usuario inquilino, Publicacion publicacion, RangoDeFechas fechasDeAlquiler) {
		this.inquilino = inquilino;
		this.publicacion = publicacion;
		this.fechasDeAlquiler = fechasDeAlquiler; 
		this.estado = new Pendiente();
	
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

	public String cancelarReserva() {
		this.getEstado().cancelarReserva(this);
		return this.getPublicacion().cancelarReserva(this.getFechas());
	
	}

	public void realizadoDeCheckOut() {
		this.getEstado().checkOut(this);
	
	}
	
	public Boolean fueAlquilada() {
		return this.getEstado().fueAlquilada(this);
	
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

	public String getTipoInmueble() {
		return this.getPublicacion().getTipoInmueble();
	
	}

}
