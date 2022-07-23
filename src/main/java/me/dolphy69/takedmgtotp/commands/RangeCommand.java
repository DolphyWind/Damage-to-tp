package me.dolphy69.takedmgtotp.commands;

import me.dolphy69.takedmgtotp.GlobalValues;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class RangeCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0)
        {
            sender.sendMessage(ChatColor.RED + "Usage: /tprange <set|value> [newValue]");
        }
        else
        {
            if(args[0].equalsIgnoreCase("value"))
            {
                sender.sendMessage(
                ChatColor.AQUA + "Teleport range is currently " + ChatColor.LIGHT_PURPLE + GlobalValues.range
                + ChatColor.AQUA + "!");
            }
            else if(args[0].equalsIgnoreCase("set"))
            {
                if(args.length == 1) sender.sendMessage(ChatColor.RED + "Usage: /tprange <set|value> [newValue]");
                else
                {
                    try
                    {
                        GlobalValues.range = Integer.parseInt(args[1]);
                        sender.sendMessage(ChatColor.GREEN + "Teleport range is set to "
                                + ChatColor.LIGHT_PURPLE + GlobalValues.range +
                                ChatColor.GREEN + "!");
                    }
                    catch (NumberFormatException exception)
                    {
                        sender.sendMessage(ChatColor.RED + "New value must be a number!");
                    }
                }
            }
            else sender.sendMessage(ChatColor.RED + "Usage: /tprange <set|value> [newValue]");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 1)
        {
            List<String> completions = new ArrayList<>();
            completions.add("set");
            completions.add("value");
            return completions;
        }
        return null;
    }
}
