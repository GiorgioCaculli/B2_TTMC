package be.helha.ttmc.ui.gui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import be.helha.ttmc.ui.GUIConstant;
import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.util.MusicGestion;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsBP extends BorderPane
{
    private Button backButton;
    private Button muteMusicButton;
    private Slider slider;
    private Settings settings;
    private MusicGestion musicGestion;
    private Label timerLabel;
    private TextField timerTextField;
    private Label languageLabel;
    private ObservableList< String > languages;
    private ComboBox< String > languageComboBox;
    private boolean languageChanged = false;
    private Label windowSizeLabel;
    private ObservableList< String > windowSizes;
    private ComboBox< String > windowSizeComboBox;
    private boolean windowSizeChanged = false;

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

        HBox timerBox = new HBox();
        timerBox.getChildren().add( getTimerLabel() );
        timerBox.getChildren().add( getTimerTextField() );

        HBox languageBox = new HBox();
        languageBox.getChildren().add( getLanguageLabel() );
        languageBox.getChildren().add( getLanguageComboBox() );
        
        HBox windowSizeBox = new HBox();
        windowSizeBox.getChildren().add( getWindowSizeLabel() );
        windowSizeBox.getChildren().add( getWindowSizeComboBox() );

        VBox settingsBox = new VBox();
        settingsBox.getChildren().add( volumeBox );
        settingsBox.getChildren().add( timerBox );
        settingsBox.getChildren().add( languageBox );
        settingsBox.getChildren().add( windowSizeBox );

        fp.getChildren().add( settingsBox );

        fp.setPadding( new Insets( 5 ) );
        fp.setAlignment( Pos.CENTER );
        setCenter( fp );
        setBottom( getBackButton() );
        
        setOnKeyPressed( new EventHandler< KeyEvent >()
        {
            @Override
            public void handle( KeyEvent keyEvent )
            {
                if ( keyEvent.getCode() == KeyCode.ESCAPE )
                {
                    getBackButton().fire();
                    keyEvent.consume();
                }
            }
        } );
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
                    try
                    {
                        settings.getProperties().store( new FileOutputStream( new File( settings.getConfigFileName() ) ), "" );
                    }
                    catch ( IOException e )
                    {
                        e.printStackTrace();
                    }
                    if ( languageChanged || windowSizeChanged )
                    {
                        musicGestion.stopMusic();
                        Stage stage = ( Stage ) getScene().getWindow();
                        stage.close();
                        Platform.runLater( new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                new MainGui().restart( new Stage() );
                            }
                        } );
                    }
                    else
                    {
                        MainPaneBP mpbp = ( MainPaneBP ) getParent().getParent();
                        mpbp.setVisibleNode( MenuPrincipalBP.class.getSimpleName() );
                    }
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
                        settings.setVolume( vol );
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

    public Label getTimerLabel()
    {
        if ( timerLabel == null )
        {
            timerLabel = new Label( "Timer duration:" );
        }
        return timerLabel;
    }

    public TextField getTimerTextField()
    {
        if ( timerTextField == null )
        {
            timerTextField = new TextField();
            timerTextField.setPrefColumnCount( 5 );
            timerTextField.setText( String.format( "%d", settings.getTimerSeconds() ) );
            timerTextField.textProperty().addListener( new ChangeListener< String >()
            {
                @Override
                public void changed( ObservableValue< ? extends String > observable, String oldValue, String newValue )
                {
                    if ( !newValue.matches( "\\d*" ) )
                    {
                        timerTextField.setText( newValue.replaceAll( "[^\\d]", "" ) );
                    }
                    else
                    {
                        try
                        {
                            int seconds = Integer.parseInt( timerTextField.getText() );
                            if ( seconds >= 10 )
                            {
                                settings.setTimerSeconds( seconds );
                            }
                            else
                            {
                                settings.setTimerSeconds( 30 );
                            }
                        }
                        catch ( NumberFormatException nfe )
                        {

                        }
                    }
                }
            } );
        }
        return timerTextField;
    }

    public Label getLanguageLabel()
    {
        if ( languageLabel == null )
        {
            languageLabel = new Label( "Language" );
        }
        return languageLabel;
    }

    public ObservableList< String > getLanguages()
    {
        if ( languages == null )
        {
            languages = FXCollections.observableArrayList( "English", "Français", "Italiano", "日本語" );
        }
        return languages;
    }

    public ComboBox< String > getLanguageComboBox()
    {
        if ( languageComboBox == null )
        {
            languageComboBox = new ComboBox<>( getLanguages() );
            switch ( settings.getLanguage() )
            {
                case "en":
                    languageComboBox.setValue( getLanguages().get( 0 ) );
                    break;
                case "fr":
                    languageComboBox.setValue( getLanguages().get( 1 ) );
                    break;
                case "it":
                    languageComboBox.setValue( getLanguages().get( 2 ) );
                    break;
                case "ja":
                    languageComboBox.setValue( getLanguages().get( 3 ) );
                    break;
                default:
                    break;
            }
            languageComboBox.valueProperty().addListener( new ChangeListener< String >()
            {
                @Override
                public void changed( ObservableValue< ? extends String > observable, String oldValue, String newValue )
                {
                    switch ( newValue )
                    {
                        case "English":
                            settings.setLanguage( "en" );
                            settings.setCountry( "UK" );
                            languageChanged = true;
                            break;
                        case "Français":
                            settings.setLanguage( "fr" );
                            settings.setCountry( "BE" );
                            languageChanged = true;
                            break;
                        case "Italiano":
                            settings.setLanguage( "it" );
                            settings.setCountry( "IT" );
                            languageChanged = true;
                            break;
                        case "日本語":
                            settings.setLanguage( "ja" );
                            settings.setCountry( "JP" );
                            languageChanged = true;
                            break;
                        default:
                            break;
                    }
                }
            } );
        }
        return languageComboBox;
    }
    
    public Label getWindowSizeLabel()
    {
        if( windowSizeLabel == null )
        {
            windowSizeLabel = new Label( "Window size:" );
        }
        return windowSizeLabel;
    }
    
    public ObservableList< String > getWindowSizes()
    {
        if( windowSizes == null )
        {
            windowSizes = FXCollections.observableArrayList( "1200x800", "1024x768", "800x600" );
        }
        return windowSizes;
    }
    
    public ComboBox< String > getWindowSizeComboBox()
    {
        if( windowSizeComboBox == null )
        {
            windowSizeComboBox = new ComboBox< String >( getWindowSizes() );
            switch ( String.format( "%dx%d", settings.getWidth(), settings.getHeight() ) )
            {
                case "1200x800":
                    windowSizeComboBox.setValue( getWindowSizes().get( 0 ) );
                    break;
                case "1024x768":
                    windowSizeComboBox.setValue( getWindowSizes().get( 1 ) );
                    break;
                case "800x600":
                    windowSizeComboBox.setValue( getWindowSizes().get( 2 ) );
                    break;
                default:
                    break;
            }
            windowSizeComboBox.valueProperty().addListener( new ChangeListener< String >()
            {
                @Override
                public void changed( ObservableValue< ? extends String > observable, String oldValue, String newValue )
                {
                    int width = Integer.parseInt( newValue.split( "x" )[0] );
                    int height = Integer.parseInt( newValue.split( "x" )[1] );
                    Stage stage = ( Stage ) getScene().getWindow();
                    switch ( newValue )
                    {
                        case "1200x800":
                            settings.setWidth( width );
                            settings.setHeight( height );
                            stage.setWidth( settings.getWidth() );
                            stage.setHeight( settings.getHeight() );
                            windowSizeChanged = true;
                            break;
                        case "1024x768":
                            settings.setWidth( width );
                            settings.setHeight( height );
                            stage.setWidth( settings.getWidth() );
                            stage.setHeight( settings.getHeight() );
                            windowSizeChanged = true;
                            break;
                        case "800x600":
                            settings.setWidth( width );
                            settings.setHeight( height );
                            stage.setWidth( settings.getWidth() );
                            stage.setHeight( settings.getHeight() );
                            windowSizeChanged = true;
                            break;
                        default:
                            break;
                    }
                }
            } );
            
        }
        return windowSizeComboBox;
    }
}
