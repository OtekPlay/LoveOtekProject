package pl.otekplay.loveotek.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.basic.Cuboid;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.main.Core;
import pl.otekplay.loveotek.main.Cuboids;
import pl.otekplay.loveotek.storage.TeleportSettings;
import pl.otekplay.loveotek.utils.RandomUtil;

public class TeleporterManager {

    private World getWorld() {
        return Bukkit.getWorlds().get(0);
    }

    public void teleportPlayerRandomly(Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(Core.getInstance(), () -> {
            while (true) {
                int x = (int) RandomUtil.getRandDouble(-TeleportSettings.RANDOM_TELEPORTER_BORDER, TeleportSettings.RANDOM_TELEPORTER_BORDER);
                int z = (int) RandomUtil.getRandDouble(-TeleportSettings.RANDOM_TELEPORTER_BORDER, TeleportSettings.RANDOM_TELEPORTER_BORDER);
                for (int i = 60; i < 70; i++) {
                    int id = getWorld().getBlockTypeIdAt(x, i, z);
                    if (!isValid(id)) {
                        continue;
                    }
                    if (getWorld().getBlockTypeIdAt(x, i + 1, z) != Material.AIR.getId()) {
                        continue;
                    }
                    player.teleport(new Location(getWorld(), x, i + 1, z));
                    player.sendMessage(TeleportSettings.MESSAGE_TELEPORT_SUCCESS);
                    return;
                }
            }
        });
    }

    public boolean canTeleport(User user, Location location) {
        if (!Cuboids.inside(location)) {
            return true;
        }
        Cuboid cuboid = Cuboids.cub(location);
        if (!cuboid.isGuildTerrain()) {
            return true;
        }
        if (!user.hasGuild()) {
            return false;
        }
        Guild guild = user.getGuild();
        return guild.getCuboid().equals(cuboid);
    }


    private boolean isValid(int id) {
        return (id == Material.GRASS.getId() ||
                id == Material.SAND.getId() ||
                id == Material.STONE.getId() ||
                id == Material.DIRT.getId() ||
                id == Material.SAND.getId() ||
                id == Material.COBBLESTONE.getId() ||
                id == Material.GRAVEL.getId());

    }
}
