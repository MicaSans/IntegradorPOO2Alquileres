package ar.edu.unq.integrador.alquileres;

import java.util.List;

public class FiltroObligatorio {

	private String ciudad;
	private RangoDeFechas rangoDeFechas;
	private List<FiltroExtra> filtrosExtras;
	
	public FiltroObligatorio(String ciudad, RangoDeFechas fechas) {
		this.ciudad = ciudad;
		this.rangoDeFechas = fechas;
		this.filtrosExtras = null;
	}
	
	public FiltroObligatorio(String ciudad, RangoDeFechas fechas, List<FiltroExtra> filtrosExtras) {
		this.ciudad = ciudad;
		this.rangoDeFechas = fechas;
		this.filtrosExtras = filtrosExtras;
	}

	public boolean filtrar(Publicacion publicacion) {
		return this.filtrarPorCiudad(publicacion) 
				&& this.filtrarPorFechas(publicacion)
				&& this.filtrarPorExtras(publicacion, rangoDeFechas); 
	}

	private boolean filtrarPorFechas(Publicacion publicacion) {
		
		return publicacion.getDiasOcupados().stream()
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
	
	private boolean filtrarPorExtras(Publicacion publicacion, RangoDeFechas rango) {
		if (this.getFiltrosExtras() == null) {
			return true;
		}
		return this.getFiltrosExtras().stream()
				.allMatch(filtro -> filtro.filtrar(publicacion, rango));
	}

	private List<FiltroExtra> getFiltrosExtras() {
		
		return this.filtrosExtras;
	}
}
