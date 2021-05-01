package be.helha.ttmc.model;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

import be.helha.ttmc.exception.QuestionDoubleException;
import be.helha.ttmc.exception.QuestionIncompatibleException;
import be.helha.ttmc.exception.BasicCardOverMaxQuestionsException;

/**
 * Class for the BasicCard This class is meant to handle the most basic card
 * that the player will be interacting with.
 *
 * @author Giorgio CACULLI LA196672, Guillaume LAMBERT LA198116, Tanguy TAMINIAU
 *         LA199566
 * @version 1.0
 */
public class BasicCard implements Serializable
{
    private static final long serialVersionUID = -7761798679803585516L;
    private String author, subject;
    private Theme theme;
    private List< Question > questions;

    /**
     * Constructor for the class BasicCard
     *
     * @param author The author of the card
     * @param theme The theme
     * @param subject The subject of the card
     */
    public BasicCard( String author, Theme theme, String subject )
    {
        this.author = author;
        this.theme = theme;
        this.subject = subject;
        questions = new ArrayList< Question >();
    }

    /**
     * Function used to add a new question to the card
     * @param q The question that the user wishes to add to his card
     * @return true if added, false if not
     */
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
            if( q.getTheme() != theme || !q.getAuthor().equalsIgnoreCase( author )
                    || !q.getSubject().equalsIgnoreCase( subject ) )
            {
                throw new QuestionIncompatibleException();
            }
            if( questions.size() == 4 )
            {
                throw new BasicCardOverMaxQuestionsException();
            }
            questions.add( q.clone() );
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

    /**
     * Function used to remove a question from a card
     * @param q The card that the user wishes to remove
     * @return true if removed, false if not
     */
    public boolean remove( Question q )
    {
        if( q == null )
        {
            return false;
        }
        return questions.remove( q );
    }

    /**
     * Function used to remove a question from a card
     * @param i The position of the card that the user wishes to remove
     * @return true if removed, false if not
     */
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
    
    /**
     * Function used to modify a question into another question
     * @param oldQuestion The question meant to be changed
     * @param newQuestion The new question
     * @return true if modified, false if not
     */
    public boolean modify( Question oldQuestion, Question newQuestion )
    {
        if( oldQuestion == null || newQuestion == null )
        {
            return false;
        }
        if( oldQuestion.equals( newQuestion ) )
        {
            return false;
        }
        questions.set( questions.indexOf( oldQuestion ), newQuestion );
        return true;
    }

    /**
     * Function used to output the different question and characteristics that compose a card
     * @return The characteristics and questions of a card in a String format
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for( Question q : getQuestions() )
        {
            sb.append( q );
            sb.append( System.getProperty( "line.separator" ) );
        }
        return String.format( "Author: %s - Theme: %s - Subject: %s - Questions: %s %s", getAuthor(), getTheme(),
                getSubject(), System.getProperty( "line.separator" ), sb.toString() );
    }

    /**
     * Function used to create a new instance of the card
     * @return The new instance of the card with the same characteristics
     */
    public BasicCard clone()
    {
        BasicCard tmpbc = new BasicCard( getAuthor(), getTheme(), getSubject() );
        for( Question q : getQuestions() )
        {
            tmpbc.add( q.clone() );
        }
        return tmpbc;
    }

    /**
     * Function used to check if two cards are equal
     * @param o The object used for comparison
     * @return true if it is the same, false if not
     */
    public boolean equals( Object o )
    {
        if( o instanceof BasicCard )
        {
            BasicCard tmpc = ( BasicCard ) o;
            return getTheme() == tmpc.getTheme() && getSubject().equalsIgnoreCase( tmpc.getSubject() );
        }
        return false;
    }

    /**
     * Getter used to return the author of the card
     * @return The author of the card
     */
    public String getAuthor()
    {
        return author;
    }

    /**
     * Getter used to return theme of the card
     * @return The theme used in the card
     */
    public Theme getTheme()
    {
        return theme;
    }

    /**
     * Getter used to return the subject of the card
     * @return The subject of the card
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     * Getter used to return the list of questions present in the card
     * @return The list of questions contained inside the card
     */
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
