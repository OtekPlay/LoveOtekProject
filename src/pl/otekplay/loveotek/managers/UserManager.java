package pl.otekplay.loveotek.managers;

import pl.otekplay.loveotek.basic.User;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {
    private final Map<UUID, User> users = new ConcurrentHashMap<>();

    public void registerUser(UUID uuid, String name) {
        User user = new User(uuid, name);
        users.put(user.getUniqueID(), user);
    }

    public User getUser(UUID uuid) {
        return users.get(uuid);
    }

    public User getUser(String name) {
        return getUsers().parallelStream().filter(user -> user.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public boolean isUser(UUID uuid) {
        return users.containsKey(uuid);
    }

    public boolean isUser(String name) {
        return getUser(name) != null;
    }

    public Collection<User> getUsers() {
        return users.values();
    }
}
