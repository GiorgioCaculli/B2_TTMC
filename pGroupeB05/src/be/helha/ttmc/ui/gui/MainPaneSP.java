package be.helha.ttmc.ui.gui;

import be.helha.ttmc.Main;
import be.helha.ttmc.model.Deck;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class MainPaneSP extends StackPane
{
    private MusicGestion musics;
    public MainPaneSP( Deck d )
    {
        getChildren().add( new MenuPrincipalBP( d ) );
        getChildren().add( new MenuPlayBP( d ) );
        getChildren().add( new MenuAjoutBP( d ) );
        getChildren().add( new FenetreAjoutBP( d ) );
        getChildren().add( new ListeCarteBP( d ) );
        for( Node n : getChildren() )
        {
            n.setVisible( false );
        }
        getChildren().get( 0 ).setVisible( true );
        System.out.println(Main.class.getResource( "assets/musics/CreativeDestruction.mp3" ).toString());
        try
        {
            musics = new MusicGestion( "assets/musics/CreativeDestruction.mp3" );
            musics.start();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
}
