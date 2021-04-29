package be.helha.ttmc.serialization;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import be.helha.ttmc.Main;
import be.helha.ttmc.model.Deck;
import be.helha.ttmc.ui.Settings;

public class Serialization
{
    private static GsonBuilder gb = new GsonBuilder();

    private static Gson gson;
    private static Settings s = new Settings( "application.properties" );

    public static void saveGame( Deck d )
    {
        gb.setPrettyPrinting();
        String path = s.getDeckName();
        File json = null;
        try
        {
            json = new File( path );
            json.createNewFile();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }

        try ( /*Writer w = new FileWriter( json )*/
                OutputStreamWriter w = new OutputStreamWriter( new FileOutputStream( json ),
                        Charset.forName( "UTF-8" ).newEncoder() ) )
        {
            gb.setPrettyPrinting();
            gson = gb.create();
            gson.toJson( d, w );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    public static Deck loadDeck( String path )
    {
        Deck d = null;
        gson = gb.create();
        try
        {
            if ( path.contains( "assets/decks" ) )
            {
                InputStream in = Main.class.getResourceAsStream( path );
                d = gson.fromJson( new BufferedReader( new InputStreamReader( in, "UTF-8" ) ), Deck.class );
            }
            else
            {
                d = gson.fromJson( new FileReader( path, Charset.forName( "UTF-8" ) ), Deck.class );
            }
        }
        catch ( JsonSyntaxException | JsonIOException | IOException e )
        {
            InputStream in = Main.class.getResourceAsStream( String.format( "assets/decks/%s", s.getDeckName() ) );
            try
            {
                d = gson.fromJson( new BufferedReader( new InputStreamReader( in, "UTF-8" ) ), Deck.class );
                saveGame( d );
                d = gson.fromJson( new FileReader( path, Charset.forName( "UTF-8" ) ), Deck.class );
            }
            catch ( JsonSyntaxException | JsonIOException | IOException e2 )
            {
                e2.printStackTrace();
            }
        }
        return d;
    }

}
