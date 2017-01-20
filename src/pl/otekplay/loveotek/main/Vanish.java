package pl.otekplay.loveotek.main;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.managers.VanishManager;

import java.util.UUID;

public class Vanish {

    private static VanishManager manager(){
        return Core.getInstance().getVanishManager();
    }
    public static void hide(Player p){
        manager().addVanish(p);
    }
    public static void show(Player p){
        manager().removeVanish(p);
    }
    public static boolean has(Player p){
       return manager().hasVanish(p);
    }
    public static boolean has(UUID uuid){
        return manager().hasVanish(uuid);
    }
    public static void join(Player p){
        manager().hideVanished(p);
    }
}
