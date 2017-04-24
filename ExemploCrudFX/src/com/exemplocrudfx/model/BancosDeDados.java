
package com.exemplocrudfx.model;

public class BancosDeDados {

	private int bdd_id;
	private int tip_id;
	private String bdd_servidor; //90
	private String bdd_nomebd;   //50
	private String bdd_usuario;  //40
	private String bdd_senha;    //38
	private String bdd_observacao;//60
	private String bdd_customizar_campos;//38
	private String bdd_nomeTipoBD;
	public int getBdd_id() {
		return bdd_id;
	}
	public void setBdd_id(int bdd_id) {
		this.bdd_id = bdd_id;
	}
	public int getTip_id() {
		return tip_id;
	}
	public void setTip_id(int tip_id) {
		this.tip_id = tip_id;
	}
	public String getBdd_servidor() {
		return bdd_servidor;
	}
	public void setBdd_servidor(String bdd_servidor) {
		this.bdd_servidor = bdd_servidor;
	}
	public String getBdd_nomebd() {
		return bdd_nomebd;
	}
	public void setBdd_nomebd(String bdd_nomebd) {
		this.bdd_nomebd = bdd_nomebd;
	}
	public String getBdd_usuario() {
		return bdd_usuario;
	}
	public void setBdd_usuario(String bdd_usuario) {
		this.bdd_usuario = bdd_usuario;
	}
	public String getBdd_senha() {
		return bdd_senha;
	}
	public void setBdd_senha(String bdd_senha) {
		this.bdd_senha = bdd_senha;
	}
	public String getBdd_observacao() {
		return bdd_observacao;
	}
	public void setBdd_observacao(String bdd_observacao) {
		this.bdd_observacao = bdd_observacao;
	}
	public String getBdd_customizar_campos() {
		return bdd_customizar_campos;
	}
	public void setBdd_customizar_campos(String bdd_customizar_campos) {
		this.bdd_customizar_campos = bdd_customizar_campos;
	}
	public String getBdd_nomeTipoBD() {
		return bdd_nomeTipoBD;
	}
	public void setBdd_nomeTipoBD(String bdd_nomeTipoBD) {
		this.bdd_nomeTipoBD = bdd_nomeTipoBD;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bdd_id;
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
		BancosDeDados other = (BancosDeDados) obj;
		if (bdd_id != other.bdd_id)
			return false;
		return true;
	}
	
		
}
