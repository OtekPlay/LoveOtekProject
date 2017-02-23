package pl.otekplay.loveotek.managers;

import com.mongodb.client.model.Projections;
import org.bson.Document;
import pl.otekplay.loveotek.basic.Ban;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.database.Database;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class BanManager {
    private final List<Ban> bans = new CopyOnWriteArrayList<>();

    public void init() {
        Database.find(Ban.class).projection(Projections.excludeId()).forEach(document -> {
            bans.add(Database.gson().fromJson(Database.gson().toJson(document), Ban.class));
        }, (aVoid, throwable) -> {
            String simpleName = this.getClass().getSimpleName();
            System.out.println("[" + simpleName + "] Loaded " + bans.size() + " " + simpleName.replace("Manager", "s").toLowerCase() + "!");
        });
    }

    public void addBan(String name, String reason, long time, User admin) {
        Ban ban = new Ban(name, reason, time, admin.getName());
        bans.add(ban);
        Database.insert(ban);
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
        Database.remove(ban, new Document("time", ban.getTime()));
    }

    public List<Ban> getBans() {
        return bans.stream().filter(Ban::isBanned).collect(Collectors.toList());
    }

}
