package ar.edu.unq.integrador.alquileres.rangoDeFechas;

import java.time.LocalDate;

public class FechaEspecial {

    private RangoDeFechas fecha;
    private double precioPorDia;

    public FechaEspecial(RangoDeFechas fecha, double precioPorDia) {
        this.fecha = fecha;
        this.precioPorDia = precioPorDia;
    
    }

    public Boolean coincideConLaFecha(LocalDate dia) {
        return this.getFecha().estaDentroDeLasFechas(dia);
    
    }

    public double getPrecio() {
        return this.precioPorDia;
    
    }

    public RangoDeFechas getFecha() {
        return this.fecha;
    
    }

}