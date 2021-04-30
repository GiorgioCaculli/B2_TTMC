package be.helha.ttmc.ui.gui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import be.helha.ttmc.model.Deck;
import be.helha.ttmc.serialization.Serialization;
import be.helha.ttmc.ui.GUIConstant;
import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.util.MusicGestion;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class MainGui extends Application
{
    private static final Logger logger = Logger.getLogger( "MainGui Class Logger" );
    private Settings s;
    private MusicGestion musics;

    public void cleanup()
    {
    }

    public void startGame( Stage stage )
    {
        s = new Settings( "application.properties" );
        int WIDTH = s.getWidth();
        int HEIGHT = s.getHeight() + 20;
        logger.log( Level.INFO, "Reading Deck" );
        Deck d = Serialization.loadDeck( s.getDeckName() );
        logger.log( Level.INFO, String.format( "Number of cards in the deck: %d", d.getCards().size() ) );
        musics = new MusicGestion( s );
        MainPaneBP mp = new MainPaneBP( d, s, musics );
        mp.getChildren().get( 0 ).setVisible( true );
        Scene scene = new Scene( mp );
        stage.setResizable( false );
        stage.setHeight( HEIGHT );
        stage.setWidth( WIDTH );
        stage.setScene( scene );
        stage.getIcons().add( new Image( "be/helha/ttmc/assets/images/paw.png" ) );
        stage.show();
        stage.setTitle( GUIConstant.TITLE );
    }

    public void restart( Stage stage )
    {
        cleanup();
        startGame( stage );
    }

    @Override
    public void start( Stage primaryStage )
    {
        startGame( primaryStage );
    }

    public MainGui()
    {
    }

    @Override
    public void stop() throws Exception
    {
        super.stop();
        try
        {
            s.getProperties().store( new FileOutputStream( new File( s.getConfigFileName() ) ), "" );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        Platform.exit();
        System.exit( 0 );
    }

}
