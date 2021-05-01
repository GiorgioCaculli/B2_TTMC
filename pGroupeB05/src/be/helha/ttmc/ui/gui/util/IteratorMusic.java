package be.helha.ttmc.ui.gui.util;

import java.util.List;

public class IteratorMusic
{
    private List< String > listMusic;
    private int index;

    public IteratorMusic( List< String > le )
    {
        super();
        this.listMusic = le;
        index = 0;
    }

    public int listeSize()
    {
        return listMusic.size();
    }

    public String item()
    {
        if ( index == listMusic.size() )
        {
            reset();
        }

        return listMusic.get( index );
    }

    public void next()
    {
        index++;
    }

    public void preview()
    {
        index--;
    }

    public void reset()
    {
        index = 0;
    }

    public int getIndex()
    {
        return index;
    }
}
