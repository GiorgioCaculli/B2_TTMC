package be.helha.ttmc.ui;

import java.util.logging.Level;
import java.util.logging.Logger;
import be.helha.ttmc.ui.gui.MainGui;
import javafx.application.Application;

public class Menu
{
    private static final Logger logger = Logger.getLogger( "Menu Class Logger" );

    public Menu( String[] args )
    {
        logger.log( Level.INFO, "Initializing Menu" );

        logger.log( Level.INFO, "Opening GUI" );

        Application.launch( MainGui.class, args );
    }
}
