package me.skelouse.mcpvp;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.UUID;

public class KitManager {
    public static KitManager instance = new KitManager();

    public static KitManager getInstance(){
        return instance;
    }

    public Dictionary<String, String> kitInfo = new Hashtable<String, String>();

    public void buildKitInfo(){
        System.out.println("kitmanager.buildkitinfo");

        kitInfo.put("apple", "desc");
        kitInfo.put("sam", "kit for great things");
        kitInfo.put("jake", "kit for not so great things");
        kitInfo.put("worm", "Break dirt instantly, also eat dirt.");
    }

    public void setup(){
        System.out.println("kitmanager.setup");
        buildKitInfo();
    }


    public static void setPlayerKit(UUID u, String kit){
        Game game = Game.getInstance();  //This needs to go
        game.playerKits.put(u, kit);
    }


    public static String getPlayerKit(UUID u){
        Game game = Game.getInstance();  //This needs to go
        return game.playerKits.get(u).toString();

    }
    public static void removePlayerKit(UUID u){
        Game game = Game.getInstance();  //This needs to go
        game.playerKits.remove(u);
    }

}
