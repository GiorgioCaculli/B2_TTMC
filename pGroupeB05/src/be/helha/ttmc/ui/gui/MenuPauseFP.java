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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class MenuPauseFP extends BorderPane
{
    private Button btnBack;
    private Button btnResume;
    private Slider slide;
    private Label lblVolume;
    private Label lblPause;

    private Settings s;
    private MusicGestion mus;
    private CheckBox mute;

    private CheckBox getMute()
    {
        if ( mute == null )
        {
            mute = new CheckBox( "Mute" );
            mute.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    if ( !mute.isSelected() )
                    {
                        mus.getMediaView().getMediaPlayer().setMute( false );
                        s.setMute( false );
                    }
                    else
                    {
                        mus.getMediaView().getMediaPlayer().setMute( true );
                        s.setMute( true );
                    }

                }
            } );
        }
        return mute;
    }

    public Label getLblPause()
    {
        if ( lblPause == null )
        {
            lblPause = new Label( "Pause" );
            Font lbl = Font.font( "Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30 );
            lblPause.setFont( lbl );
        }
        return lblPause;
    }

    public Button getBtnBack()
    {
        if ( btnBack == null )
        {
            btnBack = new Button( GUIConstant.BUTTON_BACK_TO_LOBBY );
            btnBack.setMaxSize( 200, 200 );
        }
        return btnBack;
    }

    public Button getBtnResume()
    {
        if ( btnResume == null )
        {
            btnResume = new Button( GUIConstant.BUTTON_RESUME );
            btnResume.setMaxSize( 200, 200 );
            btnResume.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    setVisible( false );
                }
            } );
        }
        return btnResume;
    }

    public Slider getSlide()
    {
        if ( slide == null )
        {
            slide = new Slider( 0, 100, s.getVolume() * 100 );
            slide.setMaxSize( 200, 200 );
            slide.setShowTickLabels( true );
            slide.setShowTickMarks( true );
            slide.valueProperty()
                    .addListener( ( ObservableValue< ? extends Number > ov, Number old_val, Number new_val ) ->
                    {
                        double vol = new_val.doubleValue() / 100.;

                        mus.gererVolume( vol );
                        s.setVolume( vol );

                    } );
            ;
        }
        return slide;
    }

    public Label getLblVolume()
    {
        if ( lblVolume == null )
            lblVolume = new Label( "Volume :" );
        return lblVolume;
    }

    public MenuPauseFP( Settings s, MusicGestion musi )
    {
        this.s = s;
        this.mus = musi;

        Stop[] etapes =
        { new Stop( 0, Color.ROYALBLUE ), new Stop( 0.5, Color.AQUA ), new Stop( 1, Color.CYAN ) };

        LinearGradient gradiant = new LinearGradient( 0, 1, 0, 0, true, CycleMethod.NO_CYCLE, etapes );

        Rectangle rect = new Rectangle( 250., 250. );
        rect.setFill( Color.TRANSPARENT );
        rect.setStroke( gradiant );
        rect.setStrokeWidth( 5 );

        VBox vb = new VBox();

        vb.getChildren().addAll( getLblPause(), getBtnResume(), getBtnBack(), getLblVolume(), getSlide(), getMute() );
        vb.setAlignment( Pos.CENTER );
        vb.setSpacing( 5. );
        vb.setPadding( new Insets( 100. ) );
        //  vb.setBackground(new Background(new BackgroundFill(Color.BLACK, null, getInsets())));
        //  setBackground(new Background(new BackgroundFill(Color.BLACK, null, getInsets())));
        StackPane fp = new StackPane();
        fp.getChildren().addAll( rect, vb );

        setCenter( fp );

        //  setOpacity(0.5);
        setWidth( 450 );
        setHeight( 750 );
    }

}
