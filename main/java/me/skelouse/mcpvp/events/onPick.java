package me.skelouse.mcpvp.events;

import me.skelouse.mcpvp.Game;
import me.skelouse.mcpvp.KitManager;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class onPick implements Listener {
    @EventHandler
    public void pickUp(EntityPickupItemEvent e){
        if (e.getItem().getItemStack().getType() == Material.NETHER_PORTAL){
            if (KitManager.getPlayerKit(e.getEntity().getUniqueId()) != "endermage"){
                e.setCancelled(true);
            }
        }
    }
}
