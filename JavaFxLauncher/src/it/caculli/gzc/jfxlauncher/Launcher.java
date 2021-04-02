package it.caculli.gzc.jfxlauncher;

import java.io.File;

public class Launcher
{
    private File openjfxZip;

    public boolean detectJava()
    {
        String versionDetected = System.getProperty( "java.version" );
        String javaVersion = String.format( "Java version detected: %s", versionDetected );
        if ( versionDetected.startsWith( "1." ) )
        {
            String.format( "%s%s%s", javaVersion, System.getProperty( "line.separator" ),
                    "Java version too old. Please, update to java" );
            return false;
        }
        else
        {
            int dot = versionDetected.indexOf( "." );
            if ( dot != -1 )
                versionDetected = versionDetected.substring( 0, dot );
            if ( Integer.parseInt( versionDetected ) < 11 )
            {
                return false;
            }
        }
        return true;
    }

    public boolean detectOS()
    {
        if ( System.getProperty( "os.name" ).toString().contains( "Linux" )
                || System.getProperty( "os.name" ).toString().contains( "Windows" )
                || System.getProperty( "os.name" ).toString().contains( "Mac" ) )
        {
            return true;
        }
        return false;
    }

    public String detectWorkingDirectory()
    {
        return String.format( "Current working directory: %s", System.getProperty( "user.dir" ) );
    }

    public boolean detectOpenJFXFile()
    {
        openjfxZip = new File( "openjfx-11.0.2_sdk.zip" );
        if ( !openjfxZip.exists() )
        {
            return false;
        }
        return true;
    }

    public boolean unzippingZipFile()
    {
        String zipFilePath = openjfxZip.toString();
        String destDirectory = "libs/";
        UnzipUtility unzipper = new UnzipUtility();
        try
        {
            unzipper.unzip( zipFilePath, destDirectory );
        }
        catch ( Exception ex )
        {
            // some errors occurred
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public Launcher()
    {

    }
}
