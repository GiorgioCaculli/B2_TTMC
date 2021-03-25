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

public class MenuPlayBP extends BorderPane
{

    private Button btnSolo, btnMulti, btnRetour;
    private Deck d;
    private StackPane choicePane = new StackPane();

    public MenuPlayBP( Deck d )
    {
        this.d = d;
        VBox tile = new VBox();
        // this.setOrientation(Orientation.VERTICAL);
        tile.setPadding( new Insets( 20 ) );
        tile.setSpacing( 50 );

        tile.setStyle( "-fx-background-color: DAE6F3;" + "-fx-font-size: 15pt;" );
        tile.getChildren().addAll( getBtnSolo(), getBtnMulti(), getBtnRetour() );
        tile.setAlignment( Pos.CENTER );
        
        choicePane.getChildren().add( tile );

        this.setCenter( choicePane );

    }

    public Button getBtnSolo()
    {
        if ( btnSolo == null )
        {
            btnSolo = new Button( "Single Player" );
            btnSolo.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
            btnSolo.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    if( choicePane.getChildren().size() > 1 )
                    {
                        for( int i = 1; i < choicePane.getChildren().size(); i++ )
                        {
                            choicePane.getChildren().remove( i );
                        }
                    }
                    JouerChoixQuestionBP jcq = new JouerChoixQuestionBP( d );
                    jcq.setScore( 0 );
                    choicePane.getChildren().get( 0 ).setVisible( false );
                    choicePane.getChildren().add( jcq );
                    choicePane.getChildren().get( 1 ).setVisible( true );
                }
            } );
        }
        return btnSolo;
    }

    public Button getBtnMulti()
    {
        if ( btnMulti == null )
        {
            btnMulti = new Button( "Multiplayer" );
            btnMulti.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
            btnMulti.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                }
            } );
        }
        return btnMulti;
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
                    MainPaneBP mpbp = (MainPaneBP) getParent().getParent();
                    mpbp.setVisibleNode( "MenuPrincipalBP" );
                }
            } );
        }
        return btnRetour;
    }
}
