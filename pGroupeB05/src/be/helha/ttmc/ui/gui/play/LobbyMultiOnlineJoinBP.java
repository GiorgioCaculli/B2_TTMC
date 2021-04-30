package be.helha.ttmc.ui.gui.play;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.SocketException;

import be.helha.ttmc.ui.Client;
import be.helha.ttmc.ui.gui.play.MenuMultiplayerOnlineBP.MenuMultiplayerOnlineMainVB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LobbyMultiOnlineJoinBP extends BorderPane
{
    private StackPane LobbyMultiOnlineJoinSP = new StackPane();
    private DataOutputStream toServer;
    private DataInputStream fromServer;
    private ChatBP chatBox = new ChatBP();
    private Client c;
    private Button disconnectButton = new Button( "Leave" );

    public LobbyMultiOnlineJoinBP( Client c )
    {
        this.c = c;

        Thread clientThread = new Thread( new ClientThread( c ) );

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

        LobbyMultiOnlineJoinSP.getChildren().add( new LobbyMultiOnlineMainBP() );

        setRight( chatBox );
        setCenter( LobbyMultiOnlineJoinSP );

        clientThread.start();
    }

    public void setVisibleNode( String nodename )
    {
        for ( Node n : LobbyMultiOnlineJoinSP.getChildren() )
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
        public LobbyMultiOnlineMainBP()
        {
            VBox choiceBox = new VBox();
            choiceBox.getChildren().addAll( disconnectButton );
            setCenter( choiceBox );
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
}
