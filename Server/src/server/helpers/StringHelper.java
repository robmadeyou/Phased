package server.helpers;

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
}
