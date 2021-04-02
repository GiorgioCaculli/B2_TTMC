package be.helha.ttmc.ui.gui;

import java.util.Optional;
import java.util.Random;

import be.helha.ttmc.model.Deck;
import be.helha.ttmc.serialization.Serialization;
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

public class MenuPlayBP extends BorderPane
{
    private Button btnSolo, btnMulti, btnRetour;
    private Deck d;
    private StackPane choicePane;

    public MenuPlayBP( Deck d )
    {
        this.d = d;

        getChoicePane().getChildren().add( new MenuPlayMainVB() );
        setVisibleNode( MenuPlayMainVB.class.getSimpleName() );

        setCenter( choicePane );

    }

    protected StackPane getChoicePane()
    {
        if ( choicePane == null )
        {
            choicePane = new StackPane();
        }
        return choicePane;
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

    protected class MenuPlayMainVB extends VBox
    {
        public MenuPlayMainVB()
        {
            // this.setOrientation(Orientation.VERTICAL);
            setPadding( new Insets( 20 ) );
            setSpacing( 50 );

            setStyle( "-fx-background-color: DAE6F3;" + "-fx-font-size: 15pt;" );
            getChildren().addAll( getBtnSolo(), getBtnMulti(), getBtnRetour() );
            setAlignment( Pos.CENTER );
        }
    }

    private Button getBtnSolo()
    {
        if ( btnSolo == null )
        {
            btnSolo = new Button( "Single Player" );
            btnSolo.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
            btnSolo.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    for ( int i = 0; i < getChoicePane().getChildren().size(); i++ )
                    {
                        if ( getChoicePane().getChildren().get( i ).getClass().getSimpleName()
                                .equals( JouerChoixQuestionBP.class.getSimpleName() ) )
                        {
                            getChoicePane().getChildren().remove( i );
                        }
                    }
                    TextInputDialog userNameDialog = new TextInputDialog();
                    userNameDialog.setTitle( "Insert your nickname" );
                    userNameDialog.setHeaderText( "Insert your nickname" );
                    userNameDialog.setContentText( "Please, insert your nickname:" );
                    Optional< String > userName = userNameDialog.showAndWait();
                    JouerChoixQuestionBP jcq = new JouerChoixQuestionBP( d );
                    jcq.setScore( 0 );
                    if( userName.isPresent() )
                    {
                        if ( userName.get().equals( "giorgio" ) || userName.get().equals( "guillaume" )
                                || userName.get().equals( "tanguy" ) )
                        {
                            d = Serialization
                                    .loadDeck( String.format( "assets/decks/%s.json", userName.get() ).toString() );
                        }
                        else if ( userName.get().isEmpty() )
                        {
                            jcq.setNickName( "User-1" );
                            jcq.getLblScore().setText( String.format( "User: %s - Score: ", jcq.getNickName() ) );
                        }
                        else
                        {
                            jcq.setNickName( String.format( "%s", userName.get() ) );
                            jcq.getLblScore().setText( String.format( "User: %s - Score: ", jcq.getNickName() ) );
                        }
                    }
                    else
                    {
                        jcq.setNickName( "User-1" );
                        jcq.getLblScore().setText( String.format( "User: %s - Score: ", jcq.getNickName() ) );
                    }
                    getChoicePane().getChildren().add( jcq );
                    setVisibleNode( jcq.getClass().getSimpleName() );
                }
            } );
        }
        return btnSolo;
    }

    public Button getBtnMulti()
    {
        if ( btnMulti == null )
        {
            btnMulti = new Button( "Multiplayer" );
            btnMulti.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
            btnMulti.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    for ( int i = 0; i < getChoicePane().getChildren().size(); i++ )
                    {
                        if ( getChoicePane().getChildren().get( i ).getClass().getSimpleName()
                                .equals( MenuMultiplayerBP.class.getSimpleName() ) )
                        {
                            getChoicePane().getChildren().remove( i );
                        }
                    }
                    getChoicePane().getChildren().add( new MenuMultiplayerBP( d ) );
                    setVisibleNode( MenuMultiplayerBP.class.getSimpleName() );
                }
            } );
        }
        return btnMulti;
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
                    MainPaneBP mpbp = ( MainPaneBP ) getParent().getParent();
                    mpbp.setVisibleNode( MenuPrincipalBP.class.getSimpleName() );
                }
            } );
        }
        return btnRetour;
    }
}
