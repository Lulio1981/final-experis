package com.experis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.experis.entity.Usuario;
import com.experis.repository.UsuarioRepository;

@Service
public class IUsuarioServiceImpl implements IUsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario registraActualizaUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuarioRepository.save(usuario);
	}

	@Override
	public List<Usuario> listarUsuarios() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}

	@Override
	public void eliminaUsuario(Long id_usuario) {
		// TODO Auto-generated method stub
		usuarioRepository.deleteById(id_usuario);
		
	}

	@Override
	public Optional<Usuario> buscarUsuarioXId(Long id_usuario) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id_usuario);
	}

}
