package com.cajapaita.cambista.models.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;



@Entity
public class TipoCambio implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="no puede estar vacio")
	@Size(min=3,max=3,message="deben ser 3 caracteres")
	@Column(nullable = false)
	private String moneda;
	
	@NotNull (message="no puede ser nulo")
	@Positive(message="debe ser mayor a cero.")
	@Column(nullable = false,precision = 5,scale = 4)
	private Float tcventa;
	
	@NotNull (message="no puede ser nulo")
	@Positive(message="debe ser mayor a cero.")
	@Column(nullable = false,precision = 5,scale = 4)
	private Float tccompra;

	@NotNull (message="no puede ser nulo")
	@Column(nullable = false)
	private Long idusuario;
	
	@NotEmpty(message="no puede estar vacio")
	@Column(nullable = false)
	private String estado;
	
	
	@Column(name="create_At")
	@Temporal(TemporalType.DATE)
	private	Date createAt ;
	
	@PrePersist
	public void prePersist() {
		createAt = new Date();
		
	}
	
	@PreUpdate
	public void preUpdate() {
		createAt = new Date();
		
	}
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Float getTcventa() {
		return tcventa;
	}
	public void setTcventa(Float tcventa) {
		this.tcventa = tcventa;
	}
	public Float getTccompra() {
		return tccompra;
	}
	public void setTccompra(Float tccompra) {
		this.tccompra = tccompra;
	}
	public Long getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(Long idusuario) {
		this.idusuario = idusuario;
	}
	public String getEstado() {
		return estado;  
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
