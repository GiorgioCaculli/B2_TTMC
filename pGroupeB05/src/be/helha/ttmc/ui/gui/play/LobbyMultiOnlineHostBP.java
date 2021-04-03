package be.helha.ttmc.ui.gui.play;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import be.helha.ttmc.model.BasicCard;
import be.helha.ttmc.model.Deck;
import be.helha.ttmc.ui.Client;
import be.helha.ttmc.ui.Player;
import be.helha.ttmc.ui.Server;
import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.play.MenuMultiplayerOnlineBP.MenuMultiplayerOnlineMainVB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LobbyMultiOnlineHostBP extends BorderPane
{
    private StackPane lobbyMultiLocalSP = new StackPane();

    private Button newGameButton = new Button( "New Game" );
    private Button returnButton = new Button( "Return" );
    private ChatBP chatBox = new ChatBP();
    private Server server;
    private List< ServerThread > serverThreads = new ArrayList<>();
    private DataOutputStream toServer;
    private Thread callThread = null;
    private Socket socket = null;
    private Player hostPlayer = null;
    private Settings settings;

    public LobbyMultiOnlineHostBP( Deck d, Settings settings ) throws IOException
    {
        server = new Server();
        this.settings = settings;
        setTop( new Label( String.format( "%s:%d", server.getServerSocket().getInetAddress().toString(),
                server.getPortNumber() ) ) );
        for ( int i = 0; i < lobbyMultiLocalSP.getChildren().size(); i++ )
        {
            if ( lobbyMultiLocalSP.getChildren().get( i ).getClass().getSimpleName()
                    .equals( JouerChoixQuestionMultiplayerOnlineBP.class.getSimpleName() ) )
            {
                lobbyMultiLocalSP.getChildren().remove( i );
            }
        }
        TextInputDialog nbPlayersDialog = new TextInputDialog();
        nbPlayersDialog.setTitle( "Number of players" );
        nbPlayersDialog.setContentText( "Please, insert the number of players:" );
        nbPlayersDialog.setHeaderText( "Insert the number of players" );
        Optional< String > nbPlayersInput = null;
        int nbPlayers = 0;
        while ( nbPlayers == 0 || nbPlayers < 0 )
        {
            nbPlayersInput = nbPlayersDialog.showAndWait();
            if ( nbPlayersInput.isPresent() && !nbPlayersInput.get().isEmpty() )
            {
                try
                {
                    nbPlayers = Integer.parseInt( nbPlayersInput.get() );
                }
                catch ( NumberFormatException nbe )
                {
                    nbPlayers = -1;
                }
            }
            else
            {
                nbPlayers = -1;
            }
        }
        int maxPlayers = nbPlayers;
        List< Player > players = new ArrayList<>( maxPlayers );
        int i = 0;
        List< BasicCard > cards = d.getCards();
        Collections.shuffle( cards );
        hostPlayer = new Player( cards );
        TextInputDialog userNameDialogPlayer = new TextInputDialog();
        userNameDialogPlayer.setTitle( String.format( "Player %d - Insert your nickname", i + 1 ) );
        userNameDialogPlayer.setHeaderText( String.format( "Player %d - Insert your nickname", i + 1 ) );
        userNameDialogPlayer.setContentText( String.format( "Please, insert your nickname player %d:", i + 1 ) );
        Optional< String > userNamePlayer = userNameDialogPlayer.showAndWait();
        if ( userNamePlayer.isPresent() && !userNamePlayer.get().isEmpty() )
        {
            hostPlayer.setNickNamePlayer( String.format( "%s", userNamePlayer.get() ) );
        }
        else
        {
            hostPlayer.setNickNamePlayer( String.format( "User-%d", i + 1 ) );
        }
        players.add( hostPlayer );

        callThread = new Thread( new Runnable()
        {
            @Override
            public void run()
            {
                ServerThread t = null;
                int maxServerJoin = 0;
                while ( !server.getServerSocket().isClosed() && maxServerJoin < maxPlayers - 1 )
                {
                    try
                    {
                        t = new ServerThread( server.getServerSocket().accept() );
                        serverThreads.add( t );
                        t.start();
                    }
                    catch ( IOException e )
                    {
                        e.printStackTrace();
                    }
                    maxServerJoin++;
                }
            }
        } );
        callThread.start();

        Client client = new Client( hostPlayer.getNickNamePlayer() );
        socket = client.getSocket();

        chatBox.getMessageField().setOnKeyPressed( new EventHandler< KeyEvent >()
        {
            @Override
            public void handle( KeyEvent keyEvent )
            {
                if ( keyEvent.getCode() == KeyCode.ENTER )
                {
                    try
                    {
                        toServer = new DataOutputStream( socket.getOutputStream() );
                        writeMessageToAll( chatBox.getMessageField().getText().trim(), toServer );
                        chatBox.getMessageField().clear();
                    }
                    catch ( IOException e )
                    {
                        e.printStackTrace();
                    }
                }
            }
        } );

        newGameButton.setOnAction( new EventHandler< ActionEvent >()
        {
            @Override
            public void handle( ActionEvent arg0 )
            {
                for ( int i = 0; i < lobbyMultiLocalSP.getChildren().size(); i++ )
                {
                    if ( lobbyMultiLocalSP.getChildren().get( i ).getClass().getSimpleName()
                            .equals( JouerChoixQuestionMultiplayerOnlineBP.class.getSimpleName() ) )
                    {
                        lobbyMultiLocalSP.getChildren().remove( i );
                    }
                }
                for ( Player p : players )
                {
                    List< BasicCard > cards = d.getCards();
                    Collections.shuffle( cards );
                    p.setCards( cards );
                }
                lobbyMultiLocalSP.getChildren().add( new JouerChoixQuestionMultiplayerOnlineBP( d, players, settings ) );
                setVisibleNode( JouerChoixQuestionMultiplayerOnlineBP.class.getSimpleName() );
            }
        } );

        returnButton.setOnAction( new EventHandler< ActionEvent >()
        {
            @Override
            public void handle( ActionEvent arg0 )
            {
                try
                {
                    server.getServerSocket().close();
                }
                catch ( IOException e )
                {
                    e.printStackTrace();
                }
                MenuMultiplayerOnlineBP mmbp = ( ( MenuMultiplayerOnlineBP ) getParent().getParent() );
                mmbp.setVisibleNode( MenuMultiplayerOnlineMainVB.class.getSimpleName() );
            }
        } );
        lobbyMultiLocalSP.getChildren().add( new LobbyMultiOnlineMainBP() );

        setRight( chatBox );
        setCenter( lobbyMultiLocalSP );
    }

    public void setVisibleNode( String nodeName )
    {
        for ( Node n : lobbyMultiLocalSP.getChildren() )
        {
            if ( n.getClass().getSimpleName().equals( nodeName ) )
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
            choiceBox.getChildren().addAll( newGameButton, returnButton );

            setCenter( choiceBox );
        }
    }

    public void sendMessageToAll( String message )
    {
        for ( ServerThread t : serverThreads )
        {
            try
            {
                t.writeMessage( message );
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
    }

    private void writeMessageToAll( String message, DataOutputStream out ) throws IOException
    {
        if ( !chatBox.getMessageField().getText().isEmpty() )
        {
            out.writeUTF( String.format( "%s: %s", hostPlayer.getNickNamePlayer(), message ) );
            out.flush();
        }
    }

    private class ServerThread extends Thread
    {
        private Socket socket = null;
        private DataInputStream inputFromClient;
        private DataOutputStream outputToClient;

        public ServerThread( Socket socket )
        {
            super( "ServerThread" );
            this.socket = socket;
        }

        public void writeMessage( String message ) throws IOException
        {
            outputToClient.writeUTF( message );
        }

        public void run()
        {
            try
            {
                outputToClient = new DataOutputStream( socket.getOutputStream() );
                inputFromClient = new DataInputStream( socket.getInputStream() );
                chatBox.getConversationArea()
                        .appendText( String.format( "IP: %s connected\n", socket.getRemoteSocketAddress() ) );
                String inputLine;
                while ( ( ( inputLine = inputFromClient.readUTF() ) != null ) )
                {
                    sendMessageToAll( inputLine );
                    chatBox.getConversationArea()
                            .appendText( String.format( "%s\n", inputLine ) );
                }
                chatBox.getConversationArea()
                        .appendText( String.format( "IP: %s disconnected\n", socket.getRemoteSocketAddress() ) );
                socket.close();
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
            finally
            {
                if ( socket != null && !socket.isClosed() )
                {
                    try
                    {
                        socket.close();
                    }
                    catch ( IOException e )
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
