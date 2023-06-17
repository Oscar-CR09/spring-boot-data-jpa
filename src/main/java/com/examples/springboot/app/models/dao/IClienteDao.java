package com.examples.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.examples.springboot.app.models.entity.Cliente;

//import com.examples.springboot.app.models.entity.Cliente;

public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long>,CrudRepository<Cliente, Long>{

	
}
