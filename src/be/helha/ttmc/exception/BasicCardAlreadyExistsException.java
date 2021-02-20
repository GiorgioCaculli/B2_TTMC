package be.helha.ttmc.exception;

public class BasicCardAlreadyExistsException extends Exception
{
    public BasicCardAlreadyExistsException()
    {
        super( "This basic card already exists" );
    }
}
