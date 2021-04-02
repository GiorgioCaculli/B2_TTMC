package be.helha.ttmc.ui.gui.admin;

import be.helha.ttmc.model.Deck;
import be.helha.ttmc.ui.gui.MainPaneBP;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MenuAdminBP extends BorderPane
{

    private Button btnAjout, btnListe, btnRetour;
    private Deck d;
    private StackPane adminChoicePane;

    public MenuAdminBP( Deck d )
    {
        this.d = d;

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
        public MenuAdminMainVB()
        {
            // this.setOrientation(Orientation.VERTICAL);
            setPadding( new Insets( 20 ) );
            setSpacing( 50 );

            setStyle( "-fx-background-color: DAE6F3;" + "-fx-font-size: 15pt;" );
            getChildren().addAll( getBtnAjout(), getBtnListe(), getBtnRetour() );
            setAlignment( Pos.CENTER );
        }
    }

    private Button getBtnAjout()
    {
        if ( btnAjout == null )
        {
            btnAjout = new Button( "Add a card" );
            btnAjout.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
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
            btnListe.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
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
            btnRetour = new Button( "Return" );
            btnRetour.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
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
