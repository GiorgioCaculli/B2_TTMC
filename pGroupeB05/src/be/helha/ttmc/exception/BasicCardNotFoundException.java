package be.helha.ttmc.exception;

public class BasicCardNotFoundException extends Exception
{
    public BasicCardNotFoundException()
    {
        super( "BasicCard not present in Deck" );
    }
}
