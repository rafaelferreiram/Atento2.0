package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import br.com.farmacia.DAO.CargoDAO;
import br.com.farmacia.domain.Cargo;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBCargo")
@ViewScoped
public class CargoBean {
	
	private Cargo cargos;
	private ArrayList<Cargo>itens;
	private ArrayList<Cargo>itensFiltrados;
	
	public Cargo getCargos() {
		return cargos;
	}

	public void setCargos(Cargo cargo) {
		this.cargos = cargo;
	}


	public ArrayList<Cargo> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Cargo> itens) {
		this.itens = itens;
	}
	
	
	public ArrayList<Cargo> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Cargo> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}



	@PostConstruct
	public void prepararPesquisa(){
		
		
		try {
			CargoDAO cargoDAO = new CargoDAO();
			itens = cargoDAO.listar();
			
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
		
	}

	public void prepararNovo(){
		this.cargos = new Cargo();
	}

public void novo(){
	
	try {
		CargoDAO cargoDAO = new CargoDAO();
		cargoDAO.salvar(cargos);
		
		
		itens = cargoDAO.listar();
		
		
		JSFUtil.adicionarMensagemSucesso("Cargo salvo com sucesso!");
		
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("ex.getMessage()");
		e.printStackTrace();
	}
}



public void excluir(){
	try {
		CargoDAO cargoDAO = new CargoDAO();
		cargoDAO.excluir(cargos);
		
		
		itens = cargoDAO.listar();
		
		
		JSFUtil.adicionarMensagemSucesso("Cargo excluido com sucesso!");
		
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("Nao eh possivel excluir um fornecedor que tenha um produto associado!");
		e.printStackTrace();
	}
}





public void editar(){
	try {
		CargoDAO cargoDAO = new CargoDAO();
		cargoDAO.editar(cargos);
		
		
		itens = cargoDAO.listar();
		
		
		JSFUtil.adicionarMensagemSucesso("Cargo editado com sucesso!");
		
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("ex.getMessage()");
		e.printStackTrace();
	}
}

}
