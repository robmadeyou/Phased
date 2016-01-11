package server.commands;

import server.model.players.Client;

/**
 * Created by r on 09/01/2016.
 */
public class PlayerCommand extends Command {

    public Client player;
    public PlayerCommand(String activation, CommandExecute<PlayerCommand> onActivate) {
        super(activation, onActivate);
    }

    public void setPlayer( Client c )
    {
        this.player = c;
    }

    public final Client getPlayer()
    {
        return this.player;
    }

}
