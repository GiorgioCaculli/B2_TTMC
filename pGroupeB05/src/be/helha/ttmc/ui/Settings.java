package be.helha.ttmc.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
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
    private List< String > languages;

    public Settings( String configFileName )
    {
        this.configFileName = configFileName;
        props = null;
        InputStream in = null;
        try
        {
            props = new Properties();
            in = new FileInputStream( new File( configFileName ) );
            props.load( new InputStreamReader( in, Charset.forName( "UTF-8" ) ) );
        }
        catch ( FileNotFoundException e )
        {
            props = new Properties();
            in = Main.class.getResourceAsStream( "res/application.properties" );
            try
            {
                Files.copy( in, Paths.get( configFileName ), StandardCopyOption.REPLACE_EXISTING );
                in = new FileInputStream( new File( configFileName ) );
                props.load( new InputStreamReader( in, Charset.forName( "UTF-8" ) ) );
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
        languages = new ArrayList<>();
        languages.add( props.getProperty( "language_english" ) );
        languages.add( props.getProperty( "language_french" ) );
        languages.add( props.getProperty( "language_italian" ) );
        languages.add( props.getProperty( "language_japanese" ) );
        setWidth( Integer.parseInt( props.getProperty( "width" ) ) );
        setHeight( Integer.parseInt( props.getProperty( "height" ) ) );
        setDeckName( props.getProperty( "deck" ) );
        NumberFormat format = NumberFormat.getInstance( Locale.getDefault() );
        try
        {
            Number number = format.parse( props.getProperty( "volume" ) );
            setVolume( number.doubleValue() );
        }
        catch ( ParseException e )
        {
            e.printStackTrace();
        }
        setTimerSeconds( Integer.parseInt( props.getProperty( "timer" ) ) );
        setMute( Boolean.parseBoolean( props.getProperty( "mute" ) ) );
        setLanguage( props.getProperty( "language" ) );
        setCountry( props.getProperty( "country" ) );
        setLocale( new Locale( getLanguage(), getCountry() ) );
        setLanguages( languages );
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
        props.setProperty( "deck", deckName );
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
        props.setProperty( "volume", String.format( "%.2f", volume ) );
    }

    public double getVolume()
    {
        return volume;
    }

    public void setMute( boolean mute )
    {
        this.mute = mute;
        props.setProperty( "mute", String.format( "%s", mute ) );
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

    public void setLanguages( List< String > languages )
    {
        this.languages = languages;
    }

    public List< String > getLanguages()
    {
        return languages;
    }
}
