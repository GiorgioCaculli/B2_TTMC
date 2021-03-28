package be.helha.ttmc.ui.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import be.helha.ttmc.model.BasicCard;
import be.helha.ttmc.model.Deck;
import be.helha.ttmc.model.Theme;
import be.helha.ttmc.ui.gui.MenuPlayBP.MenuPlayMainVB;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class JouerChoixQuestionMultiplayerBP extends BorderPane
{
    private Deck d;
    private Label lblScore;
    private Button returnButton;

    private StackPane jouerChoixQuestionMainSP;

    private Button btnVal;
    private MenuPauseFP mpfp;
    private static final int PLAYER_1_PANE = 1;
    private static final int PLAYER_2_PANE = 0;
    private List< Joueur > players;
    private int maxPlayers = 2;

    public JouerChoixQuestionMultiplayerBP( Deck d )
    {
        this.d = d;
        Deck d1 = d.clone();
        players = new ArrayList<>();
        for ( int i = 0; i < maxPlayers; i++ )
        {
            List< BasicCard > cards = d1.getCards();
            Collections.shuffle( cards );
            Joueur joueur = new Joueur( cards, i );
            //joueur.setScorePlayer( 0 );
            TextInputDialog userNameDialogPlayer = new TextInputDialog();
            userNameDialogPlayer.setTitle( "Insert your nickname" );
            userNameDialogPlayer.setHeaderText( "Insert your nickname" );
            userNameDialogPlayer.setContentText( "Please, insert your nickname:" );
            Optional< String > userNamePlayer = userNameDialogPlayer.showAndWait();
            if ( userNamePlayer.get().isEmpty() )
            {
                Random randId = new Random( 999 );
                joueur.setNickNamePlayer( String.format( "User-%d", randId.nextInt() ) );
            }
            else
            {
                joueur.setNickNamePlayer( String.format( "%s", userNamePlayer.get() ) );
            }
            //jcqm.getLblScore().setText( String.format( ": %s - %s:", jcqm.getNickNamePlayer1(), jcqm.getNickNamePlayer2() ) );
            players.add( joueur );
        }
        /*HBox scoreBox = new HBox();
        scoreBox.getChildren().addAll( getScorePlayer(), getLblScore(), getScorePlayer2() );
        scoreBox.setSpacing( 20 );
        scoreBox.setPadding( new Insets( 0, 10, 0, 5 ) );
        scoreBox.setAlignment( Pos.CENTER );
        setTop( scoreBox );*/
        //initCardPane( cards, cardNb );
        initCardPane( players, maxPlayers );
        getJouerChoixQuestionMainBP().getChildren().add( getMenuPauseFP() );
        getJouerChoixQuestionMainBP().getChildren().get( 2 ).setVisible( false );
        //setBottom( getReturnButton() ); // ESCAPE BUTTON
        ColorAdjust col = new ColorAdjust( 0, -0.9, -0.5, 0 );
        //GaussianBlur blur = new GaussianBlur( 15 ); // LENT
        BoxBlur blur = new BoxBlur();
        blur.setWidth( 15 );
        blur.setHeight( 15 );
        blur.setIterations( 3 );
        col.setInput( blur );

        setOnKeyPressed( new EventHandler< KeyEvent >()
        {
            @Override
            public void handle( KeyEvent keyEvent )
            {
                if ( keyEvent.getCode() == KeyCode.ESCAPE )
                {
                    for ( int i = 0; i < players.size(); i++ )
                    {
                        getJouerChoixQuestionMainBP().getChildren().get( i ).setEffect( blur );
                    }
                    getJouerChoixQuestionMainBP().getChildren().get( 2 ).setVisible( true );
                    MenuPauseFP mpfp = ( ( MenuPauseFP ) getJouerChoixQuestionMainBP().getChildren().get( 2 ) );
                    mpfp.getBtnResume().setOnAction( new EventHandler< ActionEvent >()
                    {
                        @Override
                        public void handle( ActionEvent arg0 )
                        {
                            for ( int i = 0; i < players.size(); i++ )
                            {
                                CardPaneJoueur cpj = ( ( CardPaneJoueur ) ( getJouerChoixQuestionMainBP().getChildren().get( i ) ) );
                                System.out.println( cpj.getCardChoicePanePlayer().getChildren().get( cpj.getCardChoicePanePlayer().getChildren().size() - 1 ) );
                                if ( cpj.getCardChoicePanePlayer().getChildren().get( cpj.getCardChoicePanePlayer().getChildren().size() - 1 ).isVisible() )
                                {
                                    players.get( i ).getCardPaneJoueur().getReponsesBP().get( i ).getAnimationPlayer().start();
                                    //cpj.getAnimationPlayer().start();
                                }
                                getJouerChoixQuestionMainBP().getChildren().get( i ).setEffect( blur );
                            }
                            for ( int i = 0; i < players.size(); i++ )
                            {
                                getJouerChoixQuestionMainBP().getChildren().get( i ).setEffect( null );
                            }
                            getJouerChoixQuestionMainBP().getChildren().get( 2 ).setVisible( false );

                        }
                    } );
                    for ( int i = 0; i < players.size(); i++ )
                    {
                        CardPaneJoueur cpj = ( ( CardPaneJoueur ) ( getJouerChoixQuestionMainBP().getChildren().get( i ) ) );
                        if ( cpj.getCardChoicePanePlayer().getChildren().get( cpj.getCardChoicePanePlayer().getChildren().size() - 1 ).isVisible() )
                        {
                            players.get( i ).getCardPaneJoueur().getReponsesBP().get( i ).getAnimationPlayer().stop();
                            //cpj.getAnimationPlayer().stop();
                        }
                        getJouerChoixQuestionMainBP().getChildren().get( i ).setEffect( blur );
                    }
                    mpfp.getBtnBack().setOnAction( new EventHandler< ActionEvent >()
                    {

                        @Override
                        public void handle( ActionEvent arg0 )
                        {
                            MenuPlayBP mp = ( MenuPlayBP ) getParent().getParent().getParent().getParent();
                            mp.setVisibleNode( MenuPlayMainVB.class.getSimpleName() );
                        }
                    } );
                    keyEvent.consume();
                }
            }
        } );
    }

    private StackPane getJouerChoixQuestionMainBP()
    {
        if ( jouerChoixQuestionMainSP == null )
        {
            jouerChoixQuestionMainSP = new StackPane();
        }
        return jouerChoixQuestionMainSP;
    }

    private MenuPauseFP getMenuPauseFP()
    {
        if ( mpfp == null )
        {
            mpfp = new MenuPauseFP();
        }
        return mpfp;
    }

    private class JeuReponseBP extends BorderPane
    {
        private ImageView sabImPlayer;
        private AnimationTimer animationPlayer;
        private int timePlayer;
        private long elapsedTimePlayer;
        private Label lblTimePlayer;
        private TextField txtRepPlayer;
        private Label lblQuestionPlayer;
        private int id;

        public JeuReponseBP( BasicCard bcPlayer, int id )
        {
            setID( id );
            timePlayer = 30;
            //BorderPane jeuReponsePane = new BorderPane();
            HBox vbTimePlayer = new HBox();
            vbTimePlayer.setPadding( new Insets( 5 ) );
            vbTimePlayer.setAlignment( Pos.CENTER );
            //vbTime.setStyle( "-fx-background-color: DAE6F3;-fx-font-size: 25pt;" );
            for ( Theme t : Theme.values() )
            {
                if ( bcPlayer.getTheme() == t )
                {
                    vbTimePlayer.setStyle( String.format( "-fx-background-color: %s;-fx-font-size: 25pt;", t.backgroundColor().get( bcPlayer.getTheme() ) ) );
                }
            }
            vbTimePlayer.getChildren().addAll( getSabImPlayer(), getLblTimePlayer() );

            VBox vbPlayer = new VBox();
            vbPlayer.setPadding( new Insets( 20 ) );
            for ( Theme t : Theme.values() )
            {
                if ( bcPlayer.getTheme() == t )
                {
                    vbPlayer.setStyle( String.format( "-fx-background-color: %s;-fx-font-size: 25pt;", t.backgroundColor().get( bcPlayer.getTheme() ) ) );
                }
            }
            vbPlayer.setAlignment( Pos.CENTER );
            vbPlayer.getChildren().addAll( vbTimePlayer, getLblQuestionPlayer(), getTxtRepPlayer()/*, getBtnVal()*/ );
            getTxtRepPlayer().setText( "" );
            //jeuReponsePane.setCenter( vbPlayer );
            setCenter( vbPlayer );
        }

        public void setID( int id )
        {
            this.id = id;
        }

        public int getID()
        {
            return id;
        }

        public ImageView getSabImPlayer()
        {
            if ( sabImPlayer == null )
            {
                sabImPlayer = new ImageView( "be/helha/ttmc/assets/images/sablier.png" );
                sabImPlayer.setFitHeight( 50 );
                sabImPlayer.setFitWidth( 50 );
                KeyValue val = new KeyValue( sabImPlayer.rotateProperty(), 360 );
                Timeline t = new Timeline( new KeyFrame( Duration.seconds( 0.9 ), val ), new KeyFrame( Duration.seconds( 1 ), val ) );
                t.setCycleCount( Timeline.INDEFINITE );
                t.play();
            }
            return sabImPlayer;
        }

        public int getTimePlayer()
        {
            return timePlayer;
        }

        public Label getLblTimePlayer()
        {
            if ( lblTimePlayer == null )
            {
                lblTimePlayer = new Label( String.format( "%d", getTimePlayer() ) );
            }
            return lblTimePlayer;
        }

        public AnimationTimer getAnimationPlayer()
        {
            if ( animationPlayer == null )
            {
                elapsedTimePlayer = System.nanoTime();
                animationPlayer = new AnimationTimer()
                {

                    @Override
                    public void handle( long now )
                    {
                        if ( now - elapsedTimePlayer > 1000000000 )
                        {
                            timePlayer--;
                            getLblTimePlayer().setText( String.format( "%d", timePlayer ) );
                            elapsedTimePlayer = now;
                            if ( timePlayer == 0 )
                            {
                                //checkAnswerPlayer( now );
                            }
                        }

                    }
                };
            }

            return animationPlayer;
        }

        public TextField getTxtRepPlayer()
        {
            if ( txtRepPlayer == null )
            {
                txtRepPlayer = new TextField();
            }
            return txtRepPlayer;
        }

        public void setLblQuestionPlayer( String str )
        {
            lblQuestionPlayer.setText( str );
        }

        public Label getLblQuestionPlayer()
        {
            if ( lblQuestionPlayer == null )
            {
                lblQuestionPlayer = new Label( "test" );
                lblQuestionPlayer.setTextAlignment( TextAlignment.CENTER );
                lblQuestionPlayer.setWrapText( true );
            }
            return lblQuestionPlayer;
        }

    }

    private class CardPaneJoueur extends BorderPane
    {
        private Label lblThemePlayer;
        private Label lblSujetPlayer;
        private List< Button > choixPlayer;

        private BasicCard bcPlayer;
        private StackPane cardChoicePanePlayer;

        private List< JeuReponseBP > reponsesBP;
        private int id;
        private int idQuestion;

        public CardPaneJoueur( BasicCard bcPlayer, int id )
        {
            reponsesBP = new ArrayList<>();
            this.bcPlayer = bcPlayer;
            setID( id );
            getLblThemePlayer().setText( bcPlayer.getTheme().toString() );
            getLblSujetPlayer().setText( bcPlayer.getSubject().toString() );
            FlowPane fpPlayer = new FlowPane();
            fpPlayer.setPadding( new Insets( 5 ) );
            fpPlayer.setVgap( 5 );
            fpPlayer.setHgap( 5 );
            fpPlayer.setPrefWrapLength( 505 );
            fpPlayer.setAlignment( Pos.CENTER );
            for ( Theme t : Theme.values() )
            {
                if ( bcPlayer.getTheme() == t )
                {
                    fpPlayer.setStyle( String.format( "-fx-background-color: %s;-fx-font-size: 25pt;", t.backgroundColor().get( bcPlayer.getTheme() ) ) );
                }
            }

            int j = 0;
            for ( Button b : getChoixPlayer() )
            {
                reponsesBP.add( new JeuReponseBP( bcPlayer, j ) );
                getReponsesBP().get( j ).getTxtRepPlayer().setOnKeyPressed( new EventHandler< KeyEvent >()
                {

                    @Override
                    public void handle( KeyEvent keyEvent )
                    {
                        if ( keyEvent.getCode() == KeyCode.ENTER && !getCardChoicePanePlayer().getChildren().get( getCardChoicePanePlayer().getChildren().size() - 1 ).isVisible() )
                        {
                            checkAnswerPlayer( keyEvent, id );
                        }
                    }
                } );
                getCardChoicePanePlayer().getChildren().add( getReponsesBP().get( j ) );
                fpPlayer.getChildren().add( b );
                j++;
            }

            VBox vbPlayer = new VBox();
            vbPlayer.setPadding( new Insets( 20 ) );
            vbPlayer.setSpacing( 10 );
            vbPlayer.getChildren().addAll( getLblThemePlayer(), getLblSujetPlayer() );
            // vb.setStyle("-fx-font-size: 25pt;");

            VBox vb2Player = new VBox();
            vb2Player.getChildren().addAll( vbPlayer );
            vb2Player.setStyle( "-fx-font-size: 25pt;" );

            setTop( vb2Player );
            setCenter( getCardChoicePanePlayer() );
            getCardChoicePanePlayer().getChildren().add( fpPlayer );
        }

        public int getID()
        {
            return id;
        }

        public void setID( int id )
        {
            this.id = id;
        }

        public List< JeuReponseBP > getReponsesBP()
        {
            return reponsesBP;
        }

        public void setIdQuestion( int idQuestion )
        {
            this.idQuestion = idQuestion;
        }

        public int getIdQuestion()
        {
            return idQuestion;
        }

        public StackPane getCardChoicePanePlayer()
        {
            if ( cardChoicePanePlayer == null )
            {
                cardChoicePanePlayer = new StackPane();
            }
            return cardChoicePanePlayer;
        }

        public List< Button > getChoixPlayer()
        {
            if ( choixPlayer == null )
            {
                choixPlayer = new ArrayList<>();
                int maxBtn = 4;
                for ( int i = 0; i < maxBtn; i++ )
                {
                    Button b = new Button( "Question Level: " + ( i + 1 ) );
                    b.setMinSize( 250, 250 );
                    b.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
                    int idQ = i;
                    b.setOnAction( new EventHandler< ActionEvent >()
                    {
                        @Override
                        public void handle( ActionEvent arg0 )
                        {
                            setIdQuestion( idQ );
                            ( (JeuReponseBP) getCardChoicePanePlayer().getChildren().get( idQ )).setLblQuestionPlayer( bcPlayer.getQuestions().get( idQ ).getChallenge() );
                            ( (JeuReponseBP) getCardChoicePanePlayer().getChildren().get( idQ )).getAnimationPlayer().start();
                            getCardChoicePanePlayer().getChildren().get( maxBtn ).setVisible( false );
                            getCardChoicePanePlayer().getChildren().get( idQ ).setVisible( true );
                        }
                    } );
                    choixPlayer.add( b );
                }
            }
            return choixPlayer;
        }

        public void setLblThemePlayer( String str )
        {
            lblThemePlayer.setText( str );
        }

        public void setLblSujetPlayer( String str )
        {
            lblSujetPlayer.setText( str );
        }

        public Label getLblScore()
        {
            if ( lblScore == null )
                lblScore = new Label( "Score : " );
            return lblScore;
        }

        public Label getLblThemePlayer()
        {
            if ( lblThemePlayer == null )
            {
                lblThemePlayer = new Label( "Theme" );
                lblThemePlayer.setMaxWidth( Double.MAX_VALUE );
                lblThemePlayer.setAlignment( Pos.CENTER );
            }
            return lblThemePlayer;
        }

        public Label getLblSujetPlayer()
        {
            if ( lblSujetPlayer == null )
            {
                lblSujetPlayer = new Label( "Subject" );
                lblSujetPlayer.setMaxWidth( Double.MAX_VALUE );
                lblSujetPlayer.setAlignment( Pos.CENTER );
            }
            return lblSujetPlayer;
        }

        public Button getReturnButton()
        {
            if ( returnButton == null )
            {
                returnButton = new Button( "Back to Main Menu" );
                returnButton.setMaxWidth( Double.MAX_VALUE );
                returnButton.setAlignment( Pos.CENTER );
                returnButton.setOnAction( new EventHandler< ActionEvent >()
                {

                    @Override
                    public void handle( ActionEvent arg0 )
                    {
                        reponsesBP.get( id ).getAnimationPlayer().stop();
                        MenuPlayBP mpbp = ( MenuPlayBP ) getParent().getParent();
                        mpbp.setVisibleNode( MenuPlayMainVB.class.getSimpleName() );
                    }
                } );
            }
            return returnButton;
        }

        public Button getBtnVal()
        {
            if ( btnVal == null )
            {
                btnVal = new Button( "Valider!" );
                btnVal.setOnAction( new EventHandler< ActionEvent >()
                {

                    @Override
                    public void handle( ActionEvent arg0 )
                    {
                        checkAnswerPlayer( arg0, id );
                    }
                } );
            }
            return btnVal;
        }

        public BasicCard getBC()
        {
            return bcPlayer.clone();
        }

        private void checkAnswerPlayer( Object o, int playerID )
        {
            players.get( playerID ).setCardNb( players.get( playerID ).getCardNb() + 1 );
            players.get( playerID ).getCardPaneJoueur().getReponsesBP().get( idQuestion ).getAnimationPlayer().stop();
            System.out.println( ( (JeuReponseBP) getCardChoicePanePlayer().getChildren().get( idQuestion )).getTxtRepPlayer().getText() );
            Alert alert = new Alert( AlertType.INFORMATION );
            alert.setTitle( "Resultats" );
            String path;
            if ( players.get( playerID ).getCardNb() >= players.get( playerID ).getCards().size() )
            {
                alert.setContentText( String.format( "All questions have been answered, you scored %d points. Thank you for playing!", players.get( playerID ).getScore() ) );
                path = "/be/helha/ttmc/assets/images/banana.gif";
                getJouerChoixQuestionMainBP().getChildren().get( PLAYER_1_PANE ).setVisible( false );
                getJouerChoixQuestionMainBP().getChildren().get( PLAYER_2_PANE ).setVisible( true );
            }
            else
            {
                if ( players.get( playerID ).getCardPaneJoueur().getReponsesBP().get( idQuestion ).getTxtRepPlayer().getText().equalsIgnoreCase( bcPlayer.getQuestions().get( players.get( playerID ).getCardPaneJoueur().getIdQuestion() ).getAnswer() ) )
                {
                    alert.setContentText( "Brava tu as reussi !" );
                    path = "/be/helha/ttmc/assets/images/banana.gif";
                    players.get( playerID ).setScorePlayer( players.get( playerID ).getScore() + players.get( playerID ).getCardPaneJoueur().getIdQuestion() + 1 );
                    //newScorePlayer += ( getIDPlayer() + 1 );
                    //setScorePlayer( newScorePlayer );
                }
                else
                {
                    alert.setContentText( "La reponse etait : " + bcPlayer.getQuestions().get( players.get( playerID ).getCardPaneJoueur().getIdQuestion() ).getAnswer() );
                    path = "/be/helha/ttmc/assets/images/sonicPleure.gif";
                }
            }
            ImageView icon = new ImageView( path );
            icon.setFitHeight( 64 );
            icon.setFitWidth( 64 );
            alert.getDialogPane().setGraphic( icon );
            alert.setHeaderText( null );
            if ( o instanceof ActionEvent || o instanceof KeyEvent )
                alert.showAndWait();
            else if ( o instanceof Long )
                alert.show();
            if ( players.get( playerID ).getCardNb() < players.get( playerID ).getCards().size() )
            {
                if ( getJouerChoixQuestionMainBP().getChildren().get( PLAYER_1_PANE ).isVisible() )
                {
                    System.out.println( "true" );
                    getJouerChoixQuestionMainBP().getChildren().get( PLAYER_1_PANE ).setVisible( false );
                    getJouerChoixQuestionMainBP().getChildren().get( PLAYER_2_PANE ).setVisible( true );
                }
                else
                {
                    System.out.println( "false" );
                    initCardPane( players, maxPlayers );
                }
            }

        }
    }

    private class Joueur
    {
        private List< BasicCard > cards;
        private int cardNb = 0;
        private int idPlayer;
        private String nickNamePlayer;
        private Label scorePlayer;

        private int newScorePlayer = 0;
        private CardPaneJoueur cardPane;

        public Joueur( List< BasicCard > cards, int id )
        {
            setIDPlayer( id );
            setCards( cards );
        }
        
        public void setCards( List< BasicCard> cards )
        {
            this.cards = cards;
        }
        
        public List< BasicCard > getCards()
        {
            return cards;
        }

        public int getCardNb()
        {
            return cardNb;
        }

        public void setCardNb( int cardNb )
        {
            this.cardNb = cardNb;
        }

        public int getIDPlayer()
        {
            return idPlayer;
        }

        public void setIDPlayer( int idPlayer )
        {
            this.idPlayer = idPlayer;
        }

        public void setNickNamePlayer( String nickNamePlayer )
        {
            this.nickNamePlayer = nickNamePlayer;
        }

        public String getNickNamePlayer1()
        {
            return nickNamePlayer;
        }

        public int getScore()
        {
            return newScorePlayer;
        }

        public Label getScorePlayer()
        {
            if ( scorePlayer == null )
                scorePlayer = new Label( String.format( "%d", newScorePlayer ) );
            return scorePlayer;
        }

        public void setScorePlayer( int newScorePlayer )
        {
            scorePlayer.setText( String.format( "%d", newScorePlayer ) );
        }

        public CardPaneJoueur getCardPaneJoueur()
        {
            return cardPane;
        }

        public void setCardPaneJoueur( CardPaneJoueur cardPane )
        {
            this.cardPane = cardPane;
        }
    }

    private void initCardPane( List< Joueur > players, int nbJoueurs )
    {
        for( int j = 0; j < players.size(); j++ )
        {
            System.out.println( players.get( j ).getCardNb() );
        }
        if ( getJouerChoixQuestionMainBP().getChildren().size() < nbJoueurs )
        {
            for ( int j = 0; j < players.size(); j++ )
            {
                players.get( j ).setCardPaneJoueur( new CardPaneJoueur( players.get( j ).getCards().get( players.get( j ).getCardNb() ), j ) );
                getJouerChoixQuestionMainBP().getChildren().add( players.get( j ).getCardPaneJoueur() );
            }
        }
        else
        {
            for ( int j = 0; j < players.size(); j++ )
            {
                players.get( j ).setCardPaneJoueur( new CardPaneJoueur( players.get( j ).getCards().get( players.get( j ).getCardNb() ), j ) );
                getJouerChoixQuestionMainBP().getChildren().set( j, players.get( j ).getCardPaneJoueur() );
            }
        }
        getJouerChoixQuestionMainBP().getChildren().get( PLAYER_2_PANE ).setVisible( false );
        setCenter( getJouerChoixQuestionMainBP() );
    }

}
