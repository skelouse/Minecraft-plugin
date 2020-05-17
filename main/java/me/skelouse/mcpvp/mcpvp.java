package me.skelouse.mcpvp;
import me.skelouse.mcpvp.commands.hg;
import me.skelouse.mcpvp.commands.kit;
import me.skelouse.mcpvp.events.Invincibility;
import me.skelouse.mcpvp.events.onPlayerDeath;
import me.skelouse.mcpvp.events.onPlayerJoin;
import me.skelouse.mcpvp.kits.Worm;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;



public class mcpvp extends JavaPlugin implements Listener{

    Game game = Game.getInstance();
    Messenger messenger = Messenger.getInstance();
    KitManager kitman = KitManager.getInstance();
    Engine engine = new Engine(this);



    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("mcpvp.onEnable()");

        game.setup();
        engine.setup();

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new onPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new onPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new Invincibility(), this);
        getCommand("hg").setExecutor(new hg());
        getCommand("kit").setExecutor(new kit());

        engine.waitForPlayers();

        //kits
        getServer().getPluginManager().registerEvents(new Worm(), this);

    }


}

