package me.skelouse.mcpvp;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Engine{

    public static Engine instance;
    Game game;
    Messenger messenger;
    KitManager kitman;
    Plugin plugin;

    public Engine (Plugin plugin) {
        instance = this;
        this.plugin = plugin;
    }

    public void setup(){
        game = Game.getInstance();
        messenger = Messenger.getInstance();
        kitman = KitManager.getInstance();
    }

    public static Engine getInstance(){
        return instance;
    }


    int online_count;
    int diff;
    public void waitForPlayers(){
        online_count = plugin.getServer().getOnlinePlayers().size();
        diff = (online_count - game.start_size) * -1;
        messenger.msgAll("Waiting for " + (diff) + " players to start!");
        if (diff >= 0 && game.game_started == false && game.countdown_started == false){
            new BukkitRunnable(){
                @Override
                public void run() {

                    waitForPlayers();
                }
            }.runTaskLater(this.plugin, game.wait_time*20);
        } else if (game.game_started == false && game.countdown_started == false){
            startGame();
        }

    }

    public void startGame(){
        System.out.println("Engine.startGame");
        game.countdown_started = true;

        //messenger.msgAll("Game starting in " + game.start_time + " seconds...");
        messenger.msgAll("Game starting within 60 seconds");
        new BukkitRunnable() {

            @Override
            public void run() {
                game.game_started = true;
                for(Player p: plugin.getServer().getOnlinePlayers()){
                    p.teleport(p.getWorld().getSpawnLocation());
                    game.playersInGame.add(p);  //add all players to an array, game.playersInGame
                }

                messenger.msgAll("Game is STARTED");
                startMatch();
            }
        }.runTaskLater(this.plugin, game.start_time * 20);
    }

    public void startMatch(){
        System.out.println("Engine.startMatch");
        messenger.msgAll("Invincibility wears off in 10 seconds, RUN!");
        for(Player p: game.playersInGame) {
            p.setGameMode(GameMode.SURVIVAL);
        }

        new BukkitRunnable() {
            @Override
            public void run(){
                System.out.println("Game Starting with - " + game.playersInGame.toString());
                for(Player p: game.playersInGame){
                    p.sendMessage("Invincibility worn off.");


                    // Invincibility wears off
                    game.invincibility = false;

                    // Give kit items here


                }

            }
        }.runTaskLater(this.plugin, 200);

    }

    public void winGame(Player p){
        p.sendMessage("Restarting server...");
        new BukkitRunnable() {
            @Override
            public void run() {
                p.kickPlayer("CONGRATULATIONS RESTARTING NOW");
                restart();
            }
        }.runTaskLater(this.plugin, 300);
    }

    public void restart(){
        plugin.getServer().reload();
    }
}
