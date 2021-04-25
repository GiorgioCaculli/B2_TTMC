package be.helha.ttmc.serialization;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import be.helha.ttmc.Main;
import be.helha.ttmc.model.Deck;

public class Serialization
{
    private static GsonBuilder gb = new GsonBuilder();

    private static Gson gson;

    public static void saveGame( Deck d )
    {
        gb.setPrettyPrinting();
        String path = "deck.json";
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

        try ( Writer w = new FileWriter( json ) )
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
            if( path.contains( "assets/decks" ) )
            {
                InputStream in = Main.class.getResourceAsStream( path );
                d = gson.fromJson( new BufferedReader( new InputStreamReader( in ) ), Deck.class );
            }
            else
            {
                d = gson.fromJson( new FileReader( path ), Deck.class );
            }
        }
        catch ( JsonSyntaxException | JsonIOException | FileNotFoundException e )
        {
            InputStream in = Main.class.getResourceAsStream( "assets/decks/deck.json" );
            d = gson.fromJson( new BufferedReader( new InputStreamReader( in ) ), Deck.class );
            saveGame( d );
            try
            {
                d = gson.fromJson( new FileReader( path ), Deck.class );
            }
            catch ( JsonSyntaxException | JsonIOException | FileNotFoundException e1 )
            {
                e1.printStackTrace();
            }
        }
        return d;
    }

}
