import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class TelefoneDAO {

	public int incluir(Telefone f, int id) {

		Conexao conexao = new Conexao();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;

		conn = conexao.abreConexaoBD();
		String sql = "Select * from telefone where num=' " + f.getNum() + " ' ";
		int retorno = 0;
		try {
			st = (Statement) conn.createStatement();
			rs = st.executeQuery(sql);
			if (!rs.next()) {
				// incluir
				sql = "insert into telefone (num,id) values (?,?)";
				ps = (PreparedStatement) conn.prepareStatement(sql);
				ps.setString(1, f.getNum());
				ps.setInt(2, id);
				ps.executeUpdate();
				retorno =  1;
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

	public int alterar(Telefone f) {
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
			sql = "update telefone set num=? where id=?";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, f.getNum());
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
}