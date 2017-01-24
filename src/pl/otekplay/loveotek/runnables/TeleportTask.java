package pl.otekplay.loveotek.runnables;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Core;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.TeleportSettings;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class TeleportTask extends BukkitRunnable {
    private static final List<UUID> uniqueIds = new CopyOnWriteArrayList<>();
    private final UUID uuid;
    private final Location from;
    private final Location to;
    private final int count;

    private TeleportTask(UUID uuid, Location from, Location to, int count) {
        this.uuid = uuid;
        this.from = from;
        this.to = to;
        this.count = count;
    }

    @Override
    public void run() {
        Player p = Bukkit.getPlayer(uuid);
        if (p == null) {
            uniqueIds.remove(uuid);
            return;
        }
        Location last = p.getLocation();
        if (from.getBlockX() != last.getBlockX() || from.getBlockZ() != last.getBlockZ()) {
            uniqueIds.remove(uuid);
            p.sendMessage(TeleportSettings.MESSAGE_TELEPORT_FAILED);
            return;
        }
        if (count == 1) {
            uniqueIds.remove(uuid);
            p.teleport(to);
            p.sendMessage(TeleportSettings.MESSAGE_TELEPORT_SUCCESS);
            return;
        }
        Replacer.build(TeleportSettings.MESSAGE_TELEPORT_WAIT).add("%seconds%", count - 1).send(p);
        new TeleportTask(uuid, from, to, count - 1).runTaskLater(Core.getInstance(), 20);
    }

    public static void start(Player p, Location loc) {
        if (uniqueIds.contains(p.getUniqueId())) {
            p.sendMessage(TeleportSettings.MESSAGE_PLAYER_TELEPORTER_BUSY);
            return;
        }
        uniqueIds.add(p.getUniqueId());
        User user = Users.get(p.getUniqueId());
        p.sendMessage(TeleportSettings.MESSAGE_TELEPORT_START);
        Bukkit.getScheduler().runTaskLater(Core.getInstance(), new TeleportTask(p.getUniqueId(), p.getLocation(), loc, (user.hasPermissions(UserRank.HELPER) ? 1 : 5)), 20);
    }
}
