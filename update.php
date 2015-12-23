<?php

goIn( './.AggrothCacheV0/Sprites/' );
function goIn( $p )
{
    $dir = scandir( $p );
    foreach( $dir as $d )
    {
        if( $d != "." && $d != ".." )
        {
            if( is_dir( $p . $d ) )
            {
                goIn( $p . $d . '/' );
            }
            else
            {
                print $p . $d . "\n";
                rename( $p . $d, $p . str_replace( '.PNG', '.png', $d ) );
            }
        }
    }
}
