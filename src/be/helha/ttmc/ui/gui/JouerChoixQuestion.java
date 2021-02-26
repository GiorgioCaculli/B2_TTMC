package be.helha.ttmc.ui.gui;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class JouerChoixQuestion extends BorderPane{

	private Label lblTheme, lblSujet;
	private List<Button> choix;

	public List<Button> getChoix() {
		if(choix == null) {
			choix= new ArrayList<>();
			for(int i =1; i<=4;i++) {
				Button b=new Button("Question lvl"+i);
				b.setMinSize(250, 250);
				b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				choix.add(b);
				
			}
		}
		return choix;
	}
	
	public Label getLblTheme() {
		if(lblTheme == null) {
			lblTheme = new Label("Theme");
			lblTheme.setMaxWidth(Double.MAX_VALUE);
			lblTheme.setAlignment(Pos.CENTER);;
		}
		return lblTheme;
	}
	
	public Label getLblSujet() {
		if(lblSujet == null) {
			lblSujet = new Label("Sujet");
			lblSujet.setMaxWidth(Double.MAX_VALUE);
			lblSujet.setAlignment(Pos.CENTER);;
		}
		return lblSujet;
	}
	
	public JouerChoixQuestion() {
		
		FlowPane fp= new FlowPane();
		fp.setPadding(new Insets(5));
		fp.setVgap(5);
		fp.setHgap(5);
		fp.setPrefWrapLength(505);
		fp.setStyle("-fx-background-color: DAE6F3;-fx-font-size: 25pt;");
		
		for(Button b : getChoix()) {
			fp.getChildren().add(b);
		}
		
		VBox vb= new VBox();
		vb.setPadding(new Insets(20));
		vb.setSpacing(10);
		vb.getChildren().addAll(getLblTheme(), getLblSujet());
		vb.setStyle("-fx-font-size: 25pt;");
		
		this.setCenter(fp);
		this.setTop(vb);
	}
	
}
