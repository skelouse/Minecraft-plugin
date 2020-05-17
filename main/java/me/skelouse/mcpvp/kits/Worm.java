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
    public Player p;
    
    @EventHandler
    public void breakDirt(BlockDamageEvent e){
        Player p = e.getPlayer();
        p.sendMessage(game.playerKits.toString());
        if (KitManager.getPlayerKit(p.getUniqueId()).equals("worm")) {
            p.sendMessage("getBlock");
            Block b = e.getBlock();
            if (b.getType().toString().equals("DIRT")) {
                p.sendMessage("getFoodLevel");
                if (p.getFoodLevel() < 20) {
                    p.sendMessage("setType");
                    b.setType(Material.AIR);
                    p.setFoodLevel(p.getFoodLevel() + 1);
                } else {
                    p.sendMessage("breakNaturally");
                    b.breakNaturally();
                }
            }
        }


    }
}
