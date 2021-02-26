package be.helha.ttmc.ui.gui;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;



public class MainGui extends Application {
	private Stage stage;
	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		Scene scene= MenuPrinci();
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public Scene MenuPrinci() {
		MenuPrincipal mp= new MenuPrincipal();
		//gestion du boutton permettant d'acceder a la gestion des cartes
		
		mp.getBtnGerer().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(MenuGestion());
				
			}
		});
		
		mp.getBtnQuitter().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				Alert alert = new Alert(AlertType.CONFIRMATION, "Leave the game ?");
				if(alert.showAndWait().get() == ButtonType.OK) {
					stage.close();
				}
				
			}
		});

		mp.getBtnJouer().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(JouerChoix());
				
			}
		});
		return new Scene(mp);
	}
	
	public Scene AjoutCarte() {
		FenetreAjout fa= new FenetreAjout();
		
		return new Scene(fa);
	}

	protected Scene MenuGestion() {
		MenuAjout ma = new MenuAjout();
		ma.getBtnAjout().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(AjoutCarte());
				
			}
		});
		
		return new Scene(ma);
	}
	
	public Scene JouerChoix() {
		JouerChoixQuestion jcq= new JouerChoixQuestion();
		
		return new Scene(jcq);
	}
	
	public MainGui() {
		
	}
}