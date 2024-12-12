package ar.edu.unq.integrador.alquileres.busqueda.filtro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GestorDeFiltros {

    private Set<Class<? extends Filtro>> clasesDeFiltros;
    private List<Filtro> filtros;

    public GestorDeFiltros(FiltroBase filtroBase) {
        this.filtros = new ArrayList<Filtro>();
        this.clasesDeFiltros = new HashSet<>();
        agregarFiltro(filtroBase);
    
    }

    public void agregarFiltro(Filtro filtro) {
        if (agregarClaseDeFiltro(filtro.getClass())) {
            agregarFiltroALaLista(filtro);
        }

    }

	private void agregarFiltroALaLista(Filtro filtro) {
		this.getFiltros().add(filtro);
	
	}

	private boolean agregarClaseDeFiltro(Class<? extends Filtro> claseDeFiltro) {
		return this.getClasesDeFiltros().add(claseDeFiltro);
	
	}

    public List<Filtro> getFiltros() {
        return this.filtros;
    
    }

    private Set<Class<? extends Filtro>> getClasesDeFiltros() {
        return this.clasesDeFiltros;
    
    }

}