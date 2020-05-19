package me.skelouse.mcpvp.events;

import me.skelouse.mcpvp.Engine;
import me.skelouse.mcpvp.Game;
import me.skelouse.mcpvp.KitManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class onPlace implements Listener {
    Game game = Game.getInstance();
    Engine engine = Engine.getInstance();
    int d = 100;
    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e){
        Player p1 = e.getPlayer();
        Block b = e.getBlock();
        if (b.getType() == Material.GLASS_PANE && KitManager.getPlayerKit(p1.getUniqueId()).equals("endermage")) {
            Location L = b.getLocation().clone();
            L.setY(64);

            for (Player p2 : Bukkit.getOnlinePlayers()) {
                Location L2 = p2.getLocation().clone();
                L2.setY(64);
                if (L2.distanceSquared(L) < 100) {
                    p2.sendMessage("Teleporting");
                    p2.teleport(b.getLocation());
                    p2.sendMessage("You are invincible for 5 seconds");
                    game.playersInvincible.add(p2.getUniqueId());
                    engine.timedInvincible(5);
                    e.setCancelled(true);

                }
            }
        }
    }
}
