package me.skelouse.mcpvp.commands;

import me.skelouse.mcpvp.Engine;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;


public class hg implements CommandExecutor {
    Engine engine = Engine.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String hg, String[] args) {
         Player p = (Player) sender;

        if (hg.equalsIgnoreCase ("hg") && p.isOp()) {
            if (args.length == 0) {
                p.sendMessage("-----------------------------------");
                p.sendMessage("All available commands you can type");
                p.sendMessage("-----------------------------------");
                p.sendMessage("/hg start - This force starts the game");
             } else {
                 switch (args[0].toLowerCase()) {
                     case "start":
                         p.sendMessage("Game is force starting");
                         engine.startGame();
                         break;
                 }
             }
         }
        return true;
    }

}