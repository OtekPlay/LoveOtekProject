package pl.otekplay.loveotek.main;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import pl.otekplay.loveotek.basic.Generator;
import pl.otekplay.loveotek.enums.GeneratorType;
import pl.otekplay.loveotek.managers.GeneratorManager;

import java.util.Collection;

public class Generators {

    private static GeneratorManager manager() {
        return Core.getInstance().getGeneratorManager();
    }

    public static void destroy(Generator generator) {
        manager().destroyGenerator(generator);
    }

    public static void repair(Generator generator) {
        manager().repairGenerator(generator);
    }

    public static Generator register(Location location, GeneratorType type) {
        return manager().registerGenerator(location, type);
    }

    public static void unregister(Location location) {
        manager().unregisterGenerator(location);
    }

    public static boolean is(Location location) {
        return manager().isGenerator(location);
    }

    public static Generator get(Location location) {
        return manager().getGenerator(location);
    }

    public static Collection<Generator> destroyed() {
        return manager().getNeedRepairGenerators();
    }

    public static ItemStack item(GeneratorType type) {
        return manager().getGeneratorItem(type);
    }
}
