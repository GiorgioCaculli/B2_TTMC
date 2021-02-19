package be.helha.ttmc.ui.cli;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import be.helha.ttmc.core.*;

public class Menu
{
    private final Scanner keyboardInput = new Scanner( System.in );
    private static final Logger logger = Logger.getLogger( "Menu Class Logger" );
    
    public Menu( String[] args )
    {
	logger.log( Level.INFO, "Initializing Menu" );
	logger.setLevel( Level.WARNING );
	for( String arg : args )
	    {
		switch( arg )
		    {
		    case "-d":
			logger.setLevel( Level.INFO );
			break;
		    }
	    }

	GsonBuilder gb = new GsonBuilder();
	gb.setPrettyPrinting();
	Gson gson = gb.create();

	Question q1 = new Question( "Giorgio Caculli", Theme.INFORMATICS, "Acronyms", "What does RAM stand for?", "Random Access Memory" );
	Question q2 = new Question( "Giorgio Caculli", Theme.INFORMATICS, "Acronyms", "What does JAR stand for?", "Java ARchive" );
	Question q3 = new Question( "Giorgio Caculli", Theme.INFORMATICS, "Acronyms", "What does WWW stand for?", "World Wide Web" );
	Question q4 = new Question( "Giorgio Caculli", Theme.INFORMATICS, "Acronyms", "What does CPU stand for?", "Central Processing Unit" );

	BasicCard bc1 = new BasicCard( "Giorgio Caculli", Theme.INFORMATICS, "Acronyms" );

	bc1.add( q1 );
	bc1.add( q2 );
	bc1.add( q3 );
	bc1.add( q4 );
	
	Deck d = new Deck();

	d.add( bc1 );
	
	String jsonDeck = gson.toJson(d);
	logger.log( Level.INFO, jsonDeck );
    }

    private void baseMenu()
    {
	String input = keyboardInput.next();
    }
}
