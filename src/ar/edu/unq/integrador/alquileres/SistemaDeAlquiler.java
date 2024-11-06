package ar.edu.unq.integrador.alquileres;

import java.util.ArrayList;
import java.util.List;

public class SistemaDeAlquiler {
	
	private List<Usuario> usuarios;
	
	public SistemaDeAlquiler() {
		usuarios = new ArrayList<Usuario>();
	}
	
	public void registrarUsuario(Usuario usuario) {
		this.getUsuarios().add(usuario);
	}

	public List<Usuario> getUsuarios() {
		
		return this.usuarios;
	}

}
