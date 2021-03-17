package be.helha.ttmc.ui.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import be.helha.ttmc.model.BasicCard;
import be.helha.ttmc.model.Deck;
import be.helha.ttmc.model.Theme;
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
import javafx.scene.image.ImageView;
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
    private StackPane sp;

    private BorderPane jeuReponseBP;
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

    public JouerChoixQuestionBP( Deck d )
    {
        this.d = d;
        cards = this.d.getCards();
        Collections.shuffle( cards );
        initCardPane( cards, cardNb );
        this.setBottom( getReturnButton() );
    }

    private void initJeuReponseBP( int id )
    {
        jeuReponseBP = new BorderPane();
        HBox vbTime = new HBox();
        vbTime.setPadding( new Insets( 5 ) );
        vbTime.setAlignment( Pos.CENTER );
        //vbTime.setStyle( "-fx-background-color: DAE6F3;-fx-font-size: 25pt;" );
        for( Theme t : Theme.values() )
        {
            if( bc.getTheme() == t )
            {
                vbTime.setStyle( String.format( "-fx-background-color: %s;-fx-font-size: 25pt;", t.backgroundColor().get( bc.getTheme() ) ) );
            }
        }
        vbTime.getChildren().addAll( getSabIm(), getLblTime() );

        VBox vb = new VBox();
        vb.setPadding( new Insets( 20 ) );
        for( Theme t : Theme.values() )
        {
            if( bc.getTheme() == t )
            {
                vb.setStyle( String.format( "-fx-background-color: %s;-fx-font-size: 25pt;", t.backgroundColor().get( bc.getTheme() ) ) );
            }
        }
        vb.setAlignment( Pos.CENTER );
        vb.getChildren().addAll( vbTime, getLblQuestion(), getTxtRep(), getBtnVal() );
        getTxtRep().setText( "" );
        jeuReponseBP.setCenter( vb );
        sp.getChildren().add( jeuReponseBP );
    }
    
    private void initCardPane( List< BasicCard > cards, int i )
    {
        time = 15;
        this.bc = cards.get( i );
        sp = new StackPane();
        getLblTheme().setText( bc.getTheme().toString() );
        getLblSujet().setText( bc.getSubject().toString() );
        FlowPane fp = new FlowPane();
        fp.setPadding( new Insets( 5 ) );
        fp.setVgap( 5 );
        fp.setHgap( 5 );
        fp.setPrefWrapLength( 505 );
        fp.setAlignment( Pos.CENTER );
        for( Theme t : Theme.values() )
        {
            if( bc.getTheme() == t )
            {
                fp.setStyle( String.format( "-fx-background-color: %s;-fx-font-size: 25pt;", t.backgroundColor().get( bc.getTheme() ) ) );
            }
        }

        for ( Button b : getChoix() )
        {
            int j = 0;
            initJeuReponseBP( j );
            fp.getChildren().add( b );
            j++;
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
        sp.getChildren().add( fp );
        this.setCenter( sp );
        this.setTop( vb2 );
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
                b.setMinSize( 250, 250 );
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
                        sp.getChildren().get( maxBtn ).setVisible( false );
                        sp.getChildren().get( idQ ).setVisible( true );
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
                    getParent().getChildrenUnmodifiable().get( 1 ).setVisible( false );
                    getParent().getChildrenUnmodifiable().get( 0 ).setVisible( true );
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
                        if( time == 0 )
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
    
    private void checkAnswer( Object o )
    {
        cardNb++;
        getAnimation().stop();
        Alert alert = new Alert( AlertType.INFORMATION );
        alert.setTitle( "Resultats" );
        String path;
        if( cardNb >= cards.size() )
        {
            alert.setContentText( String.format( "All questions have been answered, you scored %d points. Thank you for playing!", newScore ) );
            path = "/be/helha/ttmc/assets/images/banana.gif";
            getParent().getChildrenUnmodifiable().get( 1 ).setVisible( false );
            getParent().getChildrenUnmodifiable().get( 0 ).setVisible( true );
        }
        else
        {
            if ( getTxtRep().getText().equalsIgnoreCase( bc.getQuestions().get( getID() ).getAnswer() ) )
            {
                alert.setContentText( "Brava tu as reussi !" );
                path = "/be/helha/ttmc/assets/images/banana.gif";
                newScore += ( getID() + 1 );
                setScore( newScore );
            }
            else
            {
                alert.setContentText( "La reponse etait : " + bc.getQuestions().get( getID() ).getAnswer() );
                path = "/be/helha/ttmc/assets/images/sonicPleure.gif";
            }
        }
        ImageView icon = new ImageView( path );
        icon.setFitHeight( 64 );
        icon.setFitWidth( 64 );
        alert.getDialogPane().setGraphic( icon );
        alert.setHeaderText( null );
        if( o instanceof ActionEvent )
            alert.showAndWait();
        else if( o instanceof Long )
            alert.show();
        if( cardNb < cards.size() )
            initCardPane( cards, cardNb );
    }
}
