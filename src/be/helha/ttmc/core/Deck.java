package be.helha.ttmc.core;

import java.util.List;
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
	if( c == null )
	    {
		return false;
	    }
	if( cards.contains( c ) )
	    {
		return false;
	    }
	return cards.add( c );
    }

    public boolean remove( BasicCard c )
    {
	if( c == null )
	    {
		return false;
	    }
	return cards.remove( c );
    }

    public boolean remove( int i )
    {
	if( i < 1 && i > 24 )
	    {
		return false;
	    }
	if( i > cards.size() )
	    {
		return false;
	    }
	return cards.remove( cards.get( i - 1 ) );
    }
    
    public List< BasicCard > getCards()
    {
        List< BasicCard > tmpCards = new ArrayList<>();
        for( BasicCard card : cards )
        {
            tmpCards.add( card.clone() );
        }
        return tmpCards;
    }
}
