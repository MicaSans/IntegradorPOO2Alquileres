package ar.edu.unq.integrador.alquileres.busqueda;

import java.util.List;

import ar.edu.unq.integrador.alquileres.busqueda.filtro.Filtro;
import ar.edu.unq.integrador.alquileres.busqueda.filtro.GestorDeFiltros;
import ar.edu.unq.integrador.alquileres.publicacion.Publicacion;

public class Busqueda {


    private GestorDeFiltros gestorDeFiltros;

    public Busqueda(GestorDeFiltros gestor) {
        this.gestorDeFiltros = gestor;

    }

    public List<Publicacion> filtrarPublicaciones(List<Publicacion> publicaciones) {
        //List<Publicacion> filtrados = publicaciones.stream()
        //        .filter(publicacion -> this.comprobacionDeFiltrado(publicacion))
        //        .toList();
        //return filtrados;
        return publicaciones.stream()
                .filter(this::comprobacionDeFiltrado)
                .toList();

    }

    private Boolean comprobacionDeFiltrado(Publicacion publicacion) {
        return  this.getFiltros().stream()
                .allMatch(f -> f.filtrar(publicacion));

    }

    private List<Filtro> getFiltros() {

        return this.getGestorDeFiltros().getFiltros();
    }

    private GestorDeFiltros getGestorDeFiltros() {

        return this.gestorDeFiltros;
    }


}
