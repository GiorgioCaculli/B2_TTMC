package be.helha.ttmc.model;

import java.io.Serializable;

/**
 * Class meant represent a question that will contained in a card.
 * @author Giorgio CACULLI LA196672, Guillaume LAMBERT LA198116, Tanguy TAMINIAU
 *         LA199566
 * @version 1.0
 * @see BasicCard
 * @see Theme
 */
public class Question implements Serializable
{
    private static final long serialVersionUID = -5695162803564015663L;
    private String author, subject, challenge, answer;
    private Theme theme;

    public Question( String author, Theme theme, String subject, String challenge, String answer )
    {
        this.author = author;
        this.theme = theme;
        this.subject = subject;
        this.challenge = challenge;
        this.answer = answer;
    }

    public Question clone()
    {
        return new Question( author, theme, subject, challenge, answer );
    }

    public String toString()
    {
        return String.format( "Author: %s - Theme: %s - Subject: %s - Challenge: %s - Answer: %s", author, theme,
                subject, challenge, answer );
    }

    public boolean equals( Object o )
    {
        if( o instanceof Question )
        {
            Question tmpq = ( Question ) o;
            return subject.equalsIgnoreCase( tmpq.subject ) && theme == tmpq.theme
                    && answer.equalsIgnoreCase( tmpq.answer );
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

    public String getChallenge()
    {
        return challenge;
    }

    public String getAnswer()
    {
        return answer;
    }
}
