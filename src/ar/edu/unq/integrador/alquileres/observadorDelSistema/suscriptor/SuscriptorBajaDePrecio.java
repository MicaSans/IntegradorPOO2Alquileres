package ar.edu.unq.integrador.alquileres.observadorDelSistema.suscriptor;

import ar.edu.unq.integrador.alquileres.publicacion.Publicacion;
import ar.edu.unq.integrador.alquileres.reserva.Reserva;

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
		this.getHomePagePublisher().publish("Un inmueble " 
				+ publicacion.getTipoInmueble() 
				+ " a tan solo "
				+ publicacion.getPrecioPorDia().toString()
				+ " pesos");
	
	}

	private HomePagePublisher getHomePagePublisher() {
		return this.homePagePublisher;
		
	}

	@Override
	public void updatePorReserva(Reserva reserva) {
		// TODO Auto-generated method stub

	}

}
