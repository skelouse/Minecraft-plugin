package me.skelouse.mcpvp.commands;

import me.skelouse.mcpvp.Engine;
import me.skelouse.mcpvp.Messenger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;


public class kit implements CommandExecutor {
    Engine engine = Engine.getInstance();
    Messenger messenger = Messenger.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String kit, String[] args) {
        Player p = (Player) sender;

        if (kit.equalsIgnoreCase ("kit")) {
            if (args.length == 0) {
                p.sendMessage("--------------------------------");
                p.sendMessage("These are all the available kits");
                p.sendMessage("--------------------------------");
                p.sendMessage("Sam, Jacob");
            } else {
                switch (args[0].toLowerCase()) {
                    case "sam":
                        p.sendMessage("Sam is cool");
                        break;

                }
            }
        }
        return true;
    }

}