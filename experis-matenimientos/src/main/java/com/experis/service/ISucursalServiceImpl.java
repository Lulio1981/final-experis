package com.experis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.experis.entity.Sucursal;
import com.experis.repository.SucursalRepository;

@Service
public class ISucursalServiceImpl implements ISucursalService{
	
	@Autowired
	private SucursalRepository sucursalRepository;

	@Override
	public Sucursal registraActualizaSucursal(Sucursal sucursal) {
		// TODO Auto-generated method stub
		return sucursalRepository.save(sucursal);
	}

	@Override
	public List<Sucursal> listarSucursales() {
		// TODO Auto-generated method stub
		return sucursalRepository.findAll();
	}

	@Override
	public void eliminaSucursal(Long id_sucursal) {
		// TODO Auto-generated method stub
		sucursalRepository.deleteById(id_sucursal);
	}

	@Override
	public Optional<Sucursal> buscarSucursalXId(Long id_sucursal) {
		// TODO Auto-generated method stub
		return sucursalRepository.findById(id_sucursal);
	}

}
