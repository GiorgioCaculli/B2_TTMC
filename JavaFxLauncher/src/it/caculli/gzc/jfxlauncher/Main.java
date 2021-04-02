package it.caculli.gzc.jfxlauncher;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

public class Main
{
    public static void main( String[] args )
    {
        try
        {
            SwingUtilities.invokeAndWait( new Runnable()
            {
                @Override
                public void run()
                {
                    new MainFrame();
                }
            } );
        }
        catch ( InvocationTargetException e )
        {
            e.printStackTrace();
            return;
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
            return;
        }
    }
}
