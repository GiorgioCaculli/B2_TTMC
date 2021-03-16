package be.helha.ttmc.ui.gui;

import be.helha.ttmc.model.Deck;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ListeCarteBP extends BorderPane
{
    private DeckTVBP tv;
    private ListeCarteTableViewBP ls;
    private Button btnReturn;
    private Deck d;

    public ListeCarteBP( Deck d )
    {
        this.d = d;
        HBox hb = new HBox();
        hb.getChildren().addAll( getTv(), getLs() );

        VBox vb = new VBox();
        vb.getChildren().addAll( hb, getBtnReturn() );

        this.setCenter( vb );
    }

    public Button getBtnReturn()
    {
        if ( btnReturn == null )
        {
            btnReturn = new Button( "Return" );
            btnReturn.setOnAction( new EventHandler< ActionEvent >()
            {
                
                @Override
                public void handle( ActionEvent arg0 )
                {
                    getParent().getChildrenUnmodifiable().get( 4 ).setVisible( false );
                    getParent().getChildrenUnmodifiable().get( 2 ).setVisible( true );
                }
            } );
        }
        return btnReturn;
    }

    public DeckTVBP getTv()
    {
        if ( tv == null )
            tv = new DeckTVBP();
        return tv;
    }

    public ListeCarteTableViewBP getLs()
    {
        if ( ls == null )
            ls = new ListeCarteTableViewBP();
        return ls;
    }

}
