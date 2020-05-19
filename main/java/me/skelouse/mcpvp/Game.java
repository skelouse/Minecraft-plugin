package me.skelouse.mcpvp;

import org.bukkit.entity.Player;

import java.util.*;

public class Game {

    Engine engine;
    Messenger messenger;
    KitManager kitman;
    public static Game instance = new Game();
    public boolean game_started;
    public boolean countdown_started;
    public boolean invincibility = true;
    public int start_time = 5;  // seconds until game starts
    public int start_size = 5;
    public int wait_time = 10;
    public String ip = "mc-hg.com";

    public ArrayList <Player> playersInGame = new ArrayList <Player> ();
    // dictionary uuid to kit name
    public Dictionary<UUID, String> playerKits = new Hashtable<UUID, String>();

    public ArrayList <UUID> playersInvincible = new ArrayList<UUID>();




    public static Game getInstance(){
        return instance;
    }

    //public void restart() {
    //    instance = new Game();
    //    engine.waitForPlayers();
    //}


    public void startGame() {
        messenger.msgAll("Select your kit! -- /kit");
    }

    public void setup() {
        engine = Engine.getInstance();
        messenger = Messenger.getInstance();
        kitman = KitManager.getInstance();
    }



}
