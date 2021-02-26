package be.helha.ttmc.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class JeuReponse extends BorderPane{
	private Label lblTheme, lblSujet;
	private Label lblQuestion;
	private TextField txtRep;
	
	public Label getLblTheme() {
		if(lblTheme == null) {
			lblTheme = new Label("Theme");
		}
		return lblTheme;
	}
	public Label getLblSujet() {
		if(lblSujet == null) {
			lblSujet = new Label("Sujet");
		}
		return lblSujet;
	}
	public Label getLblQuestion() {
		if(lblQuestion == null) {
			lblQuestion = new Label("test");
		}
		return lblQuestion;
	}
	public TextField getTxtRep() {
		if(txtRep == null) {
			txtRep =new TextField();
		}
		return txtRep;
	}

	public JeuReponse() {
		VBox vb=new VBox();
		vb.setPadding(new Insets(20));
		vb.setStyle("-fx-background-color: DAE6F3;-fx-font-size: 25pt;");
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(getLblTheme(),getLblSujet(),getLblQuestion(),getTxtRep());
		this.setCenter(vb);
	}
	
}
