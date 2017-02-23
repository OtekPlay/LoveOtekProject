package pl.otekplay.loveotek.main;

import pl.otekplay.loveotek.basic.Ban;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.managers.BanManager;

import java.util.Collection;

public class Bans {

    private static BanManager manager() {
        return Core.getInstance().getBanManager();
    }

    public static void ban(String name, String reason, long time, User user) {
        manager().addBan(name, reason, time, user);
    }

    public static void unban(String name) {
        manager().removeBan(name);
    }

    public static boolean is(String name) {
        return manager().isBanned(name);
    }

    public static Ban get(String name) {
        return manager().getBan(name);
    }

    public static Collection<Ban> bans(){
        return manager().getBans();
    }
}
