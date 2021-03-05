package be.helha.ttmc.ui.gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ListeCarte extends BorderPane{
	private DeckTV tv;
	private ListeCarteTableView ls;

	public DeckTV getTv() {
		if(tv ==null)
			tv= new DeckTV();
		return tv;
	}
	
	public ListeCarteTableView getLs() {
		if(ls==null)
			ls=new ListeCarteTableView();
		return ls;
	}

	public ListeCarte() {
		HBox hb= new HBox();
		hb.getChildren().addAll(getTv(), getLs());
		
		this.setCenter(hb);
	}
	

}
