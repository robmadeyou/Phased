package server.commands;

/**
 * Created by r on 07/01/2016.
 */
public interface CommandExecute<C extends Command> {

    void Execute(C command);

}
