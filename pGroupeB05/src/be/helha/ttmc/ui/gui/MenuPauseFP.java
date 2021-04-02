package be.helha.ttmc.ui.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MenuPauseFP extends BorderPane
{
    private Button btnBack;
    private Button btnResume;

    public Button getBtnBack()
    {
        if ( btnBack == null )
        {
            btnBack = new Button( "Back to main menu" );
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