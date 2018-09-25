package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import br.com.farmacia.DAO.DepartamentoDAO;
import br.com.farmacia.domain.Departamento;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBDepartamento")
@ViewScoped
public class DepartamentoBean {
	
	private Departamento departamentos;
	private ArrayList<Departamento>itens;
	private ArrayList<Departamento>itensFiltrados;
	
public Departamento getDepartamento() {
		return departamentos;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamentos = departamento;
	}


	public ArrayList<Departamento> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Departamento> itens) {
		this.itens = itens;
	}
	
	
	public ArrayList<Departamento> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Departamento> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}



@PostConstruct
public void prepararPesquisa(){
	
	
	try {
		DepartamentoDAO dptoDAO = new DepartamentoDAO();
		itens = dptoDAO.listar();
		
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("ex.getMessage()");
		e.printStackTrace();
	}
	
}

public void prepararNovo(){
	this.departamentos = new Departamento();
}

public void novo(){
	
	try {
		DepartamentoDAO dptoDAO = new DepartamentoDAO();
		dptoDAO.salvar(departamentos);
		
		
		itens = dptoDAO.listar();
		
		
		JSFUtil.adicionarMensagemSucesso("Departamento salvo com sucesso!");
		
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("ex.getMessage()");
		e.printStackTrace();
	}
}



public void excluir(){
	try {
		DepartamentoDAO dptoDAO = new DepartamentoDAO();
		dptoDAO.excluir(departamentos);
		
		
		itens = dptoDAO.listar();
		
		
		JSFUtil.adicionarMensagemSucesso("Departamento excluido com sucesso!");
		
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("Nao eh possivel excluir um fornecedor que tenha um produto associado!");
		e.printStackTrace();
	}
}





public void editar(){
	try {
		DepartamentoDAO dptoDAO = new DepartamentoDAO();
		dptoDAO.editar(departamentos);
		
		
		itens = dptoDAO.listar();
		
		
		JSFUtil.adicionarMensagemSucesso("Departamento editado com sucesso!");
		
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("ex.getMessage()");
		e.printStackTrace();
	}
}

}
