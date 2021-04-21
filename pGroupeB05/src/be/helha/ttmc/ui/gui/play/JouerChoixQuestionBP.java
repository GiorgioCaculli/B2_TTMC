package be.helha.ttmc.ui.gui.play;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import be.helha.ttmc.model.BasicCard;
import be.helha.ttmc.model.Deck;
import be.helha.ttmc.model.Theme;
import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.MenuPauseFP;
import be.helha.ttmc.ui.gui.play.LobbySoloBP.LobbySoloMainBP;
import be.helha.ttmc.ui.gui.play.MenuPlayBP.MenuPlayMainVB;
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

public class JouerChoixQuestionBP extends BorderPane
{
    private List< BasicCard > cards;
    private Label lblTheme, lblSujet;
    private List< Button > choix;
    private Label score;
    private Label lblScore;
    private Button returnButton;
    private Deck d;

    private int newScore = 0;

    private BasicCard bc;
    private StackPane cardChoicePane;
    private StackPane jouerChoixQuestionMainSP;

    private Label lblQuestion;
    private TextField txtRep;
    private Button btnVal;

    private ImageView sabIm;
    private AnimationTimer animation;
    private int time;
    private long elapsedTime;
    private Label lblTime;
    private int id;
    private int cardNb = 0;
    private MenuPauseFP mpfp;
    private BorderPane cardPane;
    private String nickName;
    private PlateauBP pla;
    private Settings s;
    private int bonneRep = 0;

