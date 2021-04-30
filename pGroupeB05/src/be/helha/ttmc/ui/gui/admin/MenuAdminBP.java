package be.helha.ttmc.ui.gui.admin;

import java.util.ArrayList;
import java.util.List;

import be.helha.ttmc.model.Deck;
import be.helha.ttmc.ui.GUIConstant;
import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.MainPaneBP;
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

public class MenuAdminBP extends BorderPane
{

    private Button btnAjout, btnListe, btnRetour;
    private Deck d;
    private StackPane adminChoicePane;
    private Settings s;

    public MenuAdminBP( Deck d, Settings s )
    {
        this.d = d;
        this.s = s;

        getAdminChoicePane().getChildren().add( new MenuAdminMainVB() );
        getAdminChoicePane().getChildren().add( new FenetreAjoutBP( d ) );

        setVisibleNode( MenuAdminMainVB.class.getSimpleName() );

        setCenter( getAdminChoicePane() );

    }

    public StackPane getAdminChoicePane()
    {
        if ( adminChoicePane == null )
        {
            adminChoicePane = new StackPane();
        }
        return adminChoicePane;
    }

    public void setVisibleNode( String paneName )
    {
        for ( Node n : getAdminChoicePane().getChildren() )
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

    protected class MenuAdminMainVB extends VBox
    {
        private List< Button > buttons;
        private Font txt = Font.font( "Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 100 );
        private Effect buttonEffect = new DropShadow( 25, 13, 13, Color.DARKSLATEGREY );
        private String buttonStyle = "-fx-background-color: plum;";

        private Stop[] etapes =
        { new Stop( 0, Color.BLUEVIOLET ), new Stop( 0.3, Color.ROYALBLUE ), new Stop( 0.7, Color.LIGHTSTEELBLUE ) };
        private LinearGradient gradiant = new LinearGradient( 0, 1, 0, 0, true, CycleMethod.NO_CYCLE, etapes );

        public MenuAdminMainVB()
        {
            buttons = new ArrayList<>();
            // this.setOrientation(Orientation.VERTICAL);
            setPadding( new Insets( 20 ) );
            setSpacing( 50 );
            
            buttons.add( getBtnAjout() );
            buttons.add( getBtnListe() );
            buttons.add( getBtnRetour() );

            for ( Button b : buttons )
            {
                b.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
                b.setEffect( buttonEffect );
                b.setTextFill( gradiant );
                b.setStyle( buttonStyle );
                b.setFont( txt );
                b.setMaxWidth( s.getWidth() - 55. );
                b.setMinHeight( s.getHeight() / ( buttons.size() + 1 ) );
            }

            setStyle( "-fx-background-color: DAE6F3;" + "-fx-font-size: 15pt;" );
            getChildren().addAll( buttons );
            setAlignment( Pos.CENTER );
        }
    }

    private Button getBtnAjout()
    {
        if ( btnAjout == null )
        {
            btnAjout = new Button( "Add a card" );
            btnAjout.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    setVisibleNode( FenetreAjoutBP.class.getSimpleName() );
                }
            } );
        }
        return btnAjout;
    }

    private Button getBtnListe()
    {
        if ( btnListe == null )
        {
            btnListe = new Button( "Show the list of cards" );
            btnListe.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    for ( int i = 0; i < getAdminChoicePane().getChildren().size(); i++ )
                    {
                        if ( getAdminChoicePane().getChildren().get( i ).getClass().getSimpleName()
                                .equals( ListeCarteBP.class.getSimpleName() ) )
                        {
                            getAdminChoicePane().getChildren().remove( i );
                        }
                    }
                    getAdminChoicePane().getChildren().add( new ListeCarteBP( d ) );
                    setVisibleNode( ListeCarteBP.class.getSimpleName() );
                }
            } );
        }
        return btnListe;
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
                    MainPaneBP mpbp = ( MainPaneBP ) getParent().getParent();
                    mpbp.setVisibleNode( "MenuPrincipalBP" );
                }
            } );
        }
        return btnRetour;
    }
}
