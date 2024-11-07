package ar.edu.unq.integrador.alquileres;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SistemaDeAlquilerTest {
	private SistemaDeAlquiler sistema;
	private Usuario usuario;
	private List<String> servicios;
	private Inmueble inmueble;
	private Publicacion publicacion;
	
	@BeforeEach
	void setUp() throws Exception {
		// testRegistrarUsuario()
		sistema = new SistemaDeAlquiler();
		usuario = new Usuario("Micaela", "micaela@gmail.com", "42002240");
		// testGenerarPublicacion() "No me anda el mockito y por eso creo las clases"
		servicios = Arrays.asList("Gas","Agua");
		inmueble = new Inmueble(200, "Argentina", "Quilmes", "Conesa 722" , 5, servicios, "Casa", usuario);
		publicacion = new Publicacion(inmueble, )
		
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
