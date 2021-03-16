package be.helha.ttmc.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import be.helha.ttmc.model.Deck;

public class Serialization {
	private static GsonBuilder gb = new GsonBuilder();
    
    private static Gson gson;
	
	public static void saveGame(Deck d) {
		gb.setPrettyPrinting();
		String path= "deck.json";
		Path p= new File(path).toPath();
		File json=null;
		try {
			json=new File(path);
			json.createNewFile();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try( Writer w = new FileWriter( json ) )
        {
		    gb.setPrettyPrinting();
		    gson = gb.create();
            gson.toJson( d, w );
        }catch( Exception e )
        {
            e.printStackTrace();
        }
	}
	
	public static Deck loadDeck() {
		String path = "deck.json";
		Deck d= null;
		try {
		    gson = gb.create();
			d = gson.fromJson(new FileReader(path), Deck.class);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			d=new Deck();
			e.printStackTrace();
		}
		return d;
	}

}
