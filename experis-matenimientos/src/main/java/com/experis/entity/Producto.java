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
@Table(name = "producto", schema = "public")
public class Producto implements Serializable {

	private static final long serialVersionUID = -8117886199000226188L;

	@Id
	@Column(name = "cod_producto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod_producto;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "precio")
	private double precio;
}
