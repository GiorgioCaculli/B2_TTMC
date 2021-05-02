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
import be.helha.ttmc.exception.*;

public class BasicCardTests
{
    private static BasicCard c1, c2;
    private static Question q1, q2, q3, q4, q5, q6, q7, q8, q9;

    @BeforeAll
    static void initAll()
    {
        c1 = new BasicCard( "Giorgio Caculli", Theme.INFORMATICS, "Acronyms" );
        c2 = c1.clone();
        q1 = new Question( "Giorgio Caculli", Theme.INFORMATICS, "Acronyms", "What does RAM stand for?",
                "Random Access Memory" );
        q2 = new Question( "Giorgio Caculli", Theme.INFORMATICS, "Acronyms", "What does JAR stand for?",
                "Java ARchive" );
        q3 = new Question( "Giorgio Caculli", Theme.INFORMATICS, "Acronyms", "What does WWW stand for?",
                "World Wide Web" );
        q4 = new Question( "Giorgio Caculli", Theme.INFORMATICS, "Acronyms", "What does CPU stand for?",
                "Central Processing Unit" );
        q5 = new Question( "Giorgio Caculli", Theme.INFORMATICS, "Acronyms", "What does GPU stand for?",
                "Graphics Processing Unit" );
        q6 = new Question( "Giorgio Lambert", Theme.INFORMATICS, "Acronyms", "What does IT stand for?",
                "Information Technology" );
        q7 = new Question( "Giorgio Caculli", Theme.IMPROBABLE, "Acronyms", "What does IT stand for?",
                "Information Technology" );
        q8 = new Question( "Giorgio Caculli", Theme.INFORMATICS, "Acronym", "What does IT stand for?",
                "Information Technology" );
        q9 = q5.clone();
    }

    @BeforeEach
    void init()
    {
    }

    @Test
    public void testAddQuestions()
    {
        assertTrue( () -> c1.add( q1 ), "failure - the question was not added" );
        assertTrue( () -> c1.add( q2 ), "failure - the question was not added" );
        assertTrue( () -> c1.add( q3 ), "failure - the question was not added" );
    }

    @Test
    public void testAddDouble()
    {
        assertFalse( () -> c1.add( q1 ), "failure - the question was added" );
    }

    @Test
    public void testAddMoreThanFour()
    {
        assertTrue( () -> c1.add( q4 ), "failure - the question was not added" );
        assertFalse( () -> c1.add( q5 ), "failure - the question was added" );
    }

    @Test
    public void testAddCardWithDifferentAuthor()
    {
        assertFalse( () -> c1.add( q6 ), "failure - the question was added" );
    }

    @Test
    public void testAddCardWithDifferentTheme()
    {
        assertFalse( () -> c1.add( q7 ), "failure - the question was added" );
    }

    @Test
    public void testAddCardWithDifferentSubject()
    {
        assertFalse( () -> c1.add( q8 ), "failure - the question was added" );
    }

    @Test
    public void testRemoveCard()
    {
        assertTrue( () -> c1.remove( q1 ), "failure - the card was not removed" );
    }

    @Test
    public void testRemoveCardInt()
    {
        assertTrue( () -> c1.remove( 2 ), "failure - the card was not removed" );
    }

    @Test
    public void testRemoveNonExistantCard()
    {
        assertFalse( () -> c1.remove( q8 ), "failure - the card was not removed" );
    }

    @Test
    public void testRemoveNonExistantCardInt()
    {
        assertFalse( () -> c1.remove( 4 ), "failure - the card was not removed" );
    }
    
    @Test
    public void testSameCards()
    {
        assertEquals( c1, c2, "failure - the cards are not equal" );
    }
    
    @Test
    public void testToString()
    {
        assertEquals( c1.toString(), c2.toString(), "failure - toString is not the same" );
    }
    
    @Test
    public void testQuestionModify()
    {
        assertTrue( () -> c1.modify( q3, q5 ), "failure - the card was not modified" );
        assertEquals( c1.getQuestions().get( 0 ), q5, "failure - the card was not modified" );
    }
    
    @Test
    public void testNullQuestionModify()
    {
        assertFalse( () -> c1.modify( q5, null ), "failure - the card was modified" );
    }
    
    @Test
    public void testSameQuestionModify()
    {
        assertFalse( () -> c1.modify( q9, q5 ), "failure - the card was modified" );
    }

    @AfterEach
    void tearDown()
    {
    }

    @AfterAll
    static void tearDownAll()
    {
        c1 = null;
        q1 = null;
        q2 = null;
        q3 = null;
        q4 = null;
        q5 = null;
        q6 = null;
        q7 = null;
        q8 = null;
    }
}
