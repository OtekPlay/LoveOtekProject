package pl.otekplay.loveotek.main;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.managers.TeleporterManager;

public class Teleporter {


    private static TeleporterManager manager(){
        return Core.getInstance().getTeleporterManager();
    }
    public static void random(Player player){
        manager().teleportPlayerRandomly(player);
    }

    public static boolean can(User user, Location loc){
        return manager().canTeleport(user,loc);
    }
}
