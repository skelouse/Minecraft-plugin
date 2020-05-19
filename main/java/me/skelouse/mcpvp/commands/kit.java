package me.skelouse.mcpvp.commands;

import me.skelouse.mcpvp.Engine;
import me.skelouse.mcpvp.Game;
import me.skelouse.mcpvp.KitManager;
import me.skelouse.mcpvp.Messenger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Enumeration;


public class kit implements CommandExecutor {
    Engine engine = Engine.getInstance();
    Messenger messenger = Messenger.getInstance();
    KitManager kitmanager = KitManager.getInstance();
    Game game = Game.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String kit, String[] args) {
        if (!game.game_started) {
            Player p = (Player) sender;
            if (kit.equalsIgnoreCase("kit")) {
                if (args.length == 0) {
                    p.sendMessage("--------------------------------");
                    p.sendMessage("These are all the available kits");
                    p.sendMessage("--------------------------------");
                    Enumeration<String> e = kitmanager.kitInfo.keys();
                    StringBuilder msg = new StringBuilder();
                    while (e.hasMoreElements()) {
                        String k = e.nextElement();
                        msg.append(k).append(", ");
                    }
                    p.sendMessage(msg.toString());
                } else {

                    try {
                        String s = kitmanager.kitInfo.get(args[0]);
                        if (args.length != 2 && !s.isEmpty()) {
                            KitManager.setPlayerKit(p.getUniqueId(), args[0].toLowerCase());
                            p.sendMessage("You selected kit " + args[0]);
                        } else if (!s.isEmpty()) {
                            p.sendMessage(s);
                        } else {
                            p.sendMessage("Not a valid kit.");
                        }
                    } catch (NullPointerException e) {
                        p.sendMessage("Not a valid kit.");
                        // invalid
                    }
                }

            }

        }return true;
    }
}