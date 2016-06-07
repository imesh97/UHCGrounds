package xyz.imdafatboss.uhcgrounds.cmd.api;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.imdafatboss.uhcgrounds.cmd.*;

import java.util.*;

public class CommandManager
{

    private static Set<CommandFactory> commands;

    public CommandManager(JavaPlugin plugin)
    {
        if (commands == null) {
            commands = new HashSet<CommandFactory>();
        }
        commands.add(new SaveKitCmd(plugin));
        commands.add(new StatsGuiCmd(plugin));
        commands.add(new SetLobbyCmd(plugin));
        commands.add(new SetSpawnCmd(plugin));
        commands.add(new GameJoinCmd(plugin));
    }

    public static CommandFactory getCommand(String name)
    {
        for (CommandFactory cmd : getCommands()) {
            if (cmd.getName().equalsIgnoreCase(name)) {
                return cmd;
            }
        }
        return null;
    }

    public static Set<CommandFactory> getCommands()
    {
        return commands;
    }
}
