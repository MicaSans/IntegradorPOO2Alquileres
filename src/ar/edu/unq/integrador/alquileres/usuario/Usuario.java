package ar.edu.unq.integrador.alquileres.usuario;

import java.time.LocalDate;
import java.util.List;
import ar.edu.unq.integrador.alquileres.ranking.GestorDeRanking;
import ar.edu.unq.integrador.alquileres.ranking.Ranking;

public class Usuario {
	private String nombre;
	private String email;
	private String telefono;
	private LocalDate fechaInicioUsuario;
	private GestorDeRanking gestorRankingPropietario;
	private GestorDeRanking gestorRankingInquilino;

	public Usuario(String nombre, String email, String telefono) {
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.gestorRankingPropietario = new GestorDeRanking();
		this.gestorRankingInquilino = new GestorDeRanking();
	}

	public void agregarCalificacionPropietario(Ranking calificacion) {
		this.getGestorPropietario().agregarCalificacion(calificacion);
	}
	
	public void agregarCalificacionInquilino(Ranking calificacion) {
		this.getGestorInquilino().agregarCalificacion(calificacion);
	}
	
	public Integer verPuntajePropietario(String categoria) {
		return this.getGestorPropietario().verPuntaje(categoria);
	}
	
	public Integer verPuntajeInquilino(String categoria) {
		return this.getGestorInquilino().verPuntaje(categoria);
	}
	
	/*
	private Integer verPuntaje(List<Ranking> rankingUsuario, String categoria) {
		Integer puntaje = rankingUsuario.stream()
				.filter(r -> r.getCategoria().equals(categoria))
				.mapToInt(r -> r.getPuntaje())
				.sum();
		return puntaje;
	}
	*/

	public Integer verPromedioPropietario() {
		return this.getGestorPropietario().verPromedioRanking();
	}
	
	public Integer verPromedioInquilino() {
		return this.getGestorInquilino().verPromedioRanking();
	}
	
	/*
	public Integer verPromedio(List<Ranking> rankingUsuario) {
		Integer promedio = this.getRankingInquilino().stream()
				.mapToInt(r -> r.getPuntaje())
				.sum();
		return promedio / this.getRankingInquilino().size();
	}
	*/
	
	public List<String> verComentariosPropietario() {
		
		return this.getGestorPropietario().verComentarios();
	}

	public List<String> verComentariosInquilino() {
		
		return this.getGestorInquilino().verComentarios();
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

	public GestorDeRanking getGestorPropietario() {
		return this.gestorRankingPropietario;
	}
	
	public GestorDeRanking getGestorInquilino() {
		return this.gestorRankingInquilino;
	}

	public List<Ranking> getRankingPropietario() {
		
		return this.getGestorPropietario().getRanking();
	}
	
	public List<Ranking> getRankingInquilino() {
		
		return this.getGestorInquilino().getRanking();
	}

	public Boolean gestorPropietarioContiene(Ranking ranking) {
	
		return this.getGestorPropietario().contiene(ranking);
	}
	
	public Boolean gestorInquilinoContiene(Ranking ranking) {
		
		return this.getGestorInquilino().contiene(ranking);
	}
}