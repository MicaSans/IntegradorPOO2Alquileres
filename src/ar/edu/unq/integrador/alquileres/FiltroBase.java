package ar.edu.unq.integrador.alquileres;

public class FiltroBase implements Filtro{

	private String ciudad;
	private RangoDeFechas rangoDeFechas;
	
	public FiltroBase(String ciudad, RangoDeFechas fechas) {
		this.ciudad = ciudad;
		this.rangoDeFechas = fechas;
	}

	public boolean filtrar(Publicacion publicacion) {
		return this.filtrarPorCiudad(publicacion) 
				&& this.filtrarPorFechas(publicacion);
	}

	private boolean filtrarPorFechas(Publicacion publicacion) {
		
		return this.getRangoDeFecha().esRangoCompatible() 
				&& publicacion.getDiasOcupados().stream()
				.noneMatch(diasOcupados -> diasOcupados.seSuperponenDias(this.getRangoDeFecha()));
	}

	private RangoDeFechas getRangoDeFecha() {
		return this.rangoDeFechas;
	}
	
	private boolean filtrarPorCiudad(Publicacion publicacion) {
		return this.getCiudad().equals(publicacion.getInmueble().getCiudad());
	}

	private String getCiudad() {
		return this.ciudad;
	}
	
}
