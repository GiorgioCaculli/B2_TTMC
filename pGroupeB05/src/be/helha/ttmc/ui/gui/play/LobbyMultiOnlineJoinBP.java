package be.helha.ttmc.ui.gui.play;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import be.helha.ttmc.ui.Client;
import be.helha.ttmc.ui.GUIConstant;
import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.play.MenuMultiplayerOnlineBP.MenuMultiplayerOnlineMainVB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

public class LobbyMultiOnlineJoinBP extends BorderPane
{
    private StackPane lobbyMultiOnlineJoinSP;
    private DataOutputStream toServer;
    private DataInputStream fromServer;
    private ChatBP chatBox = new ChatBP();
    private Client c;
    private Button disconnectButton;
    private Thread clientThread;
    private Settings settings;

    public LobbyMultiOnlineJoinBP( Client c, Settings settings )
    {
        this.c = c;
        this.settings = settings;

        clientThread = new Thread( new ClientThread( c ) );

        chatBox.getMessageField().setOnKeyPressed( new EventHandler< KeyEvent >()
        {
            @Override
            public void handle( KeyEvent keyEvent )
            {
                if ( keyEvent.getCode() == KeyCode.ENTER )
                {
                    try
                    {
                        writeMessage( chatBox.getMessageField().getText().trim(), toServer );
                        chatBox.getMessageField().clear();
                    }
                    catch ( IOException e )
                    {
                        e.printStackTrace();
                    }
                }
            }
        } );

        getLobbyMultiOnlineJoineSP().getChildren().add( new LobbyMultiOnlineMainBP() );

        setRight( chatBox );
        setCenter( getLobbyMultiOnlineJoineSP() );

        clientThread.start();
    }
    
    public StackPane getLobbyMultiOnlineJoineSP()
    {
        if( lobbyMultiOnlineJoinSP == null )
        {
            lobbyMultiOnlineJoinSP = new StackPane();
        }
        return lobbyMultiOnlineJoinSP;
    }

    public void setVisibleNode( String nodename )
    {
        for ( Node n : getLobbyMultiOnlineJoineSP().getChildren() )
        {
            if ( n.getClass().getSimpleName().equals( nodename ) )
            {
                n.setVisible( true );
            }
            else
            {
                n.setVisible( false );
            }
        }
    }

    protected class LobbyMultiOnlineMainBP extends BorderPane
    {
        private List< Button > buttons;
        
        public LobbyMultiOnlineMainBP()
        {
            buttons = new ArrayList<>();
            VBox choiceBox = new VBox();
            
            buttons.add( getDisconnectButton() );

            for ( Button b : buttons )
            {
                b.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
                b.setEffect( GUIConstant.BUTTON_EFFECT );
                b.setTextFill( GUIConstant.BUTTON_GRADIENT );
                b.setStyle( GUIConstant.BUTTON_STYLE );
                b.setFont( GUIConstant.BUTTON_TEXT );
                b.setMaxWidth( settings.getWidth() - 55. );
                b.setMinHeight( settings.getHeight() / ( buttons.size() + 1 ) );
            }
            
            choiceBox.getChildren().addAll( buttons );
            setCenter( choiceBox );
            setStyle( GUIConstant.WINDOW_STYLE );
        }
    }

    private void disconnect()
    {
        if ( c.getSocket() != null )
        {
            try
            {
                writeMessage( "User Disconnected", toServer );
                c.getSocket().close();
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
    }

    private void writeMessage( String message, DataOutputStream out ) throws IOException
    {
        if ( !chatBox.getMessageField().getText().isEmpty() )
        {
            try
            {
                out.writeUTF( String.format( "%s: %s", c.getUserName(), message ) );
                out.flush();
            }
            catch ( SocketException se )
            {
                se.printStackTrace();
                MenuMultiplayerOnlineBP mmbp = ( ( MenuMultiplayerOnlineBP ) getParent().getParent() );
                mmbp.setVisibleNode( MenuMultiplayerOnlineMainVB.class.getSimpleName() );
            }
        }
    }

    private class ClientThread implements Runnable
    {
        private Client c;

        public ClientThread( Client c )
        {
            this.c = c;
        }

        @Override
        public void run()
        {
            chatBox.getConversationArea().appendText(
                    String.format( "Connected to: %s:%d\n", c.getSocket().getInetAddress(), c.getSocket().getPort() ) );

            try
            {
                fromServer = new DataInputStream( c.getSocket().getInputStream() );
                toServer = new DataOutputStream( c.getSocket().getOutputStream() );
                try
                {
                    toServer.writeUTF( c.getUserName() );
                    while ( !c.getSocket().isClosed() && !Thread.currentThread().isInterrupted() )
                    {
                        String serverIn = fromServer.readUTF();
                        System.out.println( serverIn );
                        if ( serverIn == null )
                        {
                            disconnect();
                            break;
                        }
                        chatBox.getConversationArea().appendText( String.format( "%s\n", serverIn ) );
                    }
                }
                catch ( EOFException eofe )
                {
                    eofe.printStackTrace();
                }
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
    }
    
    public Button getDisconnectButton()
    {
        if( disconnectButton == null )
        {
            disconnectButton = new Button( "Leave" );
            disconnectButton.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    if ( c.getSocket() != null )
                    {
                        clientThread.interrupt();
                        disconnect();
                        MenuMultiplayerOnlineBP mmbp = ( ( MenuMultiplayerOnlineBP ) getParent().getParent() );
                        mmbp.setVisibleNode( MenuMultiplayerOnlineMainVB.class.getSimpleName() );
                    }
                    else
                    {
                        MenuMultiplayerOnlineBP mmbp = ( ( MenuMultiplayerOnlineBP ) getParent().getParent() );
                        mmbp.setVisibleNode( MenuMultiplayerOnlineMainVB.class.getSimpleName() );
                    }
                }
            } );
        }
        return disconnectButton;
    }
}
