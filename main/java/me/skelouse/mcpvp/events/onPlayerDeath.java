package me.skelouse.mcpvp.events;

import me.skelouse.mcpvp.Engine;
import me.skelouse.mcpvp.Game;

import me.skelouse.mcpvp.Messenger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class onPlayerDeath implements Listener {
    Engine engine = Engine.getInstance();
    Game game = Game.getInstance();
    Messenger messenger = Messenger.getInstance();
    @EventHandler
    public void OnPlayerDeath(PlayerDeathEvent e){
        System.out.println("onPlayerDeath");
        game.playersInGame.remove(e.getEntity());
        if (game.game_started){
            if (game.playersInGame.size() <= 1){
                // WIN
                e.getEntity().kickPlayer("Try again next time!");
                messenger.msgAll("WINNER!");
                game.invincibility = true;
                engine.winGame(game.playersInGame.iterator().next());

            } else{
                e.getEntity().kickPlayer("Try again next time!");
            }

        }


    }
}

