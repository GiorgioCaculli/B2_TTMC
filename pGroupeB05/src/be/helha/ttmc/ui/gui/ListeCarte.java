package be.helha.ttmc.ui.gui;

import javafx.scene.layout.BorderPane;

public class ListeCarte extends BorderPane{
	private DeckTV tv;

	public DeckTV getTv() {
		if(tv ==null)
			tv= new DeckTV();
		return tv;
	}
	
	public ListeCarte() {
		this.setCenter(getTv());
	}
	

}
