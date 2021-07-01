package com.experis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.experis.entity.Usuario;
import com.experis.service.IUsuarioService;

@CrossOrigin(origins = {"http://localhost:4200", "https://angular-talent2win.uc.r.appspot.com"})
@RestController
@Transactional
@RequestMapping("/api")
public class UsuarioController {
	
	
	@Autowired
	private IUsuarioService usuarioService;
	
	
	@PostMapping("/usuarios")
	public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
		Usuario  usuarioNew=null;
		Map<String, Object> response = new HashMap<>();
		try {
			usuarioNew= usuarioService.registraActualizaUsuario(usuario);
			response.put("mensaje", "El Usuario ha sido registrado correctamente");
			response.put("usuario", usuarioNew);
		} catch (DataAccessException e) {
			response.put("mensaje", "Ocurrió un error al insertar la sucrusal en la base dedatos");
			response.put("error", e.getMessage()+" : "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/usuariosa")
	public ResponseEntity<?> actualizaUsuario(@RequestBody Usuario usuario) {
		Usuario usuarioNew=null;
		Map<String, Object> response = new HashMap<>();
		try {
			usuarioNew= usuarioService.registraActualizaUsuario(usuario);
			response.put("mensaje", "La sucursal ha sido actualizado correctamente");
			response.put("usuario", usuarioNew);
		} catch (DataAccessException e) {
			response.put("mensaje", "Ocurrió un error al actualizar la sucursal en la base dedatos");
			response.put("error", e.getMessage()+" : "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/usuariosg")
	public ResponseEntity<?> listarUsuarios(){
		List<Usuario> lista=null;
		Map<String, Object> response = new HashMap<>();
		try {
			lista=usuarioService.listarUsuarios();
		} catch (DataAccessException e) {
			response.put("mensaje", "Ocurrió un error al realizar la consulta en la Base de Datos");
			response.put("error", e.getMessage()+" : "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(lista.isEmpty()) {
			response.put("mensaje","No se encontraron usuarios en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
	}
	
	@DeleteMapping("/usuariose/{id_usuario}")
	public ResponseEntity<?> eliminarUsuario(@PathVariable Long id_usuario){
		Map<String, Object> response = new HashMap<>();
		try {
			usuarioService.eliminaUsuario(id_usuario);
			response.put("mensaje", "El usuario ha sido eliminado con exito");
		} catch (DataAccessException e) {
			response.put("mensaje", "Ocurrió un error al eliminar el usuario");
			response.put("error", e.getMessage()+" : "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}

	
	@GetMapping("/usuariosi/{id}")
	public ResponseEntity<?> buscarUsuarioXId(@PathVariable Long id){
		Optional<Usuario> usuario=null;
		Map<String, Object> response = new HashMap<>();
		try {
			usuario=usuarioService.buscarUsuarioXId(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Ocurrió un error al realizar la consulta en la Base de Datos");
			response.put("error", e.getMessage()+" : "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(usuario.isEmpty()) {
			response.put("mensaje","No se encontró el usuario en la base dedatos");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Optional<Usuario>>(usuario, HttpStatus.OK);
		
	}

}
