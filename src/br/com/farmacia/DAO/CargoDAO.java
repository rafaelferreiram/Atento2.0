package br.com.farmacia.DAO;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.farmacia.domain.Cargo;
import br.com.farmacia.domain.Departamento;
import br.com.farmacia.domain.Funcionario;
import br.com.farmacia.factory.ConexaoFactory;

public class CargoDAO {

	public void salvar(Cargo cargo) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO CARGO ");
		sql.append("(nome) ");
		sql.append("VALUES (?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, cargo.getDescricao());
		comando.executeUpdate();

	}
	
	public void excluir (Cargo cargo) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM CARGO ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, cargo.getCodigo());
		comando.executeUpdate();
		
	}
	
	
	public void editar (Cargo cargo) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE CARGO ");
		sql.append("SET nome = ? ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, cargo.getDescricao());
		comando.setInt(2, cargo.getCodigo());
		comando.executeUpdate();
		
	}
	
	public ArrayList<Cargo> listar()throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM CARGO ");
		sql.append("ORDER BY nome ASC ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
				
		ResultSet resultado = comando.executeQuery();
		 
		ArrayList<Cargo>lista = new ArrayList<Cargo>();
		
		while(resultado.next()){
			Cargo cargo = new Cargo();
			cargo.setCodigo(resultado.getInt("codigo"));
			cargo.setDescricao(resultado.getString("nome"));
			
			lista.add(cargo);
		}

		return lista;
	}
	
	public Cargo buscaPorCodigo(Cargo cargo)throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM CARGO ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		
		comando.setInt(1, cargo.getCodigo());
		
		ResultSet resultado = comando.executeQuery();
		Cargo retorno = null;
		
		if(resultado.next()){
			retorno = new Cargo();
			retorno.setCodigo(resultado.getInt("codigo"));
			retorno.setDescricao(resultado.getString("nome"));
		}
		
		return retorno;
	}

	
}
