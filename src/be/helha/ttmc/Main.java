package be.helha.ttmc;

import java.util.logging.Level;
import java.util.logging.Logger;

import be.helha.ttmc.ui.cli.Menu;

public class Main
{
    private static final Logger logger = Logger.getLogger( "Main Class Logger" );

    public static void main( String args[] )
    {
	System.out.println( "TTMC by Giorgio Caculli LA196672, Guillaume Lambert LA198116, Tanguy Taminiau LA199566" );
	logger.log( Level.INFO, "Launching main()" );
	logger.setLevel( Level.WARNING );
	for( String arg : args )
	    {
		switch( arg )
		    {
		    case "-d":
			logger.setLevel( Level.INFO );
			break;
		    case "-nogui":

			logger.log( Level.INFO, "Launching Menu()" );
			new Menu( args );
			break;
		    default:
			logger.log( Level.INFO, String.format( "Unrecognized argument: %s%s",
							       arg, System.getProperty( "line.separator" ) ) );
			break;
		    }
	    }
    }
}
