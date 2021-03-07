package be.helha.ttmc.ui;

import java.io.File;

import java.nio.file.Path;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import be.helha.ttmc.model.*;
import be.helha.ttmc.serialization.Serialization;
import be.helha.ttmc.ui.cli.MenuClient;
import be.helha.ttmc.ui.gui.MainGui;
import javafx.application.Application;

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
            switch( args[ i ] )
            {
                case "-d":
                    logger.setLevel( Level.INFO );
                    break;
                case "-nogui":
                    new MenuClient( args );
                    break;
                default:
                    logger.log( Level.INFO, String.format( "Argument: %s - NOT RECOGNIZED!%s", args[ i ],
                            System.getProperty( "line.separator" ) ) );
                    break;
            }
        }

        String fileName = "/be/helha/ttmc/assets/files/deck.json";
        Path path = new File( fileName ).toPath();

        GsonBuilder gb = new GsonBuilder();
        gb.setPrettyPrinting();
        Gson gson = gb.create();

        logger.log( Level.INFO, "Reading JSON" );

 
        Deck d=Serialization.loadDeck();
        
        
        		


        MainGui m = new MainGui();
        m.setDeck( d );
        Application.launch( m.getClass(), args );

        String jsonDeck = gson.toJson( d );
        logger.log( Level.INFO, jsonDeck );

    }
}
