package be.helha.ttmc.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MenuPrincipal extends BorderPane{

	private Button btnJouer, btnParametres, btnQuitter, btnGerer;

	public Button getBtnJouer() {
		if(btnJouer == null) {
			btnJouer = new Button("Play !");
			btnJouer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		}
		return btnJouer;
	}

	public Button getBtnParametres() {
		if(btnParametres == null) {
			btnParametres = new Button("Settings");
			btnParametres.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		}
		return btnParametres;
	}

	public Button getBtnQuitter() {
		if(btnQuitter == null) {
			btnQuitter = new Button("Leave the game");
			btnQuitter.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		}
		return btnQuitter;
	}

	public Button getBtnGerer() {
		if(btnGerer == null) {
			btnGerer = new Button("Admin Panel");
			btnGerer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		}
		return btnGerer;
	}
	
	public MenuPrincipal() {
		VBox vb = new VBox();
		vb.setPadding(new Insets(20));
		vb.setSpacing(25);
		vb.getChildren().addAll(getBtnJouer(), getBtnParametres(), getBtnQuitter(), getBtnGerer());
		
		vb.setStyle("-fx-background-color: DAE6F3;"
				+ "-fx-font-size: 15pt;");
		vb.setAlignment(Pos.CENTER);
		this.setCenter(vb);
	}
	
	public Scene MenuPrinci() {
		
		return new Scene(this);
	}
}
