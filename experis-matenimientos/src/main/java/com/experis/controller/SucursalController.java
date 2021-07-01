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

import com.experis.entity.Sucursal;
import com.experis.service.ISucursalService;

@CrossOrigin(origins = {"http://localhost:4200", "https://angular-talent2win.uc.r.appspot.com"})
@RestController
@Transactional
@RequestMapping("/api")
public class SucursalController {
	
	
	@Autowired
	private ISucursalService sucursalService;
	
	
	@PostMapping("/sucursales")
	public ResponseEntity<?> registrarSucursal(@RequestBody Sucursal sucursal) {
		Sucursal sucursalNew=null;
		Map<String, Object> response = new HashMap<>();
		try {
			sucursalNew= sucursalService.registraActualizaSucursal(sucursal);
			response.put("mensaje", "La Sucursal ha sido registrado correctamente");
			response.put("sucursal", sucursalNew);
		} catch (DataAccessException e) {
			response.put("mensaje", "Ocurrió un error al insertar la sucrusal en la base dedatos");
			response.put("error", e.getMessage()+" : "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/sucursalesa")
	public ResponseEntity<?> actualizaSucursal(@RequestBody Sucursal sucursal) {
		Sucursal sucursalNew=null;
		Map<String, Object> response = new HashMap<>();
		try {
			sucursalNew= sucursalService.registraActualizaSucursal(sucursal);
			response.put("mensaje", "La sucursal ha sido actualizado correctamente");
			response.put("sucursal", sucursalNew);
		} catch (DataAccessException e) {
			response.put("mensaje", "Ocurrió un error al actualizar la sucursal en la base dedatos");
			response.put("error", e.getMessage()+" : "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/sucursalesg")
	public ResponseEntity<?> listarSucursales(){
		List<Sucursal> lista=null;
		Map<String, Object> response = new HashMap<>();
		try {
			lista=sucursalService.listarSucursales();
		} catch (DataAccessException e) {
			response.put("mensaje", "Ocurrió un error al realizar la consulta en la Base de Datos");
			response.put("error", e.getMessage()+" : "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(lista.isEmpty()) {
			response.put("mensaje","No se encontraron sucursales en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Sucursal>>(lista, HttpStatus.OK);
	}
	
	@DeleteMapping("/sucursalese/{id_sucursal}")
	public ResponseEntity<?> eliminarSucursal(@PathVariable Long id_sucursal){
		Map<String, Object> response = new HashMap<>();
		try {
			sucursalService.eliminaSucursal(id_sucursal);
			response.put("mensaje", "La sucursal ha sido eliminado con exito");
		} catch (DataAccessException e) {
			response.put("mensaje", "Ocurrió un error al eliminar la sucursal");
			response.put("error", e.getMessage()+" : "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	
	@GetMapping("/sucursalesi/{id}")
	public ResponseEntity<?> buscarSucursalXId(@PathVariable Long id){
		Optional<Sucursal> sucursal=null;
		Map<String, Object> response = new HashMap<>();
		try {
			sucursal=sucursalService.buscarSucursalXId(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Ocurrió un error al realizar la consulta en la Base de Datos");
			response.put("error", e.getMessage()+" : "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(sucursal.isEmpty()) {
			response.put("mensaje","No se encontró la sucursal en la base dedatos");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Optional<Sucursal>>(sucursal, HttpStatus.OK);
		
	}

}
