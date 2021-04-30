package be.helha.ttmc.ui.gui.play;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class ChatBP extends BorderPane
{
    private TextField messageField;
    private TextArea conversationArea;

    public ChatBP()
    {
        setCenter( getConversationArea() );
        setBottom( getMessageField() );
    }

    public TextField getMessageField()
    {
        if ( messageField == null )
        {
            messageField = new TextField();
            messageField.setFocusTraversable( false );
        }
        return messageField;
    }

    public TextArea getConversationArea()
    {
        if ( conversationArea == null )
        {
            conversationArea = new TextArea();
            conversationArea.setEditable( false );
            conversationArea.setFocusTraversable( false );
            conversationArea.setPrefColumnCount( 20 );
            conversationArea.setWrapText( true );
        }
        return conversationArea;
    }
}
