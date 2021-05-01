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
import javafx.scene.layout.VBox;

public class RulesBP extends BorderPane
{
    private Button acceptButton;
    private Label lblRules;

    public Label getLblRules()
    {
        if ( lblRules == null )
        {
            lblRules = new Label( GUIConstant.LABEL_RULES );
            lblRules.setFont( GUIConstant.BUTTON_TEXT );
            lblRules.setStyle( "-fx-underline: true" );
            lblRules.setAlignment( Pos.CENTER );
        }
        return lblRules;
    }

    public RulesBP()
    {

        Label rulesLabel = new Label( GUIConstant.RULES );
        rulesLabel.setWrapText( true );

        HBox hbRule = new HBox();
        hbRule.getChildren().add( getLblRules() );
        hbRule.setAlignment( Pos.CENTER );

        VBox vbRules = new VBox();
        vbRules.getChildren().addAll( hbRule, rulesLabel );
        vbRules.setSpacing( 50. );
        vbRules.setPadding( new Insets( 50. ) );

        setCenter( vbRules );
        HBox acceptBox = new HBox();
        acceptBox.getChildren().add( getAcceptButton() );
        acceptBox.setAlignment( Pos.CENTER );
        acceptBox.setPadding( new Insets( 25. ) );
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
