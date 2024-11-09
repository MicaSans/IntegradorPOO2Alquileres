package ar.edu.unq.integrador.alquileres;

import java.time.LocalDate;

public class Usuario {
	private String nombre;
	private String email;
	private String telefono;
	private LocalDate fechaInicioUsuario;

	public Usuario(String nombre, String email, String telefono) {
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
	}

	public void setFechaInicioUsuario(LocalDate fechaInicio) {
		this.fechaInicioUsuario = fechaInicio;
	}

	public LocalDate getFechaInicioUsuario() {
		return this.fechaInicioUsuario;
	}

	public void agregarCalificacionInquilino(Ranking calificacion) {
		// TODO Auto-generated method stub
		
	}

	public void agregarCalificacionPropietario(Ranking calificacion) {
		// TODO Auto-generated method stub
		
	}

	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTelefono() {
		// TODO Auto-generated method stub
		return null;
	}

}
