package com.experis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


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

import com.experis.entity.Producto;
import com.experis.service.IProductoService;

@CrossOrigin(origins = {"http://localhost:4200", "https://angular-talent2win.uc.r.appspot.com"})
@RestController
@RequestMapping("/api")
public class ProductoController {
	
	
	@Autowired
	private IProductoService productoService;
	
	
	@PostMapping("/productos")
	public ResponseEntity<?> registrarProducto(@RequestBody Producto producto) {
		Producto prouctoNew=null;
		Map<String, Object> response = new HashMap<>();
		try {
			prouctoNew= productoService.registraActualizaProducto(producto);
			response.put("mensaje", "El producto ha sido registrado correctamente");
			response.put("producto", prouctoNew);
		} catch (DataAccessException e) {
			response.put("mensaje", "Ocurrió un error al insertar el producto en la base dedatos");
			response.put("error", e.getMessage()+" : "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/productosa")
	public ResponseEntity<?> actualizaProducto(@RequestBody Producto producto) {
		Producto prouctoNew=null;
		Map<String, Object> response = new HashMap<>();
		try {
			prouctoNew= productoService.registraActualizaProducto(producto);
			response.put("mensaje", "El producto ha sido actualizado correctamente");
			response.put("producto", prouctoNew);
		} catch (DataAccessException e) {
			response.put("mensaje", "Ocurrió un error al actualizar el producto en la base dedatos");
			response.put("error", e.getMessage()+" : "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/productosg")
	public ResponseEntity<?> listarProductos(){
		List<Producto> lista=null;
		Map<String, Object> response = new HashMap<>();
		try {
			lista=productoService.listarProductos();
		} catch (DataAccessException e) {
			response.put("mensaje", "Ocurrió un error al realizar la consulta en la Base de Datos");
			response.put("error", e.getMessage()+" : "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(lista.isEmpty()) {
			response.put("mensaje","No se encontraron productos en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Producto>>(lista, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/productose/{cod_producto}")
	public ResponseEntity<?> eliminarProducto(@PathVariable Long cod_producto){
		Map<String, Object> response = new HashMap<>();
		try {
			productoService.eliminaProducto(cod_producto);
			response.put("mensaje", "El producto ha sido eliminado con exito");
		} catch (DataAccessException e) {
			response.put("mensaje", "Ocurrió un error al eliminar el producto");
			response.put("error", e.getMessage()+" : "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	
	@GetMapping("/productosi/{cod_producto}")
	public ResponseEntity<?> buscarProductoXId(@PathVariable Long cod_producto){
		Optional<Producto> producto=null;
		Map<String, Object> response = new HashMap<>();
		try {
			producto=productoService.buscarProductoXId(cod_producto);
		} catch (DataAccessException e) {
			response.put("mensaje", "Ocurrió un error al realizar la consulta en la Base de Datos");
			response.put("error", e.getMessage()+" : "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(producto.isEmpty()) {
			response.put("mensaje","No se encontró el producto en la base dedatos");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Optional<Producto>>(producto, HttpStatus.OK);
		
	}

}
