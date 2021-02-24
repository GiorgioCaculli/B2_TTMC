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
			
			root.getButtonsOK().setOnAction(e->{
				if(root.getTxtAuthor().getText() != "" && root.getTxtSubject().getText() != "" ) {
					
						
							int test=0;
							for(int i=0;i<root.getMinChallenges();i++) {
								if(root.getTextsfieldAns().get(i).getText() !="" && root.getTextsfieldCha().get(i).getText() !="") {
									test++;
								}
							}
							if(test == root.getMinChallenges()) {
								BasicCard b=new BasicCard(root.getTxtAuthor().getText(), Theme.valueOf(root.getCb().getValue()), root.getTxtSubject().getText());
								for(int i=0; i<root.getMinChallenges(); i++) {
									Question q= new Question(root.getTxtAuthor().getText(), Theme.valueOf(root.getCb().getValue()), root.getTxtSubject().getText(), root.getTextsfieldCha().get(i).getText(), root.getTextsfieldAns().get(i).getText());
									b.add(q);
								}
								
								System.out.println(b.toString());
								
								
								
								
								
								
							}
							else {
								Alert alert= new Alert(AlertType.INFORMATION, "Au moins un champ est vide! Veuillez le remplir !");
								alert.showAndWait();
							}
					
					
				}else {
					Alert alert= new Alert(AlertType.INFORMATION, "Au moins un champ est vide! Veuillez le remplir !");
					alert.showAndWait();}
			});
			root.getButtonCancel().setOnAction(e ->{
				Alert alert= new Alert(AlertType.CONFIRMATION,"Voulez-vous videz les champs ?");
				if(alert.showAndWait().get() == ButtonType.OK) {
					root.getTxtAuthor().clear();
					root.getTxtSubject().clear();
					for(int i=0;i<root.getMinChallenges();i++) {
						root.getTextsfieldAns().get(i).clear();
						root.getTextsfieldCha().get(i).clear();
					}
				}
			});
			
			
			
			
			
			
			
			
			
			
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