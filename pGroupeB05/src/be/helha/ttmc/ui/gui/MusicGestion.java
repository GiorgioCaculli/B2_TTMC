package be.helha.ttmc.ui.gui;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import be.helha.ttmc.Main;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class MusicGestion
{
    private static final Logger logger = Logger.getLogger( "MainGui Class Logger" );
    private MediaView mv;
    private Media m;
    private int id=0;

    public MusicGestion( List<String> path )
    {
    	
    	
        logger.log( Level.INFO, String.format( "Reading music file: %s", Main.class.getResource( path.get(id) ).toString() ) );
       
        gererMusic(path);
    	
		start();
        
        new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(id!=4) {
					
					mv.getMediaPlayer().setOnEndOfMedia(new Runnable() {
				
						@Override
						public void run() {
							if(id!=2)
								id++;
							else
								id=0;
							gererMusic(path);					    	
							start();
							
	        			
					
						}
					});
				}
				
			}
		}).start();
        	
   
        	
        
       
    }

    public void start()
    {
        mv.getMediaPlayer().play();
       
        
      
    }

    public void gererMusic(List<String> path) {
    	 m = new Media( Main.class.getResource( path.get(id) ).toString() );
         MediaPlayer mp = new MediaPlayer( m );
         mv = new MediaView(mp);
         mv.getMediaPlayer().setVolume( 0.2 );
    }
}
