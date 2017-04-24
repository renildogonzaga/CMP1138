package com.exemplocrudfx.view;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.exemplocrudfx.dao.BancosDeDadosDAO;
import com.exemplocrudfx.dao.TipoBancoDAO;
import com.exemplocrudfx.model.BancosDeDados;
import com.exemplocrudfx.model.TipoBD;
import com.exemplocrudfx.util.Util;

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

public class TipoDeBancoDeDadosController implements Initializable {

	@FXML
	private TabPane tabPane;

	@FXML
	private Tab tabPaneCadastro;

	@FXML
	private Tab tabPanePesquisa;

	@FXML
	private TextField txtCodigo;

	@FXML
	private TextField txtSigla;

	@FXML
	private Button btnIncluir;

	@FXML
	private Button btnAlterar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnLimpar;
	
	 @FXML
    private ComboBox<String> comboPesquisa;


	@FXML
	void onActionBtnAlterar(ActionEvent event) throws SQLException
	{
		if (Util.stringVaziaOuNula(this.txtSigla.getText())) 
		{
			Util.mensagemErro("Informe a sigla do tipo de banco de dados");
		} 
		else 
		{
			TipoBD tbd = new TipoBD();
			TipoBancoDAO tbdDAO = new TipoBancoDAO();

			if (!Util.stringVaziaOuNula(this.txtCodigo.getText()))
				tbd.setD36_tipoBd_id(Integer.parseInt(this.txtCodigo.getText()));
			tbd.setD36_tipoBd_sigla(this.txtSigla.getText());
			boolean retorno = tbdDAO.alteraTipoBD(tbd);
			if (retorno)
				Util.mensagemInformacao("Alteração realizada com sucesso");
			else
				Util.mensagemErro("Erro de Alteração");
		}
		this.limpaTela();
	}

	@FXML
	void onActionBtnExcluir(ActionEvent event) throws SQLException {
		if (Util.stringVaziaOuNula(this.txtCodigo.getText())) 
		{
			Util.mensagemErro("Selecione um tipo de banco de dados");

		}
		else
		{
			TipoBD tbd = new TipoBD();
			TipoBancoDAO tbdDAO = new TipoBancoDAO();
			BancosDeDadosDAO bdDAO = new BancosDeDadosDAO();
			ArrayList<BancosDeDados> abd = new ArrayList<BancosDeDados>();
			abd = bdDAO.buscaBancosDeDadosPorTipoBancoDados(tbd.getD36_tipoBd_id());
            if (abd.size()>0)
            {
            	Util.mensagemErro("Esse tipo de Banco de Dados Não Pode Ser Excluído");
            }
            else
            {	
				if (!Util.stringVaziaOuNula(this.txtCodigo.getText()))
					tbd.setD36_tipoBd_id(Integer.parseInt(this.txtCodigo.getText()));
				tbd.setD36_tipoBd_sigla(this.txtSigla.getText());
				
				boolean retorno = tbdDAO.excluiTipoBD(tbd);
				if (retorno)
					Util.mensagemInformacao("Exclusão realizada com sucesso");
				else
					Util.mensagemErro("Erro de Exclusão");
            }
		}
		this.limpaTela();
	}
	@FXML
	void onActionBtnIncluir(ActionEvent event) throws SQLException 
	{
		if (Util.stringVaziaOuNula(this.txtSigla.getText())) {
			Util.mensagemErro("Informe a sigla do tipo de banco de dados");
		}
		else
		{
				TipoBD tbd = new TipoBD();
			TipoBancoDAO tbdDAO = new TipoBancoDAO();
	
			if (!Util.stringVaziaOuNula(this.txtCodigo.getText()))
				tbd.setD36_tipoBd_id(Integer.parseInt(this.txtCodigo.getText()));
			tbd.setD36_tipoBd_sigla(this.txtSigla.getText());
			int retorno = tbdDAO.incluiTipoBD(tbd);
			if (retorno == 1)
				Util.mensagemInformacao("Inclusão realizada com sucesso");
			if (retorno == 0)
				Util.mensagemErro("Erro de Inclusão");
			if (retorno == 2)
				Util.mensagemInformacao("Registro já cadastrado");
	   }	
		this.limpaTela();
	}

	@FXML
	void onActionBtnLimpar(ActionEvent event) {
		this.limpaTela();

	}

	 @FXML
	 void onActionComboPesquisa(ActionEvent event) throws SQLException 
	 {
		 String sigla=this.comboPesquisa.getSelectionModel().getSelectedItem();
		 TipoBD tbd = new TipoBD();
		 TipoBancoDAO tbdDAO = new TipoBancoDAO();
		 tbd = tbdDAO.buscaTipoBDPorDescricao(sigla);
		 if (tbd!=null)
		 {
			 this.txtCodigo.setText(Integer.toString(tbd.getD36_tipoBd_id()));
			 this.txtSigla.setText(tbd.getD36_tipoBd_sigla());
			 SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();

			 selectionModel.select(0); //select by object
		 }
		 
	 }
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			limpaEAlimentaComboBoxPesquisa();
		} catch (SQLException e1) {
			Util.mensagemErro("ERRO ao tentar alimentar a lista de tipos de bancos de dados");
		}
		
		//aba pesquisa selecionada
		tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {

			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
				if (newTab == tabPanePesquisa) {
					//limpa e alimenta a combobox de pesquisa de tipos de bancos de dados
					try {
						limpaEAlimentaComboBoxPesquisa();
					} catch (SQLException e) {
						Util.mensagemErro("ERRO ao tentar alimentar a lista de tipos de bancos de dados");
					}
				}

			}
		});
		
		//item da combobox selecionado
		comboPesquisa.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
			{
				 TipoBD tbd = new TipoBD();
				 TipoBancoDAO tbdDAO = new TipoBancoDAO();
				 try 
				 {
					tbd = tbdDAO.buscaTipoBDPorDescricao(newValue);
				 }
				 catch (SQLException e) 
				 {
					Util.mensagemErro(e.getMessage());
				}
				 if (tbd!=null)
				 {
					 txtCodigo.setText(Integer.toString(tbd.getD36_tipoBd_id()));
					 txtSigla.setText(tbd.getD36_tipoBd_sigla());
					 SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();

					 selectionModel.select(0); //select by object
				 }
			}
	    });
	}

	public void limpaEAlimentaComboBoxPesquisa() throws SQLException
	{
		this.comboPesquisa.getItems().clear();
		TipoBD tbd = new TipoBD();
		
		TipoBancoDAO tbdDAO = new TipoBancoDAO();
		ArrayList<TipoBD> atbd = new ArrayList<TipoBD>();
		atbd = tbdDAO.listaTipoBDAll();
		ObservableList<String> obl = FXCollections.observableArrayList();
	 //   comboPesquisa.getItems().add("Selecione...");
		 

		for(int i=0;i<atbd.size(); i++)
		{
			String s = atbd.get(i).getD36_tipoBd_sigla();
		    obl.add(s);
		}
		this.comboPesquisa.setItems(obl);
	}
	
	public void limpaTela()
	{
		this.txtCodigo.setText("");
		this.txtSigla.setText("");
		this.comboPesquisa.setPromptText(null);
	}
}