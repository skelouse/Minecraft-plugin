package me.skelouse.mcpvp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Messenger {
    public static Messenger instance = new Messenger();

    public static Messenger getInstance(){
        return instance;
    }

    public void msgAll(String msg){
        Bukkit.broadcastMessage(msg);
    }

    public void msg(String msg, boolean isPlayer, Player player){
        if (isPlayer){
            player.sendMessage(msg);
        }else{
            System.out.println(msg);
        }
    }
}
