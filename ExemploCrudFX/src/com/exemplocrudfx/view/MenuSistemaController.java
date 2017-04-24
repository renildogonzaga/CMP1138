package com.exemplocrudfx.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.exemplocrudfx.util.Util;

import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuSistemaController implements Initializable {
    public Stage stagex = new Stage();

    @FXML
	private MenuItem menuItemTipoBanco;

	@FXML
	private MenuItem menuItemBancoDeDados;

	@FXML
	private MenuItem menuItemSair;
	



	@FXML
	void onActionMenuItemBancoDeDados(ActionEvent event) {
		try {

			AnchorPane rootx = new AnchorPane();
			rootx = FXMLLoader.load(getClass().getResource("/com/exemplocrudfx/view/BancosDeDados.fxml"));
			Scene scenex = new Scene(rootx);
			final Stage stagex = new Stage();
			stagex.setScene(scenex);
			stagex.setTitle("Bancos de Dados");
			stagex.initModality(Modality.APPLICATION_MODAL);
			Main main = new Main();
			stagex.initOwner(main.stage);
			stagex.show();

			
		} catch (Exception e) {
			Util.mensagemErro(e.getMessage());
		}

	}

	@FXML
	void onActionMenuItemSair(ActionEvent event) {
		Platform.exit();
	}


	@FXML
	void onActionMenuItemTipoBanco(ActionEvent event) throws IOException
	{
		try {

			AnchorPane rootx = new AnchorPane();
			rootx = FXMLLoader.load(getClass().getResource("/com/exemplocrudfx/view/TipoDeBancosDeDados.fxml"));
			Scene scenex = new Scene(rootx);
			final Stage stagex = new Stage();
			stagex.setScene(scenex);
			stagex.setTitle("Cadastro de Tipos de Bancos");
			stagex.initModality(Modality.APPLICATION_MODAL);
			Main main = new Main();
			stagex.initOwner(main.stage);
			stagex.show();

		} catch (Exception e) {
			Util.mensagemErro(e.getMessage());
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
