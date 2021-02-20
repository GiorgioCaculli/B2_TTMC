package be.helha.ttmc.ui;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import be.helha.ttmc.core.*;
import be.helha.ttmc.ui.cli.MenuClient;

public class Menu
{
    private final Scanner keyboardInput = new Scanner( System.in );
    private static final Logger logger = Logger.getLogger( "Menu Class Logger" );
    
    public Menu( String[] args )
    {
	logger.log( Level.INFO, "Initializing Menu" );
	logger.setLevel( Level.WARNING );
	for( int i = 0; i < args.length; i++ )
	    {
		switch( args[i] )
		    {
		    case "-d":
			logger.setLevel( Level.INFO );
			break;
		    case "-nogui":
			new MenuClient( args );
			break;
		    default:
			logger.log( Level.INFO, String.format( "Argument: %s - NOT RECOGNIZED!%s", args[i], System.getProperty( "line.separator" ) ) );
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
	Question q5 = q1.clone();
	Question q6 = new Question( "Giorgio Cacull", Theme.INFORMATICS, "Acronyms", "What does CPU stand for?", "Central Processing Unit" );
	Question q7 = new Question( "Giorgio Caculli", Theme.IMPROBABLE, "Acronyms", "What does CPU stand for?", "Central Processing Unit" );
	Question q8 = new Question( "Giorgio Caculli", Theme.INFORMATICS, "Acronym", "What does CPU stand for?", "Central Processing Unit" );

	BasicCard bc1 = new BasicCard( "Giorgio Caculli", Theme.INFORMATICS, "Acronyms" );

	bc1.add( q1 );
	bc1.add( q5 );
	bc1.add( q6 );
	bc1.add( q2 );
	bc1.add( q3 );
	bc1.add( q7 );
	bc1.add( q4 );
	bc1.add( q8 );
	
	Deck d = new Deck();

	d.add( bc1 );
	
	String jsonDeck = gson.toJson(d);
	logger.log( Level.INFO, jsonDeck );
    }
}
