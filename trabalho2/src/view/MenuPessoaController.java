package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.PessoaDAO;
import dao.Conexao;
import model.Pessoa;
import util.Util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

public class MenuPessoaController implements Initializable {

	@FXML
	private TabPane tabPane;

	@FXML
	private Tab tabPaneCadastro;

	@FXML
	private Tab tabPanePesquisa;

	@FXML
	private TextField txtCodigo;
	
	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCpf;

	@FXML
	private TextField txtNomePesquisa;

	@FXML
	private TextField txtCpfPesquisa;
	
	@FXML
	private TextField txtDataNasc;

	@FXML
	private TextField txtProfissao;
	
	@FXML
	private Button btnIncluir;

	@FXML
	private Button btnAlterar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnLimpar;

	@FXML
	private Button btnPesquisar;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	void onActionBtnIncluir(ActionEvent event) throws SQLException {

		Pessoa p = new Pessoa();
		PessoaDAO pDAO = new PessoaDAO();
		Util u = new Util();
		
		p.setNome(this.txtNome.getText());
		p.setCpf(this.txtCpf.getText());
		p.setDatanasc(this.txtDataNasc.getText());
		p.setId_profis(Integer.parseInt(this.txtProfissao.getText()));
		
		//	Verifica se algum campo está em branco
		if(p.getNome().trim().equals("") || p.getCpf().trim().equals("") || p.getDatanasc().trim().equals("") || Integer.toString((p.getId_profis())).trim().equals("")){
			u.mensagemErro("Preencha todos os campos!");	
		}
		else
		{
			pDAO.incluir(p);
			u.mensagemInformacao("Cadastro Realizado com Sucesso.");
			this.limpaTela();
		}
	}
	
	@FXML
	void onActionBtnAlterar(ActionEvent event) throws SQLException {

		Pessoa p = new Pessoa();
		PessoaDAO pDAO = new PessoaDAO();
		Util u = new Util();
		
		p.setId_pessoa(Integer.parseInt(this.txtCodigo.getText()));
		p.setNome(this.txtNome.getText());
		p.setCpf(this.txtCpf.getText());
		p.setDatanasc(this.txtDataNasc.getText());
		p.setId_profis(Integer.parseInt(this.txtProfissao.getText()));
		
		//	Verifica se algum campo está em branco
		if(p.getNome().trim().equals("") || p.getCpf().trim().equals("") || p.getDatanasc().trim().equals("") || Integer.toString((p.getId_profis())).trim().equals("") || Integer.toString((p.getId_pessoa())).trim().equals("")){
			u.mensagemErro("Preencha todos os campos!");	
		}
		else
		{
			pDAO.alterar(p);
			u.mensagemInformacao("Alteração Realizada com Sucesso.");
			this.limpaTela();
		}
	}
	
	@FXML
	void onActionBtnExcluir(ActionEvent event) throws SQLException {

		Pessoa p = new Pessoa();
		PessoaDAO pDAO = new PessoaDAO();
		Util u = new Util();
		
		p.setId_pessoa(Integer.parseInt(this.txtCodigo.getText()));
		
		//	Verifica se algum campo está em branco
		if(Integer.toString((p.getId_pessoa())).trim().equals("")){
			u.mensagemErro("Preencha o código da pessoa!");	
		}
		else
		{
			pDAO.excluir(p.getId_pessoa());
			u.mensagemInformacao("Exclusão Realizada com Sucesso.");
			this.limpaTela();
		}
	}
	
	@FXML
	void onActionBtnLimpar(ActionEvent event) {
		this.limpaTela();

	}
	
	public void limpaTela()
	{
		this.txtNome.setText("");
		this.txtCpf.setText("");
		this.txtCodigo.setText("");
		this.txtDataNasc.setText("");
		this.txtProfissao.setText("");
	}
}
