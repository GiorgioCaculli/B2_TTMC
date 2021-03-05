package be.helha.ttmc.ui.gui;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ListeCarte extends BorderPane{
	private DeckTV tv;
	private ListeCarteTableView ls;
	private Button btnReturn;
	
	

	public Button getBtnReturn() {
		if(btnReturn == null)
			btnReturn= new Button("Return");
		return btnReturn;
	}

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
		
		VBox vb = new VBox();
		vb.getChildren().addAll(hb,getBtnReturn());
		
		this.setCenter(vb);
	}
	

}
