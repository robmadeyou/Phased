<?php

goIn( '.AggrothCacheV0/Sprites/' );
function goIn( $p )
{
    $dir = scandir( $p );
    foreach( $dir as $d )
    {
        if( $d != "." || $d != ".." )
        {
            if( is_dir( $d ) )
            {
                
            }
            else
            {
            }
        }
    }
}
