package be.helha.ttmc.ui.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private List< BasicCard > cards;
    private List< BasicCard > cardsPlayer1;
    private List< BasicCard > cardsPlayer2;
    private Label lblThemePlayer1;
    private Label lblSujetPlayer1;
    private Label lblThemePlayer2;
    private Label lblSujetPlayer2;
    private List< Button > choixPlayer1;
    private List< Button > choixPlayer2;
    private Label scorePlayer1;
    private Label scorePlayer2;
    private Label lblScore;
    private Button returnButton;
    private Deck d;

    private int newScorePlayer1 = 0;
    private int newScorePlayer2 = 0;

    private BasicCard bcPlayer1;
    private BasicCard bcPlayer2;
    private StackPane cardChoicePanePlayer1;
    private StackPane cardChoicePanePlayer2;
    private StackPane jouerChoixQuestionMainSP;

    private Label lblQuestionPlayer1;
    private Label lblQuestionPlayer2;
    private TextField txtRepPlayer1;
    private TextField txtRepPlayer2;
    private Button btnVal;

    private ImageView sabImPlayer1;
    private ImageView sabImPlayer2;
    private AnimationTimer animationPlayer1;
    private AnimationTimer animationPlayer2;
    private int timePlayer1;
    private int timePlayer2;
    private long elapsedTimePlayer1;
    private long elapsedTimePlayer2;
    private Label lblTimePlayer1;
    private Label lblTimePlayer2;
    private int idPlayer1;
    private int idPlayer2;
    private int cardNb = 0;
    private MenuPauseFP mpfp;
    private BorderPane cardPanePlayer1;
    private BorderPane cardPanePlayer2;
    private String nickNamePlayer1;
    private String nickNamePlayer2;
    private static final int PLAYER_1_PANE = 1;
    private static final int PLAYER_2_PANE = 0;

    public JouerChoixQuestionMultiplayerBP( Deck d )
    {
        this.d = d;
        Deck d1 = d.clone();
        Deck d2 = d.clone();
        cards = d1.getCards();
        Collections.shuffle( cards );
        cardsPlayer1 = new ArrayList<>();
        for ( BasicCard c : cards )
        {
            cardsPlayer1.add( c );
        }
        cards = d2.getCards();
        Collections.shuffle( cards );
        cardsPlayer2 = new ArrayList<>();
        for ( BasicCard c : cards )
        {
            cardsPlayer2.add( c );
        }
        HBox scoreBox = new HBox();
        scoreBox.getChildren().addAll( getScorePlayer1(), getLblScore(), getScorePlayer2() );
        scoreBox.setSpacing( 20 );
        scoreBox.setPadding( new Insets( 0, 10, 0, 5 ) );
        scoreBox.setAlignment( Pos.CENTER );
        setTop( scoreBox );
        initCardPane( cardsPlayer1, cardsPlayer2, cardNb );
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
                    cardPanePlayer1.setEffect( blur );
                    cardPanePlayer2.setEffect( blur );
                    getJouerChoixQuestionMainBP().getChildren().get( 2 ).setVisible( true );
                    ( ( MenuPauseFP ) getJouerChoixQuestionMainBP().getChildren().get( 2 ) ).getBtnResume()
                            .setOnAction( new EventHandler< ActionEvent >()
                            {
                                @Override
                                public void handle( ActionEvent arg0 )
                                {
                                    if ( !cardChoicePanePlayer1.getChildren()
                                            .get( cardChoicePanePlayer1.getChildren().size() - 1 ).isVisible() )
                                    {
                                        getAnimationPlayer1().start();
                                    }
                                    if ( !cardChoicePanePlayer2.getChildren()
                                            .get( cardChoicePanePlayer2.getChildren().size() - 1 ).isVisible() )
                                    {
                                        getAnimationPlayer2().start();
                                    }
                                    cardPanePlayer1.setEffect( null );
                                    cardPanePlayer2.setEffect( null );
                                    getJouerChoixQuestionMainBP().getChildren().get( 2 ).setVisible( false );

                                }
                            } );
                    if ( !cardChoicePanePlayer1.getChildren().get( cardChoicePanePlayer1.getChildren().size() - 1 )
                            .isVisible() )
                    {
                        getAnimationPlayer1().stop();
                    }
                    if ( !cardChoicePanePlayer2.getChildren().get( cardChoicePanePlayer2.getChildren().size() - 1 )
                            .isVisible() )
                    {
                        getAnimationPlayer2().stop();
                    }
                    ( ( MenuPauseFP ) getJouerChoixQuestionMainBP().getChildren().get( 2 ) ).getBtnBack()
                            .setOnAction( new EventHandler< ActionEvent >()
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

    private class JeuReponsePlayer1BP extends BorderPane
    {
        public JeuReponsePlayer1BP( BasicCard bc )
        {
            HBox vbTimePlayer1 = new HBox();
            vbTimePlayer1.setPadding( new Insets( 5 ) );
            vbTimePlayer1.setAlignment( Pos.CENTER );
            //vbTime.setStyle( "-fx-background-color: DAE6F3;-fx-font-size: 25pt;" );
            for ( Theme t : Theme.values() )
            {
                if ( bc.getTheme() == t )
                {
                    vbTimePlayer1.setStyle( String.format( "-fx-background-color: %s;-fx-font-size: 25pt;",
                            t.backgroundColor().get( bc.getTheme() ) ) );
                }
            }
            vbTimePlayer1.getChildren().addAll( getSabImPlayer1(), getLblTimePlayer1() );

            VBox vbPlayer1 = new VBox();
            vbPlayer1.setPadding( new Insets( 20 ) );
            for ( Theme t : Theme.values() )
            {
                if ( bc.getTheme() == t )
                {
                    vbPlayer1.setStyle( String.format( "-fx-background-color: %s;-fx-font-size: 25pt;",
                            t.backgroundColor().get( bc.getTheme() ) ) );
                }
            }
            vbPlayer1.setAlignment( Pos.CENTER );
            vbPlayer1.getChildren().addAll( vbTimePlayer1, getLblQuestionPlayer1(),
                    getTxtRepPlayer1()/*, getBtnVal()*/ );
            getTxtRepPlayer1().setText( "" );
            setCenter( vbPlayer1 );
        }
    }

    private class JeuReponsePlayer2BP extends BorderPane
    {
        public JeuReponsePlayer2BP( BasicCard bc )
        {
            HBox vbTimePlayer2 = new HBox();
            vbTimePlayer2.setPadding( new Insets( 5 ) );
            vbTimePlayer2.setAlignment( Pos.CENTER );
            //vbTime.setStyle( "-fx-background-color: DAE6F3;-fx-font-size: 25pt;" );
            for ( Theme t : Theme.values() )
            {
                if ( bc.getTheme() == t )
                {
                    vbTimePlayer2.setStyle( String.format( "-fx-background-color: %s;-fx-font-size: 25pt;",
                            t.backgroundColor().get( bc.getTheme() ) ) );
                }
            }
            vbTimePlayer2.getChildren().addAll( getSabImPlayer2(), getLblTimePlayer2() );

            VBox vbPlayer2 = new VBox();
            vbPlayer2.setPadding( new Insets( 20 ) );
            for ( Theme t : Theme.values() )
            {
                if ( bc.getTheme() == t )
                {
                    vbPlayer2.setStyle( String.format( "-fx-background-color: %s;-fx-font-size: 25pt;",
                            t.backgroundColor().get( bc.getTheme() ) ) );
                }
            }
            vbPlayer2.setAlignment( Pos.CENTER );
            vbPlayer2.getChildren().addAll( vbTimePlayer2, getLblQuestionPlayer2(),
                    getTxtRepPlayer2()/*, getBtnVal()*/ );
            getTxtRepPlayer2().setText( "" );
            setCenter( vbPlayer2 );
        }
    }

    private class CardPaneJoueur extends BorderPane
    {
        public CardPaneJoueur( List< BasicCard > cardsPlayer1, List< BasicCard > cardsPlayer2, int i )
        {
            cardPanePlayer1 = new BorderPane();
            cardPanePlayer2 = new BorderPane();
            timePlayer1 = 30;
            timePlayer2 = 30;
            bcPlayer1 = cardsPlayer1.get( i );
            bcPlayer2 = cardsPlayer2.get( i );
            cardChoicePanePlayer1 = new StackPane();
            cardChoicePanePlayer2 = new StackPane();
            getLblThemePlayer1().setText( bcPlayer1.getTheme().toString() );
            getLblSujetPlayer1().setText( bcPlayer1.getSubject().toString() );
            getLblThemePlayer2().setText( bcPlayer2.getTheme().toString() );
            getLblSujetPlayer2().setText( bcPlayer2.getSubject().toString() );
            FlowPane fpPlayer1 = new FlowPane();
            fpPlayer1.setPadding( new Insets( 5 ) );
            fpPlayer1.setVgap( 5 );
            fpPlayer1.setHgap( 5 );
            fpPlayer1.setPrefWrapLength( 505 );
            fpPlayer1.setAlignment( Pos.CENTER );
            FlowPane fpPlayer2 = new FlowPane();
            fpPlayer2.setPadding( new Insets( 5 ) );
            fpPlayer2.setVgap( 5 );
            fpPlayer2.setHgap( 5 );
            fpPlayer2.setPrefWrapLength( 505 );
            fpPlayer2.setAlignment( Pos.CENTER );
            for ( Theme t : Theme.values() )
            {
                if ( bcPlayer1.getTheme() == t )
                {
                    fpPlayer1.setStyle( String.format( "-fx-background-color: %s;-fx-font-size: 25pt;",
                            t.backgroundColor().get( bcPlayer1.getTheme() ) ) );
                }
            }
            for ( Theme t : Theme.values() )
            {
                if ( bcPlayer2.getTheme() == t )
                {
                    fpPlayer2.setStyle( String.format( "-fx-background-color: %s;-fx-font-size: 25pt;",
                            t.backgroundColor().get( bcPlayer2.getTheme() ) ) );
                }
            }

            for ( Button b : getChoixPlayer1() )
            {
                cardChoicePanePlayer1.getChildren().add( new JeuReponsePlayer1BP( bcPlayer1 ) );
                fpPlayer1.getChildren().add( b );
            }

            for ( Button b : getChoixPlayer2() )
            {
                cardChoicePanePlayer2.getChildren().add( new JeuReponsePlayer2BP( bcPlayer2 ) );
                fpPlayer2.getChildren().add( b );
            }

            VBox vbPlayer1 = new VBox();
            vbPlayer1.setPadding( new Insets( 20 ) );
            vbPlayer1.setSpacing( 10 );
            vbPlayer1.getChildren().addAll( getLblThemePlayer1(), getLblSujetPlayer1() );
            // vb.setStyle("-fx-font-size: 25pt;");

            /*HBox hbPlayer1 = new HBox();
            hbPlayer1.getChildren().addAll( new Label( getScorePlayer1().getText() ), getLblScore(), new Label( getScorePlayer2().getText() ) );
            hbPlayer1.setSpacing( 20 );
            hbPlayer1.setPadding( new Insets( 0, 10, 0, 5 ) );
            hbPlayer1.setAlignment( Pos.CENTER );*/

            VBox vb2Player1 = new VBox();
            vb2Player1.getChildren().addAll( vbPlayer1/*, hbPlayer1*/ );
            vb2Player1.setStyle( "-fx-font-size: 25pt;" );
            cardPanePlayer1.setTop( vb2Player1 );
            cardPanePlayer1.setCenter( cardChoicePanePlayer1 );

            VBox vbPlayer2 = new VBox();
            vbPlayer2.setPadding( new Insets( 20 ) );
            vbPlayer2.setSpacing( 10 );
            vbPlayer2.getChildren().addAll( getLblThemePlayer2(), getLblSujetPlayer2() );
            // vb.setStyle("-fx-font-size: 25pt;");

            /*HBox hbPlayer2 = new HBox();
            hbPlayer2.getChildren().addAll( new Label( getScorePlayer1().getText() ), getLblScorePlayer2(), new Label( getScorePlayer2().getText() ) );
            hbPlayer2.setSpacing( 20 );
            hbPlayer2.setPadding( new Insets( 0, 10, 0, 5 ) );
            hbPlayer2.setAlignment( Pos.CENTER );*/

            VBox vb2Player2 = new VBox();
            vb2Player2.getChildren().addAll( vbPlayer2/*, hbPlayer2*/ );
            vb2Player2.setStyle( "-fx-font-size: 25pt;" );
            cardChoicePanePlayer1.getChildren().add( fpPlayer1 );
            cardChoicePanePlayer2.getChildren().add( fpPlayer2 );
            cardPanePlayer2.setTop( vb2Player2 );
            cardPanePlayer2.setCenter( cardChoicePanePlayer2 );
        }
    }

    private void initCardPane( List< BasicCard > cardsPlayer1, List< BasicCard > cardsPlayer2, int i )
    {
        new CardPaneJoueur( cardsPlayer1, cardsPlayer2, i );
        if ( getJouerChoixQuestionMainBP().getChildren().size() < 2 )
        {
            getJouerChoixQuestionMainBP().getChildren().add( cardPanePlayer2 );
            getJouerChoixQuestionMainBP().getChildren().add( cardPanePlayer1 );
        }
        else
        {
            getJouerChoixQuestionMainBP().getChildren().set( PLAYER_2_PANE, cardPanePlayer2 );
            getJouerChoixQuestionMainBP().getChildren().set( PLAYER_1_PANE, cardPanePlayer1 );
        }
        getJouerChoixQuestionMainBP().getChildren().get( PLAYER_2_PANE ).setVisible( false );
        setCenter( getJouerChoixQuestionMainBP() );
    }

    public int getIDPlayer1()
    {
        return idPlayer1;
    }

    public void setIDPlayer1( int idPlayer1 )
    {
        this.idPlayer1 = idPlayer1;
    }

    public int getIDPlayer2()
    {
        return idPlayer2;
    }

    public void setIDPlayer2( int idPlayer2 )
    {
        this.idPlayer2 = idPlayer2;
    }

    public List< Button > getChoixPlayer1()
    {
        if ( choixPlayer1 == null )
        {
            choixPlayer1 = new ArrayList<>();
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
                        setIDPlayer1( idQ );
                        setLblQuestionPlayer1( bcPlayer1.getQuestions().get( idQ ).getChallenge() );
                        getAnimationPlayer1().start();
                        cardChoicePanePlayer1.getChildren().get( maxBtn ).setVisible( false );
                        cardChoicePanePlayer1.getChildren().get( idQ ).setVisible( true );
                    }
                } );
                choixPlayer1.add( b );
            }
        }
        return choixPlayer1;
    }

    public List< Button > getChoixPlayer2()
    {
        if ( choixPlayer2 == null )
        {
            choixPlayer2 = new ArrayList<>();
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
                        setIDPlayer2( idQ );
                        setLblQuestionPlayer2( bcPlayer2.getQuestions().get( idQ ).getChallenge() );
                        getAnimationPlayer2().start();
                        cardChoicePanePlayer2.getChildren().get( maxBtn ).setVisible( false );
                        cardChoicePanePlayer2.getChildren().get( idQ ).setVisible( true );
                    }
                } );
                choixPlayer2.add( b );
            }
        }
        return choixPlayer2;
    }

    public void setLblThemePlayer1( String str )
    {
        lblThemePlayer1.setText( str );
    }

    public void setLblSujetPlayer1( String str )
    {
        lblSujetPlayer1.setText( str );
    }

    public void setLblThemePlayer2( String str )
    {
        lblThemePlayer2.setText( str );
    }

    public void setLblSujetPlayer2( String str )
    {
        lblSujetPlayer2.setText( str );
    }

    public Label getLblScore()
    {
        if ( lblScore == null )
            lblScore = new Label( "Score : " );
        return lblScore;
    }

    public Label getScorePlayer1()
    {
        if ( scorePlayer1 == null )
            scorePlayer1 = new Label( String.format( "%d", newScorePlayer1 ) );
        return scorePlayer1;
    }

    public void setScorePlayer1( int newScorePlayer1 )
    {
        scorePlayer1.setText( String.format( "%d", newScorePlayer1 ) );
    }

    public Label getScorePlayer2()
    {
        if ( scorePlayer2 == null )
            scorePlayer2 = new Label( String.format( "%d", newScorePlayer2 ) );
        return scorePlayer2;
    }

    public void setScorePlayer2( int newScorePlayer2 )
    {
        scorePlayer2.setText( String.format( "%d", newScorePlayer2 ) );
    }

    public Label getLblThemePlayer1()
    {
        if ( lblThemePlayer1 == null )
        {
            lblThemePlayer1 = new Label( "Theme" );
            lblThemePlayer1.setMaxWidth( Double.MAX_VALUE );
            lblThemePlayer1.setAlignment( Pos.CENTER );
        }
        return lblThemePlayer1;
    }

    public Label getLblSujetPlayer1()
    {
        if ( lblSujetPlayer1 == null )
        {
            lblSujetPlayer1 = new Label( "Subject" );
            lblSujetPlayer1.setMaxWidth( Double.MAX_VALUE );
            lblSujetPlayer1.setAlignment( Pos.CENTER );
        }
        return lblSujetPlayer1;
    }

    public Label getLblThemePlayer2()
    {
        if ( lblThemePlayer2 == null )
        {
            lblThemePlayer2 = new Label( "Theme" );
            lblThemePlayer2.setMaxWidth( Double.MAX_VALUE );
            lblThemePlayer2.setAlignment( Pos.CENTER );
        }
        return lblThemePlayer2;
    }

    public Label getLblSujetPlayer2()
    {
        if ( lblSujetPlayer2 == null )
        {
            lblSujetPlayer2 = new Label( "Subject" );
            lblSujetPlayer2.setMaxWidth( Double.MAX_VALUE );
            lblSujetPlayer2.setAlignment( Pos.CENTER );
        }
        return lblSujetPlayer2;
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
                    getAnimationPlayer1().stop();
                    getAnimationPlayer2().stop();
                    MenuPlayBP mpbp = ( MenuPlayBP ) getParent().getParent();
                    mpbp.setVisibleNode( MenuPlayMainVB.class.getSimpleName() );
                }
            } );
        }
        return returnButton;
    }

    public AnimationTimer getAnimationPlayer1()
    {
        if ( animationPlayer1 == null )
        {
            elapsedTimePlayer1 = System.nanoTime();
            animationPlayer1 = new AnimationTimer()
            {

                @Override
                public void handle( long now )
                {
                    if ( now - elapsedTimePlayer1 > 1000000000 )
                    {
                        timePlayer1--;
                        getLblTimePlayer1().setText( String.format( "%d", timePlayer1 ) );
                        elapsedTimePlayer1 = now;
                        if ( timePlayer1 == 0 )
                        {
                            checkAnswerPlayer1( now );
                        }
                    }

                }
            };
        }

        return animationPlayer1;
    }

    public AnimationTimer getAnimationPlayer2()
    {
        if ( animationPlayer2 == null )
        {
            elapsedTimePlayer2 = System.nanoTime();
            animationPlayer2 = new AnimationTimer()
            {

                @Override
                public void handle( long now )
                {
                    if ( now - elapsedTimePlayer2 > 1000000000 )
                    {
                        timePlayer2--;
                        getLblTimePlayer2().setText( String.format( "%d", timePlayer2 ) );
                        elapsedTimePlayer2 = now;
                        if ( timePlayer2 == 0 )
                        {
                            checkAnswerPlayer2( now );
                        }
                    }

                }
            };
        }

        return animationPlayer2;
    }

    public int getTimePlayer1()
    {
        return timePlayer1;
    }

    public int getTimePlayer2()
    {
        return timePlayer2;
    }

    public ImageView getSabImPlayer1()
    {
        if ( sabImPlayer1 == null )
        {
            sabImPlayer1 = new ImageView( "be/helha/ttmc/assets/images/sablier.png" );
            sabImPlayer1.setFitHeight( 50 );
            sabImPlayer1.setFitWidth( 50 );
            KeyValue val = new KeyValue( sabImPlayer1.rotateProperty(), 360 );
            Timeline t = new Timeline( new KeyFrame( Duration.seconds( 0.9 ), val ),
                    new KeyFrame( Duration.seconds( 1 ), val ) );
            t.setCycleCount( Timeline.INDEFINITE );
            t.play();
        }
        return sabImPlayer1;
    }

    public ImageView getSabImPlayer2()
    {
        if ( sabImPlayer2 == null )
        {
            sabImPlayer2 = new ImageView( "be/helha/ttmc/assets/images/sablier.png" );
            sabImPlayer2.setFitHeight( 50 );
            sabImPlayer2.setFitWidth( 50 );
            KeyValue val = new KeyValue( sabImPlayer2.rotateProperty(), 360 );
            Timeline t = new Timeline( new KeyFrame( Duration.seconds( 0.9 ), val ),
                    new KeyFrame( Duration.seconds( 1 ), val ) );
            t.setCycleCount( Timeline.INDEFINITE );
            t.play();
        }
        return sabImPlayer2;
    }

    public Label getLblTimePlayer1()
    {
        if ( lblTimePlayer1 == null )
        {
            lblTimePlayer1 = new Label( String.format( "%d", getTimePlayer1() ) );
        }
        return lblTimePlayer1;
    }

    public Label getLblTimePlayer2()
    {
        if ( lblTimePlayer2 == null )
        {
            lblTimePlayer2 = new Label( String.format( "%d", getTimePlayer2() ) );
        }
        return lblTimePlayer2;
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
                    checkAnswerPlayer1( arg0 );
                }
            } );
        }
        return btnVal;
    }

    public Label getLblQuestionPlayer1()
    {
        if ( lblQuestionPlayer1 == null )
        {
            lblQuestionPlayer1 = new Label( "test" );
            lblQuestionPlayer1.setTextAlignment( TextAlignment.CENTER );
            lblQuestionPlayer1.setWrapText( true );
        }
        return lblQuestionPlayer1;
    }

    public Label getLblQuestionPlayer2()
    {
        if ( lblQuestionPlayer2 == null )
        {
            lblQuestionPlayer2 = new Label( "test" );
            lblQuestionPlayer2.setTextAlignment( TextAlignment.CENTER );
            lblQuestionPlayer2.setWrapText( true );
        }
        return lblQuestionPlayer2;
    }

    public TextField getTxtRepPlayer1()
    {
        if ( txtRepPlayer1 == null )
        {
            txtRepPlayer1 = new TextField();
            txtRepPlayer1.setOnKeyPressed( new EventHandler< KeyEvent >()
            {

                @Override
                public void handle( KeyEvent keyEvent )
                {
                    if ( keyEvent.getCode() == KeyCode.ENTER && !cardChoicePanePlayer1.getChildren()
                            .get( cardChoicePanePlayer1.getChildren().size() - 1 ).isVisible() )
                    {
                        checkAnswerPlayer1( keyEvent );
                    }
                }
            } );
        }
        return txtRepPlayer1;
    }

    public TextField getTxtRepPlayer2()
    {
        if ( txtRepPlayer2 == null )
        {
            txtRepPlayer2 = new TextField();
            txtRepPlayer2.setOnKeyPressed( new EventHandler< KeyEvent >()
            {

                @Override
                public void handle( KeyEvent keyEvent )
                {
                    if ( keyEvent.getCode() == KeyCode.ENTER && !cardChoicePanePlayer2.getChildren()
                            .get( cardChoicePanePlayer2.getChildren().size() - 1 ).isVisible() )
                    {
                        checkAnswerPlayer2( keyEvent );
                    }
                }
            } );
        }
        return txtRepPlayer2;
    }

    public void setLblQuestionPlayer1( String str )
    {
        lblQuestionPlayer1.setText( str );
    }

    public void setLblQuestionPlayer2( String str )
    {
        lblQuestionPlayer2.setText( str );
    }

    public BasicCard getBC()
    {
        return bcPlayer1.clone();
    }

    public void setNickNamePlayer1( String nickNamePlayer1 )
    {
        this.nickNamePlayer1 = nickNamePlayer1;
    }

    public String getNickNamePlayer1()
    {
        return nickNamePlayer1;
    }

    public void setNickNamePlayer2( String nickNamePlayer2 )
    {
        this.nickNamePlayer2 = nickNamePlayer2;
    }

    public String getNickNamePlayer2()
    {
        return nickNamePlayer2;
    }

    private void checkAnswerPlayer1( Object o )
    {
        cardNb++;
        getAnimationPlayer1().stop();
        Alert alert = new Alert( AlertType.INFORMATION );
        alert.setTitle( "Resultats" );
        String path;
        if ( cardNb >= cardsPlayer1.size() )
        {
            alert.setContentText(
                    String.format( "All questions have been answered, you scored %d points. Thank you for playing!",
                            newScorePlayer1 ) );
            path = "/be/helha/ttmc/assets/images/banana.gif";
            getJouerChoixQuestionMainBP().getChildren().get( PLAYER_1_PANE ).setVisible( false );
            getJouerChoixQuestionMainBP().getChildren().get( PLAYER_2_PANE ).setVisible( true );
        }
        else
        {
            if ( getTxtRepPlayer1().getText()
                    .equalsIgnoreCase( bcPlayer1.getQuestions().get( getIDPlayer1() ).getAnswer() ) )
            {
                alert.setContentText( "Brava tu as reussi !" );
                path = "/be/helha/ttmc/assets/images/banana.gif";
                newScorePlayer1 += ( getIDPlayer1() + 1 );
                setScorePlayer1( newScorePlayer1 );
            }
            else
            {
                alert.setContentText(
                        "La reponse etait : " + bcPlayer1.getQuestions().get( getIDPlayer1() ).getAnswer() );
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
        if ( cardNb < cardsPlayer1.size() )
        {
            getJouerChoixQuestionMainBP().getChildren().get( PLAYER_1_PANE ).setVisible( false );
            getJouerChoixQuestionMainBP().getChildren().get( PLAYER_2_PANE ).setVisible( true );
        }

    }

    private void checkAnswerPlayer2( Object o )
    {
        getAnimationPlayer2().stop();
        Alert alert = new Alert( AlertType.INFORMATION );
        Alert scoreAlert = new Alert( AlertType.INFORMATION );
        alert.setTitle( "Resultats" );
        String path;
        if ( cardNb >= cardsPlayer2.size() )
        {
            alert.setContentText(
                    String.format( "All questions have been answered, you scored %d points. Thank you for playing!",
                            newScorePlayer2 ) );
            path = "/be/helha/ttmc/assets/images/banana.gif";
            scoreAlert.setTitle( "Scores" );
            scoreAlert.setContentText( String.format( "User %s scored %d point(s)\nUser %s scored %d point(s)",
                    getNickNamePlayer1(), newScorePlayer1, getNickNamePlayer2(), newScorePlayer2 ) );
            MenuPlayBP mpbp = ( MenuPlayBP ) getParent().getParent().getParent().getParent();
            mpbp.setVisibleNode( MenuPlayMainVB.class.getSimpleName() );
        }
        else
        {
            if ( getTxtRepPlayer2().getText()
                    .equalsIgnoreCase( bcPlayer2.getQuestions().get( getIDPlayer2() ).getAnswer() ) )
            {
                alert.setContentText( "Brava tu as reussi !" );
                path = "/be/helha/ttmc/assets/images/banana.gif";
                newScorePlayer2 += ( getIDPlayer2() + 1 );
                setScorePlayer2( newScorePlayer2 );
            }
            else
            {
                alert.setContentText(
                        "La reponse etait : " + bcPlayer2.getQuestions().get( getIDPlayer2() ).getAnswer() );
                path = "/be/helha/ttmc/assets/images/sonicPleure.gif";
            }
        }
        ImageView icon = new ImageView( path );
        icon.setFitHeight( 64 );
        icon.setFitWidth( 64 );
        alert.getDialogPane().setGraphic( icon );
        alert.setHeaderText( null );
        ImageView iconScore = new ImageView( path );
        iconScore.setFitHeight( 64 );
        iconScore.setFitWidth( 64 );
        scoreAlert.getDialogPane().setGraphic( iconScore );
        scoreAlert.setHeaderText( null );
        if ( o instanceof ActionEvent || o instanceof KeyEvent )
        {
            alert.showAndWait();
            if( cardNb >= cardsPlayer2.size() )
                scoreAlert.showAndWait();
        }
        else if ( o instanceof Long )
        {
            alert.show();
            if( cardNb >= cardsPlayer2.size() )
                scoreAlert.show();
        }
        if ( cardNb < cardsPlayer2.size() )
            initCardPane( cardsPlayer1, cardsPlayer2, cardNb );
    }
}
