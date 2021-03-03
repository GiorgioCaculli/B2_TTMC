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
    private static BasicCard c;
    private static Question q1, q2, q3, q4, q5;

    @BeforeAll
    static void initAll()
    {
        c = new BasicCard( "Giorgio Caculli", Theme.INFORMATICS, "Acronyms" );
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
    }

    @BeforeEach
    void init()
    {
    }

    @Test
    public void testBasicCardEquals()
    {
        BasicCard bc1 = new BasicCard( "Giorgio Caculli", Theme.INFORMATICS, "Acronyms" );
        BasicCard bc2 = bc1.clone();
        assertEquals( bc1, bc2, "failure - byte arrays not same" );
    }

    @Test
    public void testAddQuestions()
    {
        assertTrue( () -> c.add( q1 ), "failure - the question was not added" );
        assertTrue( () -> c.add( q2 ), "failure - the question was not added" );
        assertTrue( () -> c.add( q3 ), "failure - the question was not added" );
    }

    @Test
    public void testAddDouble()
    {
        assertFalse( () -> c.add( q1 ), "failure - the question was added" );
    }

    @Test
    public void testAddMoreThanFour()
    {
        assertTrue( () -> c.add( q4 ), "failure - the question was not added" );
        assertFalse( () -> c.add( q5 ), "failure - the question was added" );
    }

    @Test
    public void testAddCardWithDifferentAuthor()
    {
        Question q6 = new Question( "Giorgio Lambert", Theme.INFORMATICS, "Acronyms", "What does IT stand for?",
                "Information Technology" );
        assertFalse( () -> c.add( q6 ), "failure - the question was added" );
    }

    @Test
    public void testAddCardWithDifferentTheme()
    {
        Question q7 = new Question( "Giorgio Caculli", Theme.IMPROBABLE, "Acronyms", "What does IT stand for?",
                "Information Technology" );
        assertFalse( () -> c.add( q7 ), "failure - the question was added" );
    }

    @Test
    public void testAddCardWithDifferentSubject()
    {
        Question q8 = new Question( "Giorgio Caculli", Theme.INFORMATICS, "Acronym", "What does IT stand for?",
                "Information Technology" );
        assertFalse( () -> c.add( q8 ), "failure - the question was added" );
    }

    @Test
    public void testRemoveCard()
    {
        assertTrue( () -> c.remove( q1 ), "failure - the card was not removed" );
    }

    @Test
    public void testRemoveCardInt()
    {
        assertTrue( () -> c.remove( 2 ), "failure - the card was not removed" );
    }

    @Test
    public void testRemoveNonExistantCard()
    {
        assertFalse( () -> c.remove( q5 ), "failure - the card was not removed" );
    }

    @Test
    public void testRemoveNonExistantCardInt()
    {
        assertFalse( () -> c.remove( 3 ), "failure - the card was not removed" );
    }

    @AfterEach
    void tearDown()
    {
    }

    @AfterAll
    static void tearDownAll()
    {
        c = null;
        q1 = null;
        q2 = null;
        q3 = null;
        q4 = null;
        q5 = null;
    }
}
