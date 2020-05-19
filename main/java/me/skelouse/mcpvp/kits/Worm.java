package me.skelouse.mcpvp.kits;

import me.skelouse.mcpvp.KitManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Worm implements Listener{
    
    @EventHandler
    public void blockDamage(BlockDamageEvent e){
        Player p = e.getPlayer();
        if (KitManager.getPlayerKit(p.getUniqueId()).equals("worm")) {
            Block b = e.getBlock();
            if (b.getType().toString().equals("DIRT")) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 0));
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