package com.dhirajprakash.vm.autor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import javax.persistence.Id;

@Entity
@Table(name = "relatorios")
public class RelatoriosJoinEntity {
	
	@Id
	@GeneratedValue
    @Column(name = "id")
	private Integer id;
	
	@Column(name = "numero_de_os")
	private String numero_de_os;
	
	@Column(name = "user")
	private String user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero_de_os() {
		return numero_de_os;
	}

	public void setNumero_de_os(String numero_de_os) {
		this.numero_de_os = numero_de_os;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "RelatoriosEntity [id=" + id + ", numero_de_os=" + numero_de_os + ", user=" + user + "]";
	}
	
	
	

}
