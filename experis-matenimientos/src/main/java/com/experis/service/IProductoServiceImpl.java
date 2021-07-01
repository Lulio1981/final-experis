package com.experis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.experis.entity.Producto;
import com.experis.repository.ProductoRepository;

@Service
public class IProductoServiceImpl implements IProductoService{
	
	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public Producto registraActualizaProducto(Producto producto) {
		// TODO Auto-generated method stub
		return productoRepository.save(producto);
	}

	@Override
	public List<Producto> listarProductos() {
		// TODO Auto-generated method stub
		return productoRepository.findAll();
	}

	@Override
	public void eliminaProducto(Long id_producto) {
		productoRepository.deleteById(id_producto);
		
	}

	@Override
	public Optional<Producto> buscarProductoXId(Long cod_producto) {
		// TODO Auto-generated method stub
		return productoRepository.findById(cod_producto);
	}

}
