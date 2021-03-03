package be.helha.ttmc.exception;

public class DeckDoubleException extends Exception
{
    public DeckDoubleException()
    {
        super( "This card is already present in the deck" );
    }
}
