package be.helha.ttmc.ui.gui;

import be.helha.ttmc.model.BasicCard;
import be.helha.ttmc.model.Deck;
import be.helha.ttmc.model.Theme;
import be.helha.ttmc.serialization.Serialization;
import be.helha.ttmc.ui.gui.MenuAdminBP.MenuAdminMainVB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
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
    private StackPane carteChoicePane;
    private TableView< BasicCard > table;

    public ListeCarteBP( Deck d )
    {
        this.d = d;

        getCarteChoicePane().getChildren().add( new ListeCarteMainBP() );

        setVisibleNode( ListeCarteMainBP.class.getSimpleName() );

        setCenter( getCarteChoicePane() );
    }

    public StackPane getCarteChoicePane()
    {
        if ( carteChoicePane == null )
        {
            carteChoicePane = new StackPane();
        }
        return carteChoicePane;
    }

    protected class ListeCarteMainBP extends BorderPane
    {
        public ListeCarteMainBP()
        {
            setCenter( getTable() );

            HBox buttonBox = new HBox();
            Region espaceVideReturnReload = new Region();
            HBox.setHgrow( espaceVideReturnReload, Priority.ALWAYS );
            Region espaceVideReloadDelete = new Region();
            HBox.setHgrow( espaceVideReloadDelete, Priority.ALWAYS );
            buttonBox.getChildren().addAll( getBtnReturn(), espaceVideReturnReload, getBtnReload(),
                    espaceVideReloadDelete, getBtnDelete() );

            setBottom( buttonBox );
        }
    }

    public void setVisibleNode( String paneName )
    {
        for ( Node n : getCarteChoicePane().getChildren() )
        {
            if ( n.getClass().getSimpleName().equals( paneName ) )
            {
                n.setVisible( true );
            }
            else
            {
                n.setVisible( false );
            }
        }
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
                                for( int i = 0; i < getCarteChoicePane().getChildren().size(); i++ )
                                {
                                    if( getCarteChoicePane().getChildren().get( i ).getClass().getSimpleName().equals( FenetreModificationBP.class.getSimpleName() ) )
                                    {
                                        getCarteChoicePane().getChildren().remove( i );
                                    }
                                }
                                getCarteChoicePane().getChildren().add( new FenetreModificationBP( d, rowCard ) );
                                setVisibleNode( FenetreModificationBP.class.getSimpleName() );
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
                    MenuAdminBP mabp = ( MenuAdminBP ) getParent().getParent();
                    mabp.setVisibleNode( MenuAdminMainVB.class.getSimpleName() );
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
