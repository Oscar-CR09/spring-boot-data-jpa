package com.examples.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.examples.springboot.app.models.entity.Cliente;

//import com.examples.springboot.app.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long>{

	
}
