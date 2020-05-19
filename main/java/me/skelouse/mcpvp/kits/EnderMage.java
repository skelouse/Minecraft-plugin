package me.skelouse.mcpvp.kits;

import me.skelouse.mcpvp.Engine;
import me.skelouse.mcpvp.Game;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class EnderMage implements Listener{
    Game game = Game.getInstance();
    Engine engine = Engine.getInstance();
    int d = 100;
    @EventHandler
    public void portalCreate(PortalCreateEvent event){
        Location L = event.getEntity().getLocation();
        for (Player p: Bukkit.getOnlinePlayers()){

            if (p.getLocation().distanceSquared(L) < 25){
                p.sendMessage("Teleporting");
                p.teleport(L);
                p.sendMessage("You are invincible for 5 seconds");
                game.playersInvincible.add(p.getUniqueId());
                engine.timedInvincible(5);

            }
        }
    }

}
//public class Worm implements Listener {
//
//    @EventHandler
//    public void blockDamage(BlockDamageEvent e){
//        Player p = e.getPlayer();
//        if (KitManager.getPlayerKit(p.getUniqueId()).equals("worm")) {
//            Block b = e.getBlock();
//            if (b.getType().toString().equals("DIRT")) {
//                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 0));
//                if (p.getFoodLevel() < 20) {
//                    b.setType(Material.AIR);
//                    p.setFoodLevel(p.getFoodLevel() + 1);
//                } else {
//                    b.breakNaturally();
//                }
//            }
//        }
//    }
//}