package be.helha.ttmc.ui.gui.play;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PionCircle extends Circle{
	
	public static Color couleur= Color.MEDIUMAQUAMARINE;
	
	public PionCircle(double centerX, double centerY,double radius) {
		super(centerX, centerY, radius);
		colorPion();
	}
	
	public void setPos(double newX, double newY) {
		this.setCenterX(newX);
		this.setCenterY(newY);
	}
	
	public void colorPion() {
		setFill(couleur);
	}
}
