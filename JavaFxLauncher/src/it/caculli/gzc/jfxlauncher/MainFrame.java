package it.caculli.gzc.jfxlauncher;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainFrame extends JFrame
{
    private static final long serialVersionUID = -8022751823585317111L;
    private static final short WIDTH = 640;
    private static final short HEIGHT = WIDTH / 4 * 3;

    public MainFrame()
    {
        ImageIcon img = new ImageIcon( Main.class.getResource( "assets/images/paw.png" ) );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setIconImage( img.getImage() );
        setLayout( new BorderLayout() );
        setLocationByPlatform( true );
        setResizable( false );
        setSize( WIDTH, HEIGHT );
        setTitle( "What Do You Know About It? Launcher" );
        add( new MainPanel(), BorderLayout.CENTER );
        setVisible( true );
        addWindowFocusListener( new WindowFocusListener()
        {
            
            @Override
            public void windowLostFocus( WindowEvent e )
            {
            }
            
            @Override
            public void windowGainedFocus( WindowEvent e )
            {
            }
        } );
        
        addWindowListener( new WindowListener()
        {
            
            @Override
            public void windowOpened( WindowEvent e )
            {
            }
            
            @Override
            public void windowIconified( WindowEvent e )
            {
            }
            
            @Override
            public void windowDeiconified( WindowEvent e )
            {
            }
            
            @Override
            public void windowDeactivated( WindowEvent e )
            {
            }
            
            @Override
            public void windowClosing( WindowEvent e )
            {
            }
            
            @Override
            public void windowClosed( WindowEvent e )
            {
            }
            
            @Override
            public void windowActivated( WindowEvent e )
            {
            }
        } );
        
        addWindowStateListener( new WindowStateListener()
        {
            @Override
            public void windowStateChanged( WindowEvent e )
            {
            }
        } );
    }
}
