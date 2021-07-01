package com.experis.service;

import java.util.List;
import java.util.Optional;

import com.experis.entity.Sucursal;

public interface ISucursalService{
	
	public Sucursal registraActualizaSucursal(Sucursal sucursal);
	
	public List<Sucursal> listarSucursales();
	
	public void eliminaSucursal(Long id_sucursal);

	public Optional<Sucursal> buscarSucursalXId(Long id_sucursal);

}
