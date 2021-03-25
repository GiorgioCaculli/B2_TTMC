package be.helha.ttmc.ui.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import be.helha.ttmc.model.Deck;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class MainPaneBP extends BorderPane
{
    private MusicGestion musics;
    private Slider slider;
    private StackPane stackpane;

    public MainPaneBP( Deck d )
    {
        

        getStackPane().getChildren().add( new MenuPrincipalBP( d ) );
        getStackPane().getChildren().add( new MenuPlayBP( d ) );
        getStackPane().getChildren().add( new MenuAdminBP( d ) );
        
        setVisibleNode( MenuPrincipalBP.class.getSimpleName() );
   //     setTop( getSlider() );
        setCenter( getStackPane() );
        List< String > path = new ArrayList< String >();
        path.add( "assets/musics/EVAmusic.wav" );
        path.add( "assets/musics/CreativeDestruction.wav" );
        path.add( "assets/musics/Intouch_IntoTheWild.wav" );
        Collections.shuffle( path );

        new Thread( new Runnable()
        {

            @Override
            public void run()
            {
                try
                {
                    musics = new MusicGestion( path );
                    musics.gererThread( path ).run();

                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }

            }
        } ).start();
        
    }

    public void setVisibleNode( String paneName )
    {

        for ( Node n : getStackPane().getChildren() )
        {
            if ( n.getClass().getSimpleName().equals( paneName ) )
            {
                n.setVisible( true );
            }
            else
            {
                n.setVisible( false );
            }
        }
    }

    public Slider getSlider()
    {
        if ( slider == null )
        {
            slider = new Slider( 0, 100, 10 );
            slider.valueProperty()
                    .addListener( ( ObservableValue< ? extends Number > ov, Number old_val, Number new_val ) ->
                    {
                        musics.gererVolume( new_val.doubleValue() / 100. );
                    } );
            slider.setShowTickMarks( true );
            slider.setShowTickLabels( true );
        }
        return slider;

    }

    public StackPane getStackPane()
    {
        if ( stackpane == null )
        {
            stackpane = new StackPane();
        }
        return stackpane;
    }

}
