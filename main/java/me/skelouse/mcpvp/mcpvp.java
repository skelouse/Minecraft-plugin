package me.skelouse.mcpvp;
import me.skelouse.mcpvp.commands.hg;
import me.skelouse.mcpvp.commands.kit;
import me.skelouse.mcpvp.events.*;
import me.skelouse.mcpvp.kits.EnderMage;
import me.skelouse.mcpvp.kits.Worm;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;


public class mcpvp extends JavaPlugin implements Listener{

    Game game = Game.getInstance();
    Messenger messenger = Messenger.getInstance();
    KitManager kitman = KitManager.getInstance();
    Engine engine = new Engine(this);


    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("mcpvp.onEnable()");

        getServer().getWorld("world").setSpawnLocation(0, 150, 0);

        game.setup();
        engine.setup();
        kitman.setup();

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new onPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new onPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new Invincibility(), this);
        getServer().getPluginManager().registerEvents(new onPlace(), this);
        getServer().getPluginManager().registerEvents(new onPick(), this);
        getServer().getPluginManager().registerEvents(new EnderMage(), this);

        getCommand("hg").setExecutor(new hg());
        getCommand("kit").setExecutor(new kit());

        engine.waitForPlayers();

        //kits
        getServer().getPluginManager().registerEvents(new Worm(), this);

    }



}

