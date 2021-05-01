package be.helha.ttmc.ui.gui.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import be.helha.ttmc.model.BasicCard;
import be.helha.ttmc.model.Deck;
import be.helha.ttmc.serialization.Serialization;
import be.helha.ttmc.ui.GUIConstant;
import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.MainPaneBP;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class MenuAdminBP extends BorderPane
{

    private Button btnAjout, btnListe, btnImport, btnExport, btnRetour;
    private Deck d;
    private StackPane adminChoicePane;
    private Settings s;

    public MenuAdminBP( Deck d, Settings s )
    {
        this.d = d;
        this.s = s;

        getAdminChoicePane().getChildren().add( new MenuAdminMainVB() );
        getAdminChoicePane().getChildren().add( new FenetreAjoutBP( d, s ) );

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

        public MenuAdminMainVB()
        {
            buttons = new ArrayList<>();
            // this.setOrientation(Orientation.VERTICAL);
            setPadding( new Insets( 20. ) );
            setSpacing( 20. );

            buttons.add( getBtnAjout() );
            buttons.add( getBtnListe() );
            buttons.add( getBtnImport() );
            buttons.add( getBtnExport() );
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

    private Button getBtnAjout()
    {
        if ( btnAjout == null )
        {
            btnAjout = new Button( s.getLanguage().getString( "button_add_card" ) );
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
            btnListe = new Button( s.getLanguage().getString( "button_list_card" ) );
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
                    getAdminChoicePane().getChildren().add( new ListeCarteBP( d, s ) );
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
            btnRetour = new Button( s.getLanguage().getString( "button_return" ) );
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

    public Button getBtnImport()
    {
        if ( btnImport == null )
        {
            btnImport = new Button( s.getLanguage().getString( "button_import_deck" ) );
            btnImport.setOnAction( new EventHandler< ActionEvent >()
            {
                @Override
                public void handle( ActionEvent arg0 )
                {
                    FileChooser fc = new FileChooser();
                    fc.setTitle( s.getLanguage().getString( "button_import_deck" ) );
                    fc.getExtensionFilters().add( new ExtensionFilter( "JSON File", "*.json" ) );
                    Stage stage = ( Stage ) getScene().getWindow();
                    File f = fc.showOpenDialog( stage );
                    if ( f == null )
                    {
                        return;
                    }
                    Deck tmp = Serialization.loadDeck( f.getAbsolutePath() );
                    for ( BasicCard bc : tmp.getCards() )
                    {
                        d.add( bc );
                    }
                }
            } );
        }
        return btnImport;
    }

    public Button getBtnExport()
    {
        if ( btnExport == null )
        {
            btnExport = new Button( s.getLanguage().getString( "button_export_deck" ) );
            btnExport.setOnAction( new EventHandler< ActionEvent >()
            {
                @Override
                public void handle( ActionEvent arg0 )
                {
                    FileChooser fc = new FileChooser();
                    fc.setTitle( s.getLanguage().getString( "button_export_deck" ) );
                    Stage stage = ( Stage ) getScene().getWindow();
                    File f = fc.showSaveDialog( stage );
                    if ( f == null )
                    {
                        return;
                    }
                    if ( !f.getName().contains( "." ) )
                    {
                        f = new File( f.getAbsoluteFile() + ".json" );
                    }
                    Serialization.saveGame( d, f.getAbsolutePath() );
                }
            } );
        }
        return btnExport;
    }
}
