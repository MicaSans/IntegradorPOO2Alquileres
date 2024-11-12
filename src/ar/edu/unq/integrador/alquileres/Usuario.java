package ar.edu.unq.integrador.alquileres;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private String nombre;
	private String email;
	private String telefono;
	private LocalDate fechaInicioUsuario;
	private List<Ranking> rankingPropietario;
	private List<Ranking> rankingInquilino;

	public Usuario(String nombre, String email, String telefono) {
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.rankingPropietario = new ArrayList<Ranking>();
		this.rankingInquilino = new ArrayList<Ranking>();
	}

	public void agregarCalificacionPropietario(Ranking calificacion) {
		this.getRankingPropietario().add(calificacion);
	}

	public Integer verPuntajePropietario(String categoria) {
		Integer puntaje = this.getRankingPropietario().stream()
				.filter(r -> r.getCategoria().equals(categoria))
				.mapToInt(r -> r.getPuntaje())
				.sum();
		return puntaje;
	}

	public Integer verPromedioPropietario() {
		Integer promedio = this.getRankingPropietario().stream()
				.mapToInt(r -> r.getPuntaje())
				.sum();
		return promedio / this.getRankingPropietario().size();
	}

	public List<String> verComentariosPropietario() {
		List<String> comentarios = this.getRankingPropietario().stream()
				.map(r -> r.getComentario()).toList();
		return comentarios;
	}

	public void agregarCalificacionInquilino(Ranking calificacion) {
		this.getRankingInquilino().add(calificacion);
	}

	public Integer verPuntajeInquilino(String categoria) {
		Integer puntaje = this.getRankingInquilino().stream()
				.filter(r -> r.getCategoria().equals(categoria))
				.mapToInt(r -> r.getPuntaje())
				.sum();
		return puntaje;
	}

	public Integer verPromedioInquilino() {
		Integer promedio = this.getRankingInquilino().stream()
				.mapToInt(r -> r.getPuntaje())
				.sum();
		return promedio / this.getRankingInquilino().size();
	}

	public List<String> verComentariosInquilino() {
		List<String> comentarios = this.getRankingInquilino().stream()
				.map(r -> r.getComentario()).toList();
		return comentarios;
	}

	public void setFechaInicioUsuario(LocalDate fechaInicio) {
		this.fechaInicioUsuario = fechaInicio;
	}

	public LocalDate getFechaInicioUsuario() {
		return this.fechaInicioUsuario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getEmail() {
		return this.email;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public List<Ranking> getRankingPropietario() {
		return this.rankingPropietario;
	}
	
	public List<Ranking> getRankingInquilino() {
		return this.rankingInquilino;
	}

}
