package it.caculli.gzc.jfxlauncher;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * This utility extracts files and directories of a standard zip file to a
 * destination directory.
 * 
 * @author www.codejava.net
 * @author Giorgio Caculli
 */
public class UnzipUtility
{
    /**
     * Size of the buffer to read/write data
     */
    private static final int BUFFER_SIZE = 1024;

    /**
     * Extracts a zip file specified by the zipFilePath to a directory specified by
     * destDirectory (will be created if does not exists)
     * 
     * @param zipFilePath
     * @param destDirectory
     * 
     * @throws IOException
     */
    public void unzip( String zipFilePath, String destDirectory )
    {
        File destDir = new File( destDirectory );
        if ( !destDir.exists() )
        {
            destDir.mkdir();
        }
        ZipInputStream zipIn;
        try
        {
            zipIn = new ZipInputStream( new FileInputStream( zipFilePath ) );
            ZipEntry entry;
            try
            {
                entry = zipIn.getNextEntry();
                // iterates over entries in the zip file
                while ( entry != null )
                {
                    String filePath = destDirectory + File.separator + entry.getName();
                    if ( !entry.isDirectory() )
                    {
                        // if the entry is a file, extracts it
                        extractFile( zipIn, filePath );
                    }
                    else
                    {
                        // if the entry is a directory, make the directory
                        File dir = new File( filePath );
                        dir.mkdirs();
                    }
                    zipIn.closeEntry();
                    entry = zipIn.getNextEntry();
                }
                zipIn.close();
            }
            catch ( IOException e )
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        catch ( FileNotFoundException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Extracts a zip entry (file entry)
     * 
     * @param zipIn
     * @param filePath
     * 
     * @throws IOException
     */
    private void extractFile( ZipInputStream zipIn, String filePath )
    {
        BufferedOutputStream bos;
        try
        {
            bos = new BufferedOutputStream( new FileOutputStream( filePath ) );
            byte[] bytesIn = new byte[ BUFFER_SIZE ];
            int read = 0;
            try
            {
                while ( ( read = zipIn.read( bytesIn ) ) != -1 )
                {
                    bos.write( bytesIn, 0, read );
                }
                bos.close();
            }
            catch ( IOException e )
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        catch ( FileNotFoundException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
