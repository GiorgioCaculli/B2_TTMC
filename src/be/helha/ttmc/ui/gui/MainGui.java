package be.helha.ttmc.ui.gui;



import be.helha.ttmc.model.BasicCard;
import be.helha.ttmc.model.Deck;
import be.helha.ttmc.model.Question;
import be.helha.ttmc.model.Theme;
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
	private int id;
	private Deck d;
	private BasicCard bc;
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
		GenererDeck();
		
		jcq.setLblTheme(bc.getTheme().toString());
		jcq.setLblSujet(bc.getSubject());
		//Click sur la question lvl 1
		jcq.getChoix().get(0).setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				id=0;
				stage.setScene(JeuReponses());
				
			}
		});
		//click sur la question lvl 2
		jcq.getChoix().get(1).setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				id=1;
				stage.setScene(JeuReponses());
				
			}
		});
		//click sur la question lvl 3
		jcq.getChoix().get(2).setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				id=2;
				stage.setScene(JeuReponses());
				
			}
		});
		//click sur la question lvl 4
		jcq.getChoix().get(3).setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				id=3;
				stage.setScene(JeuReponses());
				
			}
		});
		return new Scene(jcq);
	}
	
	public Scene JeuReponses() {
		JeuReponse jr= new JeuReponse();
		jr.setLblQuestion(bc.getQuestions().get(id).getChallenge());
		jr.setLblTheme(bc.getTheme().toString());
		jr.setLblSujet(bc.getSubject());
		jr.getBtnVal().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				if(jr.getTxtRep().getText().equalsIgnoreCase(bc.getQuestions().get(id).getAnswer())) {
					System.out.println("ok");
				}else {
					System.out.println("nope");
				}
				stage.setScene(JouerChoix());
			}
		});
		
		return new Scene(jr);
	}
	
	public void GenererDeck() {
		d= new Deck();
		bc= new BasicCard("Guillaume", Theme.IMPROBABLE, "Test");
		Question q1= new Question("Guillaume", Theme.IMPROBABLE, "Test", "Billy est-il débiles?1", "Oui");
		Question q2= new Question("Guillaume", Theme.IMPROBABLE, "Test", "Billy est-il débiles?2", "non");
		Question q3= new Question("Guillaume", Theme.IMPROBABLE, "Test", "Billy est-il débiles?3", "Peut-etre");
		Question q4= new Question("Guillaume", Theme.IMPROBABLE, "Test", "Billy est-il débiles?4", "surement");
		bc.add(q1);
		bc.add(q2);
		bc.add(q3);
		bc.add(q4);
		d.add(bc);
	}
	
	public MainGui() {
		
	}
}