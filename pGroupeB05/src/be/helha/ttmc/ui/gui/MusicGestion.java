package be.helha.ttmc.ui.gui;

import java.util.logging.Level;
import java.util.logging.Logger;

import be.helha.ttmc.Main;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicGestion
{
    private static final Logger logger = Logger.getLogger( "MainGui Class Logger" );
    private MediaPlayer mp;
    private Media m;

    public MusicGestion( String path )
    {
        logger.log( Level.INFO, String.format( "Reading music file: %s", Main.class.getResource( path ).toString() ) );
        m = new Media( Main.class.getResource( path ).toString() );
        mp = new MediaPlayer( m );
        mp.setCycleCount( MediaPlayer.INDEFINITE );
        mp.setVolume( 0.2 );

    }

    public void start()
    {
        mp.play();
    }

}
