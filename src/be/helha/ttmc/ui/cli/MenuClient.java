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
        String input = keyboardInput.next();
    }
}
