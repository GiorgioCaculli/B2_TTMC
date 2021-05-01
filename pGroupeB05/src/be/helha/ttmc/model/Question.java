package be.helha.ttmc.model;

import java.io.Serializable;

/**
 * Class meant represent a question that will contained in a card.
 * 
 * @author Giorgio CACULLI LA196672, Guillaume LAMBERT LA198116, Tanguy TAMINIAU
 *         LA199566
 * 
 * @version 1.0
 * 
 * @see BasicCard
 * @see Theme
 */
public class Question implements Serializable
{
    private static final long serialVersionUID = -5695162803564015663L;
    private String author, subject, challenge, answer;
    private Theme theme;

    /**
     * Constructor for the class Question
     * 
     * @param author    The author of the question.
     * @param theme     The theme of the question.
     * @param subject   The subject of the question.
     * @param challenge The question that the player will have to answer.
     * @param answer    The one and only answer the player has to give.
     */
    public Question( String author, Theme theme, String subject, String challenge, String answer )
    {
        this.author = author;
        this.theme = theme;
        this.subject = subject;
        this.challenge = challenge;
        this.answer = answer;
    }

    /**
     * Function meant to create a copy of the question with the same.
     * 
     * @return A copy of the card with the same characteristics of the original.
     */
    public Question clone()
    {
        return new Question( author, theme, subject, challenge, answer );
    }

    /**
     * Function meant to output the question in a String format.
     * 
     * @return The question in a String format.
     */
    public String toString()
    {
        return String.format( "Author: %s - Theme: %s - Subject: %s - Challenge: %s - Answer: %s", author, theme,
                subject, challenge, answer );
    }

    /**
     * Function meant to verify if two questions are identical.
     * 
     * @param o The question used for the comparison.
     * 
     * @return true if the questions are identical, false if not.
     */
    public boolean equals( Object o )
    {
        if ( o instanceof Question )
        {
            Question tmpq = ( Question ) o;
            return subject.equalsIgnoreCase( tmpq.subject ) && theme == tmpq.theme
                    && answer.equalsIgnoreCase( tmpq.answer );
        }
        return false;
    }

    /**
     * Function meant to return the author of the question.
     * 
     * @return The author of the question.
     */
    public String getAuthor()
    {
        return author;
    }

    /**
     * Function meant to return the theme of the question.
     * 
     * @return The theme of the question.
     */
    public Theme getTheme()
    {
        return theme;
    }

    /**
     * Function meant to return the topic of the question.
     * 
     * @return The topic of the question.
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     * Function meant to return the question the player has to answer.
     * 
     * @return The question meant to be answered by the player.
     */
    public String getChallenge()
    {
        return challenge;
    }

    /**
     * Function meant to return the one and only answer acceptable.
     * 
     * @return The one and only answer the player has to provide.
     */
    public String getAnswer()
    {
        return answer;
    }
}
