package com.gmail.robmadeyou;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by r on 06/01/16.
 */
public class ServerManager
{
    static Socket sock;

    public static void main (String[] args)
    {
        if( args == null )
        {
            args = new String[0];
        }
        try
        {
            sock = new Socket ( args[0], Integer.parseInt ( args[1] ) );

            String[] params = new String[args.length - 2];
            for( int i = 2; i < args.length; i++ )
            {
                params[ i - 2 ] = args[ i ];
            }
            PrintWriter os = new PrintWriter(sock.getOutputStream(), true);
            os.println ( ServerManager.combine ( params, " " ) );

            BufferedReader is = new BufferedReader(
                    new InputStreamReader( sock.getInputStream () ) );
            String fromServer;
            while ((fromServer = is.readLine()) != null)
            {
                System.out.println ( fromServer );
                if ( fromServer.equals ( "Bye." ) )
                    break;
            }

            is.close ();
            os.close ();
        } catch ( Exception ex )
        {
            ex.printStackTrace ();
        }
    }

    public static String combine (String[] s, String glue)
    {
        int k = s.length;
        if ( k == 0 )
        {
            return "";
        }
        StringBuilder out = new StringBuilder ();
        out.append ( s[ 0 ] );
        for ( int x = 1; x < k; ++x )
        {
            out.append ( glue ).append ( s[ x ] );
        }
        return out.toString ();
    }
}
