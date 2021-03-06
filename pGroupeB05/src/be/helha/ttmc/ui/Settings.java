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
import be.helha.ttmc.ui.internationalization.English;
import be.helha.ttmc.ui.internationalization.French;
import be.helha.ttmc.ui.internationalization.Italian;
import be.helha.ttmc.ui.internationalization.Japanese;
import be.helha.ttmc.ui.internationalization.Language;

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
    private List< String > names;
    private List< String > songArtists;

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
        try
        {
            initProperties();
        }
        catch ( Exception e )
        {
            File configFile = new File( configFileName );
            if ( configFile.exists() )
            {
                configFile.delete();
            }
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
            try
            {
                initProperties();
            }
            catch ( Exception e1 )
            {
                e1.printStackTrace();
            }
        }
    }

    private void initProperties() throws Exception
    {
        languages = new ArrayList<>();
        languages.add( props.getProperty( "language_english" ) );
        languages.add( props.getProperty( "language_french" ) );
        languages.add( props.getProperty( "language_italian" ) );
        languages.add( props.getProperty( "language_japanese" ) );
        for ( String l : languages )
        {
            if ( l == null )
            {
                throw new Exception();
            }
        }
        names = new ArrayList<>();
        names.add( props.getProperty( "name_giorgio_caculli" ) );
        names.add( props.getProperty( "name_guillaume_lambert" ) );
        names.add( props.getProperty( "name_tanguy_taminiau" ) );
        names.add( props.getProperty( "name_loic_massy" ) );
        names.add( props.getProperty( "name_yutaka_kawaguchi" ) );
        for ( String n : names )
        {
            if ( n == null )
            {
                throw new Exception( "Name is null" );
            }
        }
        songArtists = new ArrayList<>();
        songArtists.add( props.getProperty( "credits_music_eva" ) );
        songArtists.add( props.getProperty( "credits_music_intouch" ) );
        songArtists.add( props.getProperty( "credits_music_nihilore" ) );
        for ( String s : songArtists )
        {
            if ( s == null )
            {
                throw new Exception( "Artist is null" );
            }
        }
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
        if ( props.getProperty( "language" ) == null )
        {
            throw new Exception( "Language not found" );
        }
        setLanguage( props.getProperty( "language" ) );
        if ( props.getProperty( "country" ) == null )
        {
            throw new Exception( "Country not found" );
        }
        setCountry( props.getProperty( "country" ) );
        setLocale( getLanguage().getLocale() );
        setLanguages( languages );
        setNames( names );
        setSongArtists( songArtists );
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

    public Language getLanguage()
    {
        if ( language.equalsIgnoreCase( "en" ) )
        {
            return new English();
        }
        else if ( language.equalsIgnoreCase( "fr" ) )
        {
            return new French();
        }
        else if ( language.equalsIgnoreCase( "it" ) )
        {
            return new Italian();
        }
        else if ( language.equalsIgnoreCase( "ja" ) )
        {
            return new Japanese();
        }
        else
        {
            return new English();
        }
    }

    public void setCountry( String country )
    {
        this.country = country;
        props.setProperty( "country", country );
    }

    public String getCountry()
    {
        return getLanguage().getCountry();
    }

    public void setLocale( Locale locale )
    {
        this.locale = locale;
    }

    public Locale getLocale()
    {
        return getLanguage().getLocale();
    }

    public void setLanguages( List< String > languages )
    {
        this.languages = languages;
    }

    public List< String > getLanguages()
    {
        return languages;
    }

    public void setNames( List< String > names )
    {
        this.names = names;
    }

    public List< String > getNames()
    {
        return names;
    }

    public void setSongArtists( List< String > songArtists )
    {
        this.songArtists = songArtists;
    }

    public List< String > getSongArtists()
    {
        return songArtists;
    }
}
