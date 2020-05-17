package me.skelouse.mcpvp.commands;

import me.skelouse.mcpvp.Engine;
import me.skelouse.mcpvp.Messenger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;


public class hg implements CommandExecutor {
    Engine engine = Engine.getInstance();
    Messenger messenger = Messenger.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String hg, String[] args) {
         Player p = (Player) sender;

        if (hg.equalsIgnoreCase ("hg") && p.isOp()) {
            if (args.length == 0) {
                p.sendMessage("-----------------------------------");
                p.sendMessage("All available commands you can type");
                p.sendMessage("-----------------------------------");
                p.sendMessage("/hg start - This force starts the game");
                p.sendMessage("/hg restart - This stops the server and restarts it");
                p.sendMessage("/hg online - Lists all online players");
             } else {
                 switch (args[0].toLowerCase()) {
                     case "start":
                         p.sendMessage("Game is force starting");
                         engine.startGame();
                         break;
                     case "restart":
                         p.sendMessage("Game is force restarting");
                         messenger.msgAll("Admin is restarting the server");
                         engine.restart();
                         break;
                     case "online":
                         p.sendMessage("There are " + "" + " players online.");
                         break;
                 }
             }
         }
        return true;
    }

}