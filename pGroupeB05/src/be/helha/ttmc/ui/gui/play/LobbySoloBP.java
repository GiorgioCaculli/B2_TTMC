package be.helha.ttmc.ui.gui.play;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import be.helha.ttmc.model.BasicCard;
import be.helha.ttmc.model.Deck;
import be.helha.ttmc.serialization.Serialization;
import be.helha.ttmc.ui.GUIConstant;
import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.play.MenuPlayBP.MenuPlayMainVB;
import be.helha.ttmc.ui.gui.util.MusicGestion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class LobbySoloBP extends BorderPane
{
    private StackPane lobbyPaneSP;

    private Button newGameButton;
    private Button loadGameButton;
    private Button returnButton;

    private String nickName;
    private Deck d;

    private Settings s;
    private MusicGestion m;

    public LobbySoloBP( Deck d, Settings s, MusicGestion m )
    {
        this.d = d;
        this.s = s;
        this.m = m;
        for ( int i = 0; i < getLobbyPaneSP().getChildren().size(); i++ )
        {
            if ( getLobbyPaneSP().getChildren().get( i ).getClass().getSimpleName()
                    .equals( JouerChoixQuestionBP.class.getSimpleName() ) )
            {
                getLobbyPaneSP().getChildren().remove( i );
            }
        }
        TextInputDialog userNameDialog = new TextInputDialog();
        userNameDialog.setTitle( GUIConstant.DIALOG_PLAY_TITLE );
        userNameDialog.setHeaderText( GUIConstant.DIALOG_PLAY_CONTENT );
        userNameDialog.setContentText( GUIConstant.DIALOG_PLAY_MESSAGE );
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

        getLobbyPaneSP().getChildren().addAll( new LobbySoloMainBP() );

        setCenter( getLobbyPaneSP() );
    }
    
    public StackPane getLobbyPaneSP()
    {
        if( lobbyPaneSP == null )
        {
            lobbyPaneSP = new StackPane();
        }
        return lobbyPaneSP;
    }

    public void setVisibleNode( String nodeName )
    {
        for ( Node n : getLobbyPaneSP().getChildren() )
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
        private List< Button > buttons;

        public LobbySoloMainBP()
        {
            buttons = new ArrayList<>();

            buttons.add( getNewGameButton() );
            buttons.add( getLoadGameButton() );
            buttons.add( getReturnButton() );

            for ( Button b : buttons )
            {
                b.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
                b.setEffect( GUIConstant.BUTTON_EFFECT );
                b.setTextFill( GUIConstant.BUTTON_GRADIENT );
                b.setStyle( GUIConstant.BUTTON_STYLE );
                b.setFont( GUIConstant.BUTTON_TEXT );
                b.setMaxWidth( s.getWidth() - 55. );
                b.setMinHeight( s.getHeight() / ( buttons.size() + 1 ) );
            }

            VBox choiceBox = new VBox();
            choiceBox.setPadding( new Insets( 20 ) );
            choiceBox.setSpacing( 50 );
            choiceBox.getChildren().addAll( buttons );
            choiceBox.setAlignment( Pos.CENTER );

            setCenter( choiceBox );
            setStyle( GUIConstant.WINDOW_STYLE );
        }
    }

    public Button getNewGameButton()
    {
        if ( newGameButton == null )
        {

            newGameButton = new Button( GUIConstant.BUTTON_NEW_GAME );
            newGameButton.setOnAction( new EventHandler< ActionEvent >()
            {
                @Override
                public void handle( ActionEvent arg0 )
                {
                    for ( int i = 0; i < getLobbyPaneSP().getChildren().size(); i++ )
                    {
                        if ( getLobbyPaneSP().getChildren().get( i ).getClass().getSimpleName()
                                .equals( JouerChoixQuestionBP.class.getSimpleName() ) )
                        {
                            getLobbyPaneSP().getChildren().remove( i );
                        }
                    }
                    Deck deck = d.clone();
                    if ( nickName.equals( "giorgio" ) || nickName.equals( "guillaume" ) || nickName.equals( "tanguy" ) )
                    {
                        deck = Serialization.loadDeck( String.format( "assets/decks/%s.json", nickName ).toString() );
                    }
                    JouerChoixQuestionBP jcq = new JouerChoixQuestionBP( deck, s, m );
                    jcq.setScore( 0 );
                    jcq.setNickName( String.format( "%s", nickName ) );
                    jcq.getLblScore().setText( String.format( "User: %s - Score: ", jcq.getNickName() ) );
                    getLobbyPaneSP().getChildren().add( jcq );
                    setVisibleNode( jcq.getClass().getSimpleName() );
                }
            } );
        }
        return newGameButton;
    }

    public Button getLoadGameButton()
    {
        if ( loadGameButton == null )
        {
            loadGameButton = new Button( GUIConstant.BUTTON_LOAD_GAME );
            loadGameButton.setOnAction( new EventHandler< ActionEvent >()
            {
                @Override
                public void handle( ActionEvent arg0 )
                {
                    FileChooser fc = new FileChooser();
                    fc.setTitle( GUIConstant.BUTTON_IMPORT_DECK );
                    fc.getExtensionFilters().add( new ExtensionFilter( "JSON File", "*.json" ) );
                    Stage stage = ( Stage ) getScene().getWindow();
                    File f = fc.showOpenDialog( stage );
                    if ( f == null )
                    {
                        return;
                    }
                    for ( int i = 0; i < getLobbyPaneSP().getChildren().size(); i++ )
                    {
                        if ( getLobbyPaneSP().getChildren().get( i ).getClass().getSimpleName()
                                .equals( JouerChoixQuestionBP.class.getSimpleName() ) )
                        {
                            getLobbyPaneSP().getChildren().remove( i );
                        }
                    }
                    Deck deck = Serialization.loadDeck( f.getAbsolutePath() );
                    JouerChoixQuestionBP jcq = new JouerChoixQuestionBP( deck, s, m );
                    jcq.setScore( 0 );
                    jcq.setNickName( String.format( "%s", nickName ) );
                    jcq.getLblScore().setText( String.format( "User: %s - Score: ", jcq.getNickName() ) );
                    getLobbyPaneSP().getChildren().add( jcq );
                    setVisibleNode( jcq.getClass().getSimpleName() );
                }
            } );
        }
        return loadGameButton;
    }

    public Button getReturnButton()
    {
        if ( returnButton == null )
        {

            returnButton = new Button( GUIConstant.BUTTON_RETURN );
            returnButton.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    MenuPlayBP mpbp = ( ( MenuPlayBP ) getParent().getParent() );
                    mpbp.setVisibleNode( MenuPlayMainVB.class.getSimpleName() );
                }
            } );
        }
        return returnButton;
    }
}
