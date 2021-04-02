package it.caculli.gzc.jfxlauncher;

import java.awt.BorderLayout;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultCaret;

public class MainPanel extends JPanel
{
    private static final long serialVersionUID = -3001819637968779779L;
    private JTextArea logArea = new JTextArea();
    private JScrollPane scrollPane;
    private Launcher launcher = null;
    private JProgressBar downloadProgressBar = new JProgressBar();

    private void launchApplication()
    {
        logArea.append( "OpenJFX 11 Library Zip Found\n" );
        logArea.append( "Unzipping OpenJFX 11 Library Zip\n" );
        if ( launcher.unzippingZipFile() )
        {
            logArea.append( "Unzipped OpenJFX 11 Library\n" );
            logArea.append( "Opening game\n" );
            SwingUtilities.invokeLater( new Runnable()
            {
                @Override
                public void run()
                {
                    logArea.append( String.format( "Java Class Path: %s\n", System.getProperty( "java.class.path" ) ) );
                    File javafxDir = new File( "libs/javafx-sdk-11.0.2/lib" );
                    /*
                    for ( String file : javafxDir.list() )
                    {
                        if ( file.contains( ".jar" ) )
                        {
                            System.setProperty( "java.class.path", String.format( "%s:libs/javafx-sdk-11.0.2/lib/%s",
                                    System.getProperty( "java.class.path" ).toString(), file ) );
                        }
                    }
                    */
                    // System.out.println( System.getProperty( "java.class.path" ) );
                    String javaExecutable = null;
                    logArea.append( "Detecting PATHs\n" );
                    for ( String dirPaths : System.getenv( "PATH" ).split( File.pathSeparator ) )
                    {
                        logArea.append( String.format( "%s\n", dirPaths ) );
                        File file = null;
                        if ( System.getProperty( "os.name" ).contains( "Windows" ) )
                        {
                            file = new File( dirPaths, "java.exe" );
                        }
                        else
                        {
                            file = new File( dirPaths, "java" );
                        }
                        if ( file != null )
                        {
                            if ( file.isFile() && file.canExecute() )
                            {
                                javaExecutable = file.getAbsolutePath();
                                break;
                            }
                        }
                    }
                    logArea.append( String.format( "Java path is: %s\n", javaExecutable ) );
                    if ( javaExecutable == null )
                    {
                        logArea.append( "Java not found in PATH" );
                        return;
                    }

                    ProcessBuilder processBuilder = new ProcessBuilder( javaExecutable, "--module-path",
                            javafxDir.toString(), "--add-modules=ALL-MODULE-PATH", "-jar", "groupeb05.jar" );
                    SwingUtilities.invokeLater( new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            processBuilder.redirectErrorStream( true );
                            try
                            {
                                Process p = processBuilder.start();
                                BufferedReader r = new BufferedReader( new InputStreamReader( p.getInputStream() ) );
                                String line;
                                while ( true )
                                {
                                    line = r.readLine();
                                    if ( line == null )
                                    {
                                        break;
                                    }
                                    logArea.append( String.format( "%s\n", line ) );
                                }
                            }
                            catch ( IOException e )
                            {
                                e.printStackTrace();
                                return;
                            }
                            JOptionPane.showMessageDialog( null, "Thank you for playing!\nYou may close the launcher.",
                                    "Thank you for playing", JOptionPane.INFORMATION_MESSAGE );
                        }
                    } );
                }
            } );
        }

    }

    private void manageLibraries()
    {
        if ( !launcher.detectOpenJFXFile() )
        {
            downloadProgressBar.setVisible( true );
            logArea.append( "Downloading OpenJFX 11 Library\n" );
            File openjfxZip = new File( "openjfx-11.0.2_sdk.zip" );
            String path = "";
            if ( System.getProperty( "os.name" ).toString().contains( "Linux" ) )
            {
                path = "https://download2.gluonhq.com/openjfx/11.0.2/openjfx-11.0.2_linux-x64_bin-sdk.zip";
            }
            else if ( System.getProperty( "os.name" ).toString().contains( "Mac" ) )
            {
                path = "https://download2.gluonhq.com/openjfx/11.0.2/openjfx-11.0.2_osx-x64_bin-sdk.zip";
            }
            else if ( System.getProperty( "os.name" ).toString().contains( "Windows" ) )
            {
                path = "https://download2.gluonhq.com/openjfx/11.0.2/openjfx-11.0.2_windows-x64_bin-sdk.zip";
            }
            final String downloadLink = path;
            new Thread( new Runnable()
            {
                @Override
                public void run()
                {
                    URL url;
                    try
                    {
                        url = new URL( downloadLink );
                        HttpURLConnection httpConnection;
                        try
                        {
                            httpConnection = ( HttpURLConnection ) ( url.openConnection() );
                            int fileSize = httpConnection.getContentLength();
                            if ( openjfxZip == null || openjfxZip.length() != fileSize )
                            {
                                downloadProgressBar.setMaximum( fileSize );
                                BufferedInputStream in = new BufferedInputStream( httpConnection.getInputStream() );
                                FileOutputStream fos = new FileOutputStream( openjfxZip );
                                int byteSize = 1024;
                                BufferedOutputStream bout = new BufferedOutputStream( fos, byteSize );
                                byte[] data = new byte[ byteSize ];
                                long downloadFileSize = 0;
                                int x = 0;
                                while ( ( x = in.read( data, 0, byteSize ) ) >= 0 )
                                {
                                    downloadFileSize += x;
                                    final int currentProgress = ( int ) downloadFileSize;
                                    SwingUtilities.invokeLater( new Runnable()
                                    {
                                        @Override
                                        public void run()
                                        {
                                            downloadProgressBar
                                                    .setString( String.format( "%d / %d", currentProgress, fileSize ) );
                                            downloadProgressBar.setStringPainted( true );
                                            downloadProgressBar.setValue( currentProgress );
                                        }
                                    } );
                                    bout.write( data, 0, x );
                                }
                                bout.close();
                                in.close();
                                launchApplication();
                            }
                        }
                        catch ( IOException e )
                        {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    catch ( MalformedURLException e )
                    {
                        e.printStackTrace();
                    }
                }
            } ).start();
        }
        if ( launcher.detectOpenJFXFile() )
        {
            SwingUtilities.invokeLater( new Runnable()
            {

                @Override
                public void run()
                {
                    launchApplication();
                }

            } );
        }

    }

    public MainPanel()
    {
        setLayout( new BorderLayout() );
        logArea.setEditable( false );
        logArea.setWrapStyleWord( true );
        logArea.setLineWrap( true );
        logArea.setAutoscrolls( true );
        DefaultCaret caret = ( DefaultCaret ) logArea.getCaret();
        caret.setUpdatePolicy( DefaultCaret.ALWAYS_UPDATE );
        scrollPane = new JScrollPane( logArea );
        scrollPane.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );
        scrollPane.setAutoscrolls( true );
        launcher = new Launcher();
        logArea.setText( "Welcome to What Do You Know About It?'s Launcher!\n" );
        downloadProgressBar.setVisible( false );
        add( scrollPane, BorderLayout.CENTER );
        add( downloadProgressBar, BorderLayout.PAGE_END );
        SwingUtilities.invokeLater( new Runnable()
        {
            @Override
            public void run()
            {
                if ( launcher.detectJava() )
                {
                    logArea.append(
                            String.format( "Java version detected: %s\n", System.getProperty( "java.version" ) ) );
                    if ( launcher.detectOS() )
                    {
                        logArea.append(
                                String.format( "Operating System detected: %s\n", System.getProperty( "os.name" ) ) );
                        logArea.append( String.format( "%s\n", launcher.detectWorkingDirectory() ) );
                        SwingUtilities.invokeLater( new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                manageLibraries();
                            }
                        } );
                    }
                }
                else
                {

                    logArea.append( "Java version too old. Please, update to java" );
                }
            }
        } );
    }
}
