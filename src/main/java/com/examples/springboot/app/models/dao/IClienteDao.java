package com.examples.springboot.app.models.dao;

import java.util.List;

import com.examples.springboot.app.models.entity.Cliente;

public interface IClienteDao {

	public List<Cliente> findAll();
	public void save(Cliente cliente);
	
	public Cliente finOne(Long id);
	
}
