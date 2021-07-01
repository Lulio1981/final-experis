package com.experis.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "sucursal", schema = "public")
public class Sucursal implements Serializable {

	private static final long serialVersionUID = 4127471708231806898L;
	
	@Id
	@Column(name = "cod_sucursal")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod_sucursal;
	
	@Column(name = "nombre")
	private String nombre;
	

}
