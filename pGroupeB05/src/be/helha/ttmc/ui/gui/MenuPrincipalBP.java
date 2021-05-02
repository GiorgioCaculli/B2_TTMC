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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuPrincipalBP extends BorderPane
{

    private Button btnJouer, btnParametres, btnQuitter, btnGerer, btnCredits;
    private List< Button > buttons;
    private ImageView im1, im2;
    private int larg = 175, lon = 175;
    private Deck d;
    private Settings s;

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
         //   b.setMaxWidth( s.getWidth() - 55. );
         //   b.setMinHeight( s.getHeight() / ( buttons.size() + 1 ) );
            b.setEffect( GUIConstant.BUTTON_EFFECT );
            b.setTextFill( GUIConstant.BUTTON_GRADIENT );
            b.setStyle( GUIConstant.BUTTON_STYLE );
            b.setFont( GUIConstant.BUTTON_TEXT );
        }

        vb.setPadding( new Insets( 0., s.getWidth() / 3., 0., 20. ) );
        vb.setSpacing( 20. );
        vb.getChildren().addAll( buttons );

        setStyle( GUIConstant.WINDOW_STYLE );
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
            btnJouer = new Button( s.getLanguage().getString( "button_play" ) );
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
            btnCredits = new Button( s.getLanguage().getString( "button_credits" ) );
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
            btnParametres = new Button( s.getLanguage().getString( "button_settings" ) );
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
            btnQuitter = new Button( s.getLanguage().getString( "button_leave_game" ) );
            btnQuitter.setOnAction( new EventHandler< ActionEvent >()
            {
                @Override
                public void handle( ActionEvent arg0 )
                {
                    Alert alert = new Alert( AlertType.CONFIRMATION,
                            s.getLanguage().getString( "dialog_exit_content" ) );
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
            btnGerer = new Button( s.getLanguage().getString( "button_admin_panel" ) );
            btnGerer.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    AlerteLogin alert = new AlerteLogin( s );
                    if ( alert.getResultat() )
                    {
                        Alert granted = new Alert( AlertType.INFORMATION );
                        granted.setTitle( s.getLanguage().getString( "dialog_login_access_granted" ) );
                        String path;
                        granted.setContentText( s.getLanguage().getString( "dialog_login_access_granted" ) );
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
                        denied.setTitle( s.getLanguage().getString( "dialog_login_access_denied" ) );
                        String path;
                        denied.setContentText( s.getLanguage().getString( "dialog_login_access_denied" ) );
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
