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
				.noneMatch(diasOcupados -> this.seSuperponenDias(diasOcupados, this.getRangoDeFecha()));
	}

	private boolean seSuperponenDias(RangoDeFechas diasOcupados, RangoDeFechas rangoDeFecha) {
		
		return !diasOcupados.getInicio().isAfter(rangoDeFecha.getFinal()) 
				&& !rangoDeFecha.getInicio().isAfter(diasOcupados.getFinal());
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
