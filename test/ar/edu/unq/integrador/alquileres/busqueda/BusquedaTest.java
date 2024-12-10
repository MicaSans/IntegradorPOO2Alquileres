package ar.edu.unq.integrador.alquileres.busqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.integrador.alquileres.busqueda.filtro.Filtro;
import ar.edu.unq.integrador.alquileres.busqueda.filtro.FiltroBase;
import ar.edu.unq.integrador.alquileres.busqueda.filtro.GestorDeFiltros;
import ar.edu.unq.integrador.alquileres.publicacion.Publicacion;

class BusquedaTest {

    @Mock private FiltroBase filtro;
    private Busqueda busqueda;
    private List<Publicacion> publicaciones;
    @Mock private Publicacion publicacion;
    @Mock private GestorDeFiltros gestor;
    private List<Filtro> filtros;

    @BeforeEach
    void setUp() throws Exception {
        //1er Test
        filtro = mock(FiltroBase.class);
        filtros = Arrays.asList(filtro);
        gestor = mock(GestorDeFiltros.class);
        busqueda = new Busqueda(gestor); //SUT
        publicacion = mock(Publicacion.class);
        publicaciones = Arrays.asList(publicacion);

    }

    @Test
    void testFiltrarPublicaciones() {
        when(gestor.getFiltros()).thenReturn(filtros);
        when(filtro.filtrar(publicacion)).thenReturn(true);
        assertTrue(busqueda.filtrarPublicaciones(publicaciones).contains(publicacion));
    }


}