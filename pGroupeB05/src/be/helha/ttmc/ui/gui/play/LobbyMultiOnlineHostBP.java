package be.helha.ttmc.ui.gui.play;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

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
    private StackPane lobbyMultiOnlineHostSP = new StackPane();

    private Button newGameButton = new Button( "New Game" );
    private Button returnButton = new Button( "Return" );
    private ChatBP chatBox = new ChatBP();
    private Server server;
    private List< ServerThread > serverThreads = new ArrayList<>();
    private OutputStream toServer;
    private Thread callThread = null;
    private Player hostPlayer = null;
    private Settings settings;
    private CountDownLatch countDownLatch = null;
    private List< Player > players = null;

    public LobbyMultiOnlineHostBP( Deck d, Settings settings ) throws IOException
    {
        server = new Server();
        countDownLatch = new CountDownLatch( 1 );
        this.settings = settings;
        setTop( new Label( String.format( "%s:%d", server.getServerSocket().getInetAddress().toString(),
                server.getPortNumber() ) ) );
        for ( int i = 0; i < lobbyMultiOnlineHostSP.getChildren().size(); i++ )
        {
            if ( lobbyMultiOnlineHostSP.getChildren().get( i ).getClass().getSimpleName()
                    .equals( JouerChoixQuestionMultiplayerOnlineBP.class.getSimpleName() ) )
            {
                lobbyMultiOnlineHostSP.getChildren().remove( i );
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
        players = new ArrayList<>( maxPlayers );
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

        Client client = new Client( hostPlayer.getNickNamePlayer() );
        toServer = new DataOutputStream( client.getSocket().getOutputStream() );
        ( ( DataOutputStream ) toServer ).writeUTF( client.getUserName() );

        callThread = new Thread( new Runnable()
        {
            @Override
            public void run()
            {
                ServerThread t = null;
                while ( !Thread.currentThread().isInterrupted() && serverThreads.size() <= maxPlayers - 1 )
                {
                    try
                    {
                        t = new ServerThread( server.getServerSocket().accept(), cards );
                        if ( serverThreads.add( t ) )
                        {
                            t.start();
                        }
                        else
                        {
                            System.out.println( "user already present" );
                        }
                    }
                    catch ( IOException e )
                    {
                        e.printStackTrace();
                    }
                }
            }
        } );

        Thread clientIsUpThread = new Thread( new Runnable()
        {
            @Override
            public void run()
            {
                while ( !Thread.currentThread().isInterrupted() )
                {
                    for ( int i = 0; i < serverThreads.size(); i++ )
                    {
                        if ( serverThreads.get( i ).getSocket().isClosed() )
                        {
                            System.out.println( "True" );
                            chatBox.getConversationArea().appendText( String.format( "IP: %s disconnected.\n",
                                    serverThreads.get( i ).getPlayer().getNickNamePlayer() ) );
                            serverThreads.get( i ).interrupt();
                            serverThreads.remove( i );
                            players.remove( i );
                        }
                    }
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
                        if ( !chatBox.getMessageField().getText().isEmpty() )
                        {
                            ( ( DataOutputStream ) toServer ).writeUTF( String.format( "%s: %s",
                                    hostPlayer.getNickNamePlayer(), chatBox.getMessageField().getText().trim() ) );
                            toServer.flush();
                            chatBox.getMessageField().clear();
                        }
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
                for ( int i = 0; i < lobbyMultiOnlineHostSP.getChildren().size(); i++ )
                {
                    if ( lobbyMultiOnlineHostSP.getChildren().get( i ).getClass().getSimpleName()
                            .equals( JouerChoixQuestionMultiplayerOnlineBP.class.getSimpleName() ) )
                    {
                        lobbyMultiOnlineHostSP.getChildren().remove( i );
                    }
                }
                for ( int i = 0; i < serverThreads.size(); i++ )
                {
                    players.add( serverThreads.get( i ).getPlayer() );
                }
                for ( Player p : players )
                {
                    System.out.println( p );
                    List< BasicCard > cards = d.getCards();
                    Collections.shuffle( cards );
                    p.setCards( cards );
                }
                lobbyMultiOnlineHostSP.getChildren()
                        .add( new JouerChoixQuestionMultiplayerOnlineBP( d, players, settings ) );
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
                    callThread.interrupt();
                    clientIsUpThread.interrupt();
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
        lobbyMultiOnlineHostSP.getChildren().add( new LobbyMultiOnlineMainBP() );

        setRight( chatBox );
        setCenter( lobbyMultiOnlineHostSP );

        callThread.start();

        clientIsUpThread.start();
    }

    public void setVisibleNode( String nodeName )
    {
        for ( Node n : lobbyMultiOnlineHostSP.getChildren() )
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

    private void sendMessageToAll( String message )
    {
        System.out.println( serverThreads.size() );
        for ( int i = 0; i < serverThreads.size(); i++ )
        {
            try
            {
                serverThreads.get( i ).writeMessage( message );
            }
            catch ( IOException e )
            {
                serverThreads.remove( i );
            }
        }
    }

    private class ServerThread extends Thread
    {
        private Socket socket = null;
        private DataInputStream inputFromClient;
        private DataOutputStream outputToClient;
        private Player newPlayer = null;
        private List< BasicCard > cards;
        private Thread chatThread = null;

        public ServerThread( Socket socket, List< BasicCard > cards )
        {
            super( "ServerThread" );
            this.socket = socket;
            this.cards = cards;
        }

        public void writeMessage( String message ) throws IOException
        {
            outputToClient.writeUTF( message );
        }

        public void run()
        {
            try
            {
                OutputStream socketOutputStream = socket.getOutputStream();
                InputStream socketInputStream = socket.getInputStream();
                outputToClient = new DataOutputStream( socketOutputStream );
                inputFromClient = new DataInputStream( socketInputStream );
                chatBox.getConversationArea()
                        .appendText( String.format( "IP: %s connected\n", socket.getRemoteSocketAddress() ) );
                Collections.shuffle( cards );
                newPlayer = new Player( cards );
                newPlayer.setNickNamePlayer( inputFromClient.readUTF() );
                chatThread = new Thread( new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            while ( !Thread.currentThread().isInterrupted()
                                    && !socket.isClosed() )
                            {
                                String inputLine = inputFromClient.readUTF();
                                sendMessageToAll( inputLine );
                                chatBox.getConversationArea().appendText( String.format( "%s\n", inputLine ) );
                            }
                            socket.close();
                        }
                        catch ( IOException e )
                        {
                            e.printStackTrace();
                            interrupt();
                        }
                    }
                } );
                chatThread.start();
            }
            catch ( IOException e )
            {
                e.printStackTrace();
                interrupt();
            }
        }

        public Player getPlayer()
        {
            return newPlayer;
        }

        public Socket getSocket()
        {
            return socket;
        }

        public boolean equals( Object o )
        {
            if ( o instanceof ServerThread )
            {
                ServerThread tmp = ( ServerThread ) o;
                return tmp.getPlayer().getNickNamePlayer().equalsIgnoreCase( getPlayer().getNickNamePlayer() );
            }
            return false;
        }
    }
}
