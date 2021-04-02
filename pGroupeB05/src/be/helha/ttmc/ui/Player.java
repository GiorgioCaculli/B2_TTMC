package be.helha.ttmc.ui;

import java.util.List;

import be.helha.ttmc.model.BasicCard;

public class Player
{
    private List< BasicCard > cards;
    private int cardNb = 0;
    private String nickNamePlayer;
    private int scorePlayer;
    
    public Player( List< BasicCard > cards )
    {
        setCards( cards );
        setScorePlayer( 0 );
    }

    public void setCards( List< BasicCard > cards )
    {
        this.cards = cards;
    }

    public List< BasicCard > getCards()
    {
        return cards;
    }

    public int getCardNb()
    {
        return cardNb;
    }

    public void setCardNb( int cardNb )
    {
        this.cardNb = cardNb;
    }

    public void setNickNamePlayer( String nickNamePlayer )
    {
        this.nickNamePlayer = nickNamePlayer;
    }

    public String getNickNamePlayer()
    {
        return nickNamePlayer;
    }

    public int getScorePlayer()
    {
        return scorePlayer;
    }

    public void setScorePlayer( int scorePlayer )
    {
        this.scorePlayer = scorePlayer;
    }
}
