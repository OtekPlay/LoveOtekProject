package pl.otekplay.loveotek.main;

import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.managers.UserManager;

import java.util.Collection;
import java.util.UUID;

public class Users {

    public static void register(UUID uuid, String name) {
        manager().registerUser(uuid, name);
    }

    public static User get(UUID uuid) {
        return manager().getUser(uuid);
    }

    public static User get(String name) {
        return manager().getUser(name);
    }

    public static boolean is(UUID uuid) {
        return manager().isUser(uuid);
    }

    public static boolean is(String name) {
        return manager().isUser(name);
    }

    public static Collection<User> all() {
        return manager().getUsers();
    }

    private static UserManager manager() {
        return Core.getInstance().getUserManager();
    }
}
