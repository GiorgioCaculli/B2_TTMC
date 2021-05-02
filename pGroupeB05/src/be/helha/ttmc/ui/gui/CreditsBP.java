package be.helha.ttmc.ui.gui;

import be.helha.ttmc.Main;
import be.helha.ttmc.ui.GUIConstant;
import be.helha.ttmc.ui.Settings;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class CreditsBP extends BorderPane
{
    private Button backButton;
    private Settings s;
    private Animation animation;

    public CreditsBP( Settings s )
    {
        this.s = s;
        
        String giorgioName = s.getNames().get( 0 );
        String guillaumeName = s.getNames().get( 1 );
        String tanguyName = s.getNames().get( 2 );
        String loicName = s.getNames().get( 3 );
        String yutakaName = s.getNames().get( 4 );
        
        String languageEnglish = s.getLanguages().get( 0 );
        String languageFrench = s.getLanguages().get( 1 );
        String languageItalian = s.getLanguages().get( 2 );
        String languageJapanese = s.getLanguages().get( 3 );
        
        String musicEva = s.getSongArtists().get( 0 );
        String musicIntouch = s.getSongArtists().get( 1 );
        String musicNihilore = s.getSongArtists().get( 2 );
        
        VBox creditsBox = new VBox();
        creditsBox.setSpacing( 10. );
        creditsBox.setPadding( new Insets( 10. ) );
        creditsBox.setAlignment( Pos.TOP_CENTER );

        VBox leadDevelopersBox = new VBox();
        leadDevelopersBox.setSpacing( 10. );
        leadDevelopersBox.setPadding( new Insets( 10. ) );

        VBox teamBox = new VBox();

        Image image = new Image( Main.class.getResourceAsStream( "assets/images/team.png" ) );
        ImageView teamImage = new ImageView( image );
        teamImage.setFitWidth( image.getWidth() / 2 );
        teamImage.setFitHeight( image.getHeight() / 2 );
        teamImage.setPreserveRatio( true );

        teamBox.getChildren().add( new Label( s.getLanguage().getString( "credits_team" ) ) );
        teamBox.getChildren().add( teamImage );
        teamBox.setAlignment( Pos.TOP_CENTER );

        HBox giorgioBox = new HBox();
        Image imageGio = new Image( Main.class.getResourceAsStream( "assets/images/giorgio.png" ) );
        ImageView giorgioImage = new ImageView( imageGio );
        giorgioImage.setFitWidth( imageGio.getWidth() / 2 );
        giorgioImage.setFitHeight( imageGio.getHeight() / 2 );
        giorgioImage.setPreserveRatio( true );
        giorgioBox.getChildren().add( new Label( giorgioName ) );
        giorgioBox.getChildren().add( giorgioImage );
        giorgioBox.setAlignment( Pos.CENTER );
        giorgioBox.setSpacing( 10. );

        HBox guillaumeBox = new HBox();
        Image imageGui = new Image( Main.class.getResourceAsStream( "assets/images/guillaume.png" ) );
        ImageView guillaumeImage = new ImageView( imageGui );
        guillaumeImage.setFitWidth( imageGui.getWidth() / 2 );
        guillaumeImage.setFitHeight( imageGui.getHeight() / 2 );
        guillaumeImage.setPreserveRatio( true );
        guillaumeBox.getChildren().add( guillaumeImage );
        guillaumeBox.getChildren().add( new Label( guillaumeName ) );
        guillaumeBox.setAlignment( Pos.CENTER );
        guillaumeBox.setSpacing( 10. );

        HBox tanguyBox = new HBox();
        Image imageTan = new Image( Main.class.getResourceAsStream( "assets/images/tanguy.png" ) );
        ImageView tanguyImage = new ImageView( imageTan );
        tanguyImage.setFitWidth( imageTan.getWidth() / 2 );
        tanguyImage.setFitHeight( imageTan.getHeight() / 2 );
        tanguyImage.setPreserveRatio( true );
        tanguyBox.getChildren().add( new Label( tanguyName ) );
        tanguyBox.getChildren().add( tanguyImage );
        tanguyBox.setAlignment( Pos.CENTER );
        tanguyBox.setSpacing( 10. );

        leadDevelopersBox.getChildren().add( teamBox );

        leadDevelopersBox.getChildren().add( new Label( "Lead Developers" ) );
        leadDevelopersBox.getChildren().add( giorgioBox );
        leadDevelopersBox.getChildren().add( guillaumeBox );
        leadDevelopersBox.getChildren().add( tanguyBox );
        leadDevelopersBox.setAlignment( Pos.TOP_CENTER );
        leadDevelopersBox.setSpacing( 10. );

        VBox translatorsBox = new VBox();

        VBox englishTranslationBox = new VBox();
        englishTranslationBox.getChildren().add( new Label( languageEnglish ) );
        englishTranslationBox.getChildren().add( new Label( giorgioName ) );
        englishTranslationBox.getChildren().add( new Label( guillaumeName ) );
        englishTranslationBox.getChildren().add( new Label( tanguyName ) );
        englishTranslationBox.setAlignment( Pos.CENTER );

        VBox frenchTranslationBox = new VBox();
        frenchTranslationBox.getChildren().add( new Label( languageFrench ) );
        frenchTranslationBox.getChildren().add( new Label( giorgioName ) );
        frenchTranslationBox.getChildren().add( new Label( guillaumeName ) );
        frenchTranslationBox.getChildren().add( new Label( tanguyName ) );
        frenchTranslationBox.getChildren().add( new Label( loicName ) );
        frenchTranslationBox.setAlignment( Pos.CENTER );

        VBox italianTranslationBox = new VBox();
        italianTranslationBox.getChildren().add( new Label( languageItalian ) );
        italianTranslationBox.getChildren().add( new Label( giorgioName ) );
        italianTranslationBox.setAlignment( Pos.CENTER );

        VBox japaneseTranslationBox = new VBox();
        japaneseTranslationBox.getChildren().add( new Label( languageJapanese ) );
        japaneseTranslationBox.getChildren().add( new Label( giorgioName ) );
        japaneseTranslationBox.getChildren().add( new Label( yutakaName ) );
        japaneseTranslationBox.setAlignment( Pos.CENTER );

        translatorsBox.getChildren().add( englishTranslationBox );
        translatorsBox.getChildren().add( frenchTranslationBox );
        translatorsBox.getChildren().add( italianTranslationBox );
        translatorsBox.getChildren().add( japaneseTranslationBox );
        translatorsBox.setAlignment( Pos.TOP_CENTER );
        translatorsBox.setSpacing( 10. );

        VBox musicBox = new VBox();

        VBox intouchBox = new VBox();
        intouchBox.getChildren().add( new Label( s.getSongArtists().get( 1 ) ) );
        intouchBox.getChildren().add( new Label( "https://soundcloud.com/intouch​" ) );
        intouchBox.getChildren()
                .add( new Label( "Promoted by Royalty Free Planet: https://royaltyfreeplanet.xn--com-7m0a/" ) );
        intouchBox.getChildren().add( new Label( "Creative Commons Attribution 3.0​" ) );
        intouchBox.getChildren().add( new Label( "http://bit.ly/RFP_CClicense" ) );
        intouchBox.setAlignment( Pos.TOP_CENTER );

        VBox evaBox = new VBox();
        evaBox.getChildren().add( new Label( s.getSongArtists().get( 0 ) ) );
        evaBox.getChildren().add( new Label( "https://joshlis.bandcamp.xn--com-7m0a/" ) );
        evaBox.getChildren()
                .add( new Label( "Promoted by Royalty Free Planet: https://royaltyfreeplanet.xn--com-7m0a/" ) );
        evaBox.getChildren().add( new Label( "Creative Commons Attribution 3.0​" ) );
        evaBox.getChildren().add( new Label( "http://bit.ly/RFP_CClicense" ) );
        evaBox.setAlignment( Pos.TOP_CENTER );

        VBox nihiloreBox = new VBox();
        nihiloreBox.getChildren().add( new Label( s.getSongArtists().get( 2 ) ) );
        nihiloreBox.getChildren().add( new Label( "http://www.nihilore.xn--com-7m0a/" ) );
        nihiloreBox.getChildren()
                .add( new Label( "Promoted by Royalty Free Planet: https://royaltyfreeplanet.xn--com-7m0a/" ) );
        nihiloreBox.getChildren().add( new Label( "Creative Commons Attribution 3.0​" ) );
        nihiloreBox.getChildren().add( new Label( "http://bit.ly/RFP_CClicense" ) );
        nihiloreBox.setAlignment( Pos.TOP_CENTER );

        musicBox.getChildren().add( intouchBox );
        musicBox.getChildren().add( evaBox );
        musicBox.getChildren().add( nihiloreBox );
        musicBox.setAlignment( Pos.TOP_CENTER );
        musicBox.setSpacing( 10. );

        VBox extraImages = new VBox();

        Image kebabGio = new Image( Main.class.getResourceAsStream( "assets/images/kebabgio.png" ) );
        ImageView kebabGioImage = new ImageView( kebabGio );
        kebabGioImage.setFitWidth( kebabGio.getWidth() / 2 );
        kebabGioImage.setFitHeight( kebabGio.getHeight() / 2 );
        kebabGioImage.setPreserveRatio( true );

        Image guixtan = new Image( Main.class.getResourceAsStream( "assets/images/guillaumextanguy.png" ) );
        ImageView guixtanImage = new ImageView( guixtan );
        guixtanImage.setFitWidth( guixtan.getWidth() / 2 );
        guixtanImage.setFitHeight( guixtan.getHeight() / 2 );
        guixtanImage.setPreserveRatio( true );

        extraImages.getChildren().add( kebabGioImage );
        extraImages.getChildren().add( guixtanImage );
        extraImages.setAlignment( Pos.TOP_CENTER );
        extraImages.setSpacing( 10. );

        creditsBox.getChildren().add( leadDevelopersBox );
        creditsBox.getChildren().add( new Label( "Beta Testers" ) );
        creditsBox.getChildren().add( new Label( s.getNames().get( 3 ) ) );
        creditsBox.getChildren().add( new Label( "Translators" ) );
        creditsBox.getChildren().add( translatorsBox );
        creditsBox.getChildren().add( new Label( "Music" ) );
        creditsBox.getChildren().add( musicBox );
        creditsBox.getChildren().add( new Label( "Extras" ) );
        creditsBox.getChildren().add( extraImages );
        creditsBox.setAlignment( Pos.TOP_CENTER );
        creditsBox.setSpacing( 10. );

        ScrollPane creditsPane = new ScrollPane();
        creditsPane.setContent( creditsBox );
        creditsPane.setStyle( GUIConstant.WINDOW_STYLE.replace( "-fx-background-color", "-fx-background" ) );
        creditsPane.setHbarPolicy( ScrollBarPolicy.NEVER );
        creditsPane.setVbarPolicy( ScrollBarPolicy.NEVER );
        creditsPane.setFitToWidth( true );
        creditsPane.setFitToHeight( true );
        animation = new Timeline(
                new KeyFrame( Duration.seconds( 60 ), new KeyValue( creditsPane.vvalueProperty(), 1 ) ) );
        animation.setCycleCount( Timeline.INDEFINITE );
        animation.play();

        setCenter( creditsPane );
        setBottom( getBackButton() );
        setStyle( GUIConstant.WINDOW_STYLE );
    }

    private Button getBackButton()
    {
        if ( backButton == null )
        {
            backButton = new Button( s.getLanguage().getString( "button_return" ) );
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
}
