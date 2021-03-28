package be.helha.ttmc.ui.gui;

import java.util.ArrayList;
import java.util.List;

import be.helha.ttmc.model.Deck;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlateauBp extends BorderPane{
	private int nbCases;
	private List<Rectangle> cases;
	private List<Label> num;
	
	private final double WIDTH_RECT= 45.;
	private final double HEIGHT_RECT = 45.;
	private final double MOUV_X = WIDTH_RECT+1;
	private final double MOUV_Y = HEIGHT_RECT+1;
	
	public PlateauBp(Deck d) {
		nbCases= d.getCards().size();
		AnchorPane anch = new AnchorPane();
		getCases();
		getNum();
		int test=0;
		int rangX= 0;
		String text;
		for(int i = 0; i<cases.size(); i++) {
			double mouvY= (i+1)*MOUV_Y;
			text= (i+1)+"";
			Label lab= new Label(text);
			if((i+1)*MOUV_Y > 790-HEIGHT_RECT*3) {
				if((int)(((i+1)*MOUV_Y)/(790-HEIGHT_RECT*3)) > rangX) {
					rangX++;
					test=0;
				}
				
				
				mouvY= (test+1)*MOUV_Y;
				test++;
			}
			double mouvX= rangX*MOUV_X;
			getCases().get(i).setX(mouvX);
			lab.setTranslateX(mouvX);
			
			if(i== d.getCards().size()-1)
				lab.setText(text+"\nFinish");
			lab.setLayoutY(mouvY);
			getNum().add(lab);
			getCases().get(i).setY(mouvY);
			anch.getChildren().addAll(getCases().get(i), getNum().get(i));
		}
		setCenter(anch);
	}

	private List<Rectangle> getCases() {
		if(cases == null) {
			cases= new ArrayList<>();
			for(int i = 0; i<nbCases; i++) {
				Rectangle rect= new Rectangle(WIDTH_RECT, HEIGHT_RECT);
				rect.setStroke(Color.BLUE);
				rect.setFill(Color.ALICEBLUE);
				cases.add(rect);
			}
		}
		return cases;
		
	}
	
	private List<Label> getNum(){
		if(num == null)
			num = new ArrayList<>();
		return num;
	}
}
