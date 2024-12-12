package ar.edu.unq.integrador.alquileres.observadorDelSistema.suscriptor;

import ar.edu.unq.integrador.alquileres.publicacion.Publicacion;
import ar.edu.unq.integrador.alquileres.reserva.Reserva;

public class SuscriptorCancelacion implements Suscriptor {

	private PopUpWindow popUpWindow;

	public SuscriptorCancelacion(PopUpWindow pUW) {
		this.popUpWindow = pUW;
	
	}

	@Override
	public void updatePorCancelacion(Reserva reserva) {
		String mensaje = generarMensajeCancelacion(reserva);
		this.mostrarPopUp(mensaje, "Azul", 12);
	
	}

	private void mostrarPopUp(String mensaje, String color, int tamañoFuente) {
		this.getPopUpWindow().popUp(mensaje, color, tamañoFuente);
		
	}

	private String generarMensajeCancelacion(Reserva reserva) {
		return "El/la " 
				//+ reserva.getPublicacion().getInmueble().getTipoInmueble()
				+ reserva.getTipoInmueble()
				+ " que te interesa se ha liberado! Corre a reservarlo!";
	
	}

	private PopUpWindow getPopUpWindow() {
		return this.popUpWindow;
		
	}

	@Override
	public void updatePorBajaDePrecio(Publicacion publicacion) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePorReserva(Reserva reserva) {
		// TODO Auto-generated method stub

	}

}
