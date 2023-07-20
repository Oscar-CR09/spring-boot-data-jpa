package com.examples.springboot.app.view.xml;

import java.util.List;

import com.examples.springboot.app.models.entity.Cliente;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="clientesList")
public class ClienteList {

	@XmlElement(name="cliente")
	public List<Cliente> cliente;

	public ClienteList() {
	
	}

	public ClienteList(List<Cliente> cliente) {
		this.cliente = cliente;
		
	}

	public List<Cliente> getCliente() {
		return cliente;
	}
	
	
}
