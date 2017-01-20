package pl.otekplay.loveotek.main;

import org.bukkit.Location;
import org.bukkit.World;
import pl.otekplay.loveotek.basic.Cuboid;
import pl.otekplay.loveotek.enums.CuboidType;
import pl.otekplay.loveotek.managers.CuboidManager;

import java.util.Collection;

public class Cuboids {

    public static void add(String key, World world, int x, int z, int size, CuboidType type) {
        manager().registerCuboid(key, world, x, z, size, type);
    }
    public static void add(String key, Location location, int size, CuboidType type) {
        manager().registerCuboid(key, location, size, type);
    }

    public static void remove(String key) {
        manager().removeCuboid(key);
    }

    public static boolean can(Location loc) {
        return manager().isEnoughFromOthers(loc);
    }

    public static boolean is(int x, int z) {
        return manager().isCuboid(x, z);
    }

    public static boolean is(Location loc) {
        return manager().isCuboid(loc);
    }

    public static boolean is(String key) {
        return manager().isCuboid(key);
    }

    public static Cuboid get(int x, int z) {
        return manager().getCuboid(x, z);
    }

    public static Cuboid get(String key) {
        return manager().getCuboid(key);
    }

    public static Cuboid get(Location loc) {
        return manager().getCuboid(loc);
    }

    public static Cuboid cub(Location loc) {
        return manager().getInside(loc);
    }

    public static Cuboid cub(int x, int z) {
        return manager().getInside(x, z);
    }

    public static boolean inside(int x, int z) {
        return manager().isInside(x, z);
    }

    public static boolean inside(Location loc) {
        return manager().isInside(loc);
    }

    public static Location spawn(){
       return manager().getSpawn();
    }

    public static Collection<Cuboid> all() {
        return manager().getCuboids();
    }

    private static CuboidManager manager() {
        return Core.getInstance().getCuboidManager();
    }
}
