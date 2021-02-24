package be.helha.ttmc.ui.gui;



import javafx.application.Application;
import javafx.stage.Stage;
import be.helha.ttmc.model.BasicCard;
import be.helha.ttmc.model.Question;
import be.helha.ttmc.model.Theme;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;



public class MainGui extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FenetreAjout root = new FenetreAjout();
			Scene scene = new Scene(root,750,300);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//primaryStage.setResizable(false);
			primaryStage.setTitle("Add a new card");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public MainGui() {
		
	}
}