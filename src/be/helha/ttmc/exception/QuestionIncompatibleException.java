package be.helha.ttmc.exception;

public class QuestionIncompatibleException extends Exception
{
    public QuestionIncompatibleException()
    {
	super( "The question is incompatible with the card" );
    }
}
