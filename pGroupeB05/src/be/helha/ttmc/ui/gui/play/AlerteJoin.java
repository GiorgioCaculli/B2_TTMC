package be.helha.ttmc.ui.gui.play;

import java.util.Optional;

import be.helha.ttmc.Main;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class AlerteJoin extends Alert
{
    private Optional< Pair< String, String > > result = null;

    TextField username = new TextField();
    TextField ipAddress = new TextField();

    public AlerteJoin()
    {
        super( AlertType.INFORMATION );
        Dialog< Pair< String, String > > dialog = new Dialog<>();
        dialog.setTitle( "Join Server" );
        dialog.setHeaderText( "Join Server:" );

        ImageView im = new ImageView( Main.class.getResource( "assets/images/cadenas.png" ).toString() );
        im.setFitWidth( 50 );
        im.setFitHeight( 50 );

        Stage stage = ( Stage ) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add( new Image( Main.class.getResource( "assets/images/cadenas.png" ).toString() ) );

        dialog.setGraphic( im );

        ButtonType joinButtonType = new ButtonType( "Join", ButtonData.OK_DONE );
        dialog.getDialogPane().getButtonTypes().addAll( joinButtonType, ButtonType.CANCEL );

        GridPane grid = new GridPane();
        grid.setHgap( 10 );
        grid.setVgap( 10 );
        grid.setPadding( new Insets( 20, 150, 10, 10 ) );

        username.setPromptText( "Username" );
        ipAddress.setPromptText( "IP Address" );

        grid.add( new Label( "Username :" ), 0, 0 );
        grid.add( username, 1, 0 );
        grid.add( new Label( "IP Address :" ), 0, 1 );
        grid.add( ipAddress, 1, 1 );

        Node loginButton = dialog.getDialogPane().lookupButton( joinButtonType );
        loginButton.setDisable( true );

        username.textProperty().addListener( ( observable, oldValue, newValue ) ->
        {
            ipAddress.setDisable( newValue.trim().isEmpty() );
        } );

        ipAddress.textProperty().addListener( new ChangeListener< String >()
        {
            @Override
            public void changed( ObservableValue< ? extends String > observable, String oldValue, String newValue )
            {
                loginButton.setDisable( newValue.trim().isEmpty() );
            }
        } );

        dialog.getDialogPane().setContent( grid );

        Platform.runLater( () -> username.requestFocus() );

        dialog.setResultConverter( dialogButton ->
        {
            if ( dialogButton == joinButtonType )
            {
                return new Pair<>( username.getText(), ipAddress.getText() );
            }
            return new Pair<>( "", "" );
        } );

        result = dialog.showAndWait();

        result.ifPresent( usernameIPAddress ->
        {
            System.out.println(
                    "Username =" + usernameIPAddress.getKey() + ", IP Address = " + usernameIPAddress.getValue() );

        } );
    }

    public String getUsername()
    {
        return username.getText();
    }

    public String getIPAddress()
    {
        return ipAddress.getText();
    }
}
