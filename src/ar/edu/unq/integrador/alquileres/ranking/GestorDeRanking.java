package ar.edu.unq.integrador.alquileres.ranking;

import java.util.ArrayList;
import java.util.List;

public class GestorDeRanking {

    private List<Ranking> rankings;

    public GestorDeRanking() {
        this.rankings = new ArrayList<Ranking>();
    }

    public void agregarCalificacion(Ranking calificacion) {
        this.getRanking().add(calificacion);
    }


    public Integer verPuntaje(String categoria) {

        Integer puntaje = this.getRanking().stream()
                .filter(r -> r.getCategoria().equals(categoria))
                .mapToInt(r -> r.getPuntaje())
                .sum();
        return puntaje;
    }


    public Integer verPromedioRanking() {
        return verPromedio() / tamañoRanking();
    }

    private int verPromedio() {
        int promedio = this.getRanking().stream()
                .mapToInt(r -> r.getPuntaje())
                .sum();
        return promedio;
    }

    private int tamañoRanking() {
        return this.getRanking().size();

    }

    public List<Ranking> getRanking() {

        return this.rankings;
    }

    public List<String> verComentarios() {
        List<String> comentarios = this.getRanking().stream()
                .map(r -> r.getComentario()).toList();
        return comentarios;
    }

    public Boolean contiene(Ranking calificacion) {
        return    this.getRanking().contains(calificacion);
    }

}