package me.hsgamer.simpleoregen.command;

import me.hsgamer.simpleoregen.SimpleOreGen;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.Arrays;

public class ReloadCommand extends Command {
    public static final Permission PERMISSION = new Permission("simpleoregen.reload", PermissionDefault.OP);

    static {
        Bukkit.getPluginManager().addPermission(PERMISSION);
    }

    private final SimpleOreGen instance;

    public ReloadCommand(SimpleOreGen instance) {
        super("reloadgenerator", "Reload the generators", "/reloadgenerator", Arrays.asList("reloadgen", "rlgen"));
        this.instance = instance;
        setPermission(PERMISSION.getName());
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) {
            return false;
        }
        instance.getMainConfig().reload();
        sender.sendMessage(ChatColor.GREEN + "Successfully reloaded!");
        return true;
    }
}
