import javax.swing.JOptionPane;

public class Mensagem {

	String test;

	// Entrada de Informa��es
	public static String EntradaDados(String a) {
		return (JOptionPane.showInputDialog(null, a, "EXEMPLO CRUD", JOptionPane.PLAIN_MESSAGE));
	}

	// Exibe uma mensagem
	public static int Alerta(String a) {
		return JOptionPane.showConfirmDialog(null, a, "EXEMPLO CRUD", JOptionPane.PLAIN_MESSAGE);
	}

	// Confirma��o Sim ou N�o
	public int Confirm(int a) {
		if (a == 1) {
			return Integer.valueOf(JOptionPane.showConfirmDialog(null, "Conclu�do com Sucesso", "EXEMPLO CRUD", JOptionPane.PLAIN_MESSAGE));
		} else {
			return Integer.valueOf(JOptionPane.showConfirmDialog(null, "Erro", "EXEMPLO CRUD", JOptionPane.PLAIN_MESSAGE));
		}
	}
}
