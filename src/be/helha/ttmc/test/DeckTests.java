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

public class DeckTests
{
    private static Deck d;
    private static BasicCard c1, c2;

    @BeforeAll
    static void initAll()
    {
	d = new Deck();
	c1 = new BasicCard( "Giorgio Caculli", Theme.INFORMATICS, "Acronyms" );
	c2 = c1.clone();
    }

    @BeforeEach
    void init()
    {
    }

    @Test
    public void testAddCard()
    {
        assertTrue( () -> d.add( c1 ), "failure - the card was not added" );
    }

    @Test
    public void testAddDoubles()
    {
	assertFalse( () -> d.add( c2 ), "failure - the card was added" );
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
