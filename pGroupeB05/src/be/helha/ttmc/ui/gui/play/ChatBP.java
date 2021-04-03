package be.helha.ttmc.ui.gui.play;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class ChatBP extends BorderPane
{
    private TextField messageField = new TextField();
    private TextArea conversationArea = new TextArea();

    public ChatBP()
    {
        conversationArea.setEditable( false );
        conversationArea.setFocusTraversable( false );
        conversationArea.setPrefColumnCount( 20 );
        conversationArea.setWrapText( true );
        messageField.setFocusTraversable( false );
        setCenter( conversationArea );
        setBottom( messageField );
    }

    public TextField getMessageField()
    {
        if ( messageField == null )
        {
            messageField = new TextField();
        }
        return messageField;
    }

    public TextArea getConversationArea()
    {
        if ( conversationArea == null )
        {
            conversationArea = new TextArea();
        }
        return conversationArea;
    }
}
