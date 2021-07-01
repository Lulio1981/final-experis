package com.experis.service;

import java.util.List;
import java.util.Optional;

import com.experis.entity.Usuario;

public interface IUsuarioService {
	
	public Usuario registraActualizaUsuario(Usuario usuario);
	
	public List<Usuario> listarUsuarios();
	
	public void eliminaUsuario(Long id_usuario);

	public Optional<Usuario> buscarUsuarioXId(Long id_usuario);

}
