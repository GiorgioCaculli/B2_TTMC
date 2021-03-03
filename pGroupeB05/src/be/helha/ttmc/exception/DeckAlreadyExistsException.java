package be.helha.ttmc.exception;

public class DeckAlreadyExistsException extends Exception
{
    public DeckAlreadyExistsException()
    {
        super( "This deck already exists" );
    }
}
