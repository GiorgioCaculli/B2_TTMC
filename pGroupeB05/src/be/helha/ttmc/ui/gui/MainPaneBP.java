package be.helha.ttmc.ui.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import be.helha.ttmc.model.Deck;
import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.admin.MenuAdminBP;
import be.helha.ttmc.ui.gui.play.MenuPlayBP;
import be.helha.ttmc.ui.gui.util.MusicGestion;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class MainPaneBP extends BorderPane
{
    private MusicGestion musics;
    private StackPane stackpane;

    public MainPaneBP( Deck d, Settings s )
    {
        List< String > path = new ArrayList< String >();
        path.add( "assets/musics/EVAmusic.wav" );
        path.add( "assets/musics/CreativeDestruction.wav" );
        path.add( "assets/musics/Intouch_IntoTheWild.wav" );
        Collections.shuffle( path );

        new Thread( new Runnable()
        {

            @Override
            public void run()
            {
                try
                {
                    musics = new MusicGestion( path, s );
                    musics.gererThread( path ).run();

                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }

            }
        } ).start();

        getStackPane().getChildren().add( new MenuPrincipalBP( d ) );
        getStackPane().getChildren().add( new MenuPlayBP( d, s ) );
        getStackPane().getChildren().add( new SettingsBP( s, musics ) );
        getStackPane().getChildren().add( new MenuAdminBP( d ) );
        getStackPane().getChildren().add( new CreditsBP() );

        setVisibleNode( MenuPrincipalBP.class.getSimpleName() );
        setCenter( getStackPane() );
    }

    public void setVisibleNode( String paneName )
    {

        for ( Node n : getStackPane().getChildren() )
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

    public StackPane getStackPane()
    {
        if ( stackpane == null )
        {
            stackpane = new StackPane();
        }
        return stackpane;
    }

}
