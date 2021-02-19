package be.helha.ttmc.core;

import java.util.List;
import java.util.ArrayList;

public class BasicCard
{
    private String author, subject;
    private Theme theme;
    private List< Question > questions;

    public BasicCard( String author, Theme theme, String subject )
    {
        this.author = author;
        this.theme = theme;
        this.subject = subject;
        questions = new ArrayList< Question >();
    }

    public boolean add( Question q )
    {
	if( q == null )
	    {
		return false;
	    }
	if( questions.contains( q ) )
	    {
		return false;
	    }
	if( questions.size() == 4 )
	    {
		return false;
	    }
	if( q.getTheme() != theme )
	    {
		return false;
	    }
	if( !q.getAuthor().equalsIgnoreCase( author ) )
	    {
		return false;
	    }
	if( !q.getSubject().equalsIgnoreCase( subject ) )
	    {
		return false;
	    }
	return questions.add( q );
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
