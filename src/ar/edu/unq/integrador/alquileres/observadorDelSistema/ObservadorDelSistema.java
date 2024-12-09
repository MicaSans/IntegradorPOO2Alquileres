package ar.edu.unq.integrador.alquileres.observadorDelSistema;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.integrador.alquileres.observadorDelSistema.suscriptor.Suscriptor;
import ar.edu.unq.integrador.alquileres.publicacion.Publicacion;
import ar.edu.unq.integrador.alquileres.reserva.Reserva;

public class ObservadorDelSistema {
	
	private List<Suscriptor> suscriptores;

	public ObservadorDelSistema() {
		this.suscriptores = new ArrayList<Suscriptor>();
	
	}
	
	public List<Suscriptor> getSuscriptores() {
		return this.suscriptores;
	
	}

	public void agregarSuscriptor(Suscriptor suscriptor) {
		this.getSuscriptores().add(suscriptor);
	
	}
	
	public void eliminarSuscriptor(Suscriptor suscriptor) {
		this.getSuscriptores().remove(suscriptor);
		
	}
	
	public void notificarCancelacion(Reserva reserva) {
		this.getSuscriptores().stream()
			.forEach(s -> s.updatePorCancelacion(reserva));
	
	}

	public void notificarBajaDePrecio(Publicacion publicacion) {
		this.getSuscriptores().stream()
			.forEach(s -> s.updatePorBajaDePrecio(publicacion));
	
	}

	public void notificarReserva(Reserva reserva) {
		this.getSuscriptores().stream()
			.forEach(s -> s.updatePorReserva(reserva));
	
	}
	
}
