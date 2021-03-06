package be.helha.ttmc.ui.gui.admin;

import java.util.Optional;

import be.helha.ttmc.Main;
import be.helha.ttmc.ui.GUIConstant;
import be.helha.ttmc.ui.Settings;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class AlerteLogin extends Alert
{
    private Optional< Pair< String, String > > result = null;

    TextField username = new TextField();

    public AlerteLogin( Settings s )
    {
        super( AlertType.INFORMATION );
        Dialog< Pair< String, String > > dialog = new Dialog<>();
        dialog.setTitle( s.getLanguage().getString( "dialog_login_title" ) );
        dialog.setHeaderText( s.getLanguage().getString( "dialog_login_content" ) );

        ImageView im = new ImageView( Main.class.getResource( "assets/images/cadenas.png" ).toString() );
        im.setFitWidth( 50 );
        im.setFitHeight( 50 );

        Stage stage = ( Stage ) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add( new Image( Main.class.getResource( "assets/images/cadenas.png" ).toString() ) );

        dialog.setGraphic( im );

        ButtonType loginButtonType = new ButtonType( s.getLanguage().getString( "dialog_login_button" ),
                ButtonData.OK_DONE );
        dialog.getDialogPane().getButtonTypes().addAll( loginButtonType, ButtonType.CANCEL );

        GridPane grid = new GridPane();
        grid.setHgap( 10 );
        grid.setVgap( 10 );
        grid.setPadding( new Insets( 20, 150, 10, 10 ) );

        username.setPromptText( s.getLanguage().getString( "dialog_login_username" ) );
        PasswordField password = new PasswordField();
        password.setPromptText( s.getLanguage().getString( "dialog_login_password" ) );

        grid.add( new Label( s.getLanguage().getString( "dialog_login_username" ) + ":" ), 0, 0 );
        grid.add( username, 1, 0 );
        grid.add( new Label( s.getLanguage().getString( "dialog_login_password" ) + ":" ), 0, 1 );
        grid.add( password, 1, 1 );

        Node loginButton = dialog.getDialogPane().lookupButton( loginButtonType );
        loginButton.setDisable( true );

        username.textProperty().addListener( ( observable, oldValue, newValue ) ->
        {
            loginButton.setDisable( newValue.trim().isEmpty() );
        } );

        dialog.getDialogPane().setContent( grid );

        Platform.runLater( () -> username.requestFocus() );

        dialog.setResultConverter( dialogButton ->
        {
            if ( dialogButton == loginButtonType )
            {
                return new Pair<>( username.getText(), password.getText() );
            }
            return new Pair<>( "", "" );
        } );

        result = dialog.showAndWait();

        result.ifPresent( usernamePassword ->
        {
        } );
    }

    public boolean getResultat()
    {
        Optional< Pair< String, String > > credentials = result;
        if ( credentials != null )
            return ( credentials.get().getKey().equals( "admin" ) || credentials.get().getKey().equals( "user" )
                    || credentials.get().getKey().equals( "giorgio" )
                    || credentials.get().getKey().equals( "guillaume" ) || credentials.get().getKey().equals( "tanguy" )
                    || credentials.get().getKey().equals( "altares" ) )
                    && credentials.get().getValue().equals( "helha" );
        return false;
    }

    public String getUsername()
    {
        return username.getText();
    }
}
