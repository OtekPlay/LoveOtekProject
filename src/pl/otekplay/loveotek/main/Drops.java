package pl.otekplay.loveotek.main;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.basic.Drop;
import pl.otekplay.loveotek.basic.DropUser;
import pl.otekplay.loveotek.managers.DropManager;

import java.util.List;
import java.util.UUID;

public class Drops {

    protected static DropManager manager() {
        return Core.getInstance().getDropManager();
    }


    public static void menu(Player p) {
        manager().getMenu(p.getUniqueId()).open(p);
    }

    public static List<Drop> drops() {
        return manager().getDrops();
    }

    public static void register(UUID uuid) {
        manager().registerDropUser(uuid);
    }

    public static void unregister(UUID uuid) {
        manager().unregisterDropUser(uuid);
    }

    public static DropUser get(UUID uuid) {
        return manager().getUserDrop(uuid);
    }

}
