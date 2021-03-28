package be.helha.ttmc.ui.gui;

import java.util.logging.Level;
import java.util.logging.Logger;

import be.helha.ttmc.model.Deck;
import be.helha.ttmc.serialization.Serialization;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class MainGui extends Application
{
    private static final Logger logger = Logger.getLogger( "MainGui Class Logger" );
    private static final short WIDTH = 750;
    private static final short HEIGHT = WIDTH + 20;

    @Override
    public void start( Stage primaryStage )
    {
        logger.log( Level.INFO, "Reading Deck" );
        Deck d = Serialization.loadDeck( "deck.json" );
        logger.log( Level.INFO, String.format( "Number of cards in the deck: %d", d.getCards().size() ) );
        MainPaneBP mp = new MainPaneBP( d );
        mp.getChildren().get( 0 ).setVisible( true );
        Scene scene = new Scene( mp );
        primaryStage.setResizable( false );
        primaryStage.setHeight( HEIGHT );
        primaryStage.setWidth( WIDTH );
        primaryStage.setScene( scene );
        primaryStage.getIcons().add( new Image( "be/helha/ttmc/assets/images/paw.png" ) );
        primaryStage.show();
        primaryStage.setTitle( "What do you know about it?" );
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
