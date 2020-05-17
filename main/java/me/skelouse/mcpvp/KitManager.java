package me.skelouse.mcpvp;

import me.skelouse.mcpvp.Game;

import java.util.UUID;

public class KitManager {
    public static KitManager instance = new KitManager();

    public static KitManager getInstance(){
        return instance;
    }


    public static void setPlayerKit(UUID u, String kit){
        Game game = Game.getInstance();
        game.playerKits.put(u, kit);
    }

    public static String getPlayerKit(UUID u){
        Game game = Game.getInstance();
        return game.playerKits.get(u).toString();

    }
    public static void removePlayerKit(UUID u){
        Game game = Game.getInstance();
        game.playerKits.remove(u);
    }

}
