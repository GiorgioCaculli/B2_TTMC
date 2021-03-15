package be.helha.ttmc.ui.gui;



import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class JeuReponse extends BorderPane{
	private Label lblTheme, lblSujet;
	private Label lblQuestion;
	private TextField txtRep;
	private Button btnVal;
	
	private ImageView sabIm;
	private AnimationTimer animation;
	private Integer time=15;
	private long elapsedTime;
	private Label lblTime;
	
	public AnimationTimer getAnimation(){
		if(animation==null) {
			elapsedTime= System.nanoTime();
			animation= new AnimationTimer() {
				
				@Override
				public void handle(long now) {
					if(now-elapsedTime >1000000000) {
						time--;
						getLblTime().setText(time.toString());
						elapsedTime=now;
						if(time == 0) {
							
							getBtnVal().fire();
						} 
					}
					
				}
			};
		}
		
		return animation;
	}
	
	public Integer getTime() {
		return time;
	}
	
	public ImageView getSabIm() {
		if(sabIm==null) {
			sabIm= new ImageView("be/helha/ttmc/assets/images/sablier.png");
			sabIm.setFitHeight(50);sabIm.setFitWidth(50);
			KeyValue val= new KeyValue(sabIm.rotateProperty(), 360);
			Timeline t= new Timeline(
					new KeyFrame(Duration.seconds(0.9), val),
					new KeyFrame(Duration.seconds(1), val)
					);
			t.setCycleCount(Timeline.INDEFINITE);
			t.play();
		}
		return sabIm;
	}
	
	public Label getLblTime() {
		if(lblTime == null) {
			lblTime= new Label(getTime().toString());
		}
		return lblTime;
	}
	
	public Button getBtnVal() {
		if(btnVal == null) {
			btnVal=new Button("Valider!");
			btnVal.setOnAction(null);
		}
		return btnVal;
	}
	
	public void setLblTheme(String str) {
		lblTheme.setText(str);
	}
	
	public void setLblSujet(String str) {
		lblSujet.setText(str);
	}
	
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
			lblQuestion.setTextAlignment(TextAlignment.CENTER);;
			lblQuestion.setWrapText(true);
		}
		return lblQuestion;
	}
	
	public TextField getTxtRep() {
		if(txtRep == null) {
			txtRep =new TextField();
		}
		return txtRep;
	}

	public void setLblQuestion(String str) {
		lblQuestion.setText(str);
	}
	
	public JeuReponse() {
		HBox vbTime= new HBox();
		vbTime.setPadding(new Insets(5));
		vbTime.setAlignment(Pos.CENTER);
		vbTime.setStyle("-fx-background-color: DAE6F3;-fx-font-size: 25pt;");
		vbTime.getChildren().addAll(getSabIm(), getLblTime());
		
		VBox vb=new VBox();
		vb.setPadding(new Insets(20));
		vb.setStyle("-fx-background-color: DAE6F3;-fx-font-size: 25pt;");
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(vbTime,getLblTheme(),getLblSujet(),getLblQuestion(),getTxtRep(),getBtnVal());
		this.setCenter(vb);
	}
	
}
