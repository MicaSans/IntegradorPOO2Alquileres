package ar.edu.unq.integrador.alquileres.publicacion;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import ar.edu.unq.integrador.alquileres.publicacion.inmueble.Inmueble;
import ar.edu.unq.integrador.alquileres.publicacion.politicaDeCancelacion.PoliticaDeCancelacion;
import ar.edu.unq.integrador.alquileres.rangoDeFechas.FechaEspecial;
import ar.edu.unq.integrador.alquileres.rangoDeFechas.RangoDeFechas;
import ar.edu.unq.integrador.alquileres.ranking.GestorDeRanking;
import ar.edu.unq.integrador.alquileres.ranking.Ranking;
import ar.edu.unq.integrador.alquileres.reserva.Reserva;

public class Publicacion {

	private Inmueble inmueble;
	private PoliticaDeCancelacion politicaDeCancelacion;
	private double precioPorDia;
	private LocalTime horarioCheckOut;
	private LocalTime horarioCheckIn;
	private List<Foto> fotos;
	private GestorDeRanking gestorRankingPublicacion;
	private List<FormaDePago> formasDePago;
	private ArrayList<RangoDeFechas> diasOcupados;
	private ArrayList<FechaEspecial> diasEspeciales;
	private List<Reserva> condicionales;

	public Publicacion(Inmueble inmueble, LocalTime checkIn, LocalTime checkOut, double precio,
			PoliticaDeCancelacion politicaDeCancelacion, List<FormaDePago> formasDePago) {
		this.inmueble = inmueble;
		this.fotos = new ArrayList<Foto>();
		this.horarioCheckIn = checkIn;
		this.horarioCheckOut = checkOut;
		this.precioPorDia = precio;
		this.politicaDeCancelacion = politicaDeCancelacion;
		this.gestorRankingPublicacion = new GestorDeRanking();
		this.formasDePago = formasDePago;
		this.diasOcupados = new ArrayList<RangoDeFechas>();
		this.diasEspeciales = new ArrayList<FechaEspecial>();
		this.condicionales = new ArrayList<Reserva>();
		
	}

	public void setPoliticaCancelacion(PoliticaDeCancelacion politicaDeCancelacion) {
		this.politicaDeCancelacion = politicaDeCancelacion;	
	
	}
	
	public PoliticaDeCancelacion getPoliticaDeCancelacion() {
		return this.politicaDeCancelacion;
	
	}

	public void agregarFoto(Foto foto) {
		if (espacioParaFotosLleno()) {
			this.almacenarFoto(foto);
		}
	
	}
	
	private void almacenarFoto(Foto foto) {
		this.getFotos().add(foto);
	
	}
	
	private boolean espacioParaFotosLleno() {
		return this.espacioFotos() < 5;
	
	}

	private int espacioFotos() {
		return this.getFotos().size();
	
	}

	public List<Foto> getFotos() {
		return this.fotos;
	
	}

	public void agregarCalificacion(Ranking calificacion) {
		this.getGestorDeRanking().agregarCalificacion(calificacion);;
	
	}
	
	public GestorDeRanking getGestorDeRanking() {
		return this.gestorRankingPublicacion;
	
	}

	public int verPuntajeCategoria(String categoria) {
		return this.getGestorDeRanking().verPuntaje(categoria);
	
	}
	
	public int verPromedioTotal() {
		//Integer promedio = this.getRanking().stream()
		//		.mapToInt(r -> r.getPuntaje())
		//		.sum();
		//return promedio / this.getRanking().size();
		return this.getGestorDeRanking().verPromedioRanking();
	
	}
/*
	private int tamañoRanking() {
		return this.getRanking().size();
		
	}

	private int verPromedio() {
		int promedio = this.getRanking().stream()
				.mapToInt(r -> r.getPuntaje())
				.sum();
		return promedio;
		
	}
*/
	public List<String> verComentarios() {
		return this.getGestorDeRanking().verComentarios();
	
	}
	
	public void agregarADiasOcupados(RangoDeFechas fechas) {
		this.getDiasOcupados().add(fechas);
	
	}

	public void quitarADiasOcupados(RangoDeFechas fechas) {
		//if (this.getDiasOcupados().stream()
		//		.anyMatch(fechasOcupadas -> fechasOcupadas.equals(fechas))) {
		if(diasOcupadosContiene(fechas)) {
			eliminarFechas(fechas);
		}
	
	}

