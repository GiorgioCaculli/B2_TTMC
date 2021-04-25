package be.helha.ttmc.ui.gui.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import be.helha.ttmc.Main;
import be.helha.ttmc.ui.Settings;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MusicGestion
{
    private static final Logger logger = Logger.getLogger( "MainGui Class Logger" );
    private MediaView mv;
    private Media m;
    private int id = 0;
    private double vol;
    private Settings s;

    public MusicGestion( Settings s )
    {
        this.s = s;
        List< String > path = new ArrayList< String >();
        path.add( "assets/musics/EVAmusic.wav" );
        path.add( "assets/musics/CreativeDestruction.wav" );
        path.add( "assets/musics/Intouch_IntoTheWild.wav" );
        Collections.shuffle( path );
        logger.log( Level.INFO,
                String.format( "Reading music file: %s", Main.class.getResource( path.get( id ) ).toString() ) );
        setVol( s.getVolume() );

        gererMusic( path );

        startMusic();

        new Thread( new Runnable()
        {

            @Override
            public void run()
            {
                try
                {
                    gererThread( path ).run();
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }

            }
        } ).start();
    }
    
    public double getVol()
    {
        return vol;
    }
    
    public void setVol( double vol )
    {
        this.vol = vol;
    }

    public MediaView getMediaView()
    {
        return mv;
    }

    public Media getMedia()
    {
        return m;
    }

    public void startMusic()
    {
        mv.getMediaPlayer().play();
        gererVolume( getVol() );
    }

    public void gererVolume( double newVolume )
    {
        s.setVolume( newVolume );
        setVol( newVolume );
        mv.getMediaPlayer().setVolume( getVol() );
    }

    public void gererMusic( List< String > path )
    {
        m = new Media( Main.class.getResource( path.get( id ) ).toString() );
        MediaPlayer mp = new MediaPlayer( m );
        if( s.isMute() )
        {
            mp.setMute( true );
        }
        mv = new MediaView( mp );
    }

    public void stopMusic()
    {
        mv.getMediaPlayer().stop();
    }

    public Thread gererThread( List< String > path )
    {
        Thread tmp = new Thread( new Runnable()
        {

            @Override
            public void run()
            {
                while ( true )
                {

                    mv.getMediaPlayer().setOnEndOfMedia( new Runnable()
                    {

                        @Override
                        public void run()
                        {
                            if ( id < path.size() - 1 )
                            {
                                id++;
                            }
                            else
                            {
                                id = 0;
                            }
                            gererMusic( path );
                            logger.log( Level.INFO, String.format( "Reading music file: %s",
                                    Main.class.getResource( path.get( id ) ).toString() ) );
                            startMusic();

                        }
                    } );
                }

            }
        } );
        return tmp;
    }
}
