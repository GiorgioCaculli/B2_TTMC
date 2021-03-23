package be.helha.ttmc.ui.gui;

import java.util.ArrayList;
import java.util.List;

import be.helha.ttmc.model.BasicCard;
import be.helha.ttmc.model.Deck;
import be.helha.ttmc.model.Theme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;


public class ListeCarteTableViewBP extends BorderPane{
	private TableView<BasicCard> table;
	
	@SuppressWarnings("unchecked")
	public TableView<BasicCard> getTable() {
		if(table ==null) {
			table=new TableView<>();
			table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			TableColumn<BasicCard, String> colAuteur =new TableColumn<BasicCard, String>("Author");
			
			TableColumn<BasicCard, Theme> colTheme = new TableColumn<>("Theme");
			
			TableColumn<BasicCard, String> colSubject= new TableColumn<>("Subject");
			table.getColumns().addAll(colAuteur, colTheme, colSubject);
			
			ObservableList<BasicCard> data=FXCollections.observableArrayList(
					
					);
			
			colAuteur.setCellValueFactory(new PropertyValueFactory<BasicCard, String>("author"));
			colTheme.setCellValueFactory(new PropertyValueFactory<BasicCard, Theme>("theme"));
			colSubject.setCellValueFactory(new PropertyValueFactory<BasicCard, String>("subject"));
			
			table.setItems(data);
		}
		return table;
	}
	
	
	public ListeCarteTableViewBP(Deck deck) {
		
		
			getTable().setItems(FXCollections.observableArrayList(deck.getCards()));
		
		
		
		
		setCenter(getTable());
	}

}
