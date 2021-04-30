package be.helha.ttmc.ui.gui.play;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import be.helha.ttmc.model.Deck;
import be.helha.ttmc.ui.Client;
import be.helha.ttmc.ui.GUIConstant;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
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
        this.m = m;
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
        private List< Button > buttons;
        private Font txt = Font.font( "Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 100 );
        private Effect buttonEffect = new DropShadow( 25, 13, 13, Color.DARKSLATEGREY );
        private String buttonStyle = "-fx-background-color: plum;";

        private Stop[] etapes =
        { new Stop( 0, Color.BLUEVIOLET ), new Stop( 0.3, Color.ROYALBLUE ), new Stop( 0.7, Color.LIGHTSTEELBLUE ) };
        private LinearGradient gradiant = new LinearGradient( 0, 1, 0, 0, true, CycleMethod.NO_CYCLE, etapes );

        public MenuMultiplayerOnlineMainVB()
        {
            buttons = new ArrayList<>();
            // this.setOrientation(Orientation.VERTICAL);
            setPadding( new Insets( 20 ) );
            setSpacing( 50 );

            buttons.add( getBtnHost() );
            buttons.add( getBtnJoin() );
            buttons.add( getBtnRetour() );

            for ( Button b : buttons )
            {
                b.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
                b.setEffect( buttonEffect );
                b.setTextFill( gradiant );
                b.setStyle( buttonStyle );
                b.setFont( txt );
                b.setMaxWidth( s.getWidth() - 55. );
                b.setMinHeight( s.getHeight() / ( buttons.size() + 1 ) );
            }

            setStyle( "-fx-background-color: DAE6F3;" + "-fx-font-size: 15pt;" );
            getChildren().addAll( buttons );
            setAlignment( Pos.CENTER );
        }
    }

    private Button getBtnHost()
    {
        if ( btnHost == null )
        {
            btnHost = new Button( "Host" );
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
                        getChoicePane().getChildren().add( new LobbyMultiOnlineHostBP( d, s, m ) );
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
                    catch ( ConnectException e )
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
            btnRetour = new Button( GUIConstant.BUTTON_RETURN );
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
