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

public class DepartamentoDAO {
	public void salvar(Departamento dpto) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO DEPARTAMENTO ");
		sql.append("(nome) ");
		sql.append("VALUES (?,?,?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, dpto.getDescricao());
		comando.executeUpdate();

	}
	
	public void excluir (Departamento dpto) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM DEPARTAMENTO ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, dpto.getCodigo());
		comando.executeUpdate();
		
	}
	
	
	public void editar (Departamento dpto) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE DEPARTAMENTO ");
		sql.append("SET nome = ? ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, dpto.getDescricao());
		comando.setInt(2, dpto.getCodigo());
		comando.executeUpdate();
		
	}
	
	public ArrayList<Departamento> listar()throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM DEPARTAMENTO ");
		sql.append("ORDER BY nome ASC ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
				
		ResultSet resultado = comando.executeQuery();
		 
		ArrayList<Departamento>lista = new ArrayList<Departamento>();
		
		while(resultado.next()){
			Departamento dpto = new Departamento();
			dpto.setCodigo(resultado.getInt("codigo"));
			dpto.setDescricao(resultado.getString("nome"));
			
			lista.add(dpto);
		}

		return lista;
	}
	
	public Departamento buscaPorCodigo(Departamento departamento)throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM DEPARTAMENTO ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		
		comando.setInt(1, departamento.getCodigo());
		
		ResultSet resultado = comando.executeQuery();
		Departamento retorno = null;
		
		if(resultado.next()){
			retorno = new Departamento();
			retorno.setCodigo(resultado.getInt("codigo"));
			retorno.setDescricao(resultado.getString("nome"));
		}
		
		return retorno;
	}

	
}
