package com.exemplocrudfx.model;

public class TipoBD {
	
	private int d36_tipoBd_id;
	private String d36_tipoBd_sigla;
	private String d36_tipo_descricao;
	
	public int getD36_tipoBd_id() {
		return d36_tipoBd_id;
	}
	public void setD36_tipoBd_id(int d36_tipoBd_id) {
		this.d36_tipoBd_id = d36_tipoBd_id;
	}
	public String getD36_tipoBd_sigla() {
		return d36_tipoBd_sigla;
	}
	public void setD36_tipoBd_sigla(String d36_tipoBd_sigla) {
		this.d36_tipoBd_sigla = d36_tipoBd_sigla;
	}
	public String getD36_tipo_descricao() {
		return d36_tipo_descricao;
	}
	public void setD36_tipo_descricao(String d36_tipo_descricao) {
		this.d36_tipo_descricao = d36_tipo_descricao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + d36_tipoBd_id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoBD other = (TipoBD) obj;
		if (d36_tipoBd_id != other.d36_tipoBd_id)
			return false;
		return true;
	}
	
	

}
