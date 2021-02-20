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

    @BeforeAll
    static void initAll()
    {
    }

    @BeforeEach
    void init()
    {
    }

    @Test
    public void testAssertEquals()
    {
        Question q1 = new Question( "Giorgio Caculli", Theme.INFORMATICS, "Acronyms", "What does RAM stand for?", "Random Access Memory" );
        Question q2 = q1.clone();
        assertEquals( q1, q2, "failure - strings are not equal" );
    }

    @AfterEach
    void tearDown()
    {
    }

    @AfterAll
    static void tearDownAll()
    {
    }
}
