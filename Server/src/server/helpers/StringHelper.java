package server.helpers;

import java.util.ArrayList;

public class StringHelper
{
    public static String pluralize( int n, String s )
    {
        return n == 1 ? s : s + "s";
    }

    public static String combine (String[] s, String glue)
    {
        int k = s.length;
        if ( k == 0 )
        {
            return "";
        }
        StringBuilder out = new StringBuilder ();
        boolean first = true;
        for ( int x = 0; x < k; ++x )
        {
            if( s[ x ] != null && !s[ x ].equals( "" ) )
            {
                if( !first )
                {
                    out.append ( glue );
                }
                out.append ( s[ x ] );
                first = false;
            }
        }
        return out.toString ();
    }

    /**
     *
     * @param prepend String[]
     * @param s String[] Base strings that you want to prepend this one thing to
     * @return String[] of all prepended strings
     */
    public static String[] prepend( String prepend, String[] s )
    {
        for( int i = 0; i < s.length; i++ )
        {
            s[i] = prepend( prepend, s[i] );
        }
        return s;
    }

    /**
     *
     * @param prepend String you want to prepend to base
     * @param base String base that you want things to be prepended to
     * @return String
     */
    public static String prepend( String prepend, String base )
    {
        return prepend + base;
    }

    public static ArrayList<String> removeEmptyArrayEntries( String[] str )
    {
        ArrayList<String> tmp = new ArrayList<>();
        for( String s : str )
        {
            if( !s.equals( "" ) && s != null )
            {
                tmp.add( s );
            }
        }
        return tmp;
    }
}
