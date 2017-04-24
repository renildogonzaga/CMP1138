
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/*
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;*/
import java.util.Vector;
import java.sql.*;

public class PessoaDAO {

	public int incluir(Pessoa p) {

		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;

		conn = conexao.abreConexaoBD();
		String sql = "Select * from pessoa where nome=' " + p.getNome() + " ' ";
		int retorno = 0;
		try {
			st = (Statement) conn.createStatement();
			rs = st.executeQuery(sql);
			if (!rs.next()) {
				// incluir
				sql = "insert into pessoa (nome,cpf) values (?,?)";
				ps = (PreparedStatement) conn.prepareStatement(sql);
				ps.setString(1, p.getNome());
				ps.setString(2, p.getCpf());
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
			sql = "update pessoa set nome=?, cpf=? where id=?";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, p.getNome());
			ps.setString(2, p.getCpf());
			ps.setInt(3, p.getId());
			ps.executeUpdate();
			retorno = 1;
		} catch (SQLException e) {
			// erro
			retorno = 2;
		}
		return retorno;
	}

	public int excluir(int id) {
		// pode retornar: 1=ok, 2=erro
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
			sql = "delete from pessoa where id=?";
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
	
	public int excluirTel(int id) {
		// pode retornar: 1=ok, 2=erro
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
			sql = "delete from telefone where id=?";
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

	/*
	 * public int retornaId(String nome) { Conexao conexao = new Conexao();
	 * Connection conn = null; ResultSet rs = null; Statement st = null; conn =
	 * conexao.abreConexaoBD(); String sql =
	 * "select from pessoa where nome like '" + nome + "'"; int id = 0;
	 * 
	 * try { st = (Statement) conn.createStatement(); rs = st.executeQuery(sql);
	 * if (rs.next()) { id = rs.getInt(1); } } catch (SQLException e) { return
	 * 9999; } return id; } }
	 */

	/*
	 * public int retornaID(Pessoa p) { Conexao conexao = new Conexao();
	 * Connection conn = null; ResultSet rs = null; Statement st = null;
	 * PreparedStatement ps = null;
	 * 
	 * conn = conexao.abreConexaoBD(); String sql = ""; int retorno = 0;
	 * 
	 * try { // buscar id sql = "select from pessoa where nome like '?'"; ps =
	 * (PreparedStatement) conn.prepareStatement(sql); ps.setString(1,
	 * p.getNome()); rs=ps.executeQuery(); retorno = rs.getInt(1); } catch
	 * (SQLException e) { // erro retorno = 2; } return retorno; } }
	 */

	public int retornaID(Pessoa p) {
		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		conn = conexao.abreConexaoBD();
		int id = 0;
		String sql = "SELECT id FROM pessoa where nome like '" + p.getNome() + "%'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			return 9;
		}
		return id;
	}

	public void incluirTelefones(Pessoa p) {

		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;

		conn = conexao.abreConexaoBD();
		String sql = "";
		int retorno = 0;
		try {
			// incluir telefones
			for (int i =0; i<p.getTelefone().size();i++){
			sql = "insert into telefone (num,id) values (?,?)";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, p.getTelefone().get(i).getNum());
			ps.setInt(2, p.getId());
			ps.executeUpdate();
			}
		} catch (SQLException e) {
			// erro
			retorno = 2;
		}
	}
	
	public String retornaNome(Pessoa p) {
		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		conn = conexao.abreConexaoBD();
		String n = "";
		String sql = "SELECT nome FROM pessoa where id like '" + p.getId() + "'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				n = rs.getString(1);
			}
		} catch (SQLException e) {
			return "Erro";
		}
		return n;
	}
	
	public String retornaCPF(Pessoa p) {
		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		conn = conexao.abreConexaoBD();
		String cpf = "";
		String sql = "SELECT cpf FROM pessoa where nome like '" + p.getNome() + "%'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				cpf = rs.getString(1);
			}
		} catch (SQLException e) {
			return "Erro";
		}
		return cpf;
	}
	
	public ArrayList<Pessoa> consultaAll(String parametro) {

		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		Pessoa p;

		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		String sql = "";
		conn = conexao.abreConexaoBD();

		if (parametro == null) {
			sql = "Select * from pessoa order by nome";
		} else {
			sql = "select * from pessoa where nome like '" + parametro + "%'";
		}
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				p = new Pessoa();
				p.setId(rs.getInt("id"));
				p.setCpf(rs.getString("cpf"));
				p.setNome(rs.getString("nome"));
				pessoas.add(p);
			}
		} catch (SQLException e) {
			pessoas = null;
		}

		return pessoas;
	}
	
	public ArrayList<Telefone> consultaTelefones(int id) {

		ArrayList<Telefone> telefones = new ArrayList<Telefone>();
		Pessoa p;
		Telefone tel;

		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		String sql = "";
		conn = conexao.abreConexaoBD();
		sql = "select * from telefone where id="+id;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				tel = new Telefone();
				tel.setNum(rs.getString("num"));
				telefones.add(tel);
			}
		} catch (SQLException e) {
			telefones = null;
		}

		return telefones;
	}
}