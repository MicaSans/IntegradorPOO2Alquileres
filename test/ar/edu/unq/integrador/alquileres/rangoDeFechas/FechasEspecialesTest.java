package ar.edu.unq.integrador.alquileres.rangoDeFechas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class FechasEspecialesTest {

    private FechaEspecial navidad;
    @Mock private RangoDeFechas fechas;

    @BeforeEach
    void setUp() throws Exception {
        fechas = mock(RangoDeFechas.class);
        navidad = new FechaEspecial(fechas, 2000d);
    }

    @Test
    void testCoincideConLaFecha() {
        when(fechas.estaDentroDeLasFechas(LocalDate.of(2024, 12, 25))).thenReturn(true);
        when(fechas.estaDentroDeLasFechas(LocalDate.of(2024, 11, 2))).thenReturn(false);
        assertTrue(navidad.coincideConLaFecha(LocalDate.of(2024, 12, 25)));
        assertFalse(navidad.coincideConLaFecha(LocalDate.of(2024, 11, 2)));

    }


    @Test
    void testGetPrecio() {
        assertEquals(navidad.getPrecio(), 2000d);
    }

    @Test
    void testGetFecha() {
        assertEquals(navidad.getFecha(), fechas);
    }
}