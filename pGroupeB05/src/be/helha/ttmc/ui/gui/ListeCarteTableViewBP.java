package be.helha.ttmc.ui.gui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class ListeCarteTableViewBP extends BorderPane{
	private TableView<String> table;
	private List<TableColumn> cols;
	
	public TableView<String> getTable() {
		if(table ==null) {
			table=new TableView<>();
		}
		return table;
	}
	public List<TableColumn> getCols() {
		if(cols == null) {
			cols= new ArrayList<TableColumn>();
			cols.add(new TableColumn<>("Test"));
		}
		return cols;
	}
	
	public ListeCarteTableViewBP() {
		TableView<String> tb= new TableView<String>();
		tb.getColumns().addAll(getCols().get(0));
		setCenter(tb);
	}

}
