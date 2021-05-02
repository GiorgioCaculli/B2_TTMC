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
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

import be.helha.ttmc.model.*;

public class DeckTests
{
    private static Deck d;
    private static BasicCard c1, c2, c3, c4, c5;

    @BeforeAll
    static void initAll()
    {
        d = new Deck();
        c1 = new BasicCard( "Giorgio Caculli", Theme.INFORMATICS, "Acronyms" );
        c2 = c1.clone();
        c3 = new BasicCard( "Giorgio Caculli", Theme.INFORMATICS, "Computer Science" );
        c4 = new BasicCard( "Giorgio Caculli", Theme.INFORMATICS, "Information Security" );
        c5 = new BasicCard( "Giorgio Caculli", Theme.INFORMATICS, "Networking" );
    }

    @BeforeEach
    void init()
    {
    }

    @Test
    public void testAddCard()
    {
        assertTrue( () -> d.add( c1 ), "failure - the card was not added" );
        assertTrue( () -> d.add( c3 ), "failure - the card was not added" );
        assertTrue( () -> d.add( c4 ), "failure - the card was not added" );
    }

    @Test
    public void testAddDoubles()
    {
        assertFalse( () -> d.add( c2 ), "failure - the card was added" );
    }

    @Test
    public void testAddNull()
    {
        assertFalse( () -> d.add( null ), "failure - the card was added" );
    }

    @Test
    public void testRemoveCard()
    {
        assertTrue( () -> d.remove( c3 ), "failure - the card was not removed" );
    }

    @Test
    public void testRemoveCardInt()
    {
        assertTrue( () -> d.remove( 1 ), "failure - the card was not removed" );
    }

    @Test
    public void testRemoveNonExistantCard()
    {
        assertFalse( () -> d.remove( c2 ), "failure - the card was removed" );
    }

    @Test
    public void testRemoveNullCard()
    {
        assertFalse( () -> d.remove( null ), "failure - the card was removed" );
    }

    @Test
    public void testRemoveNonExistantCardInt()
    {
        assertFalse( () -> d.remove( 1 ), "failure - the card was removed" );
    }

    @Test
    public void testRemoveNonExistantCardIntBiggerThanDeck()
    {
        assertFalse( () -> d.remove( 10 ), "failure - the card was removed" );
    }

    @Test
    public void testSameDeck()
    {
        Deck tmp = d.clone();
        assertEquals( d.getCards(), tmp.getCards(), "failure - the decks are not equal" );
        assertEquals( d.toString(), tmp.toString(), "failure - the decks are not equal" );
    }

    @Test
    public void testModifyCard()
    {
        assertTrue( d.modify( c4, c5 ) );
    }

    @AfterEach
    void tearDown()
    {
    }

    @AfterAll
    static void tearDownAll()
    {
        d = null;
        c1 = null;
        c2 = null;
    }
}
