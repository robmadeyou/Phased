package server.commands;


import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by r on 08/01/16.
 */
public class CommandArguments
{
    private ArrayList< CommandArgument > arguments;

    public CommandArguments ()
    {
        super ();
    }

    public void addArgument (ArgumentExecute execution, String name, String... alias)
    {
        ArrayList<String> a = new ArrayList<>();
        Collections.addAll ( a, alias );
        this.arguments.add ( new CommandArgument ( execution, name, a ) );
    }

    public void parseArgumentsFromCommand( String c )
    {
        String[] split = c.split ( " " );
        for( int i = 0; i < split.length; i++ )
        {
        }
    }


    private class CommandArgument
    {

        private ArgumentExecute execution;
        private String name;
        private ArrayList<String> alias;
        public CommandArgument ( ArgumentExecute execution, String name, ArrayList<String> alias )
        {
            super ();

            this.execution = execution;
            this.name = "-" + name;
            this.alias = alias;
        }

        public boolean checkHasArgumentAndExecute( String argument, String variable )
        {
            if( name.equals ( argument ) )
            {
                this.execute ( variable );
                return true;
            }
            else
            {
                for( String s : alias )
                {
                    if( s.equals ( argument ) )
                    {
                        this.execute ( variable );
                        return true;
                    }
                }
            }
            return false;
        }

        public void execute( String variable )
        {
            this.execution.argumentExecute ( variable );
        }
    }

    private class InputArguments
    {
        private String name, arguments;
        public InputArguments ()
        {
            super ();
        }
    }

}


