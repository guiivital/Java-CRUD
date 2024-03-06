package com.guilhermevital.crudcompleto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO {
	public void save(Cliente Cliente) {
		String sql = "INSERT INTO Clientes(nome, idade,datacadastro) VALUES (?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Criar conexão com banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, Cliente.getNome());
			pstm.setInt(2, Cliente.getIdade());
			pstm.setDate(3, new Date(Cliente.getDataCadastro().getTime()));
			
			pstm.execute();
			System.out.println("Salvo com sucesso!");
		}
		catch (Exception e){
			System.out.println(e);
		}
		finally {
			try {
				if(pstm!= null) {
					pstm.close();
				}
				
				if(conn!= null) {
					conn.close();
				}
			}catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public void update(Cliente Cliente) {
		String sql = "UPDATE Clientes SET nome = ?, idade = ?, datacadastro = ? " + "WHERE id = ? ";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, Cliente.getNome());
			pstm.setInt(2, Cliente.getIdade());
			pstm.setDate(3, new Date(Cliente.getDataCadastro().getTime()));
			//ID PARA ATUALIZAR
			pstm.setInt(4, Cliente.getId());
			
			//Executar a query
			
			pstm.execute();
			System.out.println("OK");
		}catch (Exception e) {
			System.out.println(e);
		}finally {
			try {
				if(pstm!= null) {
					pstm.close();
				}
				
				if(conn!= null) {
					conn.close();
				}
			}catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public void deleteByID (int id) {
		String sql = "DELETE FROM Clientes WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();
			System.out.println("Deletado com sucesso!");
		}catch (Exception e) {
			System.out.println(e);
		}finally {
			try {
				if(pstm!= null) {
					pstm.close();
				}
				
				if(conn!= null) {
					conn.close();
				}
				}catch (Exception e) {
					System.out.println(e);
			}
		}
	}
	
	public List<Cliente> getClientes(){
		String sql = "SELECT * FROM Clientes";
		List<Cliente> Clientes = new ArrayList<Cliente>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		ResultSet rst = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			rst = pstm.executeQuery();
			
			
			
			while(rst.next()) {
				Cliente Cliente = new Cliente();
				
				Cliente.setId(rst.getInt("id"));
				Cliente.setNome(rst.getString("nome"));
				Cliente.setIdade(rst.getInt("idade"));
				Cliente.setDataCadastro(rst.getDate("datacadastro"));
				
				Clientes.add(Cliente);
				
			}
					
			
		}catch (Exception e){
			System.out.println(e);
		}finally {
			try {
				if(rst!= null) {
					rst.close();
				}
				
				if(pstm != null) {
					pstm.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			}catch (Exception e){
				System.out.println(e);
			}
		}
		
		return Clientes;
	}
}
