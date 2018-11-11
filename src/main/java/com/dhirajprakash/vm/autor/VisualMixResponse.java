package com.dhirajprakash.vm.autor;

import org.springframework.stereotype.Component;

@Component
public class VisualMixResponse {
	
	String descricao;
	Object logicJson;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Object getLogicJson() {
		return logicJson;
	}
	public void setLogicJson(Object logicJson) {
		this.logicJson = logicJson;
	}
	
	
	
	

}
