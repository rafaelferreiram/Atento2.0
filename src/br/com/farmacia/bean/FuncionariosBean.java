package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import br.com.farmacia.DAO.FuncionarioDAO;
import br.com.farmacia.domain.Funcionario;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FuncionariosBean {
	
	private Funcionario funcionarios;
	private ArrayList<Funcionario>itens;
	private ArrayList<Funcionario>itensFiltrados;
	
public Funcionario getFornecedores() {
		return funcionarios;
	}

	public void setFornecedores(Funcionario funcionarios) {
		this.funcionarios = funcionarios;
	}


	public ArrayList<Funcionario> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Funcionario> itens) {
		this.itens = itens;
	}
	
	
	public ArrayList<Funcionario> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Funcionario> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}



@PostConstruct
public void prepararPesquisa(){
	
	
	try {
		FuncionarioDAO fdao = new FuncionarioDAO();
		itens = fdao.listar();
		
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("ex.getMessage()");
		e.printStackTrace();
	}
	
}

public void prepararNovo(){
	funcionarios = new Funcionario();
}

public void novo(){
	
	try {
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.salvar(funcionarios);
		
		
		itens = fdao.listar();
		
		
		JSFUtil.adicionarMensagemSucesso("Funcionario salvo com sucesso!");
		
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("ex.getMessage()");
		e.printStackTrace();
	}
}



public void excluir(){
	try {
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.excluir(funcionarios);
		
		
		itens = fdao.listar();
		
		
		JSFUtil.adicionarMensagemSucesso("Funcionario excluido com sucesso!");
		
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("Nao eh possivel excluir um fornecedor que tenha um produto associado!");
		e.printStackTrace();
	}
}





public void editar(){
	try {
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.editar(funcionarios);
		
		
		itens = fdao.listar();
		
		
		JSFUtil.adicionarMensagemSucesso("Funcionario editado com sucesso!");
		
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("ex.getMessage()");
		e.printStackTrace();
	}
}

}
