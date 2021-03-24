package be.helha.ttmc.ui.gui;

import be.helha.ttmc.model.BasicCard;
import be.helha.ttmc.model.Deck;
import be.helha.ttmc.model.Theme;
import be.helha.ttmc.serialization.Serialization;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

public class ListeCarteBP extends BorderPane
{
    private Button btnReturn, btnDelete, btnReload;
    private Deck d;
    private StackPane carteChoicePane = new StackPane();
    private TableView< BasicCard > table;

    public ListeCarteBP( Deck d )
    {
        this.d = d;

        BorderPane cardListPane = new BorderPane();
        cardListPane.setCenter( getTable() );

        HBox buttonBox = new HBox();
        Region espaceVideReturnReload = new Region();
        HBox.setHgrow( espaceVideReturnReload, Priority.ALWAYS );
        Region espaceVideReloadDelete = new Region();
        HBox.setHgrow( espaceVideReloadDelete, Priority.ALWAYS );
        buttonBox.getChildren().addAll( getBtnReturn(), espaceVideReturnReload, getBtnReload(), espaceVideReloadDelete, getBtnDelete() );

        cardListPane.setBottom( buttonBox );

        carteChoicePane.getChildren().add( cardListPane );
        carteChoicePane.getChildren().get( 0 ).setVisible( true );

        setCenter( carteChoicePane );
    }

    public TableView< BasicCard > getTable()
    {
        if ( table == null )
        {
            table = new TableView<>();

            table.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );
            table.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );

            TableColumn< BasicCard, String > colAuteur = new TableColumn< BasicCard, String >( "Author" );
            colAuteur.setCellValueFactory( new PropertyValueFactory< BasicCard, String >( "author" ) );

            TableColumn< BasicCard, Theme > colTheme = new TableColumn<>( "Theme" );
            colTheme.setCellValueFactory( new PropertyValueFactory< BasicCard, Theme >( "theme" ) );

            TableColumn< BasicCard, String > colSubject = new TableColumn<>( "Subject" );
            colSubject.setCellValueFactory( new PropertyValueFactory< BasicCard, String >( "subject" ) );

            table.getColumns().addAll( colAuteur, colTheme, colSubject );

            table.setItems( FXCollections.observableArrayList( d.getCards() ) );

            table.setRowFactory( new Callback< TableView< BasicCard >, TableRow< BasicCard > >()
            {
                @Override
                public TableRow< BasicCard > call( TableView< BasicCard > arg0 )
                {
                    TableRow< BasicCard > row = new TableRow<>();
                    row.setOnMouseClicked( new EventHandler< MouseEvent >()
                    {
                        @Override
                        public void handle( MouseEvent arg0 )
                        {
                            if ( arg0.getClickCount() == 2 && ( !row.isEmpty() ) )
                            {
                                BasicCard rowCard = row.getItem();
                                for ( int i = 1; i < carteChoicePane.getChildren().size(); i++ )
                                {
                                    carteChoicePane.getChildren().remove( i );
                                }
                                carteChoicePane.getChildren().add( new FenetreModificationBP( d, rowCard ) );
                                carteChoicePane.getChildren().get( 0 ).setVisible( false );
                                carteChoicePane.getChildren().get( 1 ).setVisible( true );
                            }
                        }
                    } );
                    return row;
                }
            } );
        }
        return table;
    }

    private Button getBtnReturn()
    {
        if ( btnReturn == null )
        {
            btnReturn = new Button( "Return" );
            btnReturn.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    getParent().getChildrenUnmodifiable().get( 2 ).setVisible( false );
                    getParent().getChildrenUnmodifiable().get( 0 ).setVisible( true );
                }
            } );
        }
        return btnReturn;
    }

    private Button getBtnDelete()
    {
        if ( btnDelete == null )
        {
            btnDelete = new Button( "Delete" );
            btnDelete.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    ObservableList< BasicCard > selected = getTable().getSelectionModel().getSelectedItems();
                    for ( BasicCard card : selected )
                    {
                        d.remove( card );
                    }
                    getTable().setItems( FXCollections.observableArrayList( d.getCards() ) );
                    getTable().getSelectionModel().clearSelection();
                    Serialization.saveGame( d );
                }
            } );
        }
        return btnDelete;
    }

    private Button getBtnReload()
    {
        if ( btnReload == null )
        {
            btnReload = new Button( "Reload" );
            btnReload.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    table.setItems( FXCollections.observableArrayList( d.getCards() ) );
                }
            } );
        }
        return btnReload;
    }

}
