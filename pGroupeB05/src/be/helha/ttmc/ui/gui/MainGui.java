package be.helha.ttmc.ui.gui;

import java.util.logging.Level;
import java.util.logging.Logger;

import be.helha.ttmc.model.Deck;
import be.helha.ttmc.serialization.Serialization;
import be.helha.ttmc.ui.GUIConstant;
import be.helha.ttmc.ui.Settings;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class MainGui extends Application
{
    private static final Logger logger = Logger.getLogger( "MainGui Class Logger" );

    @Override
    public void start( Stage primaryStage )
    {
        Settings s = new Settings( "application.properties" );
        int WIDTH = s.getWidth();
        int HEIGHT = s.getHeight() + 20;
        logger.log( Level.INFO, "Reading Deck" );
        Deck d = Serialization.loadDeck( s.getDeckName() );
        logger.log( Level.INFO, String.format( "Number of cards in the deck: %d", d.getCards().size() ) );
        MainPaneBP mp = new MainPaneBP( d, s );
        mp.getChildren().get( 0 ).setVisible( true );
        Scene scene = new Scene( mp );
        primaryStage.setResizable( false );
        primaryStage.setHeight( HEIGHT );
        primaryStage.setWidth( WIDTH );
        primaryStage.setScene( scene );
        primaryStage.getIcons().add( new Image( "be/helha/ttmc/assets/images/paw.png" ) );
        primaryStage.show();
        primaryStage.setTitle( GUIConstant.TITLE );
    }

    public MainGui()
    {
    }

    @Override
    public void stop() throws Exception
    {
        super.stop();
        Platform.exit();
        System.exit( 0 );
    }
    
    
}
