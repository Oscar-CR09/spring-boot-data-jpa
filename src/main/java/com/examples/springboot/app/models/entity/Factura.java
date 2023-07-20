package com.examples.springboot.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="facturas")
public class Factura implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String descripcion;
	
	private String observacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name="create_at")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date createAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Cliente cliente;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="factura_id")
	private List<itemFactura> items;

	public Factura() {	
		this.items =new ArrayList<itemFactura>();
		
	}
	
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	@XmlTransient
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	public List<itemFactura> getItems() {
		return items;
	}
	public void setItems(List<itemFactura> items) {
		this.items = items;
	}


	public void addItemFactura(itemFactura item) {
		this.items.add(item);
	}
	
	public Double getTotal() {
		
		Double total = 0.0;
		int size =items.size();
		
		for (int i = 0; i < size; i++) {
			total += items.get(i).calcularImporte();
			
		}
		return total;
	}
	
	private static final long serialVersionUID = 1L;
	
}
