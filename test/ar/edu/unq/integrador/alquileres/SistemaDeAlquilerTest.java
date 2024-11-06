package ar.edu.unq.integrador.alquileres;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SistemaDeAlquilerTest {
	private SistemaDeAlquiler sistema;
	private Usuario usuario;
	
	@BeforeEach
	void setUp() throws Exception {
		sistema = new SistemaDeAlquiler();
		usuario = new Usuario("Micaela", "micaela@gmail.com", "42002240");
	}

	@Test
	void testRegistrarUsuario() {
		sistema.registrarUsuario(usuario);
		assertTrue(sistema.getUsuarios().contains(usuario));
	}

}
