package me.skelouse.mcpvp.events;

import me.skelouse.mcpvp.Game;
import me.skelouse.mcpvp.KitManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;

public class onPlayerJoin implements Listener {
    Game game = Game.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        boolean eligible = false;

        if (game.game_started){

            for(Player x : game.playersInGame){
                if (p.getUniqueId().equals(x.getUniqueId())){
                    eligible = true;
                }
            }
            if (eligible){
                p.sendMessage("Welcome back!");

                // can't move for 5 seconds

            }
            else{
                p.kickPlayer("Game already started");
            }

        } else {
            if (game.countdown_started){
                p.sendMessage("Game starting in " + game.start_time + "seconds");  // need x
            } else {
                game.playersInvincible.add(p.getUniqueId());
                p.sendMessage("Waiting for players...");
            }
            // add to HashMap if not in
            boolean inKits;
            try{
                KitManager.getPlayerKit(p.getUniqueId());
            } catch (NullPointerException exc){
                game.playerKits.put(p.getUniqueId(), "");
            }

            // turn on invincibility here
            Inventory inventory = p.getInventory();
            inventory.clear();
            p.setGameMode(GameMode.ADVENTURE);
            p.sendMessage("IP is " + game.ip);
        }
    }
}
