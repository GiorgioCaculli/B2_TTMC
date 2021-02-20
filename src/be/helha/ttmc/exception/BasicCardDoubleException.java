package be.helha.ttmc.exception;

public class BasicCardDoubleException extends Exception
{
    public BasicCardDoubleException()
    {
	super( "This basic card has already been added" );
    }
}
