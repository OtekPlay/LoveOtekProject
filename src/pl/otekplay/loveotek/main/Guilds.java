package pl.otekplay.loveotek.main;

import org.bukkit.Location;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.managers.GuildManager;

import java.util.Collection;
import java.util.UUID;

public class Guilds {

    public static void create(String tag, String name, UUID leader, Location loc) {
        manager().createGuild(tag, name, leader, loc);
    }
    public static void destroy(String tag){
        manager().deleteGuild(tag);
    }

    public static boolean isTag(String name) {
        return manager().isGuildByTag(name.toUpperCase());
    }

    public static boolean isName(String name) {
        return manager().isGuildByName(name);
    }

    public static Guild tag(String name) {
        return manager().getGuildByTag(name.toUpperCase());
    }

    public static Guild name(String name) {
        return manager().getGuildByName(name);
    }

    public static Collection<Guild> all() {
        return manager().getGuilds();
    }

    private static GuildManager manager() {
        return Core.getInstance().getGuildManager();
    }
}
