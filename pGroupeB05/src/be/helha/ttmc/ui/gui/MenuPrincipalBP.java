package be.helha.ttmc.ui.gui;

import java.util.ArrayList;
import java.util.List;

import be.helha.ttmc.Main;
import be.helha.ttmc.model.Deck;
import be.helha.ttmc.ui.GUIConstant;
import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.admin.AlerteLogin;
import be.helha.ttmc.ui.gui.admin.MenuAdminBP;
import be.helha.ttmc.ui.gui.play.MenuPlayBP;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MenuPrincipalBP extends BorderPane
{

    private Button btnJouer, btnParametres, btnQuitter, btnGerer, btnCredits;
    private List< Button > buttons;
    private ImageView im1, im2;
    private int larg = 175, lon = 175;
    private Deck d;
    private Settings s;

    private Stop[] etapes =
    { new Stop( 0, Color.BLUEVIOLET ), new Stop( 0.3, Color.ROYALBLUE ), new Stop( 0.7, Color.LIGHTSTEELBLUE ) };
    private LinearGradient gradiant = new LinearGradient( 0, 1, 0, 0, true, CycleMethod.NO_CYCLE, etapes );
    private Font txt = Font.font( "Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 75 );
    private String buttonStyle = "-fx-background-color: plum;" + "-fx-border-radius: 40 40 40 40;"
            + "-fx-background-radius: 40 40 40 40;";
    private Effect buttonEffect = new DropShadow( 25, 13, 13, Color.DARKSLATEGREY );

    public MenuPrincipalBP( Deck d, Settings s )
    {
        this.d = d;
        this.s = s;
        VBox vb = new VBox();
        buttons = new ArrayList<>();
        buttons.add( getBtnJouer() );
        buttons.add( getBtnParametres() );
        buttons.add( getBtnQuitter() );
        buttons.add( getBtnGerer() );
        buttons.add( getBtnCredits() );

        for ( Button b : buttons )
        {
            b.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
            b.setMaxWidth( s.getWidth() - 55. );
            b.setMinHeight( s.getHeight() / ( buttons.size() + 1 ) );
            b.setEffect( buttonEffect );
            b.setTextFill( gradiant );
            b.setStyle( buttonStyle );
            b.setFont( txt );
        }

        vb.setPadding( new Insets( 0., s.getWidth() / 3., 0., 20. ) );
        vb.setSpacing( 20. );
        vb.getChildren().addAll( buttons );

        this.setStyle( "-fx-background-color: mediumslateblue;" + "-fx-font-size: 15pt;" );
        vb.setAlignment( Pos.CENTER );

        VBox vbIm = new VBox();
        getIm1().setFitHeight( s.getHeight() / 1.25 );
        getIm1().setFitWidth( s.getHeight() / 1.25 );
        vbIm.getChildren().add( getIm1() );
        vbIm.setPadding( new Insets( 95, 20, 20, s.getWidth() * ( 1.45 / 3. ) ) );

        StackPane fp = new StackPane();
        fp.getChildren().addAll( vbIm, vb );

        this.setCenter( fp );
    }

    public ImageView getIm1()
    {
        if ( im1 == null )
        {
            im1 = new ImageView( "be/helha/ttmc/assets/images/paw.png" );
            im1.setOpacity( 0.80 );

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
            btnJouer = new Button( GUIConstant.BUTTON_PLAY );
            btnJouer.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    if ( d.getCards().size() == 0 )
                    {
                        Alert alert = new Alert( AlertType.ERROR,
                                "No deck found!\nPlease, add some cards from the admin panel!" );
                        alert.showAndWait();
                    }
                    else
                    {
                        MainPaneBP mpbp = ( MainPaneBP ) getParent().getParent();
                        mpbp.setVisibleNode( MenuPlayBP.class.getSimpleName() );
                    }
                }
            } );
        }
        return btnJouer;
    }

    public Button getBtnCredits()
    {
        if ( btnCredits == null )
        {
            btnCredits = new Button( GUIConstant.BUTTON_CREDITS );
            btnCredits.setOnAction( new EventHandler< ActionEvent >()
            {
                @Override
                public void handle( ActionEvent arg0 )
                {
                    MainPaneBP mpbp = ( MainPaneBP ) getParent().getParent();
                    mpbp.setVisibleNode( CreditsBP.class.getSimpleName() );
                }
            } );
        }
        return btnCredits;
    }

    public Button getBtnParametres()
    {
        if ( btnParametres == null )
        {
            btnParametres = new Button( GUIConstant.BUTTON_SETTINGS );
            btnParametres.setOnAction( new EventHandler< ActionEvent >()
            {
                @Override
                public void handle( ActionEvent arg0 )
                {
                    MainPaneBP mpbp = ( MainPaneBP ) getParent().getParent();
                    mpbp.setVisibleNode( SettingsBP.class.getSimpleName() );
                }
            } );
        }
        return btnParametres;
    }

    public Button getBtnQuitter()
    {
        if ( btnQuitter == null )
        {
            btnQuitter = new Button( GUIConstant.BUTTON_LEAVE_GAME );
            btnQuitter.setOnAction( new EventHandler< ActionEvent >()
            {
                @Override
                public void handle( ActionEvent arg0 )
                {
                    Alert alert = new Alert( AlertType.CONFIRMATION, GUIConstant.DIALOG_EXIT_CONTENT );
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
            btnGerer = new Button( GUIConstant.BUTTON_ADMIN_PANEL );
            btnGerer.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    AlerteLogin alert = new AlerteLogin();
                    if ( alert.getResultat() )
                    {
                        Alert granted = new Alert( AlertType.INFORMATION );
                        granted.setTitle( GUIConstant.DIALOG_LOGIN_ACCESS_GRANTED );
                        String path;
                        granted.setContentText( GUIConstant.DIALOG_LOGIN_ACCESS_GRANTED );
                        path = "/be/helha/ttmc/assets/images/hackerman.gif";
                        Image img = new Image( path );
                        ImageView icon = new ImageView( img );
                        icon.setFitHeight( img.getHeight() / 3 );
                        icon.setFitWidth( img.getWidth() / 3 );
                        granted.getDialogPane().setGraphic( icon );
                        granted.setHeaderText( null );
                        DialogPane grantedPane = granted.getDialogPane();
                        grantedPane.getStylesheets().add( Main.class
                                .getResource( "assets/stylesheets/alert_granted_stylesheet.css" ).toExternalForm() );
                        grantedPane.getStyleClass().add( "granted" );
                        granted.showAndWait();
                        MainPaneBP mpbp = ( MainPaneBP ) getParent().getParent();
                        mpbp.setVisibleNode( MenuAdminBP.class.getSimpleName() );
                    }
                    else
                    {

                        Alert denied = new Alert( AlertType.INFORMATION );
                        denied.setTitle( GUIConstant.DIALOG_LOGIN_ACCESS_DENIED );
                        String path;
                        denied.setContentText( GUIConstant.DIALOG_LOGIN_ACCESS_DENIED );
                        path = "/be/helha/ttmc/assets/images/anonymous.png";
                        Image img = new Image( path );
                        ImageView icon = new ImageView( img );
                        icon.setFitHeight( img.getHeight() / 3 );
                        icon.setFitWidth( img.getWidth() / 3 );
                        denied.getDialogPane().setGraphic( icon );
                        denied.setHeaderText( null );
                        DialogPane deniedPane = denied.getDialogPane();
                        deniedPane.getStylesheets().add( Main.class
                                .getResource( "assets/stylesheets/alert_denied_stylesheet.css" ).toExternalForm() );
                        deniedPane.getStyleClass().add( "denied" );
                        denied.showAndWait();
                    }
                }
            } );
        }
        return btnGerer;
    }
}
