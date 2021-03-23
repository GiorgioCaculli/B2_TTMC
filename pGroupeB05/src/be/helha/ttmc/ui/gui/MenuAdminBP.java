package be.helha.ttmc.ui.gui;

import be.helha.ttmc.model.Deck;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MenuAdminBP extends BorderPane
{

    private Button btnAjout, btnListe, btnRetour;
    private Deck d;
    private StackPane adminChoicePane = new StackPane();

    public MenuAdminBP( Deck d )
    {
        this.d = d;
        
        
        VBox tile = new VBox();
        // this.setOrientation(Orientation.VERTICAL);
        tile.setPadding( new Insets( 20 ) );
        tile.setSpacing( 50 );

        tile.setStyle( "-fx-background-color: DAE6F3;" + "-fx-font-size: 15pt;" );
        tile.getChildren().addAll( getBtnAjout(), getBtnListe(), getBtnRetour() );
        tile.setAlignment( Pos.CENTER );
        adminChoicePane.getChildren().add( tile );
        adminChoicePane.getChildren().add( new FenetreAjoutBP( d ) );
        for( int i = 0; i < adminChoicePane.getChildren().size(); i++ )
        {
            adminChoicePane.getChildren().get( i ).setVisible( false );
        }
        adminChoicePane.getChildren().get( 0 ).setVisible( true );

        this.setCenter( adminChoicePane );

    }

    private Button getBtnAjout()
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
                    adminChoicePane.getChildren().get( 0 ).setVisible( false );
                    adminChoicePane.getChildren().get( 1 ).setVisible( true );
                }
            } );
        }
        return btnAjout;
    }

    private Button getBtnListe()
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
                    if( adminChoicePane.getChildren().size() > 2 )
                    {
                        for( int i = 2; i < adminChoicePane.getChildren().size(); i++ )
                        {
                            adminChoicePane.getChildren().remove( i );
                        }
                    }
                    adminChoicePane.getChildren().add( new ListeCarteBP( d ) );
                    adminChoicePane.getChildren().get( 0 ).setVisible( false );
                    adminChoicePane.getChildren().get( 2 ).setVisible( true );
                }
            } );
        }
        return btnListe;
    }

    private Button getBtnRetour()
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
