package be.helha.ttmc.ui.gui.play;

import be.helha.ttmc.ui.GUIConstant;
import be.helha.ttmc.ui.gui.play.MenuPlayBP.MenuPlayMainVB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class RulesBP extends BorderPane
{
    private Button acceptButton;

    public RulesBP()
    {
        Label rulesLabel = new Label( GUIConstant.RULES );
        rulesLabel.setWrapText( true );
        setCenter( rulesLabel );
        HBox acceptBox = new HBox();
        acceptBox.getChildren().add( getAcceptButton() );
        acceptBox.setAlignment(Pos.CENTER);
        acceptBox.setPadding(new Insets(25.));
        setBottom( acceptBox );
        setStyle( GUIConstant.WINDOW_STYLE );
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
