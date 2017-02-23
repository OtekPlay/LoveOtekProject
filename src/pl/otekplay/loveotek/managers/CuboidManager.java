package pl.otekplay.loveotek.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import pl.otekplay.loveotek.basic.Cuboid;
import pl.otekplay.loveotek.enums.CuboidType;
import pl.otekplay.loveotek.main.Cuboids;
import pl.otekplay.loveotek.storage.CuboidSettings;
import pl.otekplay.loveotek.storage.GuildSettings;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CuboidManager {
    private final Map<String, Cuboid> cuboids = new ConcurrentHashMap<>();

    public void registerCuboid(String key, World world, int x, int z, int size, CuboidType type) {
        Cuboid cuboid = new Cuboid(key, new Location(world, x, 40, z), type, size);
        cuboids.put(cuboid.getKey(), cuboid);
    }

    public void registerCuboid(String key, Location loc, int size, CuboidType type) {
        Cuboid cuboid = new Cuboid(key, loc, type, size);
        cuboids.put(cuboid.getKey(), cuboid);
    }
    public void removeCuboid(String key) {
        cuboids.remove(key);
    }

    public boolean isEnoughFromOthers(Location loc) {
        int size = GuildSettings.GUILD_SIZE_MAX * 2 + 1;
        return Cuboids.all().stream().filter(cuboid -> Math.abs(cuboid.getCenterX() - loc.getBlockX()) <= size && Math.abs(cuboid.getCenterZ() - loc.getBlockZ()) <= size).findFirst().orElse(null) == null;
    }

    public Location getSpawn(){
        if(isCuboid(CuboidSettings.MESSAGE_CUBOID_SPAWN_NAME)){
            return getCuboid(CuboidSettings.MESSAGE_CUBOID_SPAWN_NAME).getLocation();
        }
        return new Location(Bukkit.getWorlds().get(0),0,100,0);
    }
    public Cuboid getCuboid(Location loc) {
        return getCuboid(loc.getBlockX(), loc.getBlockZ());
    }

    public Cuboid getCuboid(int x, int z) {
        return cuboids.values().stream().filter(cuboid -> cuboid.getCenterX() == x && cuboid.getCenterZ() == z).findFirst().orElse(null);
    }

    public Cuboid getCuboid(String key) {
        return cuboids.get(key);
    }

    public boolean isCuboid(String name) {
        return getCuboid(name) != null;
    }

    public boolean isCuboid(int x, int z) {
        return getCuboid(x, z) != null;
    }

    public boolean isCuboid(Location loc) {
        return getCuboid(loc) != null;
    }

    public Cuboid getInside(Location loc) {
        return getInside(loc.getBlockX(), loc.getBlockZ());
    }

    public Cuboid getInside(int x, int z) {
        return cuboids.values().stream().filter(cuboid -> cuboid.isInside(x, z)).findFirst().orElse(null);
    }

    public boolean isInside(int x, int z) {
        return getInside(x, z) != null;
    }

    public boolean isInside(Location loc) {
        return getInside(loc) != null;
    }

    public Collection<Cuboid> getCuboids() {
        return cuboids.values();
    }

}
