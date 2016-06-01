package xyz.imdafatboss.uhcgrounds.cmd.api;

import org.bukkit.command.CommandSender;

public abstract class CommandFactory
{
    private final String name;
    private String[] args;
    private String permission;
    private final boolean console;

    public CommandFactory(String name, String[] args, String permission, boolean console)
    {
        this.name = name;
        this.permission = permission;
        this.args = args;
        this.console = console;
    }

    public CommandFactory(String name, String permission, boolean console) {
        this.name = name;
        this.permission = permission;
        this.console = console;

        this.args = new String[] { "" };
    }

    public CommandFactory(String name, boolean console)
    {
        this.name = name;
        this.console = console;

        this.permission = "";
        this.args = new String[] { "" };
    }

    public String getName()
    {
        return this.name;
    }

    public String[] getArgs()
    {
        return this.args;
    }

    public String getPermission()
    {
        return this.permission;
    }

    public boolean allowsConsole()
    {
        return this.console;
    }

    public abstract void execute(CommandSender paramCommandSender, String[] paramArrayOfString);
}