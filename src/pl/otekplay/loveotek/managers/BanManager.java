package pl.otekplay.loveotek.managers;

import pl.otekplay.loveotek.basic.Ban;
import pl.otekplay.loveotek.basic.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class BanManager {

    private final List<Ban> bans = new CopyOnWriteArrayList<>();

    public void addBan(String name, String reason, long time, User admin) {
        Ban ban = new Ban(name, reason, time, admin.getName());
        bans.add(ban);
    }

    public Ban getBan(String name) {
        for (Ban ban : bans) {
            if (!ban.getNickname().equalsIgnoreCase(name)) {
                continue;
            }
            if (ban.isBanned()) {
                return ban;
            }
            bans.remove(ban);
        }
        return null;
    }

    public boolean isBanned(String name) {
        return getBan(name) != null;
    }

    public void removeBan(String name) {
        Ban ban = getBan(name);
        bans.remove(ban);
    }

    public List<Ban> getBans() {
        return bans.stream().filter(Ban::isBanned).collect(Collectors.toList());
    }

}
