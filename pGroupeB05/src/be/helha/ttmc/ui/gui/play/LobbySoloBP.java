package be.helha.ttmc.ui.gui.play;

import java.util.Optional;

import be.helha.ttmc.model.Deck;
import be.helha.ttmc.serialization.Serialization;
import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.play.MenuPlayBP.MenuPlayMainVB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LobbySoloBP extends BorderPane
{
    private StackPane lobbyPaneSP = new StackPane();

    private Button newGameButton = new Button( "New Game" );
    private Button loadGameButton = new Button( "Load Game" );
    private Button returnButton = new Button( "Return" );

    private String nickName;
    
    private Settings s;

    public LobbySoloBP( Deck d, Settings s )
    {
        this.s = s;
        for ( int i = 0; i < lobbyPaneSP.getChildren().size(); i++ )
        {
            if ( lobbyPaneSP.getChildren().get( i ).getClass().getSimpleName()
                    .equals( JouerChoixQuestionBP.class.getSimpleName() ) )
            {
                lobbyPaneSP.getChildren().remove( i );
            }
        }
        TextInputDialog userNameDialog = new TextInputDialog();
        userNameDialog.setTitle( "Insert your nickname" );
        userNameDialog.setHeaderText( "Insert your nickname" );
        userNameDialog.setContentText( "Please, insert your nickname:" );
        Optional< String > userName = userNameDialog.showAndWait();
        if ( userName.isPresent() )
        {
            if ( userName.get().isEmpty() )
            {
                nickName = "User-1";
            }
            else
            {
                nickName = userName.get();
            }
        }
        else
        {
            nickName = "User-1";
        }
        newGameButton.setOnAction( new EventHandler< ActionEvent >()
        {
            @Override
            public void handle( ActionEvent arg0 )
            {
                for ( int i = 0; i < lobbyPaneSP.getChildren().size(); i++ )
                {
                    if ( lobbyPaneSP.getChildren().get( i ).getClass().getSimpleName()
                            .equals( JouerChoixQuestionBP.class.getSimpleName() ) )
                    {
                        lobbyPaneSP.getChildren().remove( i );
                    }
                }
                Deck deck = d.clone();
                if ( nickName.equals( "giorgio" ) || nickName.equals( "guillaume" ) || nickName.equals( "tanguy" ) )
                {
                    deck = Serialization.loadDeck( String.format( "assets/decks/%s.json", nickName ).toString() );
                }
                JouerChoixQuestionBP jcq = new JouerChoixQuestionBP( deck, s );
                jcq.setScore( 0 );
                jcq.setNickName( String.format( "%s", nickName ) );
                jcq.getLblScore().setText( String.format( "User: %s - Score: ", jcq.getNickName() ) );
                lobbyPaneSP.getChildren().add( jcq );
                setVisibleNode( jcq.getClass().getSimpleName() );
            }
        } );
        returnButton.setOnAction( new EventHandler< ActionEvent >()
        {

            @Override
            public void handle( ActionEvent arg0 )
            {
                MenuPlayBP mpbp =  ( ( MenuPlayBP ) getParent().getParent() );
                mpbp.setVisibleNode( MenuPlayMainVB.class.getSimpleName() );
            }
        } );
        lobbyPaneSP.getChildren().add( new LobbySoloMainBP() );
        setCenter( lobbyPaneSP );
    }

    public void setVisibleNode( String nodeName )
    {
        for ( Node n : lobbyPaneSP.getChildren() )
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

    protected class LobbySoloMainBP extends BorderPane
    {
        public LobbySoloMainBP()
        {
            VBox choiceBox = new VBox();
            choiceBox.getChildren().addAll( newGameButton, loadGameButton, returnButton );

            setCenter( choiceBox );
        }
    }
}
