package ar.edu.unq.integrador.alquileres.reserva;

import java.time.LocalDate;
import java.util.List;

import ar.edu.unq.integrador.alquileres.publicacion.Publicacion;
import ar.edu.unq.integrador.alquileres.rangoDeFechas.RangoDeFechas;
import ar.edu.unq.integrador.alquileres.reserva.estado.Estado;
import ar.edu.unq.integrador.alquileres.reserva.estado.Pendiente;
import ar.edu.unq.integrador.alquileres.usuario.Usuario;

public class Reserva {
	
	private Usuario inquilino;
	private Publicacion publicacion;
	private RangoDeFechas fechasDeAlquiler;
	private Estado estadoActual;

	public Reserva() {
		//Creo este constructor con la finalidad de poder usar un SPY en el test
	
	}
	
	public Reserva(Usuario inquilino, Publicacion publicacion, RangoDeFechas fechasDeAlquiler) {
		this.inquilino = inquilino;
		this.publicacion = publicacion;
		this.fechasDeAlquiler = fechasDeAlquiler; 
		this.estadoActual = new Pendiente();
	
	}

	public String verCiudadDeReserva() {
		//return this.getPublicacion().getInmueble().getCiudad();
		return this.getCiudad();
	
	}

	private String getCiudad() {
		return this.getPublicacion().getCiudad();
		
	}

	public Publicacion getPublicacion() {
		return this.publicacion;
	
	}	
	
	public Estado getEstado() {
		return this.estadoActual;
	
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
	
	public boolean fueAlquilada() {
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
		this.estadoActual = estado;
	
	}

	public void setNuevoInquilino(Usuario inquilino) {
		this.inquilino = inquilino;
	
	}

	public String getTipoInmueble() {
		return this.getPublicacion().getTipoInmueble();
	
	}

	public List<RangoDeFechas> getDiasOcupadosPublicacion() {
		//Creo para estado Pendiente
		return this.getPublicacion().getDiasOcupados();
		
	}
	
	public LocalDate getFechaFinal() {
		//Creo para estado Reservada
		return this.getFechas().getFinal();
		
	}

}