    public JouerChoixQuestionBP( Deck d, Settings s )
    {
        this.d = d;
        this.s = s;
        cards = this.d.getCards();
        getPla();
        Collections.shuffle( cards );
        initCardPane( cards, cardNb );
        getJouerChoixQuestionMainSP().getChildren().add( getMenuPauseFP() );
        getJouerChoixQuestionMainSP().getChildren().get( 1 ).setVisible( false );
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
                    cardPane.setEffect( blur );
                    getJouerChoixQuestionMainSP().getChildren().get( 1 ).setVisible( true );
                    MenuPauseFP mpfp = ( ( MenuPauseFP ) getJouerChoixQuestionMainSP().getChildren().get( 1 ) );
                    mpfp.getBtnResume().setOnAction( new EventHandler< ActionEvent >()
                    {
                        @Override
                        public void handle( ActionEvent arg0 )
                        {
                            if ( !cardChoicePane.getChildren().get( cardChoicePane.getChildren().size() - 1 )
                                    .isVisible() )
                            {
                                getAnimation().start();
                            }
                            cardPane.setEffect( null );
                            getJouerChoixQuestionMainSP().getChildren().get( 1 ).setVisible( false );

                        }
                    } );
                    if ( !cardChoicePane.getChildren().get( cardChoicePane.getChildren().size() - 1 ).isVisible() )
                    {
                        getAnimation().stop();
                    }
                    mpfp.getBtnBack().setOnAction( new EventHandler< ActionEvent >()
                    {

                        @Override
                        public void handle( ActionEvent arg0 )
                        {
                            LobbySoloBP lsbp = ( LobbySoloBP ) getParent().getParent();
                            lsbp.setVisibleNode( LobbySoloMainBP.class.getSimpleName() );
                        }
                    } );
                    keyEvent.consume();
                }
            }
        } );
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
        public JeuReponseBP()
        {
            HBox vbTime = new HBox();
            vbTime.setPadding( new Insets( 5 ) );
            vbTime.setAlignment( Pos.CENTER );
            //vbTime.setStyle( "-fx-background-color: DAE6F3;-fx-font-size: 25pt;" );
            for ( Theme t : Theme.values() )
            {
                if ( bc.getTheme() == t )
                {
                    vbTime.setStyle( String.format( "-fx-background-color: %s;-fx-font-size: 25pt;",
                            t.backgroundColor().get( bc.getTheme() ) ) );
                }
            }
            vbTime.getChildren().addAll( getSabIm(), getLblTime() );

            VBox vb = new VBox();
            vb.setPadding( new Insets( 20 ) );
            for ( Theme t : Theme.values() )
            {
                if ( bc.getTheme() == t )
                {
                    vb.setStyle( String.format( "-fx-background-color: %s;-fx-font-size: 25pt;",
                            t.backgroundColor().get( bc.getTheme() ) ) );
                }
            }
            vb.setAlignment( Pos.CENTER );
            vb.getChildren().addAll( vbTime, getLblQuestion(), getTxtRep(), getBtnVal() );
            getTxtRep().setText( "" );
            setCenter( vb );
        }
    }

    public PlateauBP getPla()
    {
        if ( pla == null )
            pla = new PlateauBP( d );
        return pla;
    }

    private void initCardPane( List< BasicCard > cards, int i )
    {
        cardPane = new BorderPane();
        time = s.getTimerSeconds();
        this.bc = cards.get( i );
        cardChoicePane = new StackPane();
        getLblTheme().setText( bc.getTheme().toString() );
        getLblSujet().setText( bc.getSubject().toString() );
        FlowPane fp = new FlowPane();
        fp.setPadding( new Insets( 5 ) );
        fp.setVgap( 5 );
        fp.setHgap( 5 );
        fp.setPrefWrapLength( 505 );
        fp.setAlignment( Pos.CENTER );
        for ( Theme t : Theme.values() )
        {
            if ( bc.getTheme() == t )
            {
                fp.setStyle( String.format( "-fx-background-color: %s;-fx-font-size: 25pt;",
                        t.backgroundColor().get( bc.getTheme() ) ) );
            }
        }

        for ( Button b : getChoix() )
        {
            cardChoicePane.getChildren().add( new JeuReponseBP() );
            fp.getChildren().add( b );
        }

        VBox vb = new VBox();
        vb.setPadding( new Insets( 20 ) );
        vb.setSpacing( 10 );
        vb.getChildren().addAll( getLblTheme(), getLblSujet() );
        // vb.setStyle("-fx-font-size: 25pt;");

        HBox hb = new HBox();
        hb.getChildren().addAll( getLblScore(), getScore() );
        hb.setSpacing( 20 );
        hb.setPadding( new Insets( 0, 10, 0, 5 ) );
        hb.setAlignment( Pos.BASELINE_RIGHT );

        VBox vb2 = new VBox();
        vb2.getChildren().addAll( vb, hb );
        vb2.setStyle( "-fx-font-size: 25pt;" );
        cardChoicePane.getChildren().add( fp );
        cardPane.setTop( vb2 );
        cardPane.setCenter( cardChoicePane );
        if ( getJouerChoixQuestionMainSP().getChildren().size() < 1 )
        {
            getJouerChoixQuestionMainSP().getChildren().add( cardPane );
        }
        else
        {
            getJouerChoixQuestionMainSP().getChildren().set( 0, cardPane );
        }
        //HBox hbpla = new HBox();
        //hbpla.getChildren().addAll( getPla(), getJouerChoixQuestionMainSP() );
        setBottom( getPla() );
        setCenter( getJouerChoixQuestionMainSP() );
    }

    public int getID()
    {
        return id;
    }

    public void setID( int id )
    {
        this.id = id;
    }

    public List< Button > getChoix()
    {
        if ( choix == null )
        {
            choix = new ArrayList<>();
            int maxBtn = 4;
            for ( int i = 0; i < maxBtn; i++ )
            {
                Button b = new Button( "Question Level: " + ( i + 1 ) );
                b.setMinSize( s.getWidth() / 3, s.getHeight() / 3 ); // 250 x 250
                b.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
                int idQ = i;
                b.setOnAction( new EventHandler< ActionEvent >()
                {
                    @Override
                    public void handle( ActionEvent arg0 )
                    {
                        setID( idQ );
                        setLblQuestion( bc.getQuestions().get( idQ ).getChallenge() );
                        getAnimation().start();
                        cardChoicePane.getChildren().get( maxBtn ).setVisible( false );
                        cardChoicePane.getChildren().get( idQ ).setVisible( true );
                    }
                } );
                choix.add( b );
            }
        }
        return choix;
    }

    public void setLblTheme( String str )
    {
        lblTheme.setText( str );
    }

    public void setLblSujet( String str )
    {
        lblSujet.setText( str );
    }

    public Label getLblScore()
    {
        if ( lblScore == null )
            lblScore = new Label( "Score : " );
        return lblScore;
    }

    public Label getScore()
    {
        if ( score == null )
            score = new Label( String.format( "%d", newScore ) );
        return score;
    }

    public void setScore( int newScore )
    {
        score.setText( String.format( "%d", newScore ) );
    }

    public Label getLblTheme()
    {
        if ( lblTheme == null )
        {
            lblTheme = new Label( "Theme" );
            lblTheme.setMaxWidth( Double.MAX_VALUE );
            lblTheme.setAlignment( Pos.CENTER );
            ;
        }
        return lblTheme;
    }

    public Label getLblSujet()
    {
        if ( lblSujet == null )
        {
            lblSujet = new Label( "Subject" );
            lblSujet.setMaxWidth( Double.MAX_VALUE );
            lblSujet.setAlignment( Pos.CENTER );
            ;
        }
        return lblSujet;
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
                    getAnimation().stop();
                    MenuPlayBP mpbp = ( MenuPlayBP ) getParent().getParent();
                    mpbp.setVisibleNode( MenuPlayMainVB.class.getSimpleName() );
                }
            } );
        }
        return returnButton;
    }

    public AnimationTimer getAnimation()
    {
        if ( animation == null )
        {
            elapsedTime = System.nanoTime();
            animation = new AnimationTimer()
            {

                @Override
                public void handle( long now )
                {
                    if ( now - elapsedTime > 1000000000 )
                    {
                        time--;
                        getLblTime().setText( String.format( "%d", time ) );
                        elapsedTime = now;
                        if ( time == 0 )
                        {
                            checkAnswer( now );
                        }
                    }

                }
            };
        }

        return animation;
    }

    public int getTime()
    {
        return time;
    }

    public ImageView getSabIm()
    {
        if ( sabIm == null )
        {
            sabIm = new ImageView( "be/helha/ttmc/assets/images/sablier.png" );
            sabIm.setFitHeight( 50 );
            sabIm.setFitWidth( 50 );
            KeyValue val = new KeyValue( sabIm.rotateProperty(), 360 );
            Timeline t = new Timeline( new KeyFrame( Duration.seconds( 0.9 ), val ),
                    new KeyFrame( Duration.seconds( 1 ), val ) );
            t.setCycleCount( Timeline.INDEFINITE );
            t.play();
        }
        return sabIm;
    }

    public Label getLblTime()
    {
        if ( lblTime == null )
        {
            lblTime = new Label( String.format( "%d", getTime() ) );
        }
        return lblTime;
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
                    checkAnswer( arg0 );
                }
            } );
        }
        return btnVal;
    }

    public Label getLblQuestion()
    {
        if ( lblQuestion == null )
        {
            lblQuestion = new Label( "test" );
            lblQuestion.setTextAlignment( TextAlignment.CENTER );
            lblQuestion.setWrapText( true );
        }
        return lblQuestion;
    }

    public TextField getTxtRep()
    {
        if ( txtRep == null )
        {
            txtRep = new TextField();
            txtRep.setOnKeyPressed( new EventHandler< KeyEvent >()
            {

                @Override
                public void handle( KeyEvent keyEvent )
                {
                    if ( keyEvent.getCode() == KeyCode.ENTER && !cardChoicePane.getChildren()
                            .get( cardChoicePane.getChildren().size() - 1 ).isVisible() )
                    {
                        checkAnswer( keyEvent );
                    }
                }
            } );
        }
        return txtRep;
    }

    public void setLblQuestion( String str )
    {
        lblQuestion.setText( str );
    }

    public BasicCard getBC()
    {
        return bc.clone();
    }

    public void setNickName( String nickName )
    {
        this.nickName = nickName;
    }

    public String getNickName()
    {
        return nickName;
    }

    private void checkAnswer( Object o )
    {
        cardNb++;
        getAnimation().stop();
        Alert alert = new Alert( AlertType.INFORMATION );
        alert.setTitle( "Resultats" );
        String path;
        if ( cardNb >= cards.size() )
        {
            alert.setContentText( String.format(
                    "All questions have been answered, you scored %d points. Thank you for playing!", newScore ) );
            path = "/be/helha/ttmc/assets/images/banana.gif";
            LobbySoloBP lsbp = ( LobbySoloBP ) getParent().getParent();
            lsbp.setVisibleNode( LobbySoloMainBP.class.getSimpleName() );
        }
        else
        {

            if ( getTxtRep().getText().equalsIgnoreCase( bc.getQuestions().get( getID() ).getAnswer() ) )
            {
                alert.setContentText( "The answer is correct! Good job!" );
                path = "/be/helha/ttmc/assets/images/banana.gif";
                newScore += ( getID() + 1 );
                setScore( newScore );
                bonneRep++;
                getPla().getPion().setPos( getPla().getCases().get( bonneRep ).getX() + getPla().getWIDTH_RECT() / 2,
                        getPla().getCases().get( bonneRep ).getY() + getPla().getHEIGHT_RECT() / 2 );
            }
            else
            {
                alert.setContentText( "The answer is not correct\nThe correct answer was: "
                        + bc.getQuestions().get( getID() ).getAnswer() );
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
        if ( cardNb < cards.size() )
            initCardPane( cards, cardNb );
    }
}
