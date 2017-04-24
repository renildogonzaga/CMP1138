import javax.swing.JOptionPane;

public class Mensagem {

	String test;

	// Entrada de Informações
	public static String EntradaDados(String a) {
		return (JOptionPane.showInputDialog(null, a, "EXEMPLO CRUD", JOptionPane.PLAIN_MESSAGE));
	}

	// Exibe uma mensagem
	public static int Alerta(String a) {
		return JOptionPane.showConfirmDialog(null, a, "EXEMPLO CRUD", JOptionPane.PLAIN_MESSAGE);
	}

	// Confirmação Sim ou Não
	public int Confirm(int a) {
		if (a == 1) {
			return Integer.valueOf(JOptionPane.showConfirmDialog(null, "Concluído com Sucesso", "EXEMPLO CRUD", JOptionPane.PLAIN_MESSAGE));
		} else {
			return Integer.valueOf(JOptionPane.showConfirmDialog(null, "Erro", "EXEMPLO CRUD", JOptionPane.PLAIN_MESSAGE));
		}
	}
}
