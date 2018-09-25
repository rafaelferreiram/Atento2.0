package br.com.farmacia.domain;

public class Funcionario {
	private Long codigo;
	private String descricao;
	private String email;
	private int cargo; 
	private int departamento;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public int getCargo() {
		return cargo;
	}
	public void setCargo(int cargo) {
		this.cargo = cargo;
	}
	public int getDepartamento() {
		return departamento;
	}
	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(int i) {
		this.codigo = (long) i;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		String saida = codigo + " - " + descricao+" - "+ cargo;
		return saida;
	}
	
} 
