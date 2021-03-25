package be.helha.ttmc.ui.gui;

import be.helha.ttmc.ui.gui.MenuPlayBP.MenuPlayMainVB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MenuPauseFP extends BorderPane
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
            btnResume.setOpacity(1.0);
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
        vb.setAlignment(Pos.CENTER);
       
        vb.setPadding(new Insets(100.));
      //  vb.setBackground(new Background(new BackgroundFill(Color.BLACK, null, getInsets())));
      //  setBackground(new Background(new BackgroundFill(Color.BLACK, null, getInsets())));
        setCenter( vb );
      //  setOpacity(0.5);
        setWidth(450);
        setHeight(750);
    }

}
