import java.util.Vector;
import javax.swing.DebugGraphics;
import javax.swing.JOptionPane;
import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Principal {
	public static void main(String[] args) throws SQLException {

		Conexao cx = new Conexao();
		Connection conn = null;
		conn = cx.abreConexaoBD();
		Mensagem msg = new Mensagem();
		Pessoa p = new Pessoa();
		PessoaDAO pDAO = new PessoaDAO();

		String op, sql;

		// MENU
		op = msg.EntradaDados("[1] Conectar Banco de Dados\n" + "[2] Incluir Pessoa\n" + "[3] Alterar Pessoa\n"
				+ "[4] Excluir Pessoa\n" + "[5] Consultar por nome\n" + "[6] Consultar por código\n"
				+ "[7] Listar todas as pessoas\n ");
		
		switch (Integer.valueOf(op)) {
		
		// CONFERIR CONEXÃO COM BANCO DE DADOS
		case 1:
			if (conn == null) {
				msg.Alerta("A conexão não ocorreu");
			} else {
				msg.Alerta("Conectado");
			}
			break;
		
		// INCLUIR PESSOA
		case 2:
			p.setNome(msg.EntradaDados("Nome:"));
			p.setCpf(msg.EntradaDados("CPF:"));
			
			Vector<Telefone> vector = new Vector<Telefone>();
			
			int op_tel = JOptionPane.showConfirmDialog(null, "Deseja registrar um telefone?", "EXEMPLO CRUD", JOptionPane.YES_NO_OPTION);
			while(op_tel != 1){
				Telefone tel = new Telefone();
				String num = Mensagem.EntradaDados("Digite o Número:");
				tel.setNum(num);
				op_tel = JOptionPane.showConfirmDialog(null, "Deseja registrar um novo telefone?", "EXEMPLO CRUD", JOptionPane.YES_NO_OPTION);
				vector.add(tel);
			}
			p.setTelefone(vector);
			
			pDAO.incluir(p);
			
			//	busca e seta id pessoa
			p.setId(pDAO.retornaID(p));
			
			pDAO.incluirTelefones(p);
			
			break;
			
		// ALTERAR PESSOA
		case 3:
			p.setId(Integer.valueOf(Mensagem.EntradaDados("Digite o id da pessoa:")));
			p.setNome(msg.EntradaDados("Nome:"));
			p.setCpf(Mensagem.EntradaDados("CPF:"));
			
			pDAO.alterar(p);
			break;
			
		//	EXCLUIR PESSOA
		case 4:
			p.setId(Integer.valueOf(Mensagem.EntradaDados("Digite o ID da pessoa")));
			pDAO.excluir(Integer.valueOf(p.getId()));
			pDAO.excluirTel(Integer.valueOf(p.getId()));

			break;
			
		//	CONSULTAR POR NOME
		case 5:
			p.setNome(msg.EntradaDados("Nome:"));
			p.setId(pDAO.retornaID(p));
			
			//Mensagem.Alerta("Nome: "+pDAO.retornaNome(p)+"\nCPF: "+pDAO.retornaCPF(p));
			
			String listaTel = "";
			String msg2 = "";
			for (Telefone t : pDAO.consultaTelefones(p.getId())) {
				msg2 = ("Telefone: "+t.num+"\n\n");				
				listaTel = listaTel + msg2;
			}
			Mensagem.Alerta("Nome: "+pDAO.retornaNome(p)+"\nCPF: "+pDAO.retornaCPF(p)+"\n\n"+listaTel);
			
			break;
		
		//	CONSULTAR POR CÓDIGO
		case 6:
			p.setId(Integer.valueOf(Mensagem.EntradaDados("Digite o ID da pessoa")));
			Mensagem.Alerta("O nome da pessoa é: "+pDAO.retornaNome(p));
			break;
			
		//	LISTAR TODAS AS PESSOAS
		case 7:
			String lista = "";
			String msg1 = "";
			for (Pessoa p2 : pDAO.consultaAll("")) {
				msg1 = ("Nome: "+p2.getNome()+"\nCPF: "+p2.getCpf()+"\n\n");				
				lista = lista + msg1;
			}
			Mensagem.Alerta(lista);
			break;
			
		default:
			break;
		}
	}
}