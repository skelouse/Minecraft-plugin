package me.skelouse.mcpvp.kits;


import me.skelouse.mcpvp.Game;

import me.skelouse.mcpvp.KitManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;

public class Worm implements Listener{
    Game game = Game.getInstance();
    KitManager kitman = KitManager.getInstance();
    public Player p;
    
    @EventHandler
    public void breakDirt(BlockDamageEvent e){
        Player p = e.getPlayer();
        if (kitman.getPlayerKit(p.getUniqueId()) == "worm") {
            Block b = e.getBlock();
            if (b.getType().toString() == "DIRT") {
                if (p.getFoodLevel() < 20) {
                    b.setType(Material.AIR);
                    p.setFoodLevel(p.getFoodLevel() + 1);
                } else {
                    b.breakNaturally();
                }
            }
        }


    }
}
