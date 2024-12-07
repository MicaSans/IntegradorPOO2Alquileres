package ar.edu.unq.integrador.alquileres.publicacion.inmueble;

import java.util.List;

import ar.edu.unq.integrador.alquileres.usuario.Usuario;

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
	
    public Usuario getPropietario() {
		return this.propietario;
	}

	public Integer getSuperficie() {
		return this.superficie;
	}

	public String getPais() {
		return this.pais;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public Integer getCapacidad() {
		return this.capacidad;
	}

	public List<String> getServicios() {
		return this.servicios;
	}

	public String getTipoInmueble() {
		return this.tipoDeInmueble;
	}


}
