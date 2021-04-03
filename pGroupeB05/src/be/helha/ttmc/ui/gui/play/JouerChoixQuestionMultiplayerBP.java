package be.helha.ttmc.ui.gui.play;

import java.util.ArrayList;
import java.util.List;

import be.helha.ttmc.model.BasicCard;
import be.helha.ttmc.model.Deck;
import be.helha.ttmc.model.Theme;
import be.helha.ttmc.ui.Player;
import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.MenuPauseFP;
import be.helha.ttmc.ui.gui.play.LobbyMultiLocalBP.LobbyMultiLocalMainBP;
import be.helha.ttmc.ui.gui.play.LobbyMultiOnlineBP.LobbyMultiOnlineMainBP;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class JouerChoixQuestionMultiplayerBP extends BorderPane
{
    private StackPane jouerChoixQuestionMainSP;

    private MenuPauseFP mpfp;
    private List< Joueur > joueurs;
    private int maxPlayers;
    private int currentlyPlaying;
    private Settings settings;

    public JouerChoixQuestionMultiplayerBP( Deck d, List< Player > players, Settings settings )
    {
        this.settings = settings;
        this.maxPlayers = players.size();
        joueurs = new ArrayList<>();
        for( Player p : players )
        {
            Joueur j = new Joueur( p.getCards() );
            j.setNickNamePlayer( p.getNickNamePlayer() );
            j.setScorePlayer( 0 );
            joueurs.add( j );
        }
        initCardPane( joueurs, maxPlayers );
        getJouerChoixQuestionMainSP().getChildren().add( getMenuPauseFP() );
        getJouerChoixQuestionMainSP().getChildren().get( maxPlayers ).setVisible( false );
        ColorAdjust col = new ColorAdjust( 0, -0.9, -0.5, 0 );
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
                    requestFocus();
                    getJouerChoixQuestionMainSP().getChildren().get( maxPlayers ).setVisible( true );
                    MenuPauseFP mpfp = ( ( MenuPauseFP ) getJouerChoixQuestionMainSP().getChildren()
                            .get( maxPlayers ) );
                    mpfp.getBtnResume().setOnAction( new EventHandler< ActionEvent >()
                    {
                        @Override
                        public void handle( ActionEvent arg0 )
                        {
                            CardPaneJoueur cpj = ( ( CardPaneJoueur ) ( getJouerChoixQuestionMainSP().getChildren()
                                    .get( currentlyPlaying ) ) );
                            if ( cpj.getReponsesBP().get( cpj.getIdQuestion() ).isVisible() )
                            {
                                getJouerChoixQuestionMainSP().getChildren().get( currentlyPlaying ).setEffect( null );
                                cpj.getReponsesBP().get( cpj.getIdQuestion() ).getAnimationPlayer().start();
                            }
                            getJouerChoixQuestionMainSP().getChildren().get( maxPlayers ).setVisible( false );

                        }
                    } );
                    CardPaneJoueur cpj = ( ( CardPaneJoueur ) ( getJouerChoixQuestionMainSP().getChildren()
                            .get( currentlyPlaying ) ) );
                    if ( cpj.getReponsesBP().get( cpj.getIdQuestion() ).isVisible() )
                    {
                        getJouerChoixQuestionMainSP().getChildren().get( currentlyPlaying ).setEffect( blur );
                        cpj.getReponsesBP().get( cpj.getIdQuestion() ).getAnimationPlayer().stop();
                    }
                    mpfp.getBtnBack().setOnAction( new EventHandler< ActionEvent >()
                    {

                        @Override
                        public void handle( ActionEvent arg0 )
                        {
                            if( getParent().getParent() instanceof LobbyMultiLocalBP )
                            {
                                LobbyMultiLocalBP lmlbp = ( LobbyMultiLocalBP ) getParent().getParent();
                                lmlbp.setVisibleNode( LobbyMultiLocalMainBP.class.getSimpleName() );
                            }
                            else if ( getParent().getParent() instanceof LobbyMultiOnlineBP )
                            {
                                LobbyMultiOnlineBP lmobp = ( LobbyMultiOnlineBP ) getParent().getParent();
                                lmobp.setVisibleNode( LobbyMultiOnlineMainBP.class.getSimpleName() );
                            }
                        }
                    } );
                    keyEvent.consume();
                }
            }
        } );
        setFocused( true );
        setFocusTraversable( true );
    }

    private StackPane getJouerChoixQuestionMainSP()
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
        private BasicCard bcPlayer;
        private Button btnVal;

        public JeuReponseBP( BasicCard bcPlayer, int id )
        {
            this.bcPlayer = bcPlayer;
            setID( id );
            timePlayer = settings.getTimerSeconds();
            HBox vbTimePlayer = new HBox();
            vbTimePlayer.setPadding( new Insets( 5 ) );
            vbTimePlayer.setAlignment( Pos.CENTER );
            for ( Theme t : Theme.values() )
            {
                if ( bcPlayer.getTheme() == t )
                {
                    vbTimePlayer.setStyle( String.format( "-fx-background-color: %s;-fx-font-size: 25pt;",
                            t.backgroundColor().get( bcPlayer.getTheme() ) ) );
                }
            }
            vbTimePlayer.getChildren().addAll( getSabImPlayer(), getLblTimePlayer() );

            VBox vbPlayer = new VBox();
            vbPlayer.setPadding( new Insets( 20 ) );
            for ( Theme t : Theme.values() )
            {
                if ( bcPlayer.getTheme() == t )
                {
                    vbPlayer.setStyle( String.format( "-fx-background-color: %s;-fx-font-size: 25pt;",
                            t.backgroundColor().get( bcPlayer.getTheme() ) ) );
                }
            }
            vbPlayer.setAlignment( Pos.CENTER );
            vbPlayer.getChildren().addAll( vbTimePlayer, getLblQuestionPlayer(), getTxtRepPlayer(), getBtnVal() );
            getTxtRepPlayer().setText( "" );
            setCenter( vbPlayer );
        }

        public void setID( int id )
        {
            this.id = id;
        }

        public ImageView getSabImPlayer()
        {
            if ( sabImPlayer == null )
            {
                sabImPlayer = new ImageView( "be/helha/ttmc/assets/images/sablier.png" );
                sabImPlayer.setFitHeight( 50 );
                sabImPlayer.setFitWidth( 50 );
                KeyValue val = new KeyValue( sabImPlayer.rotateProperty(), 360 );
                Timeline t = new Timeline( new KeyFrame( Duration.seconds( 0.9 ), val ),
                        new KeyFrame( Duration.seconds( 1 ), val ) );
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
                                joueurs.get( currentlyPlaying ).getCardPaneJoueur().checkAnswerPlayer( now, currentlyPlaying );
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
                lblQuestionPlayer = new Label( "Question: Test" );
                lblQuestionPlayer.setTextAlignment( TextAlignment.CENTER );
                lblQuestionPlayer.setWrapText( true );
            }
            return lblQuestionPlayer;
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
                        joueurs.get( currentlyPlaying ).getCardPaneJoueur().checkAnswerPlayer( arg0, currentlyPlaying );
                    }
                } );
            }
            return btnVal;
        }

        public boolean equals( Object o )
        {
            if ( o instanceof JeuReponseBP )
            {
                JeuReponseBP tmp = ( JeuReponseBP ) o;
                return tmp.id == id && tmp.bcPlayer == bcPlayer;
            }
            return false;
        }

    }

    protected class CardPaneJoueur extends BorderPane
    {
        private Label lblThemePlayer;
        private Label lblSujetPlayer;

        private BasicCard bcPlayer;
        private StackPane cardChoicePanePlayer;

        private List< JeuReponseBP > reponsesBP;
        private int id;
        private int idQuestion;
        private int maxBtn = 4;
        private Label lblScore;

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
                    fpPlayer.setStyle( String.format( "-fx-background-color: %s;-fx-font-size: 25pt;",
                            t.backgroundColor().get( bcPlayer.getTheme() ) ) );
                }
            }

            for ( int j = 0; j < maxBtn; j++ )
            {
                JeuReponseBP jeuRep = new JeuReponseBP( bcPlayer, j );
                jeuRep.setLblQuestionPlayer( bcPlayer.getQuestions().get( j ).getChallenge() );
                jeuRep.getTxtRepPlayer().setOnKeyPressed( new EventHandler< KeyEvent >()
                {

                    @Override
                    public void handle( KeyEvent keyEvent )
                    {
                        if ( keyEvent.getCode() == KeyCode.ENTER && getReponsesBP().get( getIdQuestion() ).isVisible()
                                && !getCardChoicePanePlayer().getChildren().get( maxBtn ).isVisible() )
                        {
                            checkAnswerPlayer( keyEvent, id );
                        }
                    }
                } );
                getReponsesBP().add( jeuRep );
                getCardChoicePanePlayer().getChildren().add( jeuRep );
                int idQ = j;
                Button b = new Button( "Question Level: " + ( idQ + 1 ) );
                b.setMinSize( 100, 100 );
                b.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
                b.setOnAction( new EventHandler< ActionEvent >()
                {
                    @Override
                    public void handle( ActionEvent arg0 )
                    {
                        setIdQuestion( idQ );
                        getReponsesBP().get( getIdQuestion() ).getAnimationPlayer().start();
                        for ( int i = 0; i <= maxBtn; i++ )
                        {
                            if ( i == idQ )
                            {
                                getCardChoicePanePlayer().getChildren().get( i ).setVisible( true );
                            }
                            else
                            {
                                getCardChoicePanePlayer().getChildren().get( i ).setVisible( false );
                            }
                        }
                    }
                } );
                fpPlayer.getChildren().add( b );
            }

            HBox scoreBox = new HBox();
            Region espaceVideNicknameScore = new Region();
            HBox.setHgrow( espaceVideNicknameScore, Priority.ALWAYS );
            scoreBox.getChildren().addAll( new Label( String.format( "%s", joueurs.get( id ).getNickNamePlayer() ) ),
                    espaceVideNicknameScore, getLblScore() );

            VBox vbPlayer = new VBox();
            vbPlayer.setPadding( new Insets( 20 ) );
            vbPlayer.setSpacing( 10 );
            vbPlayer.getChildren().addAll( getLblThemePlayer(), getLblSujetPlayer(), scoreBox );

            VBox vb2Player = new VBox();
            vb2Player.getChildren().addAll( vbPlayer );
            vb2Player.setStyle( "-fx-font-size: 25pt;" );

            setTop( vb2Player );
            setCenter( getCardChoicePanePlayer() );
            getCardChoicePanePlayer().getChildren().add( fpPlayer );
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

        public Label getLblScore()
        {
            if ( lblScore == null )
                lblScore = new Label( String.format( "Score: %2d", joueurs.get( id ).getScorePlayer() ) );
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

        private void checkAnswerPlayer( Object o, int playerID )
        {
            joueurs.get( playerID ).setCardNb( joueurs.get( playerID ).getCardNb() + 1 );
            joueurs.get( playerID ).getCardPaneJoueur().getReponsesBP().get( idQuestion ).getAnimationPlayer().stop();
            Alert alert = new Alert( AlertType.INFORMATION );
            alert.setTitle( "Resultats" );
            String path;
            if ( joueurs.get( playerID ).getCardNb() >= joueurs.get( playerID ).getCards().size() )
            {
                alert.setContentText(
                        String.format( "All questions have been answered, you scored %d points. Thank you for playing!",
                                joueurs.get( playerID ).getScorePlayer() ) );
                path = "/be/helha/ttmc/assets/images/banana.gif";
                if ( playerID < maxPlayers - 1 )
                {
                    getJouerChoixQuestionMainSP().getChildren().get( playerID ).setVisible( false );
                    getJouerChoixQuestionMainSP().getChildren().get( playerID + 1 ).setVisible( true );
                }
                else
                {
                    if( getParent().getParent().getParent().getParent() instanceof LobbyMultiLocalBP )
                    {
                        LobbyMultiLocalBP lmlbp = ( LobbyMultiLocalBP ) getParent().getParent().getParent().getParent();
                        lmlbp.setVisibleNode( LobbyMultiLocalMainBP.class.getSimpleName() );
                    }
                    if( getParent().getParent().getParent().getParent() instanceof LobbyMultiOnlineBP )
                    {
                        LobbyMultiOnlineBP lmobp = ( LobbyMultiOnlineBP ) getParent().getParent().getParent().getParent();
                        lmobp.setVisibleNode( LobbyMultiOnlineMainBP.class.getSimpleName() );
                    }
                }
            }
            else
            {
                if ( joueurs.get( playerID ).getCardPaneJoueur().getReponsesBP().get( idQuestion ).getTxtRepPlayer()
                        .getText().equalsIgnoreCase( bcPlayer.getQuestions()
                                .get( joueurs.get( playerID ).getCardPaneJoueur().getIdQuestion() ).getAnswer() ) )
                {
                    alert.setContentText( "Brava tu as reussi !" );
                    path = "/be/helha/ttmc/assets/images/banana.gif";
                    joueurs.get( playerID ).setScorePlayer( joueurs.get( playerID ).getScorePlayer()
                            + joueurs.get( playerID ).getCardPaneJoueur().getIdQuestion() + 1 );
                    getLblScore().setText( String.format( "Score: %2d", joueurs.get( playerID ).getScorePlayer() ) );
                }
                else
                {
                    alert.setContentText( "La reponse etait : " + bcPlayer.getQuestions()
                            .get( joueurs.get( playerID ).getCardPaneJoueur().getIdQuestion() ).getAnswer() );
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
            if ( joueurs.get( playerID ).getCardNb() < joueurs.get( playerID ).getCards().size() )
            {
                if ( playerID < maxPlayers - 1
                        && getJouerChoixQuestionMainSP().getChildren().get( playerID ).isVisible() )
                {
                    currentlyPlaying++;
                    getJouerChoixQuestionMainSP().getChildren().get( playerID ).setVisible( false );
                    getJouerChoixQuestionMainSP().getChildren().get( playerID + 1 ).setVisible( true );
                }
                else
                {
                    currentlyPlaying = 0;
                    initCardPane( joueurs, maxPlayers );
                }
            }

        }
    }

    protected class Joueur extends Player
    {
        private CardPaneJoueur cardPane;

        public Joueur( List< BasicCard > cards )
        {
            super( cards );
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

    private void initCardPane( List< Joueur > joueurs, int nbJoueurs )
    {
        if ( getJouerChoixQuestionMainSP().getChildren().size() < nbJoueurs )
        {
            for ( int j = 0; j < nbJoueurs; j++ )
            {
                joueurs.get( j ).setCardPaneJoueur(
                        new CardPaneJoueur( joueurs.get( j ).getCards().get( joueurs.get( j ).getCardNb() ), j ) );
                getJouerChoixQuestionMainSP().getChildren().add( joueurs.get( j ).getCardPaneJoueur() );
                if ( j > 0 )
                {
                    getJouerChoixQuestionMainSP().getChildren().get( j ).setVisible( false );
                }
            }
        }
        else
        {
            for ( int j = 0; j < nbJoueurs; j++ )
            {
                joueurs.get( j ).setCardPaneJoueur(
                        new CardPaneJoueur( joueurs.get( j ).getCards().get( joueurs.get( j ).getCardNb() ), j ) );
                getJouerChoixQuestionMainSP().getChildren().set( j, joueurs.get( j ).getCardPaneJoueur() );
                if ( j > 0 )
                {
                    getJouerChoixQuestionMainSP().getChildren().get( j ).setVisible( false );
                }
            }
        }
        setCenter( getJouerChoixQuestionMainSP() );
    }

}