	private void eliminarFechas(RangoDeFechas fechas) {
		this.getDiasOcupados().remove(fechas);
	
	}
	
	private boolean diasOcupadosContiene(RangoDeFechas fechas) {
		return getDiasOcupados().contains(fechas);
		
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
	
	public LocalTime getHorarioCheckIn() {
		return this.horarioCheckIn;
	
	}
	
	public LocalTime getHorarioCheckOut() {
		return this.horarioCheckOut;
	
	}

	public List<RangoDeFechas> getDiasOcupados() {
		return this.diasOcupados;
	
	}

	public double getPrecio(RangoDeFechas rangodeDias) {
		List<LocalDate> diasEnLista = crearListaFechas(rangodeDias.getInicio(), rangodeDias.getFinal());
		Double precioTotal = diasEnLista.stream()
			.mapToDouble(dia -> this.calcularPrecioDelDia(dia))
			.sum();
		return precioTotal;
	
	}
	
	private double calcularPrecioDelDia(LocalDate dia) {
		//if (this.perteneceADiasEspeciales(dia)) {
		//	return this.getPrecioPorDia() 
		//			+ (this.getPrecioPorDia() * this.getPorcentajeDiaEspecial())/ 100;
		//}
		//else {
		//	return this.getPrecioPorDia();
		//}
		if(perteneceADiasEspeciales(dia)) {
			return calcularPrecioPorDiaEspecial(dia);
		}
		else {
			return precioBase();
		}
	
	}

	private double calcularPrecioPorDiaEspecial(LocalDate dia) {
		FechaEspecial fechaEspecial = this.getDiasEspeciales().stream()
				.filter(diasEspeciales -> diasEspeciales.coincideConLaFecha(dia))
				.findFirst()
				.get();
		return fechaEspecial.getPrecio();
		
	}

	private double precioBase() {
		return this.getPrecioPorDia();
		
	}

	private boolean perteneceADiasEspeciales(LocalDate dia) {
		return this.getDiasEspeciales().stream()
				.anyMatch(diasEspeciales -> diasEspeciales.coincideConLaFecha(dia));
	
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
	
	public List<FechaEspecial> getDiasEspeciales() {
		return this.diasEspeciales;
	
	}
	
	public void agregarDiasEspeciales(FechaEspecial fecha) {
		//Checkeo que al agregar dias especiales no se superpongan
		if (!this.seSuperponenDiasEspeciales(fecha)) {
			agregarFechaEspecial(fecha);
		}
	
	}

	private void agregarFechaEspecial(FechaEspecial fecha) {
		this.diasEspeciales.add(fecha);
	
	}
	
	/*private boolean seSuperponenDiasEspeciales(FechasEspeciales navidad) {
		return this.diasEspeciales.stream()
				.anyMatch(diasYaAgregados -> diasYaAgregados.getFecha().seSuperponenDias(navidad.getFecha()));
	
	}*/
	
	private boolean seSuperponenDiasEspeciales(FechaEspecial fechaEspecial) {
		return existeSuperposicionCon(fechaEspecial.getFecha());
	
	}
	
	private boolean existeSuperposicionCon(RangoDeFechas fecha) {
		return diasEspeciales.stream()
				.anyMatch(diasYaAgregados -> seSuperponen(diasYaAgregados.getFecha(), fecha));
	
	}
	
	private boolean seSuperponen(RangoDeFechas fecha1, RangoDeFechas fecha2) {
		return fecha1.seSuperponenDias(fecha2);
	
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
	
	public boolean tieneCapacidadPara(int cantidadHuespedes) {
		return this.getInmueble().tieneCapacidadPara(cantidadHuespedes);
		
	}

	public boolean tieneSuperposicionDeDiasCon(RangoDeFechas rangoDeFechas) {
		return this.getDiasOcupados().stream()
				.anyMatch(dia -> dia.seSuperponenDias(rangoDeFechas));
		
	}

	public String getCiudad() {
		return this.getInmueble().getCiudad();
	
	}

	public String getTipoInmueble() {
		return this.getInmueble().getTipoInmueble();
		
	}

	public List<Ranking> getRanking() {
		return this.getGestorDeRanking().getRanking();
	
	}

	public boolean gestorContiene(Ranking ranking) {
		return this.getRanking().contains(ranking);
	
	}

}
