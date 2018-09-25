package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.farmacia.domain.Funcionario;
import br.com.farmacia.factory.ConexaoFactory;

public class FuncionarioDAO {
	public void salvar(Funcionario f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO USUARIO ");
		sql.append("(nome,cargo,departamento) ");
		sql.append("VALUES (?,?,?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.setInt(2, f.getCargo());
		comando.setInt(3, f.getDepartamento());
		comando.executeUpdate();

	}
	
	public void excluir (Funcionario f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM USUARIO ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());
		comando.executeUpdate();
		
	}
	
	
	public void editar (Funcionario f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE USUARIO ");
		sql.append("SET nome = ? ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());
		comando.executeUpdate();
		
	}
	
	
	public Funcionario buscaPorCodigo(Funcionario f)throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, nome, cargo ");
		sql.append("FROM USUARIO ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		
		comando.setLong(1, f.getCodigo());
		
		ResultSet resultado = comando.executeQuery();
		Funcionario retorno = null;
		
		if(resultado.next()){
			retorno = new Funcionario();
			retorno.setCodigo((int) resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));
			retorno.setCargo(resultado.getInt("cargo"));
			retorno.setDepartamento(resultado.getInt("departamento"));
		}
		
		return retorno;
	}
	
	
	public ArrayList<Funcionario>buscarPorDescricao(Funcionario f)throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, nome ,cargo");
		sql.append("FROM USUARIO ");
		sql.append("WHERE nome LIKE ? ");
		sql.append("ORDER BY nome ASC ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, "%" + f.getDescricao() + "%");
		
		
		ResultSet resultado = comando.executeQuery();
		 
		ArrayList<Funcionario>lista = new ArrayList<Funcionario>();
		
		while(resultado.next()){
			Funcionario item = new Funcionario();
			item.setCodigo((int) resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("nome"));
			item.setCargo(resultado.getInt("cargo"));
			item.setDepartamento(resultado.getInt("departamento"));
			item.setEmail(resultado.getString("email"));
			
			lista.add(item);
		}

		return lista;
	}
	
	
	public ArrayList<Funcionario> listar()throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM USUARIO ");
		sql.append("ORDER BY nome ASC ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
				
		ResultSet resultado = comando.executeQuery();
		 
		ArrayList<Funcionario>lista = new ArrayList<Funcionario>();
		
		while(resultado.next()){
			Funcionario f = new Funcionario();
			f.setCodigo(resultado.getInt("codigo"));
			f.setDescricao(resultado.getString("nome"));
			f.setCargo(resultado.getInt("cargo"));
			f.setDepartamento(resultado.getInt("departamento"));
			
			lista.add(f);
		}

		return lista;
	}
	
}
