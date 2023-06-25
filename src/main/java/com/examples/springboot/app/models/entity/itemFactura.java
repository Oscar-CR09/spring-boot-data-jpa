package com.examples.springboot.app.models.entity;

import java.io.Serializable;

public class itemFactura implements Serializable {

	private Long id;
	
	private Integer cantidad;
	
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Integer getCantidad() {
		return cantidad;
	}



	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}



	private static final long serialVersionUID = 1L;
	
}
