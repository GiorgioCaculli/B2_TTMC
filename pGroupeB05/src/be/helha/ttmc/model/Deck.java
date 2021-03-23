package be.helha.ttmc.model;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Deck
{
    private List< BasicCard > cards;

    public Deck()
    {
        cards = new ArrayList< BasicCard >();
    }

    public boolean add( BasicCard c )
    {
        if ( c == null )
        {
            return false;
        }
        if ( cards.contains( c ) )
        {
            return false;
        }
        return cards.add( c );
    }

    public boolean remove( BasicCard c )
    {
        if ( c == null )
        {
            return false;
        }
        return cards.remove( c );
    }

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
    
    public boolean modify( BasicCard oldCard, BasicCard newCard )
    {
        if( oldCard == null || newCard == null )
        {
            return false;
        }
        if( cards.indexOf( oldCard ) < 0 )
        {
            return false;
        }
        cards.set( cards.indexOf( oldCard ), newCard );
        return true;
    }

    public List< BasicCard > getCards()
    {
        List< BasicCard > tmpCards = new ArrayList<>();
        for ( BasicCard card : cards )
        {
            tmpCards.add( card.clone() );
        }
        return tmpCards;
    }

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
}
