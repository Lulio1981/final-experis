package com.experis.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import lombok.Data;


@Entity
@Data
@Table(name = "usuario", schema = "public")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -7462053657333577906L;

	@Id
	@Column(name = "cod_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod_usuario;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "usera")
	private String user;

	@Column(name = "password")
	private String password;
	
	@NotNull()
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_sucursal")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Sucursal sucursal;
	
}
