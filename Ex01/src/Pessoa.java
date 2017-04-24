import java.util.Vector;

public class Pessoa {
	
	int id;
	String nome, cpf;
	Vector<Telefone> Telefone;
	
	public Vector<Telefone> getTelefone() {
		return Telefone;
	}

	public void setTelefone(Vector<Telefone> telefone) {
		Telefone = telefone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
