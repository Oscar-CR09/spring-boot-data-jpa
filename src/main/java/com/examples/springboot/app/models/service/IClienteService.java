package com.examples.springboot.app.models.service;

import java.util.List;

import com.examples.springboot.app.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();
	
	public void save(Cliente cliente);
	
	public Cliente finOne(Long id);
	
	public void delete(Long id);
	
}
