package ar.edu.unq.integrador.alquileres;

import java.util.List;

public class Inmueble {

	private int superficie;
	private String pais;
	private String ciudad;
	private String direccion;
	private int capacidad;
	private List<String> servicios;
	private String tipoDeInmueble;
	private Usuario propietario;

	public Inmueble(int superficie, String pais, String ciudad, String direccion, int capacidad, List<String> servicios, String tipoDeInmueble,
			Usuario propietario) {
		this.superficie = superficie;
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.capacidad = capacidad;
		this.servicios = servicios;
		this.tipoDeInmueble = tipoDeInmueble;
		this.propietario = propietario;
	}

}
