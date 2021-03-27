package be.helha.ttmc.ui.gui;

import java.util.Optional;
import java.util.Random;

import be.helha.ttmc.model.Deck;
import be.helha.ttmc.ui.gui.MenuPlayBP.MenuPlayMainVB;
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

public class MenuMultiplayerBP extends BorderPane
{
    private Button btnLocal, btnOnline, btnRetour;
    private Deck d;
    private StackPane choiceMultiplayerPane;

    public MenuMultiplayerBP( Deck d )
    {
        this.d = d;        

        getChoicePane().getChildren().add( new MenuMultiplayerMainVB() );
        setVisibleNode( MenuMultiplayerMainVB.class.getSimpleName() );

        setCenter( choiceMultiplayerPane );

    }
   

    protected StackPane getChoicePane()
    {
        if ( choiceMultiplayerPane == null )
        {
            choiceMultiplayerPane = new StackPane();
        }
        return choiceMultiplayerPane;
    }

    public void setVisibleNode( String paneName )
    {
        for ( Node n : getChoicePane().getChildren() )
        {
            if ( n.getClass().getSimpleName().equals( paneName ) )
            {
                n.setVisible( true );
            }
            else
            {
                n.setVisible( false );
            }
        }
    }

    protected class MenuMultiplayerMainVB extends VBox
    {
        public MenuMultiplayerMainVB()
        {
            // this.setOrientation(Orientation.VERTICAL);
            setPadding( new Insets( 20 ) );
            setSpacing( 50 );

            setStyle( "-fx-background-color: DAE6F3;" + "-fx-font-size: 15pt;" );
            getChildren().addAll( getBtnLocal(), getBtnOnline(), getBtnRetour() );
            setAlignment( Pos.CENTER );
        }
    }

    private Button getBtnLocal()
    {
        if ( btnLocal == null )
        {
            btnLocal = new Button( "Local" );
            btnLocal.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
            btnLocal.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    for( int i = 0; i < getChoicePane().getChildren().size(); i++ )
                    {
                        if( getChoicePane().getChildren().get( i ).getClass().getSimpleName().equals( JouerChoixQuestionMultiplayerBP.class.getSimpleName() ) )
                        {
                            getChoicePane().getChildren().remove( i );
                        }
                    }
                    JouerChoixQuestionMultiplayerBP jcqm = new JouerChoixQuestionMultiplayerBP( d );
                    jcqm.setScorePlayer2( 0 );
                    TextInputDialog userNameDialogPlayer1 = new TextInputDialog();
                    userNameDialogPlayer1.setTitle( "Insert your nickname" );
                    userNameDialogPlayer1.setHeaderText( "Insert your nickname" );
                    userNameDialogPlayer1.setContentText( "Please, insert your nickname:" );
                    Optional< String > userNamePlayer1 = userNameDialogPlayer1.showAndWait();
                    TextInputDialog userNameDialogPlayer2 = new TextInputDialog();
                    userNameDialogPlayer2.setTitle( "Insert your nickname" );
                    userNameDialogPlayer2.setHeaderText( "Insert your nickname" );
                    userNameDialogPlayer2.setContentText( "Please, insert your nickname:" );
                    Optional< String > userNamePlayer2 = userNameDialogPlayer2.showAndWait();
                    if( userNamePlayer1.get().isEmpty() )
                    {
                        Random randId1 = new Random(999);
                        jcqm.setNickNamePlayer1( String.format( "User-%d", randId1.nextInt() ) );
                    }
                    else
                    {
                        jcqm.setNickNamePlayer1( String.format( "%s", userNamePlayer1.get() ) );
                    }
                    if( userNamePlayer2.get().isEmpty() )
                    {
                        Random randId2 = new Random(999);
                        jcqm.setNickNamePlayer2( String.format( "User-%d", randId2.nextInt() ) );
                    }
                    else
                    {
                        jcqm.setNickNamePlayer2( String.format( "%s", userNamePlayer2.get() ) );
                    }
                    jcqm.getLblScore().setText( String.format( ": %s - %s:", jcqm.getNickNamePlayer1(), jcqm.getNickNamePlayer2() ) );
                    getChoicePane().getChildren().add( jcqm );
                    setVisibleNode( jcqm.getClass().getSimpleName() );
                }
            } );
        }
        return btnLocal;
    }

    public Button getBtnOnline()
    {
        if ( btnOnline == null )
        {
            btnOnline = new Button( "Online" );
            btnOnline.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
            btnOnline.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                }
            } );
        }
        return btnOnline;
    }

    private Button getBtnRetour()
    {
        if ( btnRetour == null )
        {
            btnRetour = new Button( "Return" );
            btnRetour.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
            btnRetour.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    MenuPlayBP mpbp = ( MenuPlayBP ) getParent().getParent();
                    mpbp.setVisibleNode( MenuPlayMainVB.class.getSimpleName() );
                }
            } );
        }
        return btnRetour;
    }
}
