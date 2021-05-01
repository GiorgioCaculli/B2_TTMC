package be.helha.ttmc.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import be.helha.ttmc.model.*;

public class QuestionTests
{
    private static Question q1, q2, q3;

    @BeforeAll
    static void initAll()
    {
        q1 = new Question( "Giorgio Caculli", Theme.INFORMATICS, "Acronyms", "What does RAM stand for?",
                "Random Access Memory" );
        q2 = q1.clone();
        q3 = new Question( "Giorgio Caculli", Theme.INFORMATICS, "Acronyms", "What does RAM stand for?",
                "Random Access Memory" );
    }

    @BeforeEach
    void init()
    {
    }

    @Test
    public void testSameQuestion()
    {
        assertEquals( q1, q2, "failure - strings are not equal" );
        assertEquals( q1, q3, "failure - strings are not equal" );
    }

    @Test
    public void testSameAuthor()
    {
        assertEquals( q1.getAuthor(), q2.getAuthor(), "failure - the authors are not the same" );
        assertEquals( q1.getAuthor(), q3.getAuthor(), "failure - the authors are not the same" );
    }

    @Test
    public void testSameTheme()
    {
        assertEquals( q1.getTheme(), q2.getTheme(), "failure - the authors are not the same" );
        assertEquals( q1.getTheme(), q3.getTheme(), "failure - the authors are not the same" );
    }

    @Test
    public void testSameSubject()
    {
        assertEquals( q1.getSubject(), q2.getSubject(), "failure - the authors are not the same" );
        assertEquals( q1.getSubject(), q3.getSubject(), "failure - the authors are not the same" );
    }

    @Test
    public void testSameChallenge()
    {
        assertEquals( q1.getChallenge(), q2.getChallenge(), "failure - the authors are not the same" );
        assertEquals( q1.getChallenge(), q3.getChallenge(), "failure - the authors are not the same" );
    }

    @Test
    public void testSameAnswer()
    {
        assertEquals( q1.getAnswer(), q2.getAnswer(), "failure - the authors are not the same" );
        assertEquals( q1.getAnswer(), q3.getAnswer(), "failure - the authors are not the same" );
    }

    @Test
    public void testSameToString()
    {
        assertEquals( q1.toString(), q2.toString(), "failure - the authors are not the same" );
        assertEquals( q1.toString(), q3.toString(), "failure - the authors are not the same" );
    }

    @AfterEach
    void tearDown()
    {
    }

    @AfterAll
    static void tearDownAll()
    {
        q1 = null;
        q2 = null;
        q3 = null;
    }
}
