package ar.edu.unq.integrador.alquileres;

import java.util.ArrayList;
import java.util.List;

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
