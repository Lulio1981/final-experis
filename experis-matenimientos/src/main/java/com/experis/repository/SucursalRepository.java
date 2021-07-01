package com.experis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.experis.entity.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long>{

}
