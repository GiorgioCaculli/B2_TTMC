package be.helha.ttmc.ui.gui.play;

import be.helha.ttmc.model.Deck;
import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.MainPaneBP;
import be.helha.ttmc.ui.gui.MenuPrincipalBP;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MenuPlayBP extends BorderPane
{
    private Button btnSolo, btnMulti, btnRetour;
    private Deck d;
    private StackPane choicePane;
    private Settings s;

    public MenuPlayBP( Deck d, Settings s )
    {
        this.d = d;
        this.s = s;
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
                                .equals( LobbySoloBP.class.getSimpleName() ) )
                        {
                            getChoicePane().getChildren().remove( i );
                        }
                    }
                    getChoicePane().getChildren().add( new LobbySoloBP( d, s ) );
                    setVisibleNode( LobbySoloBP.class.getSimpleName() );
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
                    getChoicePane().getChildren().add( new MenuMultiplayerBP( d, s ) );
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
