package be.helha.ttmc.ui.gui.play;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import be.helha.ttmc.model.BasicCard;
import be.helha.ttmc.model.Deck;
import be.helha.ttmc.ui.GUIConstant;
import be.helha.ttmc.ui.Player;
import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.play.JouerChoixQuestionMultiplayerLocalBP.Joueur;
import be.helha.ttmc.ui.gui.play.MenuMultiplayerBP.MenuMultiplayerMainVB;
import be.helha.ttmc.ui.gui.util.MusicGestion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.DropShadow;
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

public class LobbyMultiLocalBP extends BorderPane
{
    private StackPane lobbyMultiLocalSP = new StackPane();

    private Button newGameButton;
    private Button returnButton;
    private Settings s;
    private MusicGestion m;

    private Stop[] etapes = { new Stop(0, Color.BLUEVIOLET),
    		new Stop(0.3, Color.ROYALBLUE),new Stop(0.7,Color.LIGHTSTEELBLUE)};
	private LinearGradient gradiant= new LinearGradient(0, 1, 0, 0, true, CycleMethod.NO_CYCLE,
			etapes
			
			);
    
    

	public Button getNewGameButton() {
		if(newGameButton==null) {
			newGameButton = new Button( "New Game" );
			Font txt= Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 100);
			newGameButton.setFont(txt);
			newGameButton.setEffect(new DropShadow(25, 13, 13, Color.DARKSLATEGREY));
			newGameButton.setStyle("-fx-background-color: plum;");
			newGameButton.setTextFill(gradiant);
			newGameButton.setMaxWidth(s.getWidth()-55.);
			newGameButton.setMinHeight(s.getHeight()/4);
			
		}
		return newGameButton;
	}

	public Button getReturnButton() {
		if(returnButton==null) {
			returnButton = new Button( GUIConstant.BUTTON_RETURN );
			Font txt= Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 100);
			returnButton.setFont(txt);
			returnButton.setEffect(new DropShadow(25, 13, 13, Color.DARKSLATEGREY));
			returnButton.setStyle("-fx-background-color: plum;");
			returnButton.setTextFill(gradiant);
			returnButton.setMaxWidth(s.getWidth()-55.);
			returnButton.setMinHeight(s.getHeight()/4);
		}
		return returnButton;
	}

	public LobbyMultiLocalBP( Deck d, Settings s, MusicGestion m )
    {
        this.s = s;
        this.m= m;
        for ( int i = 0; i < lobbyMultiLocalSP.getChildren().size(); i++ )
        {
            if ( lobbyMultiLocalSP.getChildren().get( i ).getClass().getSimpleName()
                    .equals( JouerChoixQuestionMultiplayerLocalBP.class.getSimpleName() ) )
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
        for ( int i = 0; i < maxPlayers; i++ )
        {
            List< BasicCard > cards = d.getCards();
            Collections.shuffle( cards );
            Player player = new Player( cards );
            //joueur.setScorePlayer( 0 );
            TextInputDialog userNameDialogPlayer = new TextInputDialog();
            userNameDialogPlayer.setTitle( String.format( "Player %d - Insert your nickname", i + 1 ) );
            userNameDialogPlayer.setHeaderText( String.format( "Player %d - Insert your nickname", i + 1 ) );
            userNameDialogPlayer.setContentText( String.format( "Please, insert your nickname player %d:", i + 1 ) );
            Optional< String > userNamePlayer = userNameDialogPlayer.showAndWait();
            if ( userNamePlayer.isPresent() && !userNamePlayer.get().isEmpty() )
            {
                player.setNickNamePlayer( String.format( "%s", userNamePlayer.get() ) );
            }
            else
            {
                player.setNickNamePlayer( String.format( "User-%d", i + 1 ) );
            }
            players.add( player );
        }
        
        getNewGameButton().setOnAction( new EventHandler< ActionEvent >()
        {
            @Override
            public void handle( ActionEvent arg0 )
            {
                for ( int i = 0; i < lobbyMultiLocalSP.getChildren().size(); i++ )
                {
                    if ( lobbyMultiLocalSP.getChildren().get( i ).getClass().getSimpleName()
                            .equals( JouerChoixQuestionMultiplayerLocalBP.class.getSimpleName() ) )
                    {
                        lobbyMultiLocalSP.getChildren().remove( i );
                    }
                }
                for( Player p : players )
                {
                    List< BasicCard > cards = d.getCards();
                    Collections.shuffle( cards );
                    p.setCards( cards );
                }
                lobbyMultiLocalSP.getChildren().add( new JouerChoixQuestionMultiplayerLocalBP( d, players, s ,m) );
                setVisibleNode( JouerChoixQuestionMultiplayerLocalBP.class.getSimpleName() );
            }
        } );

        getReturnButton().setOnAction( new EventHandler< ActionEvent >()
        {
            @Override
            public void handle( ActionEvent arg0 )
            {
                MenuMultiplayerBP mmbp = ( ( MenuMultiplayerBP ) getParent().getParent() );
                mmbp.setVisibleNode( MenuMultiplayerMainVB.class.getSimpleName() );
            }
        } );
        lobbyMultiLocalSP.getChildren().add( new LobbyMultiLocalMainBP() );

        setCenter( lobbyMultiLocalSP );
    }
    
    public void setVisibleNode( String nodeName )
    {
        for( Node n : lobbyMultiLocalSP.getChildren() )
        {
            if( n.getClass().getSimpleName().equals( nodeName ) )
            {
                n.setVisible( true );
            }
            else
            {
                n.setVisible( false );
            }
        }
    }

    protected class LobbyMultiLocalMainBP extends BorderPane
    {
        public LobbyMultiLocalMainBP()
        {
            VBox choiceBox = new VBox();
            choiceBox.getChildren().addAll( getNewGameButton(), getReturnButton() );
            choiceBox.setAlignment(Pos.CENTER);
            choiceBox.setSpacing(25.);
            setCenter( choiceBox );
        }
    }
}
