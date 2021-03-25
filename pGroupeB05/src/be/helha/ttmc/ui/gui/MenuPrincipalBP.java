package be.helha.ttmc.ui.gui;

import be.helha.ttmc.Main;
import be.helha.ttmc.model.Deck;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuPrincipalBP extends BorderPane
{

    private Button btnJouer, btnParametres, btnQuitter, btnGerer;
    private ImageView im1, im2;
    private int larg = 175, lon = 175;
    private Deck d;

    public MenuPrincipalBP( Deck d )
    {
        this.d = d;
        VBox vb = new VBox();
        vb.setPadding( new Insets( 20 ) );
        vb.setSpacing( 25 );
        vb.getChildren().addAll( getIm2(), getBtnJouer(), getBtnParametres(), getBtnQuitter(), getBtnGerer(),
                getIm1() );

        vb.setStyle( "-fx-background-color: DAE6F3;" + "-fx-font-size: 15pt;" );
        vb.setAlignment( Pos.CENTER );
        this.setCenter( vb );
    }

    public ImageView getIm1()
    {
        if ( im1 == null )
        {
            im1 = new ImageView( "be/helha/ttmc/assets/images/paw.png" );
            im1.setFitHeight( larg );
            im1.setFitWidth( lon );
        }
        return im1;
    }

    public ImageView getIm2()
    {
        if ( im2 == null )
        {
            im2 = new ImageView( "be/helha/ttmc/assets/images/paw.png" );
            im2.setFitHeight( larg );
            im2.setFitWidth( lon );
        }
        return im2;
    }

    public Button getBtnJouer()
    {
        if ( btnJouer == null )
        {
            btnJouer = new Button( "Play !" );
            btnJouer.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
            btnJouer.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    if ( d.getCards().size() == 0 )
                    {
                        Alert alert = new Alert( AlertType.ERROR,
                                "Aucun deck de question trouve!\n Veuillez ajouter des cartes depuis l'admin panel!" );
                        alert.showAndWait();
                    }
                    else
                    {
                        MainPaneBP mpsp = ( MainPaneBP ) getParent();
                        mpsp.setVisibleNode( "MenuPlayBP" );
                    }
                }
            } );
        }
        return btnJouer;
    }

    public Button getBtnParametres()
    {
        if ( btnParametres == null )
        {
            btnParametres = new Button( "Settings" );
            btnParametres.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
        }
        return btnParametres;
    }

    public Button getBtnQuitter()
    {
        if ( btnQuitter == null )
        {
            btnQuitter = new Button( "Leave the game" );
            btnQuitter.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
            btnQuitter.setOnAction( new EventHandler< ActionEvent >()
            {
                @Override
                public void handle( ActionEvent arg0 )
                {
                    Alert alert = new Alert( AlertType.CONFIRMATION, "Leave the game ?" );
                    if ( alert.showAndWait().get() == ButtonType.OK )
                    {
                        Stage stage = ( Stage ) getScene().getWindow();
                        stage.close();
                    }
                }
            } );
        }
        return btnQuitter;
    }

    public Button getBtnGerer()
    {
        if ( btnGerer == null )
        {
            btnGerer = new Button( "Admin Panel" );
            btnGerer.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
            btnGerer.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    AlerteLogin alert = new AlerteLogin();
                    if ( alert.getResultat() )
                    {
                        Alert granted = new Alert( AlertType.INFORMATION );
                        granted.setTitle( "ACCESS GRANTED" );
                        String path;
                        granted.setContentText( "ACCESS GRANTED" );
                        path = "/be/helha/ttmc/assets/images/hackerman.gif";
                        Image img = new Image( path );
                        ImageView icon = new ImageView( img );
                        icon.setFitHeight( img.getHeight() / 3 );
                        icon.setFitWidth( img.getWidth() / 3 );
                        granted.getDialogPane().setGraphic( icon );
                        granted.setHeaderText( null );
                        DialogPane grantedPane = granted.getDialogPane();
                        grantedPane.getStylesheets().add( Main.class.getResource( "assets/stylesheets/alert_granted_stylesheet.css" ).toExternalForm() );
                        grantedPane.getStyleClass().add( "granted" );
                        granted.showAndWait();
                        MainPaneBP mpsp = ( MainPaneBP ) getParent();
                        mpsp.setVisibleNode( "MenuAdminBP" );
                    }
                    else
                    {

                        Alert denied = new Alert( AlertType.INFORMATION );
                        denied.setTitle( "ACCESS DENIED" );
                        String path;
                        denied.setContentText( "ACCESS DENIED" );
                        path = "/be/helha/ttmc/assets/images/anonymous.png";
                        Image img = new Image( path );
                        ImageView icon = new ImageView( img );
                        icon.setFitHeight( img.getHeight() / 3 );
                        icon.setFitWidth( img.getWidth() / 3 );
                        denied.getDialogPane().setGraphic( icon );
                        denied.setHeaderText( null );
                        DialogPane deniedPane = denied.getDialogPane();
                        deniedPane.getStylesheets().add( Main.class.getResource( "assets/stylesheets/alert_denied_stylesheet.css" ).toExternalForm() );
                        deniedPane.getStyleClass().add( "denied" );
                        denied.showAndWait();
                    }
                }
            } );
        }
        return btnGerer;
    }
}
