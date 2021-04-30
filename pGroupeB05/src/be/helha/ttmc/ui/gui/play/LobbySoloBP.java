package be.helha.ttmc.ui.gui.play;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import be.helha.ttmc.model.Deck;
import be.helha.ttmc.serialization.Serialization;
import be.helha.ttmc.ui.GUIConstant;
import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.play.MenuPlayBP.MenuPlayMainVB;
import be.helha.ttmc.ui.gui.util.MusicGestion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
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

public class LobbySoloBP extends BorderPane
{
    private StackPane lobbyPaneSP = new StackPane();

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
        for ( int i = 0; i < lobbyPaneSP.getChildren().size(); i++ )
        {
            if ( lobbyPaneSP.getChildren().get( i ).getClass().getSimpleName()
                    .equals( JouerChoixQuestionBP.class.getSimpleName() ) )
            {
                lobbyPaneSP.getChildren().remove( i );
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
        private List< Button > buttons;
        private Font txt = Font.font( "Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 100 );
        private Effect buttonEffect = new DropShadow( 25, 13, 13, Color.DARKSLATEGREY );
        private String buttonStyle = "-fx-background-color: plum;";

        private Stop[] etapes =
        { new Stop( 0, Color.BLUEVIOLET ), new Stop( 0.3, Color.ROYALBLUE ), new Stop( 0.7, Color.LIGHTSTEELBLUE ) };
        private LinearGradient gradiant = new LinearGradient( 0, 1, 0, 0, true, CycleMethod.NO_CYCLE, etapes );

        public LobbySoloMainBP()
        {
            buttons = new ArrayList<>();

            buttons.add( getNewGameButton() );
            buttons.add( getLoadGameButton() );
            buttons.add( getReturnButton() );

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

            VBox choiceBox = new VBox();
            choiceBox.getChildren().addAll( buttons );
            choiceBox.setAlignment( Pos.CENTER );
            choiceBox.setSpacing( 25. );

            setCenter( choiceBox );
            setStyle( "-fx-background-color: mediumslateblue" );
        }
    }

    public Button getNewGameButton()
    {
        if ( newGameButton == null )
        {

            newGameButton = new Button( "New Game" );
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
                    JouerChoixQuestionBP jcq = new JouerChoixQuestionBP( deck, s, m );
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

    public Button getLoadGameButton()
    {
        if ( loadGameButton == null )
        {
            loadGameButton = new Button( "Load Game" );
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
