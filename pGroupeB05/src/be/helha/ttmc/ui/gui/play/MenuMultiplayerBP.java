package be.helha.ttmc.ui.gui.play;

import be.helha.ttmc.model.Deck;
import be.helha.ttmc.ui.gui.play.MenuPlayBP.MenuPlayMainVB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
                    for ( int i = 0; i < getChoicePane().getChildren().size(); i++ )
                    {
                        if ( getChoicePane().getChildren().get( i ).getClass().getSimpleName()
                                .equals( LobbyMultiLocalBP.class.getSimpleName() ) )
                        {
                            getChoicePane().getChildren().remove( i );
                        }
                    }
                    getChoicePane().getChildren().add( new LobbyMultiLocalBP( d ) );
                    setVisibleNode( LobbyMultiLocalBP.class.getSimpleName() );
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
