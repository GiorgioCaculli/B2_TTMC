package be.helha.ttmc.ui.gui;

import java.util.ArrayList;
import java.util.List;

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
        getChildren().add( new MenuAdminBP( d ) );
        for( Node n : getChildren() )
        {
            n.setVisible( false );
        }
        getChildren().get( 0 ).setVisible( true );
       List<String> path= new ArrayList<String>();
       path.add("assets/musics/EVAmusic.wav");
       path.add("assets/musics/CreativeDestruction.wav");
       path.add("assets/musics/intouch_IntoTheWild.wav");
    	   
    	   
        try
        {
            musics = new MusicGestion( path );
       //     musics.start();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
}
