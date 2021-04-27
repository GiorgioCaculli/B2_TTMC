package be.helha.ttmc.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import java.util.Properties;

import be.helha.ttmc.Main;

public class Settings
{
    private String configFileName;
    private int width;
    private int height;
    private String deckName;
    private int timerSeconds;
    private double volume;
    private boolean mute;
    private String language;
    private String country;
    private Locale locale;
    private Properties props;

    public Settings( String configFileName )
    {
        this.configFileName = configFileName;
        props = null;
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
                Files.copy( in, Paths.get( configFileName ), StandardCopyOption.REPLACE_EXISTING );
                in = new FileInputStream( new File( configFileName ) );
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
        setWidth( Integer.parseInt( props.getProperty( "width" ) ) );
        setHeight( Integer.parseInt( props.getProperty( "height" ) ) );
        setDeckName( props.getProperty( "deck" ) );
        setVolume( Double.parseDouble( props.getProperty( "volume" ) ) );
        setTimerSeconds( Integer.parseInt( props.getProperty( "timer" ) ) );
        setMute( Boolean.parseBoolean( props.getProperty( "mute" ) ) );
        setLanguage( props.getProperty( "language" ) );
        setCountry( props.getProperty( "country" ) );
        setLocale( new Locale( getLanguage(), getCountry() ) );
    }
    
    public Properties getProperties()
    {
        return props;
    }
    
    public String getConfigFileName()
    {
        return configFileName;
    }

    public void setWidth( int width )
    {
        this.width = width;
        props.setProperty( "width", String.format( "%d", width ) );
    }

    public int getWidth()
    {
        return width;
    }

    public void setHeight( int height )
    {
        this.height = height;
        props.setProperty( "height", String.format( "%d", height ) );
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
        props.setProperty( "timer", String.format( "%d", timerSeconds ) );
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
    
    public void setLanguage( String language )
    {
        this.language = language;
        props.setProperty( "language", language );
    }
    
    public String getLanguage()
    {
        return language;
    }
    
    public void setCountry( String country )
    {
        this.country = country;
        props.setProperty( "country", country );
    }
    
    public String getCountry()
    {
        return country;
    }
    
    public void setLocale( Locale locale )
    {
        this.locale = locale;
    }
    
    public Locale getLocale()
    {
        return locale;
    }
}
