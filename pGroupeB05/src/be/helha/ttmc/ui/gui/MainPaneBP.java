package be.helha.ttmc.ui.gui;

import be.helha.ttmc.model.Deck;
import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.admin.MenuAdminBP;
import be.helha.ttmc.ui.gui.play.MenuPlayBP;
import be.helha.ttmc.ui.gui.util.MusicGestion;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class MainPaneBP extends BorderPane
{
    private StackPane stackpane;

    public MainPaneBP( Deck d, Settings s, MusicGestion musics )
    {
        //getStackPane();
        getStackPane().getChildren().add( new MenuPrincipalBP( d, s ) );
        getStackPane().getChildren().add( new MenuPlayBP( d, s, musics ) );
        getStackPane().getChildren().add( new SettingsBP( s, musics ) );
        getStackPane().getChildren().add( new MenuAdminBP( d, s ) );
        getStackPane().getChildren().add( new CreditsBP() );

        setVisibleNode( MenuPrincipalBP.class.getSimpleName() );
        setCenter( getStackPane() );
    }

    public void setVisibleNode( String paneName )
    {

        for ( Node n : getStackPane().getChildren() )
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

    public StackPane getStackPane()
    {
        if ( stackpane == null )
        {
            stackpane = new StackPane();
        }
        return stackpane;
    }

}
