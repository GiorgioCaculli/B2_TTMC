package be.helha.ttmc.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class MenuAjout extends BorderPane{

	private Button btnAjout, btnListe, btnRetour;

	public Button getBtnAjout() {
		if(btnAjout==null) {
			btnAjout=new Button("Add a card");
			btnAjout.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		}	
		return btnAjout;
	}

	public Button getBtnListe() {
		if(btnListe==null) {
			btnListe=new Button("Show the list of cards");
			btnListe.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		}
		return btnListe;
	}

	public Button getBtnRetour() {
		if(btnRetour==null) {
			btnRetour=new Button("Return");
			btnRetour.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		}
		return btnRetour;
	}
	
	public MenuAjout() {
		
		VBox tile=new VBox();
		//this.setOrientation(Orientation.VERTICAL);
		tile.setPadding(new Insets(20));
		tile.setSpacing(50);
		
		tile.setStyle("-fx-background-color: DAE6F3;"
				+ "-fx-font-size: 15pt;");
		tile.getChildren().addAll(getBtnAjout(), getBtnListe(),getBtnRetour());
		
		
		this.setCenter(tile);
	}
	
	
}
