package be.helha.ttmc.ui.gui.play;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import be.helha.ttmc.model.Deck;
import be.helha.ttmc.ui.Client;
import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.play.MenuMultiplayerBP.MenuMultiplayerMainVB;
import be.helha.ttmc.ui.gui.util.MusicGestion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MenuMultiplayerOnlineBP extends BorderPane
{
    private Button btnHost, btnJoin, btnRetour;
    private Deck d;
    private StackPane choiceMultiplayerOnlinePane;
    private Settings s;
    private MusicGestion m;

    public MenuMultiplayerOnlineBP( Deck d, Settings s, MusicGestion m )
    {
        this.d = d;
        this.s = s;
        this.m= m;
        getChoicePane().getChildren().add( new MenuMultiplayerOnlineMainVB() );
        setVisibleNode( MenuMultiplayerOnlineMainVB.class.getSimpleName() );

        setCenter( choiceMultiplayerOnlinePane );

    }

    protected StackPane getChoicePane()
    {
        if ( choiceMultiplayerOnlinePane == null )
        {
            choiceMultiplayerOnlinePane = new StackPane();
        }
        return choiceMultiplayerOnlinePane;
    }

    public void setVisibleNode( String paneName )
    {
        for ( Node n : getChoicePane().getChildren() )
        {
            if ( n.getClass().getSimpleName().equals( paneName ) )
            {
                n.setVisible( true );
            }
            else
            {
                n.setVisible( false );
            }
        }
    }

    protected class MenuMultiplayerOnlineMainVB extends VBox
    {
        public MenuMultiplayerOnlineMainVB()
        {
            // this.setOrientation(Orientation.VERTICAL);
            setPadding( new Insets( 20 ) );
            setSpacing( 50 );

            setStyle( "-fx-background-color: DAE6F3;" + "-fx-font-size: 15pt;" );
            getChildren().addAll( getBtnHost(), getBtnJoin(), getBtnRetour() );
            setAlignment( Pos.CENTER );
        }
    }

    private Button getBtnHost()
    {
        if ( btnHost == null )
        {
            btnHost = new Button( "Host" );
            btnHost.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
            btnHost.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    for ( int i = 0; i < getChoicePane().getChildren().size(); i++ )
                    {
                        if ( getChoicePane().getChildren().get( i ).getClass().getSimpleName()
                                .equals( LobbyMultiOnlineHostBP.class.getSimpleName() ) )
                        {
                            getChoicePane().getChildren().remove( i );
                        }
                    }
                    try
                    {
                        getChoicePane().getChildren().add( new LobbyMultiOnlineHostBP( d, s , m) );
                        setVisibleNode( LobbyMultiOnlineHostBP.class.getSimpleName() );
                    }
                    catch ( IOException e )
                    {
                        e.printStackTrace();
                    }
                }
            } );
        }
        return btnHost;
    }

    public Button getBtnJoin()
    {
        if ( btnJoin == null )
        {
            btnJoin = new Button( "Join" );
            btnJoin.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
            btnJoin.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    for ( int i = 0; i < getChoicePane().getChildren().size(); i++ )
                    {
                        if ( getChoicePane().getChildren().get( i ).getClass().getSimpleName()
                                .equals( LobbyMultiOnlineJoinBP.class.getSimpleName() ) )
                        {
                            getChoicePane().getChildren().remove( i );
                        }
                    }
                    try
                    {
                        AlerteJoin aj = new AlerteJoin();
                        Client c = new Client( aj.getUsername(), aj.getIPAddress() );
                        getChoicePane().getChildren().add( new LobbyMultiOnlineJoinBP( c ) );
                        setVisibleNode( LobbyMultiOnlineJoinBP.class.getSimpleName() );
                    }
                    catch ( UnknownHostException e )
                    {
                        e.printStackTrace();
                    }
                    catch( ConnectException e )
                    {
                        Alert alert = new Alert( AlertType.ERROR );
                        alert.setTitle( "ConnectException" );
                        alert.setHeaderText( "ConnectException caught" );
                        alert.setContentText( e.getMessage() );
                        e.printStackTrace();
                        alert.showAndWait();
                    }
                    catch ( IOException e )
                    {
                        e.printStackTrace();
                    }
                }
            } );
        }
        return btnJoin;
    }

    private Button getBtnRetour()
    {
        if ( btnRetour == null )
        {
            btnRetour = new Button( "Return" );
            btnRetour.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
            btnRetour.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    MenuMultiplayerBP mpbp = ( MenuMultiplayerBP ) getParent().getParent();
                    mpbp.setVisibleNode( MenuMultiplayerMainVB.class.getSimpleName() );
                }
            } );
        }
        return btnRetour;
    }
}
