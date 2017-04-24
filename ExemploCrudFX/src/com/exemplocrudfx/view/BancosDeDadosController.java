package com.exemplocrudfx.view;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

//import com.exemplocrudfx.controller.CorbestDB;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BancosDeDadosController implements Initializable
{
	  MenuSistemaController msc = new MenuSistemaController();
	  
	 @FXML
	    private TabPane tabPane;

	    @FXML
	    private Tab tabPaneCadastro;

	    @FXML
	    private TextField txtCodigo;

	    @FXML
	    private TextField txtServidor;

	    @FXML
	    private ComboBox<String> comboBoxTipoBanco;

	    @FXML
	    private TextField txtNomeBanco;

	    @FXML
	    private TextField txtUsuario;

	    @FXML
	    private Button btnIncluir;

	    @FXML
	    private Button btnAlterar;

	    @FXML
	    private Button btnExcluir;

	    @FXML
	    private Button btnLimpar;

	    @FXML
	    private PasswordField txtSenha;

	    @FXML
	    private Tab tabPanePesquisa;

	    @FXML
	    private TextField txtPesquisa;

	    @FXML
	    private TableView<BancosDeDados> tableViewBancosDados;

	    @FXML
	    private TableColumn<BancosDeDados,Integer> colunaCodigo;

	    @FXML
	    private TableColumn<BancosDeDados,String> colunaNomeBanco;
	    
	    @FXML
	    private Button btnConfirmaPesq;
	    
	    @FXML
	    private Button btnAtualizar;

	    
	    @FXML
	    void onActionBtnAtualizar(ActionEvent event)
	    {
	    	alimentaComboBoxTipoBanco();
	    }

	    @FXML
	    void onActionBtnConfirmaPesq(ActionEvent event)
	    {
	    	String pesquisa = txtPesquisa.getText();
	    	if (pesquisa==null)
	    		pesquisa="";

	    	    //consulta e prepara tableView
            	try 
            	{
					consultaDadosEPreparaTableView(pesquisa);
				} catch (SQLException e) 
            	{
					Util.mensagemErro("erro:"+e.getMessage());
				}
   	    }

	    @FXML
	    void onActionBtnAlterar(ActionEvent event) throws SQLException {
	    	String msg="";
	    	if (Util.stringVaziaOuNula(this.txtServidor.getText())) 
			{
				msg="Informe o nome do servidor do banco de dados";
			} 
	    	if (Util.stringVaziaOuNula(this.txtNomeBanco.getText())) 
			{
				msg+="Informe o nome do banco de dados";
			} 
	    	if (Util.stringVaziaOuNula(this.txtSenha.getText())) 
			{
				msg+="Informe a senha";
			} 
	    	if (Util.stringVaziaOuNula(this.txtUsuario.getText())) 
			{
				msg+="Informe o usuário";
			} 
	    	if (msg.equals(""))
			{
				TipoBD tbd = new TipoBD();
				TipoBancoDAO tbdDAO = new TipoBancoDAO();
				BancosDeDados bd = new BancosDeDados();
				BancosDeDadosDAO bdDAO = new BancosDeDadosDAO();
				if (!Util.stringVaziaOuNula(this.txtCodigo.getText()))
					bd.setBdd_id(Integer.parseInt(this.txtCodigo.getText()));
				bd.setBdd_nomebd(this.txtNomeBanco.getText());
				bd.setBdd_senha(this.txtSenha.getText());
				bd.setBdd_servidor(this.txtServidor.getText());
				bd.setBdd_usuario(this.txtUsuario.getText());
				String s=this.comboBoxTipoBanco.getPromptText();
				bd.setTip_id(this.retornaObjetoTipoBancoDeDados(s).getD36_tipoBd_id());
				bd.setBdd_nomeTipoBD(s);
				boolean retorno = bdDAO.alteraconfbd(bd);
				if (retorno)
				{	
	
					Util.mensagemInformacao("Alteração realizada com sucesso");
				}	
				else
					Util.mensagemErro("Erro de Alteração");
			}
	    	else
	    	{
	    		Util.mensagemErro(msg);
	    	}
	    	this.limparTela();
	    }

	    @FXML
	    void onActionBtnExcluir(ActionEvent event) {
				Util.mensagemErro("Opção Não ativada");
	    }

	    @FXML
	    void onActionBtnIncluir(ActionEvent event) throws SQLException {
	    	String msg="";
	    	if (Util.stringVaziaOuNula(this.txtServidor.getText())) 
			{
				msg="Informe o nome do servidor do banco de dados";
			} 
	    	if (Util.stringVaziaOuNula(this.txtNomeBanco.getText())) 
			{
				msg+="Informe o nome do banco de dados";
			} 
	    	if (Util.stringVaziaOuNula(this.txtSenha.getText())) 
			{
				msg+="Informe a senha";
			} 
	    	if (Util.stringVaziaOuNula(this.txtUsuario.getText())) 
			{
				msg+="Informe o usuário";
			} 
	    	if (msg.equals(""))
			{
				TipoBD tbd = new TipoBD();
				TipoBancoDAO tbdDAO = new TipoBancoDAO();
				BancosDeDados bd = new BancosDeDados();
				BancosDeDadosDAO bdDAO = new BancosDeDadosDAO();
					if (!Util.stringVaziaOuNula(this.txtCodigo.getText()))
						bd.setBdd_id(Integer.parseInt(this.txtCodigo.getText()));
					bd.setBdd_nomebd(this.txtNomeBanco.getText());
					bd.setBdd_senha(this.txtSenha.getText());
					bd.setBdd_servidor(this.txtServidor.getText());
					bd.setBdd_usuario(this.txtUsuario.getText());
					String s=this.comboBoxTipoBanco.getSelectionModel().getSelectedItem();
					if (s==null)
					{
						s=this.comboBoxTipoBanco.getPromptText();
					}
					
					tbd=this.retornaObjetoTipoBancoDeDados(s);

					bd.setTip_id(tbd.getD36_tipoBd_id());
					bd.setBdd_nomeTipoBD(tbd.getD36_tipoBd_sigla());
				int retorno = bdDAO.incluiconfbd(bd);
				if (retorno==1)
				{
					bd = bdDAO.buscaconfbdPorNomeBanco(bd.getBdd_nomebd());
					if (bd!=null)
					{ 
					  Util.mensagemInformacao("Inclusão realizada com sucesso");
					}  
				}	
				if (retorno == 0)
					Util.mensagemErro("Erro de Inclusão");
				if (retorno == 2)
					Util.mensagemInformacao("Registro já cadastrado");			}
	    	else
	    	{
	    		Util.mensagemErro(msg);
	    	}
	    	
	    }

	    @FXML
	    void onActionBtnLimpar(ActionEvent event) {
	    	 this.limparTela();
	    	   
	    }
	public void consultaDadosEPreparaTableView(String s) throws SQLException
	{
		alimentaComboBoxTipoBanco();
		ArrayList<BancosDeDados> abdVazia = new ArrayList<BancosDeDados>();
		ObservableList obl = FXCollections.observableArrayList(abdVazia);	
		this.tableViewBancosDados.setItems(obl);
		TipoBD tbd = new TipoBD();
		TipoBancoDAO tbdDAO = new TipoBancoDAO();
		BancosDeDados bd = new BancosDeDados();
		BancosDeDadosDAO bdDAO = new BancosDeDadosDAO();
		ArrayList<BancosDeDados> abd = new ArrayList<BancosDeDados>();
		abd = bdDAO.buscaconfbdPorDescricaoNomeBancoAll(s);
		obl = FXCollections.observableArrayList(abd);	
		this.tableViewBancosDados.setItems(obl);
		String siglaBanco="";
		this.colunaCodigo.setCellValueFactory(new PropertyValueFactory<BancosDeDados,Integer>("bdd_id"));
		this.colunaNomeBanco.setCellValueFactory(new PropertyValueFactory<BancosDeDados,String>("bdd_nomebd"));
 	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		  alimentaComboBoxTipoBanco();
		  tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
	            @Override
	            public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) 
	            {
	            	if (t1==tabPaneCadastro)
	            	{	
	                   //preenche comboBoxTipoBanco
	                   alimentaComboBoxTipoBanco();
	            	}   
	            }
	        });
		
		//item da combobox selecionado
	         comboBoxTipoBanco.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()  {
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
				 }
			}
	    });
	         
	         
	    //tableView da pesquisa recebe clique do mouse esquerdo
	         tableViewBancosDados.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
	        	    @Override
	        	    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
	        	        //Check whether item is selected and set value of selected item to Label
	        	        if(tableViewBancosDados.getSelectionModel().getSelectedItem() != null) 
	        	        {    
	        	           BancosDeDados bd = new BancosDeDados();
	        	           bd.setBdd_id(tableViewBancosDados.getSelectionModel().getSelectedItem().getBdd_id());
	        	           bd.setBdd_nomebd(tableViewBancosDados.getSelectionModel().getSelectedItem().getBdd_nomebd());
	        	           bd.setBdd_senha(tableViewBancosDados.getSelectionModel().getSelectedItem().getBdd_senha());
	        	           bd.setBdd_servidor(tableViewBancosDados.getSelectionModel().getSelectedItem().getBdd_servidor());
	        	           bd.setTip_id(tableViewBancosDados.getSelectionModel().getSelectedItem().getTip_id());
	        	           bd.setBdd_usuario(tableViewBancosDados.getSelectionModel().getSelectedItem().getBdd_usuario());
                           try {
							alimentaCamposDaTelaCadastro(bd);
						    } catch (SQLException e) {
							 Util.mensagemErro("ERRO:"+e.getMessage());
						    }	        	           	        	           	        	           	        	           	        	           
	        	          }
	        	         }
	        	     });
	         
	         
	         
	}
	public void alimentaCamposDaTelaCadastro(BancosDeDados bd) throws SQLException
	{
		
		TipoBD tbd = new TipoBD();
		TipoBancoDAO tbdDAO = new TipoBancoDAO();
		tbd = tbdDAO.buscaTipoBDPorId(bd.getTip_id());
		String siglaTipoBanco="*";
		if (tbd!=null)
			siglaTipoBanco=tbd.getD36_tipoBd_sigla();
		for(int i=0;i<this.comboBoxTipoBanco.getItems().size(); i++)
		{
			String s=this.comboBoxTipoBanco.getItems().get(i).toString();
			if (s.equals(siglaTipoBanco))
			{
                 comboBoxTipoBanco.setPromptText(siglaTipoBanco);
			}
		}
		this.txtCodigo.setText(Integer.toString(bd.getBdd_id()));
		this.txtNomeBanco.setText(bd.getBdd_nomebd());
		this.txtSenha.setText(bd.getBdd_senha());
		this.txtServidor.setText(bd.getBdd_servidor());
		this.txtUsuario.setText(bd.getBdd_usuario());
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(tabPaneCadastro);

	}
	public TipoBD retornaObjetoTipoBancoDeDados(String s)
	{
		 //String s=combobox.getPromptText();
		 TipoBD tbd = new TipoBD();
		 TipoBancoDAO tbdDAO = new TipoBancoDAO();
		 try 
		 {
			tbd = tbdDAO.buscaTipoBDPorDescricao(s);
		 }
		 catch (SQLException e) 
		 {
			Util.mensagemErro("tentou retornar tipo de banco de dados:"+e.getMessage());
		}
		return tbd;
	}

	public void alimentaComboBoxTipoBanco()
	{
		 comboBoxTipoBanco.getItems().remove(0, comboBoxTipoBanco.getItems().size());
		 TipoBD tbd = new TipoBD();
		 TipoBancoDAO tbdDAO = new TipoBancoDAO();
		 ArrayList<TipoBD> atbd = new ArrayList<TipoBD>();
		
		 try 
		 {
			atbd = tbdDAO.listaTipoBDAll();
		 }
		 catch (SQLException e) 
		 {
			Util.mensagemErro("ERRO ao tentar alimentar lista de tipos: "+e.getMessage());
		}

	
		 
		 for(int i=0;i<atbd.size();i++)
		 {
			 comboBoxTipoBanco.getItems().add(atbd.get(i).getD36_tipoBd_sigla());
		 }
	}
	
	public void limparTela()
	{
		this.txtCodigo.setText("");
		this.txtNomeBanco.setText("");
		this.txtPesquisa.setText("");
		this.txtSenha.setText("");
		this.txtServidor.setText("");
		this.txtUsuario.setText("");
		alimentaComboBoxTipoBanco();
		comboBoxTipoBanco.setPromptText(null);
		ArrayList<BancosDeDados> abdVazia = new ArrayList<BancosDeDados>();
		ObservableList obl = FXCollections.observableArrayList(abdVazia);	
		this.tableViewBancosDados.setItems(obl);
	}
	

}
