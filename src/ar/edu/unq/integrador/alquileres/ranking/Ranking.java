package ar.edu.unq.integrador.alquileres.ranking;

public class Ranking {

	private int puntaje;
	private String categoria;
	private String comentario;

	public Ranking(int puntaje, String categoria, String comentario) {
		this.puntaje = puntaje;
		this.categoria = categoria;
		this.comentario = comentario;
	}

	public int getPuntaje() {
		return this.puntaje;
	}

	public String getCategoria() {
		return this.categoria;
	}
	
	public String getComentario() {
		return this.comentario;
	}

}
