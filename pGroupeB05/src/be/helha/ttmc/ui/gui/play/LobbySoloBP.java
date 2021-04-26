package be.helha.ttmc.ui.gui.play;

import java.util.Optional;

import be.helha.ttmc.model.Deck;
import be.helha.ttmc.serialization.Serialization;
import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.play.MenuPlayBP.MenuPlayMainVB;
import be.helha.ttmc.ui.gui.util.MusicGestion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class LobbySoloBP extends BorderPane
{
    private StackPane lobbyPaneSP = new StackPane();

    private Button newGameButton ;
    private Button loadGameButton;
    private Button returnButton;

    private String nickName;
    private Deck d;
    
    private Settings s;
    private MusicGestion m;
    
    

    public Button getNewGameButton() {
    	if(newGameButton== null) {
    		
    		newGameButton= new Button( "New Game" );
    		Font txt= Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 100);
    		newGameButton.setFont(txt);
    		newGameButton.setMaxWidth(s.getWidth()-55.);
    		newGameButton.setMinHeight(s.getHeight()/4);
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
                    JouerChoixQuestionBP jcq = new JouerChoixQuestionBP( deck, s ,m);
                    jcq.setScore( 0 );
                    jcq.setNickName( String.format( "%s", nickName ) );
                    jcq.getLblScore().setText( String.format( "User: %s - Score: ", jcq.getNickName() ) );
                    lobbyPaneSP.getChildren().add( jcq );
                    setVisibleNode( jcq.getClass().getSimpleName() );
                }
            } );
    	}
		return newGameButton;
	}

	public Button getLoadGameButton() {
		if(loadGameButton==null) {
			loadGameButton = new Button( "Load Game" );
			Font txt= Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 100);
			loadGameButton.setFont(txt);
			loadGameButton.setMaxWidth(s.getWidth()-55.);
			loadGameButton.setMinHeight(s.getHeight()/4);
		}
		return loadGameButton;
	}

	public Button getReturnButton() {
		if(returnButton== null) {
			returnButton = new Button( "Return" );
			Font txt= Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 100);
			returnButton.setFont(txt);
			returnButton.setMaxWidth(s.getWidth()-55.);
			returnButton.setMinHeight(s.getHeight()/4);
			returnButton.setOnAction( new EventHandler< ActionEvent >()
	        {

	            @Override
	            public void handle( ActionEvent arg0 )
	            {
	                MenuPlayBP mpbp =  ( ( MenuPlayBP ) getParent().getParent() );
	                mpbp.setVisibleNode( MenuPlayMainVB.class.getSimpleName() );
	            }
	        } );
		}
		return returnButton;
	}

	public LobbySoloBP( Deck d, Settings s, MusicGestion m )
    {
		this.d= d;
        this.s = s;
        this.m= m;
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
        
        
        lobbyPaneSP.getChildren().addAll( new LobbySoloMainBP() );
     
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
            choiceBox.getChildren().addAll( getNewGameButton(), getLoadGameButton(), getReturnButton() );
            choiceBox.setAlignment(Pos.CENTER);
            choiceBox.setSpacing(5.);
            
            setCenter( choiceBox );
        }
    }
}
