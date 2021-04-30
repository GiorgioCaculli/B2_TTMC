package be.helha.ttmc.ui.gui.play;

import java.util.ArrayList;
import java.util.List;

import be.helha.ttmc.model.Deck;
import be.helha.ttmc.ui.GUIConstant;
import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.play.MenuPlayBP.MenuPlayMainVB;
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

public class MenuMultiplayerBP extends BorderPane
{
    private Button btnLocal, btnOnline, btnRetour;
    private Deck d;
    private StackPane choiceMultiplayerPane;
    private Settings s;
    private MusicGestion m;

    public MenuMultiplayerBP( Deck d, Settings s, MusicGestion m )
    {
        this.d = d;
        this.s = s;
        this.m = m;

        getChoicePane().getChildren().add( new MenuMultiplayerMainVB() );
        getChoicePane().getChildren().add( new MenuMultiplayerOnlineBP( d, s, m ) );
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
        private List< Button > buttons;

        public MenuMultiplayerMainVB()
        {
            buttons = new ArrayList<>();
            // this.setOrientation(Orientation.VERTICAL);
            setPadding( new Insets( 20 ) );
            setSpacing( 50 );

            buttons.add( getBtnLocal() );
            buttons.add( getBtnOnline() );
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

    private Button getBtnLocal()
    {
        if ( btnLocal == null )
        {
            btnLocal = new Button( "Local" );
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
                    getChoicePane().getChildren().add( new LobbyMultiLocalBP( d, s, m ) );
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
            btnOnline.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    setVisibleNode( MenuMultiplayerOnlineBP.class.getSimpleName() );
                }
            } );
        }
        return btnOnline;
    }

    private Button getBtnRetour()
    {
        if ( btnRetour == null )
        {
            btnRetour = new Button( GUIConstant.BUTTON_RETURN );
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
