package be.helha.ttmc.exception;

public class BasicCardOverMaxQuestionsException extends Exception
{
    public BasicCardOverMaxQuestionsException()
    {
	super( "The max amount of questions has been reached" );
    }
}
