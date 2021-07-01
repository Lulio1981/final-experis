package com.experis.service;

import java.util.List;
import java.util.Optional;

import com.experis.entity.Producto;

public interface IProductoService {
	
	public Producto registraActualizaProducto(Producto producto);
	
	public List<Producto> listarProductos();
	
	public void eliminaProducto(Long cod_producto);
	
	public Optional<Producto> buscarProductoXId(Long cod_producto);
	
}
