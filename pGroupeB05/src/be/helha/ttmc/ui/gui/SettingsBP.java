package be.helha.ttmc.ui.gui;

import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.util.MusicGestion;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class SettingsBP extends BorderPane
{
    private Button backButton;
    private Slider slider;
    private Settings settings;
    private MusicGestion musicGestion;
    
    public SettingsBP( Settings s, MusicGestion musicGestion )
    {
        this.settings = s;
        this.musicGestion = musicGestion;
        FlowPane fp = new FlowPane();
        
        fp.getChildren().add( getSlider() );
        
        setCenter( fp );
        setBottom( getBackButton() );
    }
    
    public Button getBackButton()
    {
        if( backButton == null )
        {
            backButton = new Button( "Return" );
            backButton.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
            backButton.setOnAction( new EventHandler< ActionEvent >()
            {
                @Override
                public void handle( ActionEvent arg0 )
                {
                    MainPaneBP mpbp = ( MainPaneBP ) getParent().getParent();
                    mpbp.setVisibleNode( MenuPrincipalBP.class.getSimpleName() );
                }
            } );
        }
        return backButton;
    }

    public Slider getSlider()
    {
        if ( slider == null )
        {
            slider = new Slider( 0, 100, 10 );
            slider.valueProperty()
                    .addListener( ( ObservableValue< ? extends Number > ov, Number old_val, Number new_val ) ->
                    {
                        musicGestion.gererVolume( new_val.doubleValue() / 100. );
                    } );
            slider.setShowTickMarks( true );
            slider.setShowTickLabels( true );
        }
        return slider;

    }
}
