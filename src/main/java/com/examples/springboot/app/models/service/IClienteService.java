package com.examples.springboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.examples.springboot.app.models.entity.Cliente;
import com.examples.springboot.app.models.entity.Factura;
import com.examples.springboot.app.models.entity.Producto;

public interface IClienteService {

	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pageable);
	
	public void save(Cliente cliente);
	
	public Cliente finOne(Long id);
	
	public void delete(Long id);
	
	public List<Producto> finByNombre(String term);
	
	public void saveFactura(Factura factura);
	
	public Producto finProductoById(Long id);
	
}
