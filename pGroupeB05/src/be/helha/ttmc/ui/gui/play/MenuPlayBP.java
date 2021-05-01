package be.helha.ttmc.ui.gui.play;

import java.util.ArrayList;
import java.util.List;

import be.helha.ttmc.model.Deck;
import be.helha.ttmc.ui.GUIConstant;
import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.MainPaneBP;
import be.helha.ttmc.ui.gui.MenuPrincipalBP;
import be.helha.ttmc.ui.gui.util.MusicGestion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

public class MenuPlayBP extends BorderPane
{
    private Button btnSolo, btnMulti, btnRetour;
    private Deck d;
    private StackPane choicePane;
    private Settings s;
    private MusicGestion m;

    public MenuPlayBP( Deck d, Settings s, MusicGestion m )
    {
        this.d = d;
        this.s = s;
        this.m = m;
        getChoicePane().getChildren().add( new MenuPlayMainVB() );
        getChoicePane().getChildren().add( new RulesBP( s ) );
        setVisibleNode( RulesBP.class.getSimpleName() );

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
        private List< Button > buttons;
        
        public MenuPlayMainVB()
        {
            buttons = new ArrayList<>();
            // this.setOrientation(Orientation.VERTICAL);
            setPadding( new Insets( 20 ) );
            setSpacing( 50 );

            buttons.add( getBtnSolo() );
            buttons.add( getBtnMulti() );
            buttons.add( getBtnRetour() );

            for ( Button b : buttons )
            {
                b.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
                b.setEffect( GUIConstant.BUTTON_EFFECT );
                b.setTextFill( GUIConstant.BUTTON_GRADIENT );
                b.setStyle( GUIConstant.BUTTON_STYLE );
                b.setFont( GUIConstant.BUTTON_TEXT );
                b.setMaxWidth( s.getWidth() - 55. );
                b.setMinHeight( s.getHeight() / ( buttons.size() + 1 ) );
            }

            setStyle( GUIConstant.WINDOW_STYLE );
            getChildren().addAll( buttons );
            setAlignment( Pos.CENTER );
        }
    }

    private Button getBtnSolo()
    {
        if ( btnSolo == null )
        {
            btnSolo = new Button( s.getLanguage().getString( "button_single_player" ) );
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
                    getChoicePane().getChildren().add( new LobbySoloBP( d, s, m ) );
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
            btnMulti = new Button( s.getLanguage().getString( "button_multiplayer" ) );
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
                    getChoicePane().getChildren().add( new MenuMultiplayerBP( d, s, m ) );
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
            btnRetour = new Button( s.getLanguage().getString( "button_return" ) );
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
