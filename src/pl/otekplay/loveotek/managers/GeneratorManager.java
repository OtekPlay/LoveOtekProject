package pl.otekplay.loveotek.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import pl.otekplay.loveotek.basic.Generator;
import pl.otekplay.loveotek.builders.ItemBuilder;
import pl.otekplay.loveotek.enums.GeneratorType;
import pl.otekplay.loveotek.storage.GeneratorSettings;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class GeneratorManager {

    private final Map<Location, Generator> generatorMap = new HashMap<>();
    private final List<Generator> destroyed = new CopyOnWriteArrayList<>();
    private ItemStack GENERATOR_ITEM_OBSIDIAN, GENERATOR_ITEM_STONE;

    public void init() {
        GENERATOR_ITEM_OBSIDIAN = new ItemBuilder(Material.ENDER_STONE).setName(GeneratorSettings.GENERATOR_ITEMSTACK_NAME_OBSIDIAN).toItemStack();
        GENERATOR_ITEM_STONE = new ItemBuilder(Material.ENDER_STONE).setName(GeneratorSettings.GENERATOR_ITEMSTACK_NAME_STONE).toItemStack();
        Arrays.asList(GeneratorType.values()).forEach(this::registerRecipe);
    }

    private void registerRecipe(GeneratorType type) {
        if (type == GeneratorType.STONE) {
            ShapedRecipe recipe = new ShapedRecipe(GENERATOR_ITEM_STONE);
            recipe.shape(GeneratorSettings.GENERATOR_RECIPE_STONE_SHAPE.toArray(new String[3]));
            for (String s : GeneratorSettings.GENERATOR_RECIPE_STONE_MATERIALS) {
                String[] tab = s.split(":");
                recipe.setIngredient(tab[0].charAt(0), Material.getMaterial(Integer.parseInt(tab[1])));
            }
            Bukkit.addRecipe(recipe);
        } else if (type == GeneratorType.OBSIDIAN) {
            ShapedRecipe recipe = new ShapedRecipe(GENERATOR_ITEM_OBSIDIAN);
            recipe.shape(GeneratorSettings.GENERATOR_RECIPE_OBSIDIAN_SHAPE.toArray(new String[3]));
            for (String s : GeneratorSettings.GENERATOR_RECIPE_OBSIDIAN_MATERIALS) {
                String[] tab = s.split(":");
                recipe.setIngredient(tab[0].charAt(0), Material.getMaterial(Integer.parseInt(tab[1])));
            }
            Bukkit.addRecipe(recipe);
        }
    }

    public Generator registerGenerator(Location location, GeneratorType type) {
        Generator generator = new Generator(location, type);
        generatorMap.put(generator.getLocation(), generator);
        return generator;
    }

    public void unregisterGenerator(Location location) {
        generatorMap.remove(location);
    }

    public boolean isGenerator(Location location) {
        return generatorMap.containsKey(location);
    }

    public Generator getGenerator(Location location) {
        return generatorMap.get(location);
    }

    public void destroyGenerator(Generator generator) {
        destroyed.add(generator);
    }

    public void repairGenerator(Generator generator) {
        destroyed.remove(generator);
    }

    public ItemStack getGeneratorItem(GeneratorType type) {
        if (type == GeneratorType.OBSIDIAN) {
            return GENERATOR_ITEM_OBSIDIAN;
        }
        return GENERATOR_ITEM_STONE;
    }

    public Collection<Generator> getNeedRepairGenerators() {
        return destroyed.parallelStream().filter(generator -> generator.isReadyForRepair()).collect(Collectors.toList());
    }
}
