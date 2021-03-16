package be.helha.ttmc.ui.gui;

import be.helha.ttmc.model.Deck;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class MainPane extends StackPane
{
    public MainPane( Deck d )
    {
        getChildren().add( new MenuPrincipal( d ) );
        getChildren().add( new MenuPlay( d ) );
        getChildren().add( new MenuAjout( d ) );
        getChildren().add( new FenetreAjout( d ) );
        getChildren().add( new ListeCarte( d ) );
        for( Node n : getChildren() )
        {
            n.setVisible( false );
        }
        getChildren().get( 0 ).setVisible( true );
    }
}
