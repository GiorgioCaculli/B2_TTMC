package be.helha.ttmc.ui.gui;

import be.helha.ttmc.model.Deck;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MenuAjout extends BorderPane
{

    private Button btnAjout, btnListe, btnRetour;
    private Deck d;

    public MenuAjout( Deck d )
    {
        this.d = d;
        VBox tile = new VBox();
        // this.setOrientation(Orientation.VERTICAL);
        tile.setPadding( new Insets( 20 ) );
        tile.setSpacing( 50 );

        tile.setStyle( "-fx-background-color: DAE6F3;" + "-fx-font-size: 15pt;" );
        tile.getChildren().addAll( getBtnAjout(), getBtnListe(), getBtnRetour() );
        tile.setAlignment( Pos.CENTER );

        this.setCenter( tile );

    }

    public Button getBtnAjout()
    {
        if ( btnAjout == null )
        {
            btnAjout = new Button( "Add a card" );
            btnAjout.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
            btnAjout.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    getParent().getChildrenUnmodifiable().get( 2 ).setVisible( false );
                    getParent().getChildrenUnmodifiable().get( 3 ).setVisible( true );
                }
            } );
        }
        return btnAjout;
    }

    public Button getBtnListe()
    {
        if ( btnListe == null )
        {
            btnListe = new Button( "Show the list of cards" );
            btnListe.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
            btnListe.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    getParent().getChildrenUnmodifiable().get( 2 ).setVisible( false );
                    getParent().getChildrenUnmodifiable().get( 4 ).setVisible( true );
                }
            } );
        }
        return btnListe;
    }

    public Button getBtnRetour()
    {
        if ( btnRetour == null )
        {
            btnRetour = new Button( "Return" );
            btnRetour.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
            btnRetour.setOnAction( new EventHandler< ActionEvent >()
            {
                
                @Override
                public void handle( ActionEvent arg0 )
                {
                    getParent().getChildrenUnmodifiable().get( 2 ).setVisible( false );
                    getParent().getChildrenUnmodifiable().get( 0 ).setVisible( true );
                }
            } );
        }
        return btnRetour;
    }
}
