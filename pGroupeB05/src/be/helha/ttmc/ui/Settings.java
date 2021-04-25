package be.helha.ttmc.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import be.helha.ttmc.Main;

public class Settings
{
    private String title;
    private int width;
    private int height;
    private String deckName;
    private int timerSeconds;
    private double volume;
    private boolean mute;

    public Settings( String configFileName )
    {
        Properties props = null;
        InputStream in = null;
        try
        {
            props = new Properties();
            in = new FileInputStream( new File( configFileName ) );
            props.load( in );
        }
        catch ( FileNotFoundException e )
        {
            props = new Properties();
            in = Main.class.getResourceAsStream( "res/application.properties" );
            try
            {
                props.load( in );
            }
            catch ( IOException e1 )
            {
                e1.printStackTrace();
            }
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        setTitle( props.getProperty( "title" ) );
        setWidth( Integer.parseInt( props.getProperty( "width" ) ) );
        setHeight( Integer.parseInt( props.getProperty( "height" ) ) );
        setDeckName( props.getProperty( "deck" ) );
        setVolume( Double.parseDouble( props.getProperty( "volume" ) ) );
        setTimerSeconds( Integer.parseInt( props.getProperty( "timer" ) ) );
        setMute( Boolean.parseBoolean( props.getProperty( "mute" ) ) );
    }
    
    public void setTitle( String title )
    {
        this.title = title;
    }
    
    public String getTitle()
    {
        return title;
    }

    public void setWidth( int width )
    {
        this.width = width;
    }

    public int getWidth()
    {
        return width;
    }

    public void setHeight( int height )
    {
        this.height = height;
    }

    public int getHeight()
    {
        return height;
    }

    public void setDeckName( String deckName )
    {
        this.deckName = deckName;
    }

    public String getDeckName()
    {
        return deckName;
    }

    public void setTimerSeconds( int timerSeconds )
    {
        this.timerSeconds = timerSeconds;
    }

    public int getTimerSeconds()
    {
        return timerSeconds;
    }

    public void setVolume( double volume )
    {
        this.volume = volume;
    }

    public double getVolume()
    {
        return volume;
    }
    
    public void setMute( boolean mute )
    {
        this.mute = mute;
    }
    
    public boolean isMute()
    {
        return mute;
    }
}
