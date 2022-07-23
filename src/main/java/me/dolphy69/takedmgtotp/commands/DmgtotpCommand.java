package me.dolphy69.takedmgtotp.commands;

import me.dolphy69.takedmgtotp.GlobalValues;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class DmgtotpCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length > 0)
        {
            if(args[0].equalsIgnoreCase("enable"))
            {
                GlobalValues.isEnabled = true;
                sender.sendMessage(ChatColor.AQUA + "Damage to tp is " + ChatColor.GREEN + "enabled" + ChatColor.AQUA + "!");
            }
            else if(args[0].equalsIgnoreCase("disable"))
            {
                GlobalValues.isEnabled = false;
                sender.sendMessage(ChatColor.AQUA + "Damage to tp is " + ChatColor.RED + "disabled" + ChatColor.AQUA + "!");
            }
            else if (args[0].equalsIgnoreCase("toggle"))
            {
                GlobalValues.isEnabled = !GlobalValues.isEnabled;
                if(GlobalValues.isEnabled)
                    sender.sendMessage(ChatColor.AQUA + "Damage to tp is " + ChatColor.GREEN + "enabled" + ChatColor.AQUA + "!");
                else
                    sender.sendMessage(ChatColor.AQUA + "Damage to tp is " + ChatColor.RED + "disabled" + ChatColor.AQUA + "!");
            }
            else if (args[0].equalsIgnoreCase("status"))
            {
                if(GlobalValues.isEnabled)
                    sender.sendMessage(ChatColor.AQUA + "Damage to tp is currently " + ChatColor.GREEN + "enabled" + ChatColor.AQUA + "!");
                else
                    sender.sendMessage(ChatColor.AQUA + "Damage to tp is currently " + ChatColor.RED + "disabled" + ChatColor.AQUA + "!");
            }
            else
            {
                sender.sendMessage(ChatColor.RED + "Usage: /dmgtotp <enable|disable|toggle|status>");
            }
        }
        else
        {
            sender.sendMessage(ChatColor.RED + "Usage: /dmgtotp <enable|disable|toggle|status>");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1)
        {
            List<String> completions = new ArrayList<>();
            completions.add("enable");
            completions.add("disable");
            completions.add("status");
            completions.add("toggle");
            return completions;
        }
        return null;
    }
}
