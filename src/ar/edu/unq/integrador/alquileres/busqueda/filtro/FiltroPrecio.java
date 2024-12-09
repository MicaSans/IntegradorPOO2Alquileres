package ar.edu.unq.integrador.alquileres.busqueda.filtro;

import ar.edu.unq.integrador.alquileres.publicacion.Publicacion;
import ar.edu.unq.integrador.alquileres.rangoDeFechas.RangoDeFechas;

public class FiltroPrecio implements Filtro {

	private double precioMinimo;
	private double precioMaximo;
	private RangoDeFechas rangoDeDias;

	public FiltroPrecio(double precioMinimo, double precioMaximo, RangoDeFechas rangoDeFechas) {
		this.precioMinimo = precioMinimo;
		this.precioMaximo = precioMaximo;
		this.rangoDeDias = rangoDeFechas;
	
	}

	@Override
	public boolean filtrar(Publicacion publicacion) {
		double precioFinalDePublicacion = obtenerPrecioFinal(publicacion);
		if (comprobarPreciosCorrectos() && esRangoCompatible()) {
			return this.filtrarPrecios(precioFinalDePublicacion);
		}
		else {
			return false;
		}
	
	}
	
	public boolean filtrarPrecios(double precioFinal) {
		return this.getPrecioMinimo() <= precioFinal && precioFinal <= this.getPrecioMaximo();
	
	}
	
	private double getPrecioMaximo() {	
		return this.precioMaximo;
	
	}

	private double getPrecioMinimo() {
		return this.precioMinimo;
	
	}
	
	private boolean comprobarPreciosCorrectos() {
		return this.getPrecioMinimo() < this.getPrecioMaximo();
	
	}
	
	private RangoDeFechas getRangoDeDias() {	
		return this.rangoDeDias;
	
	}
	
	private boolean esRangoCompatible() {
		return this.getRangoDeDias().esRangoCompatible();
		
	}
	
	private double obtenerPrecioFinal(Publicacion publicacion) {
		return publicacion.getPrecio(this.getRangoDeDias());
		
	}

}
