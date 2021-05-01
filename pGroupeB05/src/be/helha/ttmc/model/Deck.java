package be.helha.ttmc.model;

import java.io.Serializable;
import java.util.List;

import be.helha.ttmc.exception.BasicCardDoubleException;
import be.helha.ttmc.exception.BasicCardNotFoundException;

import java.util.ArrayList;

/**
 * Class meant to contain all the cards that will be played.
 * 
 * @author Giorgio CACULLI LA196672, Guillaume LAMBERT LA198116, Tanguy TAMINIAU
 *         LA199566
 * 
 * @version 1.0
 * 
 * @see BasicCard
 */
public class Deck implements Serializable
{
    private static final long serialVersionUID = -7971558764768326440L;
    private List< BasicCard > cards;

    /**
     * Constructor for the Deck initializing the ArrayList containing the
     * BasicCard(s).
     */
    public Deck()
    {
        cards = new ArrayList< BasicCard >();
    }

    /**
     * Function meant to add a new BasicCard to the Deck.
     * 
     * @param c The card that is meant to be added.
     * 
     * @return true if the card is successfully added, false if not.
     */
    public boolean add( BasicCard c )
    {
        try
        {
            if ( c == null )
            {
                throw new NullPointerException();
            }
            if ( cards.contains( c ) )
            {
                throw new BasicCardDoubleException();
            }
            return cards.add( c.clone() );
        }
        catch ( NullPointerException npe )
        {
            npe.printStackTrace();
            return false;
        }
        catch ( BasicCardDoubleException bcde )
        {
            bcde.printStackTrace();
            return false;
        }
    }

    /**
     * Function meant to remove a card from the deck.
     * 
     * @param c The card meant to be removed.
     * 
     * @return true if the card has been removed successfully, false if not
     */
    public boolean remove( BasicCard c )
    {
        if ( c == null )
        {
            return false;
        }
        return cards.remove( c );
    }

    /**
     * Function meant to remove a card from the deck based on its position.
     * 
     * @param i The position of the card meant to be removed.
     * 
     * @return true if the card has been removed successfully, false if not.
     */
    public boolean remove( int i )
    {
        if ( i < 1 && i > 24 )
        {
            return false;
        }
        if ( i > cards.size() )
        {
            return false;
        }
        return cards.remove( cards.get( i - 1 ) );
    }

    /**
     * Function meant to replace a card with a new one.
     * 
     * @param oldCard The card meant to be replaced.
     * @param newCard The card that will replace it
     * 
     * @return true if the card has been successfully replaced, false if not.
     */
    public boolean modify( BasicCard oldCard, BasicCard newCard )
    {
        try
        {
            if ( oldCard == null || newCard == null )
            {
                throw new NullPointerException();
            }
            if ( cards.indexOf( oldCard ) < 0 )
            {
                throw new BasicCardNotFoundException();
            }
            cards.set( cards.indexOf( oldCard ), newCard );
            return true;
        }
        catch (NullPointerException npe) {
            npe.printStackTrace();
            return false;
        }
        catch (BasicCardNotFoundException bcnfe) {
            bcnfe.printStackTrace();
            return false;
        }
    }

    /**
     * Function meant to return the cards contained in the deck.
     * 
     * @return the list of cards contained inside the deck.
     */
    public List< BasicCard > getCards()
    {
        List< BasicCard > tmpCards = new ArrayList<>();
        for ( BasicCard card : cards )
        {
            tmpCards.add( card.clone() );
        }
        return tmpCards;
    }

    /**
     * Function meant to return the content of the deck in a String format.
     * 
     * @return The deck in String format
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append( String.format( "%s%s", "Deck", System.getProperty( "line.separator" ) ) );
        for ( BasicCard card : cards )
        {
            sb.append( card );
            sb.append( System.getProperty( "line.separator" ) );
        }
        return sb.toString();
    }

    /**
     * Function meant to return a new instance of the deck
     * 
     * @return The new deck with the same cards as the original one.
     */
    public Deck clone()
    {
        Deck tmpDeck = new Deck();
        for ( BasicCard c : cards )
        {
            tmpDeck.add( c.clone() );
        }
        return tmpDeck;
    }
}
