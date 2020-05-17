package me.skelouse.mcpvp.events;

import me.skelouse.mcpvp.Game;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Invincibility implements Listener {
    Game game = Game.getInstance();
    @EventHandler
    public void Invincibility(EntityDamageEvent e){
        if (game.invincibility){
            e.setDamage(0);
        }

    }
}
