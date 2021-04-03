package be.helha.ttmc.ui;

import java.io.IOException;
import java.net.ServerSocket;

public class Server
{
    private int portNumber;
    private static final int DEFAULT_PORT_NUMBER = 7000;
    private ServerSocket serverSocket;

    public Server( int portNumber ) throws IOException
    {
        setPortNumber( portNumber );
        this.serverSocket = new ServerSocket( getPortNumber() );
    }
    
    public Server() throws IOException
    {
        this( DEFAULT_PORT_NUMBER );
    }
    
    public int getPortNumber()
    {
        return portNumber;
    }

    public void setPortNumber( int portNumber )
    {
        this.portNumber = portNumber;
    }

    public ServerSocket getServerSocket()
    {
        return serverSocket;
    }

    public void setServerSocket( ServerSocket serverSocket )
    {
        this.serverSocket = serverSocket;
    }
}
