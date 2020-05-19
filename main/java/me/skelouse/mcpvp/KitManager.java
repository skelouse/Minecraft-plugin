package me.skelouse.mcpvp;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.*;

public class KitManager {
    public static KitManager instance = new KitManager();

    public static KitManager getInstance(){
        return instance;
    }

    public Dictionary<String, String> kitInfo = new Hashtable<String, String>();
    public static Dictionary<String, Object> kitItems = new Hashtable<String, Object>();

    Game game = Game.getInstance();

    public ItemStack getPotionItemStack(PotionType type, int level, boolean extend, boolean upgraded, String displayName, int amount){
        ItemStack potion = new ItemStack(Material.POTION, amount);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        assert meta != null;
        meta.setBasePotionData(new PotionData(type, extend, upgraded));
        potion.setItemMeta(meta);
        return potion;
    }


    public void buildKits(){
        System.out.println("kitman.buildKits");

        ArrayList <ItemStack> worm = new ArrayList <ItemStack> ();
        kitInfo.put("worm", "Break dirt instantly, also eat dirt for regeneration.");
        worm.add(new ItemStack(Material.LEAD, 1));
        kitItems.put("worm", worm);
        System.out.println("worm send()" + worm);

        // barbarian
        ArrayList <ItemStack> barbarian = new ArrayList <ItemStack> ();
        kitInfo.put("barbarian", "Start with 2 strength potions, and a sword.");
        barbarian.add(getPotionItemStack(PotionType.STRENGTH, 2, false,
                true, "Tha Juice", 2));
        barbarian.add((new ItemStack(Material.STONE_SWORD, 1)));
        kitItems.put("barbarian", barbarian);
        System.out.println("barbarian send()" + barbarian);

        //archer
        ArrayList <ItemStack> archer = new ArrayList <ItemStack> ();
        kitInfo.put("archer", "Start with a bow and arrows");
        archer.add(new ItemStack(Material.BOW, 1));
        archer.add(new ItemStack(Material.ARROW, 15));
        kitItems.put("archer", archer);

        //glider
        ArrayList <ItemStack> glider = new ArrayList <ItemStack> ();
        kitInfo.put("glider", "Start with elytra and some rockets");
        glider.add(new ItemStack(Material.ELYTRA, 1));
        glider.add(new ItemStack(Material.FIREWORK_ROCKET, 5));
        kitItems.put("glider", glider);

        //endermage
        ArrayList <ItemStack> endermage = new ArrayList <ItemStack> ();
        kitInfo.put("endermage", "Start with elytra and some rockets");
        endermage.add(new ItemStack(Material.GLASS_PANE, 1));
        kitItems.put("endermage", endermage);


    }

    public void setup(){
        System.out.println("kitmanager.setup");
        buildKits();
    }

    public void startGame(){
        // game.playersInGame
        System.out.println("kitman.startGame");
        ItemStack compass = new ItemStack(Material.COMPASS, 1);
        for (Player p: game.playersInGame){
            System.out.println("KITITEMS" + kitItems.toString());
            Inventory i = p.getInventory();
            i.clear();
            i.addItem(compass);
            for (Object o: (ArrayList) kitItems.get(getPlayerKit(p.getUniqueId()))){
                i.addItem((ItemStack) o);
            }
        }

    }


    public static void setPlayerKit(UUID u, String kit){
        Game game = Game.getInstance();  //This needs to go
        game.playerKits.put(u, kit);
    }


    public static String getPlayerKit(UUID u){
        Game game = Game.getInstance();  //This needs to go
        return game.playerKits.get(u);

    }
    public static void removePlayerKit(UUID u){
        Game game = Game.getInstance();  //This needs to go
        game.playerKits.remove(u);
    }

}
