package ar.edu.unq.integrador.alquileres.publicacion;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.integrador.alquileres.publicacion.inmueble.Inmueble;
import ar.edu.unq.integrador.alquileres.publicacion.politicaDeCancelacion.PoliticaDeCancelacion;
import ar.edu.unq.integrador.alquileres.rangoDeFechas.RangoDeFechas;
import ar.edu.unq.integrador.alquileres.ranking.Ranking;
import ar.edu.unq.integrador.alquileres.reserva.Reserva;

public class Publicacion {

	private Inmueble inmueble;
	private PoliticaDeCancelacion politicaDeCancelacion;
	private double precioPorDia;
	private LocalTime horarioCheckOut;
	private LocalTime horarioCheckIn;
	private List<Foto> fotos;
	private List<Ranking> ranking;
	private List<FormaDePago> formasDePago;
	private ArrayList<RangoDeFechas> diasOcupados;
	private ArrayList<RangoDeFechas> diasEspeciales;
	private Integer porcentajeDiaEspecial;
	private List<Reserva> condicionales;

	public Publicacion(Inmueble inmueble, LocalTime checkIn, LocalTime checkOut, double precio,
			PoliticaDeCancelacion politicaDeCancelacion, List<FormaDePago> formasDePago) {
		this.inmueble = inmueble;
		this.fotos = new ArrayList<Foto>();
		this.horarioCheckIn = checkIn;
		this.horarioCheckOut = checkOut;
		this.precioPorDia = precio;
		this.politicaDeCancelacion = politicaDeCancelacion;
		this.ranking = new ArrayList<Ranking>();
		this.formasDePago = formasDePago;
		this.diasOcupados = new ArrayList<RangoDeFechas>();
		this.diasEspeciales = new ArrayList<RangoDeFechas>();
		this.porcentajeDiaEspecial = 0;
		this.condicionales = new ArrayList<Reserva>();
		
	}

	public void setPoliticaCancelacion(PoliticaDeCancelacion politicaDeCancelacion) {
		this.politicaDeCancelacion = politicaDeCancelacion;	
	}
	
	public PoliticaDeCancelacion getPoliticaDeCancelacion() {
		return this.politicaDeCancelacion;
	}

	public void agregarFoto(Foto foto) {
		if (this.espacioParaFotosLleno()) {
			this.getFotos().add(foto);
		}
	}
	
	private boolean espacioParaFotosLleno() {
		return this.getFotos().size() < 5;
	}

	public List<Foto> getFotos() {
		return this.fotos;
	}

	public void agregarCalificacion(Ranking calificacion) {
		this.getRanking().add(calificacion);
	}
	
	public List<Ranking> getRanking() {
		return this.ranking;
	}

	public int verPuntajeCategoria(String categoria) {
		Integer puntaje = this.getRanking().stream()
				.filter(r -> r.getCategoria().equals(categoria))
				.mapToInt(r -> r.getPuntaje())
				.sum();
		return puntaje;
	}
	
	public Integer verPromedioTotal() {
		Integer promedio = this.getRanking().stream()
				.mapToInt(r -> r.getPuntaje())
				.sum();
		return promedio / this.getRanking().size();
	}

	public List<String> verComentarios() {
		List<String> comentarios = this.getRanking().stream()
				.map(r -> r.getComentario()).toList();
		return comentarios;
	}
	
	public void agregarADiasOcupados(RangoDeFechas fechas) {
		this.getDiasOcupados().add(fechas);
	}

	public void quitarADiasOcupados(RangoDeFechas fechas) {
		if (this.getDiasOcupados().stream().anyMatch(fechasOcupadas -> fechasOcupadas.equals(fechas))) {
			this.getDiasOcupados().remove(fechas);
		};
	}
	
	public void cambiarPrecioPorDia(double precio) {
		this.precioPorDia = precio;
	}
	
	public Inmueble getInmueble() {
		
		return this.inmueble;
	}

	public List<FormaDePago> getFormasDePago() {
		return this.formasDePago;
	}
	
	public LocalTime getHorarioChekIn() {
		return this.horarioCheckIn;
	}
	
	public LocalTime getHorarioChekOut() {
		return this.horarioCheckOut;
	}

	public List<RangoDeFechas> getDiasOcupados() {
		return this.diasOcupados;
	}

	public Double getPrecio(RangoDeFechas rangodeDias) {
		List<LocalDate> diasEnLista = crearListaFechas(rangodeDias.getInicio(), rangodeDias.getFinal());
		Double precioTotal = diasEnLista.stream()
			.mapToDouble(dia -> this.calcularPrecioDelDia(dia))
			.sum();
		return precioTotal;
	}
	
	private double calcularPrecioDelDia(LocalDate dia) {
		if (this.perteneceADiasEspeciales(dia)) {
			return this.getPrecioPorDia() 
					+ (this.getPrecioPorDia() * this.getPorcentajeDiaEspecial())/ 100;
		}
		else {
			return this.getPrecioPorDia();
		}
	}

	private boolean perteneceADiasEspeciales(LocalDate dia) {
		return this.getDiasEspeciales().stream().anyMatch(rango -> rango.estaDentroDeLasFechas(dia));
	}

	private List<LocalDate> crearListaFechas(LocalDate inicio, LocalDate fin) {
		List <LocalDate> fechas = new ArrayList<LocalDate>();
		while (!inicio.isAfter(fin)) {
			fechas.add(inicio);
			inicio = inicio.plusDays(1);
		}
		
		return fechas;
	}

	public Double getPrecioPorDia() {
		return this.precioPorDia;
	}
	
	public List<RangoDeFechas> getDiasEspeciales() {
		return this.diasEspeciales;
	}
	
	public void agregarDiasEspeciales(RangoDeFechas rangoDeFechas) {
		this.diasEspeciales.add(rangoDeFechas);
		
	}

	public void setPorcentajeDiaEspecial(int porcentaje) {
		this.porcentajeDiaEspecial = porcentaje;
		
	}

	public Integer getPorcentajeDiaEspecial() {
		return this.porcentajeDiaEspecial;
	}

	public String cancelarReserva(RangoDeFechas rangoDeFechas) {
		return this.getPoliticaDeCancelacion().cancelarReserva(rangoDeFechas);
	}

	public void agregarACondicionales(Reserva reserva) {
		this.condicionales.add(reserva);
	}

	public List<Reserva> getCondicionales() {
		return this.condicionales;
	}

}
