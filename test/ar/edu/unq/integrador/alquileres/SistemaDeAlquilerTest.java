package ar.edu.unq.integrador.alquileres;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SistemaDeAlquilerTest {
	private SistemaDeAlquiler sistema;
	private Usuario usuario;
	private Publicacion publicacion;
	
	@BeforeEach
	void setUp() throws Exception {
		// testRegistrarUsuario()
		sistema = new SistemaDeAlquiler();
		usuario = mock(Usuario.class);
		// testGenerarPublicacion() "No me anda el mockito y por eso creo las clases"
		publicacion = mock(Publicacion.class);
		
	}

	@Test
	void testRegistrarUsuario() {
		sistema.registrarUsuario(usuario);
		assertTrue(sistema.getUsuarios().contains(usuario));
	}
	
	@Test
	void testGenerarPublicacion() {
		sistema.generarPublicacion(publicacion);
		assertTrue(sistema.getPublicacion().contains(publicacion));
	}

	
}
