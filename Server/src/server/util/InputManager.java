package server.util;

import server.Server;
import server.commands.ArgumentExecute;
import server.commands.Command;
import server.commands.CommandExecute;
import server.commands.PlayerCommand;
import server.helpers.StringHelper;
import server.model.items.Item;
import server.model.items.ItemList;
import server.model.players.Client;
import server.model.players.PacketHandler;
import server.model.players.Player;
import server.model.players.PlayerHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class InputManager implements Runnable {

    private ServerSocket server;
    private Socket socket;
    private ArrayList<Command> commands;

    public InputManager() {
        try {
            server = new ServerSocket(4412, 0);
        } catch (UnknownHostException e) {
            System.out.println("Could not create a new server :(");
        } catch (IOException e) {
            System.out.println("IO Exception when creating the server");
        }

        commands = new ArrayList<>();

        commands.add(new Command("uptime", new CommandExecute() {
            @Override
            public void Execute(Command command) {
                command.output(Server.getUptime());
            }
        }));

        commands.add(new Command("players", new CommandExecute() {
            @Override
            public void Execute(Command command) {
                command.output(StringHelper.combine(PlayerHandler.playersCurrentlyOn, ","));
            }
        }));

        commands.add(new PlayerCommand("player", new CommandExecute<PlayerCommand>() {
            @Override
            public void Execute(PlayerCommand command) {
                command.getArgs().addArgument(new ArgumentExecute() {
                    @Override
                    public void argumentExecute(String variable) {
                        command.setPlayer((Client) PlayerHandler.getPlayerFromName(variable));
                    }
                }, "p", "-player");

                command.getArgs().addArgument(new ArgumentExecute() {
                    @Override
                    public void argumentExecute(String variable) {
                        command.getPlayer().logout();
                    }
                }, "k", "-kick");

                command.getArgs().addArgument(new ArgumentExecute() {
                    @Override
                    public void argumentExecute(String variable) {
                        ArrayList<String> x = StringHelper.removeEmptyArrayEntries(variable.split(" "));
                        command.getPlayer().getItems().addItem(Integer.parseInt(x.get(0)), Integer.parseInt(x.get(1)));
                    }
                }, "g", "-give");

                command.getArgs().addArgument(new ArgumentExecute() {
                    @Override
                    public void argumentExecute(String variable) {
                        command.getPlayer().getItems().deleteAllItems();
                    }
                }, "-delete-invent");

                command.getArgs().addArgument(new ArgumentExecute() {
                    @Override
                    public void argumentExecute(String variable) {
                        command.getPlayer().getPA().bankAll();
                    }
                }, "-bank-invent");

                command.getArgs().addArgument(new ArgumentExecute() {
                    @Override
                    public void argumentExecute(String variable) {
                        //TODO figure out TP locations
                    }
                }, "t", "-teleport");
            }
        }));

        commands.add(new PlayerCommand("item", new CommandExecute() {
            @Override
            public void Execute(Command command) {
                command.getArgs().addArgument(new ArgumentExecute() {
                    @Override
                    public void argumentExecute(String variable) {
                        ArrayList<String> x = StringHelper.removeEmptyArrayEntries(variable.split(" "));
                        ItemList i = Server.itemHandler.ItemList[Integer.parseInt(x.get(0))];
                        i.Bonuses[Integer.parseInt(x.get(1))] = Integer.parseInt(x.get(2));
                    }
                }, "c", "-change");

                command.getArgs().addArgument(new ArgumentExecute() {
                    @Override
                    public void argumentExecute(String variable) {
                        for(String p : PlayerHandler.playersCurrentlyOn )
                        {
                            Server.itemHandler.reloadItems( (Client)PlayerHandler.getPlayerFromName( p ) );
                        }
                    }
                }, "-reload" );
            }
        }));
    }

    public String handleInput(String line) {
        for (Command c : this.commands) {
            if (line.startsWith(c.getActivation())) {
                return c.runCommand(line.replace(c.getActivation() + " ", ""));
            }
        }
        return "";
    }

    public void run() {
        BufferedReader is;
        PrintWriter os;

        while (true) {
            try {
                socket = server.accept();
                is = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                os = new PrintWriter(socket.getOutputStream(), true);

                String responseLine;
                while ((responseLine = is.readLine()) != null) {
                    System.out.println("Server: " + responseLine);
                    os.println(handleInput(responseLine));
                    break;
                }

                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
