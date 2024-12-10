package ar.edu.unq.integrador.alquileres.rangoDeFechas;

import java.time.LocalDate;

public class FechasEspeciales {

    private RangoDeFechas fecha;
    private double precioPorDia;

    public FechasEspeciales(RangoDeFechas fecha, double precioPorDia) {
        this.fecha = fecha;
        this.precioPorDia = precioPorDia;
    }

    public Boolean coincideConLaFecha(LocalDate dia) {

        return this.getFecha().estaDentroDeLasFechas(dia);
    }

    public RangoDeFechas getFecha() {

        return this.fecha;
    }

    public Double getPrecio() {

        return this.precioPorDia;
    }



}