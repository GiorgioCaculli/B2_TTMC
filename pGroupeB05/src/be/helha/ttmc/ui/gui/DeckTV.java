package be.helha.ttmc.ui.gui;

import java.util.ArrayList;
import java.util.List;

import be.helha.ttmc.model.Theme;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;

public class DeckTV extends BorderPane{
	private TreeItem<String> deck;
	private List<TreeItem<String>> theme;
	private List<TreeItem<String>> author;
	private TreeTableColumn<String, String> col;
	
	
	
	public TreeItem<String> getDeck() {
		if(deck ==null) {
			deck=new TreeItem<String>("Deck");
			deck.setExpanded(true);
		}
		return deck;
	}

	public TreeTableColumn<String, String> getCol() {
		if(col==null) {
			col=new TreeTableColumn<String, String>("Decks");
			col.setCellValueFactory((CellDataFeatures<String, String> p)->
				new ReadOnlyStringWrapper(p.getValue().getValue()));
			col.setPrefWidth(250);
		}
		return col;
	}
	
	public List<TreeItem<String>> getTheme() {
		if(theme == null) {
			theme=new ArrayList<>();
			for(Theme t: Theme.values()) {
				TreeItem<String> d=new TreeItem<String>(t.toString());
				theme.add(d);
			}
		}
		return theme;
	}
	public List<TreeItem<String>> getAuthor() {
		if(author==null) {
			author=new ArrayList<>();
			author.add(new TreeItem<String>("Giorgio"));
			author.add(new TreeItem<String>("Tanguy"));
			author.add(new TreeItem<String>("Guillaume"));
		}
		return author;
	}
	
	public TreeItem<String> creaHierachie() {
		getTheme(); getAuthor(); getDeck();
		for(int i=0; i< getTheme().size();i++) {
			
			getTheme().get(i).getChildren().setAll(getAuthor());
			getDeck().getChildren().setAll(getTheme());
		}
		
		return getDeck();
	}
	
	public DeckTV() {
		TreeTableView<String> tv=new TreeTableView<String>(creaHierachie());
		tv.setPrefWidth(250);
		tv.getColumns().add(getCol());
		tv.setShowRoot(true);
		setCenter(tv);
		
	}
	

}
