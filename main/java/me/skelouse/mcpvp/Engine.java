package me.skelouse.mcpvp;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
        if (diff >= 0 && !game.game_started && !game.countdown_started){
            new BukkitRunnable(){
                @Override
                public void run() {

                    waitForPlayers();
                }
            }.runTaskLater(this.plugin, game.wait_time*20);
        } else if (!game.game_started && !game.countdown_started){
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
        kitman.startGame();  // give players kits and items
        new BukkitRunnable() {
            @Override
            public void run(){
                System.out.println("Game Starting with - " + game.playersInGame.toString());
                for(Player p: game.playersInGame){
                    p.sendMessage("Invincibility worn off.");

                    // Invincibility wears off
                    game.playersInvincible.clear();
                }

            }
        }.runTaskLater(this.plugin, 200);

    }

    public void timedInvincible(int time){
        new BukkitRunnable() {
            @Override
            public void run() {
                game.playersInvincible.clear();
            }
        }.runTaskLater(this.plugin, time*20);
    }

    public void winGame(Player p) {
        new BukkitRunnable() {

            @Override
            public void run() {
                p.kickPlayer("CONGRATULATIONS RESTARTING NOW");
                plugin.getServer().spigot().restart();
            }
        }.runTaskLater(this.plugin, 200);
    }


    public void restart(){
        for (Player p: plugin.getServer().getOnlinePlayers()){
            p.kickPlayer("Server restarting...");
        }
        plugin.getServer().spigot().restart();






    }
}
