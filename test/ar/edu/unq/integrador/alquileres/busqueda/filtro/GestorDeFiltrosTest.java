package ar.edu.unq.integrador.alquileres.busqueda.filtro;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class GestorDeFiltrosTest {

    private GestorDeFiltros gestor;
    @Mock private FiltroBase filtroBase;
    @Mock private FiltroPrecio filtroPrecio;
    @Mock private FiltroPrecio filtroPrecio2;
    @Mock private FiltroHuespedes filtroHuespedes;

    @BeforeEach
    void setUp() throws Exception {
        filtroBase = mock(FiltroBase.class);
        gestor = new GestorDeFiltros(filtroBase);
        //Test agrego filtros
        filtroPrecio = mock(FiltroPrecio.class);
        filtroPrecio2 = mock(FiltroPrecio.class);
        filtroHuespedes = mock(FiltroHuespedes.class);
    }

    @Test
    void testFiltrosExtras() {
        gestor.agregarFiltro(filtroPrecio);
        gestor.agregarFiltro(filtroPrecio2);
        gestor.agregarFiltro(filtroHuespedes);
        assertTrue(gestor.getFiltros().contains(filtroPrecio));
        assertFalse(gestor.getFiltros().contains(filtroPrecio2));
        assertTrue(gestor.getFiltros().contains(filtroHuespedes));
    }
}