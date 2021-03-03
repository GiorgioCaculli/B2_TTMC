package be.helha.ttmc.exception;

public class QuestionDoubleException extends Exception
{
    public QuestionDoubleException()
    {
        super( "This question is already present on the card" );
    }
}
