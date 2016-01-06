package server.util;

import server.Server;
import server.model.players.PlayerHandler;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class InputManager implements Runnable {

    private ServerSocket server;
    private Socket socket;

    public InputManager( )
    {
        try
        {
            server = new ServerSocket ( 4412, 0 );
        }
        catch( UnknownHostException e )
        {
            System.out.println ( "Could not create a new server :(" );
        }
        catch( IOException e )
        {
            System.out.println ( "IO Exception when creating the server" );
        }
    }

    public String handleInput(String line) {
        switch( line )
        {
            case "online":
                return PlayerHandler.playerCount + "";
            case "uptime":
                return Server.getUptime ();
            default:
                return "Sorry, no idea what you want";
        }
    }

    public void run() {
        BufferedReader is = null;
        PrintWriter os = null;

        while( true )
        {
            try
            {
                socket = server.accept ();
                is = new BufferedReader(
                        new InputStreamReader( socket.getInputStream () ) );
                os = new PrintWriter( socket.getOutputStream (), true );

                String responseLine;
                while ((responseLine = is.readLine()) != null) {
                    System.out.println("Server: " + responseLine);
                    os.println ( handleInput ( responseLine ) );
                    break;
                }

                socket.close ();
            }
            catch( Exception e )
            {
                e.printStackTrace ();
            }
        }
    }
}
