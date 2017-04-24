package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import model.Pessoa;

public class PessoaDAO {
	
	public int incluir(Pessoa p) {

		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;

		conn = conexao.abreConexaoBD();
		String sql = "Select * from pessoa where cpf='"+p.getCpf()+"' ";
		int retorno = 0;
		try {
			st = (Statement) conn.createStatement();
			rs = st.executeQuery(sql);
			if (!rs.next()) {
				// incluir
				sql = "insert into pessoa (cpf,nome,datanasc,id_profis) values (?,?,?,?)";
				ps = (PreparedStatement) conn.prepareStatement(sql);
				ps.setString(1, p.getCpf());
				ps.setString(2, p.getNome());
				ps.setString(3, p.getDatanasc());
				ps.setInt(4, p.getId_profis());
				ps.executeUpdate();
				retorno = 1;
			} else {
				// cadastro existente
				retorno = 3;
			}
		} catch (SQLException e) {
			// erro
			retorno = 2;
		}
		return retorno;
	}
	
	public int alterar(Pessoa p) {
		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;

		conn = conexao.abreConexaoBD();
		String sql = "";
		int retorno = 0;
		try {
			// alterar
			sql = "update pessoa set cpf=?, nome=?, datanasc=?, id_profis=? where id_pessoa=?";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, p.getCpf());
			ps.setString(2, p.getNome());
			ps.setString(3, p.getDatanasc());
			ps.setInt(4, p.getId_profis());
			ps.setInt(5, p.getId_pessoa());
			ps.executeUpdate();
			retorno = 1;
		} catch (SQLException e) {
			// erro
			retorno = 2;
		}
		return retorno;
	}
	
	public int excluir(int id) {
		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;

		conn = conexao.abreConexaoBD();
		String sql = "";
		int retorno = 0;
		try {
			// excluir
			sql = "delete from pessoa where id_pessoa=?";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			retorno = 1;
		} catch (SQLException e) {
			// erro
			retorno = 2;
		}
		return retorno;
	}
}
