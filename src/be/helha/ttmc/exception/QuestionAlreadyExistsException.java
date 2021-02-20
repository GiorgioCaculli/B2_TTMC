package be.helha.ttmc.exception;

public class QuestionAlreadyExistsException extends Exception
{
    public QuestionAlreadyExistsException()
    {
        super( "This question exists already" );
    }
}
