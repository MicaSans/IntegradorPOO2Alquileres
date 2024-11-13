package ar.edu.unq.integrador.alquileres;

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
		double precioFinalDePublicacion = publicacion.getPrecio(this.getRangodeDias());
		if (this.comprobrarPreciosCorrectos() && this.getRangodeDias().esRangoCompatible()) {
			return this.filtrarPrecios(precioFinalDePublicacion);
		}
		else { return false;}
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
	
	private boolean comprobrarPreciosCorrectos() {
		return this.getPrecioMinimo()<this.getPrecioMaximo();
	}
	
	private RangoDeFechas getRangodeDias() {
		
		return this.rangoDeDias;
	}

}
