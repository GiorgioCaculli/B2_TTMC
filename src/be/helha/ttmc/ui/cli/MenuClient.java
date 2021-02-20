package be.helha.ttmc.ui.cli;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import be.helha.ttmc.model.*;

public class MenuClient
{
    private final Scanner keyboardInput = new Scanner( System.in );
    private static final Logger logger = Logger.getLogger( "Menu Class Logger" );

    public MenuClient( String[] args )
    {
        logger.log( Level.INFO, "Initializing MenuClient" );
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
    }

    private void baseMenu()
    {
        System.out.println("Hello and welcome on  TTMC\n------------------------");
        System.out.println("\n\nFor play press 1, for manage cards press 2");
        int rep = keyboardInput.nextInt();
        while(rep<1 || rep>2) {
        	System.out.println("please press 1 for playing or 2 for manage cards");
        	rep = keyboardInput.nextInt();
        }
        //manque le switch et appel de méthode
    }
    
    private void manageMenu() {
    	System.out.println("Manage menu\n------------");
    	System.out.println("to add a new card press 1, to manage the cards press 2");
    	int rep = keyboardInput.nextInt();
    	while(rep<1 || rep>2) {
        	System.out.println("please press 1 to add a new card or 2 to manage cards");
        	rep = keyboardInput.nextInt();
        }
    	//manque le switch et appel de méthode
    }
    
}

