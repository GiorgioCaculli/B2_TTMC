package be.helha.ttmc.model;

import java.util.List;
import java.util.ArrayList;

import be.helha.ttmc.exception.QuestionDoubleException;
import be.helha.ttmc.exception.QuestionIncompatibleException;
import be.helha.ttmc.exception.BasicCardOverMaxQuestionsException;

/**
 * Class for the BasicCard
 *
 * This class is meant to handle the most basic card that the player will be interacting with.
 *
 * @author Giorgio CACULLI LA196672, Guillaume LAMBERT LA198116, Tanguy TAMINIAU LA199566
 * @version 1.0
 */
public class BasicCard
{
    private String author, subject;
    private Theme theme;
    private List< Question > questions;

    /**
     * Constructor for the class BasicCard
     *
     * @param author The author of the card
     * @param 
     */
    public BasicCard( String author, Theme theme, String subject )
    {
	this.author = author;
	this.theme = theme;
	this.subject = subject;
	questions = new ArrayList< Question >();
    }

    public boolean add( Question q )
    {
	try
	    {
		if( q == null )
		    {
			throw new NullPointerException();
		    }
		if( questions.contains( q ) )
		    {
			throw new QuestionDoubleException();
		    }
		if( q.getTheme() != theme ||
		    !q.getAuthor().equalsIgnoreCase( author ) ||
		    !q.getSubject().equalsIgnoreCase( subject ) )
		    {
			throw new QuestionIncompatibleException();
		    }
		if( questions.size() == 4 )
		    {
			throw new BasicCardOverMaxQuestionsException();
		    }
		questions.add( q );
	    }
	catch( NullPointerException npe )
	    {
		npe.printStackTrace();
		return false;
	    }
	catch( QuestionDoubleException qde )
	    {
		qde.printStackTrace();
		return false;
	    }
	catch( QuestionIncompatibleException qie )
	    {
		qie.printStackTrace();
		return false;
	    }
	catch( BasicCardOverMaxQuestionsException bcomqe )
	    {
		bcomqe.printStackTrace();
		return false;
	    }
	return true;
    }

    public boolean remove( Question q )
    {
	if( q == null )
	    {
		return false;
	    }
	return questions.remove( q );
    }

    public boolean remove( int i )
    {
	if( i < 1 && i > 4 )
	    {
		return false;
	    }
	if( i > questions.size() )
	    {
		return false;
	    }
	return questions.remove( questions.get( i - 1 ) );
    }

    public String toString()
    {
	StringBuilder sb = new StringBuilder();
	for( Question q : getQuestions() )
	    {
		sb.append( q );
		sb.append( System.getProperty( "line.separator" ) );
	    }
	return String.format( "Author: %s - Theme: %s - Subject: %s - Questions: %s %s",
			      getAuthor(), getTheme(), getSubject(), System.getProperty( "line.separator" ), sb.toString() );
    }

    public BasicCard clone()
    {
	return new BasicCard( getAuthor(), getTheme(), getSubject() );
    }

    public boolean equals( Object o )
    {
	if( o instanceof BasicCard )
	    {
		BasicCard tmpc = ( BasicCard ) o;
		return getTheme() == tmpc.getTheme() &&
		    getSubject().equalsIgnoreCase( tmpc.getSubject() );
	    }
	return false;
    }

    public String getAuthor()
    {
	return author;
    }

    public Theme getTheme()
    {
	return theme;
    }

    public String getSubject()
    {
	return subject;
    }

    public List< Question > getQuestions()
    {
	List< Question > tmp = new ArrayList< Question >();
	for( Question q : questions )
	    {
		tmp.add( q.clone() );
	    }
	return tmp;
    }
}
