package server.commands;

import server.model.players.Client;
import server.model.players.PlayerHandler;

import java.util.ArrayList;

/**
 * I'm going to try to replicate the bash commandline interface
 * <p>
 * So commands will always start with the activation string, followed by parameters.
 * This should be more or less a structured way to interpret commands and execute them relentless of input source - meaning
 * commands could be executed from the server as a player, or using Sockets, or through standard input.
 * <p>
 * Created by r on 07/01/16.
 */
public class Command
{

    private String activation;
    private CommandArguments commandArguments = new CommandArguments ();
    private CommandExecute onActivate;
    private StringBuilder output;

    public Command (String activation, CommandExecute onActivate)
    {
        super ();
        this.activation = activation;
        this.onActivate = onActivate;
    }

    public CommandArguments getArgs ()
    {
        return this.commandArguments;
    }

    public String runCommand (String command)
    {
        this.output = new StringBuilder ();
        this.commandArguments = new CommandArguments ();
        onActivate.Execute ( this );
        commandArguments.parseArgumentsFromCommand ( command );
        return this.output.toString ();
    }

    public final Client getClient (String username)
    {
        return ( Client ) PlayerHandler.getPlayerFromName ( username );
    }

    public void output (String text)
    {
        this.output.append ( text );
    }

    public String getActivation ()
    {
        return this.activation;
    }
}
