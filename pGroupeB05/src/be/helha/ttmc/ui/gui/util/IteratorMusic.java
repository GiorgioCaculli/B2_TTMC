package be.helha.ttmc.ui.gui.util;

import java.util.List;

public class IteratorMusic
{
    private List< String > listM;
    private int index;

    public IteratorMusic( List< String > le )
    {
        super();
        this.listM = le;
        index = 0;
    }

    public int listeSize()
    {
        return listM.size();
    }

    public String item()
    {
        if ( index == listM.size() )
        {
            reset();
        }

        return listM.get( index );
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
