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
		this.getPopUpWindow().popUp("El/la " 
							//+ reserva.getPublicacion().getInmueble().getTipoInmueble()
							+ reserva.getTipoInmueble()
							+ " que te interesa se ha liberado! Correa a reservarlo!",
							"Azul", 12);
	
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
