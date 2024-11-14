package ar.edu.unq.integrador.alquileres;

public class SuscriptorBajaDePrecio implements Suscriptor {

	private HomePagePublisher homePagePublisher;

	public SuscriptorBajaDePrecio(HomePagePublisher hPP) {
		this.homePagePublisher = hPP;  //Paso como parametro el hpp en el constructor ya que depende del sitio, y que tipo de hpp utiliza
	}

	@Override
	public void updatePorCancelacion(Reserva reserva) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePorBajaDePrecio(Publicacion publicacion) {
		homePagePublisher.publish("Un inmueble " 
				+ publicacion.getInmueble().getTipoInmueble() 
				+ " a tan solo "
				+ publicacion.getPrecioPorDia().toString()
				+ " pesos");
	}

	@Override
	public void updatePorReserva(Reserva reserva) {
		// TODO Auto-generated method stub

	}

}
