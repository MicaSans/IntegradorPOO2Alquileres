package ar.edu.unq.integrador.alquileres;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsuarioTest {

	private Usuario usuario;
	private SistemaDeAlquiler sistema;
	
	@BeforeEach
	void setUp() throws Exception {
		usuario = new Usuario("Francisco","francisco@gmail.com","48596745");
		sistema = new SistemaDeAlquiler();
	}

	@Test
	void testSetFechaInicio() {
		sistema.registrarUsuario(usuario);
		assertEquals(usuario.getFechaInicioUsuario(), LocalDate.now());
	}

}
