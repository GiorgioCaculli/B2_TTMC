package be.helha.ttmc.ui.gui;

import be.helha.ttmc.ui.gui.MenuPlayBP.MenuPlayMainVB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class MenuPauseFP extends FlowPane
{
    private Button btnBack;
    private Button btnResume;

    public Button getBtnBack()
    {
        if ( btnBack == null )
        {
            btnBack = new Button( "Back to main menu" );
            btnBack.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    MenuPlayBP mp = ( MenuPlayBP ) getParent().getParent();
                    mp.setVisibleNode( MenuPlayMainVB.class.getSimpleName() );

                }
            } );
        }
        return btnBack;
    }

    public Button getBtnResume()
    {
        if ( btnResume == null )
        {
            btnResume = new Button( "resume" );
            btnResume.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    setVisible( false );
                }
            } );
        }
        return btnResume;
    }

    public MenuPauseFP()
    {
        VBox vb = new VBox();
        vb.getChildren().addAll( getBtnResume(), getBtnBack() );
        getChildren().add( vb );
        //	setWidth(20);
        //	setHeight(60);
    }

}
