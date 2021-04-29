package be.helha.ttmc.ui.gui.play;

import be.helha.ttmc.ui.GUIConstant;
import be.helha.ttmc.ui.gui.play.MenuPlayBP.MenuPlayMainVB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class RulesBP extends BorderPane
{
    private Button acceptButton;

    public RulesBP()
    {
        setCenter( new Label( "Rules" ) );
        HBox acceptBox = new HBox();
        Region espaceVideGauche = new Region();
        HBox.setHgrow( espaceVideGauche, Priority.ALWAYS );
        Region espaceVideDroite = new Region();
        HBox.setHgrow( espaceVideDroite, Priority.ALWAYS );
        acceptBox.getChildren().add( espaceVideGauche );
        acceptBox.getChildren().add( getAcceptButton() );
        acceptBox.getChildren().add( espaceVideDroite );
        setBottom( acceptBox );
    }

    public Button getAcceptButton()
    {
        if ( acceptButton == null )
        {
            acceptButton = new Button( GUIConstant.BUTTON_RULES_ACCEPT );
            acceptButton.setOnAction( new EventHandler< ActionEvent >()
            {
                @Override
                public void handle( ActionEvent arg0 )
                {
                    MenuPlayBP mpbp = ( MenuPlayBP ) getParent().getParent();
                    mpbp.setVisibleNode( MenuPlayMainVB.class.getSimpleName() );
                }
            } );
        }
        return acceptButton;
    }
}
