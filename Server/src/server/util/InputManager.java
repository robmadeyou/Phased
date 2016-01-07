package server.util;

import server.Server;
import server.helpers.StringHelper;
import server.model.players.Client;
import server.model.players.Player;
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

        if( line.startsWith( "message" ) )
        {
            String[] ar = line.split( " " );
            String name = ar[ 1 ];
            ar[0] = null;
            ar[1] = null;
            String message = StringHelper.combine( ar, " " );

            Client c = (Client)PlayerHandler.getPlayerFromName( name );
            c.sendMessage( message );
            return "done";
        }
        else if( line.startsWith( "kick" ) )
        {
            String[] heh = line.split( " " );
            heh[ 0 ] = null;
            for( String s : heh )
            {
                if( s != null )
                {
                    Client c = (Client)PlayerHandler.getPlayerFromName( s );
                    c.logout();
                }
            }
            return "Done";
        }

        switch( line )
        {
            case "player-list":
                return StringHelper.combine( PlayerHandler.playersCurrentlyOn, "," );
            case "online":
                return PlayerHandler.playerCount + "";
            case "uptime":
                return Server.getUptime ();
            default:
                return "Sorry, no idea what you want";
        }
    }

    public void run() {
        BufferedReader is;
        PrintWriter os;

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
