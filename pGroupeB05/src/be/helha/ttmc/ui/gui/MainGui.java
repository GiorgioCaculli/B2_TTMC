package be.helha.ttmc.ui.gui;

import be.helha.ttmc.model.Deck;
import be.helha.ttmc.serialization.Serialization;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class MainGui extends Application
{
    private static final short WIDTH = 750;
    private static final short HEIGHT = WIDTH;
    @Override
    public void start( Stage primaryStage )
    {
        Deck d = Serialization.loadDeck();
        MainPane mp = new MainPane( d );
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
}
