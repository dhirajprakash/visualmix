package com.dhirajprakash.vm.autor;

public class Relatorios {

	private Integer id;
	private String numeroDeOs;
	private String user;
	private String full_name;
	
	

	public Relatorios(Integer id, String numero_de_os, String user) {
		super();
		this.id = id;
		this.numeroDeOs = numero_de_os;
		this.user = user;
	}

	public Relatorios(Integer id, String numero_de_os, String user, String full_name) {
		super();
		this.id = id;
		this.numeroDeOs = numero_de_os;
		this.user = user;
		this.full_name = full_name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getNumeroDeOs() {
		return numeroDeOs;
	}

	public void setNumeroDeOs(String numeroDeOs) {
		this.numeroDeOs = numeroDeOs;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	@Override
	public String toString() {
		return "Relatorios [id=" + id + ", numero_de_os=" + numeroDeOs + ", user=" + user + ", full_name=" + full_name
				+ "]";
	}

}
