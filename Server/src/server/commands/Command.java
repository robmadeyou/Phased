package server.commands;

import java.util.ArrayList;

/**
 *
 * I'm going to try to replicate the bash commandline interface
 *
 * So commands will always start with the activation string, followed by parameters.
 * This should be more or less a structured way to interpret commands and execute them relentless of input source - meaning
 * commands could be executed from the server as a player, or using Sockets, or through standard input.
 *
 * Created by r on 07/01/16.
 */
public class Command
{

    private String activation;
    private CommandArguments commandArguments;
    private CommandExecute onActivate;

    public Command ( String activation, CommandExecute onActivate )
    {
        super ();
        this.activation = activation;
        this.onActivate = onActivate;


        this.commandArguments = new CommandArguments ();
    }

    public CommandArguments getArgs()
    {
        return this.commandArguments;
    }

    public void runCommand( String command )
    {
        commandArguments.parseArgumentsFromCommand ( command );
    }

    public String getActivation()
    {
        return this.activation;
    }
}
