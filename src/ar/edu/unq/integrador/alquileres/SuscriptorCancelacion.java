package ar.edu.unq.integrador.alquileres;

public class SuscriptorCancelacion implements Suscriptor {

	private PopUpWindow popUpWindow;

	public SuscriptorCancelacion(PopUpWindow pUW) {
		this.popUpWindow = pUW;
	}

	@Override
	public void updatePorCancelacion(Reserva reserva) {
		this.popUpWindow.popUp("El/la " 
							+ reserva.getPublicacion().getInmueble().getTipoInmueble()
							+ " que te interesa se ha liberado! Correa a reservarlo!"
							, "Azul", 12);
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
