package me.skelouse.mcpvp.events;

import me.skelouse.mcpvp.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.UUID;

public class Invincibility implements Listener {
    Game game = Game.getInstance();
    @EventHandler
    public void Invincibility(EntityDamageEvent e){
        for (UUID u: game.playersInvincible){
            if (e.getEntity().getUniqueId() == u){
                e.setDamage(0);
                break;
            }
        }
    }
}
