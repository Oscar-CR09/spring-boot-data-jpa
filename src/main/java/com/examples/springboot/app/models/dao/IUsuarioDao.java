package com.examples.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.examples.springboot.app.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

	public Usuario findByUsuario(String username);
}
