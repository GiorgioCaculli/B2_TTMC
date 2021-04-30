package be.helha.ttmc.ui.gui;

import be.helha.ttmc.Main;
import be.helha.ttmc.ui.GUIConstant;
import be.helha.ttmc.ui.Settings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CreditsBP extends BorderPane
{
    private Button backButton;
    private Settings s;

    public CreditsBP( Settings s )
    {
        this.s = s;
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
        
        teamBox.getChildren().add( new Label( GUIConstant.CREDITS_TEAM ) );
        teamBox.getChildren().add( teamImage );
        teamBox.setAlignment( Pos.TOP_CENTER );
        
        HBox giorgioBox = new HBox();
        Image imageGio = new Image( Main.class.getResourceAsStream( "assets/images/team.png" ) );
        ImageView giorgioImage = new ImageView( imageGio );
        giorgioImage.setFitWidth( imageGio.getWidth() / 2 );
        giorgioImage.setFitHeight( imageGio.getHeight() / 2 );
        giorgioImage.setPreserveRatio( true );
        giorgioBox.getChildren().add( new Label( "Giorgio \"Masticass\" Caculli" ) );
        giorgioBox.getChildren().add( giorgioImage );
        
        HBox guillaumeBox = new HBox();
        Image imageGui = new Image( Main.class.getResourceAsStream( "assets/images/team.png" ) );
        ImageView guillaumeImage = new ImageView( imageGui );
        guillaumeImage.setFitWidth( imageGui.getWidth() / 2 );
        guillaumeImage.setFitHeight( imageGui.getHeight() / 2 );
        guillaumeImage.setPreserveRatio( true );
        guillaumeBox.getChildren().add( guillaumeImage );
        guillaumeBox.getChildren().add( new Label( "Guillaume \"Onyxkira\" Lambert" ) );
        
        HBox tanguyBox = new HBox();
        Image imageTan = new Image( Main.class.getResourceAsStream( "assets/images/team.png" ) );
        ImageView tanguyImage = new ImageView( imageTan );
        tanguyImage.setFitWidth( imageTan.getWidth() / 2 );
        tanguyImage.setFitHeight( imageTan.getHeight() / 2 );
        tanguyImage.setPreserveRatio( true );
        tanguyBox.getChildren().add( new Label( "Tanguy \"Agonytech\" Taminiau" ) );
        tanguyBox.getChildren().add( tanguyImage );
        
        leadDevelopersBox.getChildren().add( teamBox );
        
        leadDevelopersBox.getChildren().add( new Label( "Lead Developers" ) );
        leadDevelopersBox.getChildren().add( giorgioBox );
        leadDevelopersBox.getChildren().add( guillaumeBox );
        leadDevelopersBox.getChildren().add( tanguyBox );
        leadDevelopersBox.getChildren().add( new Label( "Beta Testers" ) );
        leadDevelopersBox.getChildren().add( new Label( "Loic \"Alcoolorible\" Massy" ) );
        leadDevelopersBox.setAlignment( Pos.TOP_CENTER );
        
        creditsBox.getChildren().add( leadDevelopersBox );
        
        ScrollPane creditsPane = new ScrollPane();
        creditsPane.setContent( creditsBox );
        creditsPane.setStyle( GUIConstant.WINDOW_STYLE.replace( "-fx-background-color", "-fx-background" ) );
        creditsPane.setHbarPolicy( ScrollBarPolicy.NEVER );
        creditsPane.setFitToWidth( true );
        creditsPane.setFitToHeight( true );
        
        setCenter( creditsPane );
        setBottom( getBackButton() );
        setStyle( GUIConstant.WINDOW_STYLE );
    }

    private Button getBackButton()
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
}
