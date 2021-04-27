package be.helha.ttmc.ui.gui;

import be.helha.ttmc.ui.GUIConstant;
import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.util.MusicGestion;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingsBP extends BorderPane
{
    private Button backButton;
    private Button muteMusicButton;
    private Slider slider;
    private Settings settings;
    private MusicGestion musicGestion;

    public SettingsBP( Settings s, MusicGestion musicGestion )
    {
        this.settings = s;
        this.musicGestion = musicGestion;
        FlowPane fp = new FlowPane();
        fp.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
        fp.setPrefSize( s.getWidth(), s.getHeight() );
        fp.setVgap( 8 );
        fp.setHgap( 4 );

        HBox volumeBox = new HBox();
        volumeBox.getChildren().add( getSlider() );
        volumeBox.getChildren().add( getMuteMusicButton() );
        volumeBox.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );

        VBox settingsBox = new VBox();
        settingsBox.getChildren().add( volumeBox );

        fp.getChildren().add( settingsBox );

        fp.setPadding( new Insets( 5 ) );
        fp.setAlignment( Pos.CENTER );
        setCenter( fp );
        setBottom( getBackButton() );
    }

    public Button getBackButton()
    {
        if ( backButton == null )
        {
            backButton = new Button( GUIConstant.BUTTON_RETURN );
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
            slider = new Slider();
            slider.setMin( 0 );
            slider.setMax( 100 );
            slider.setValue( settings.getVolume() * 100 );
            slider.valueProperty()
                    .addListener( ( ObservableValue< ? extends Number > ov, Number old_val, Number new_val ) ->
                    {
                        double vol = new_val.doubleValue() / 100.;
                        musicGestion.gererVolume( vol );
                        musicGestion.setVol( vol );
                        settings.setVolume(vol);
                    } );
            slider.setShowTickMarks( true );
            slider.setShowTickLabels( true );
            slider.setBlockIncrement( 10 );
            slider.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
            slider.setPrefWidth( settings.getWidth() / 1.2 );
        }
        return slider;
    }

    public Button getMuteMusicButton()
    {
        if ( muteMusicButton == null )
        {
            muteMusicButton = new Button( "Mute" );
            muteMusicButton.setOnAction( new EventHandler< ActionEvent >()
            {
                @Override
                public void handle( ActionEvent arg0 )
                {
                    if ( musicGestion.getMediaView().getMediaPlayer().isMute() )
                    {

                        musicGestion.getMediaView().getMediaPlayer().setMute( false );
                        settings.setMute( false );
                    }
                    else
                    {
                        musicGestion.getMediaView().getMediaPlayer().setMute( true );
                        settings.setMute( true );
                    }
                }
            } );
        }
        return muteMusicButton;
    }
}
