package be.helha.ttmc.ui;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client
{
    private String userName;
    private String hostName;
    private int portNumber;
    private static final String DEFAULT_HOST_NAME = "localhost";
    private static final int DEFAULT_PORT_NUMBER = 7000;
    private Socket s;

    public Client( String userName, String hostName, int portNumber ) throws UnknownHostException, IOException
    {
        setUserName( userName );
        setHostName( hostName );
        setPortNumber( portNumber );
        s = new Socket( getHostName(), getPortNumber() );
    }
    
    public Client( String userName, String hostName ) throws UnknownHostException, IOException
    {
        this( userName, hostName, DEFAULT_PORT_NUMBER );
    }
    
    public Client( String userName ) throws UnknownHostException, IOException
    {
        this( userName, DEFAULT_HOST_NAME );
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName( String userName )
    {
        this.userName = userName;
    }

    public String getHostName()
    {
        return hostName;
    }

    public void setHostName( String hostName )
    {
        this.hostName = hostName;
    }

    public int getPortNumber()
    {
        return portNumber;
    }

    public void setPortNumber( int portNumber )
    {
        this.portNumber = portNumber;
    }
    
    public Socket getSocket()
    {
        return s;
    }

    public void setSocket( Socket s )
    {
        this.s = s;
    }
}
