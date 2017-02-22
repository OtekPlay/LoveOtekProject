package pl.otekplay.loveotek.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pl.otekplay.loveotek.main.Generators;
import pl.otekplay.loveotek.storage.GeneratorSettings;

@Getter
@RequiredArgsConstructor
public enum GeneratorType {
    OBSIDIAN(Material.OBSIDIAN),
    STONE(Material.STONE);

    private final Material material;

    public long getRepairTime(){
        return this == OBSIDIAN ? GeneratorSettings.GENERATOR_REPAIR_TIME_OBSIDIAN:GeneratorSettings.GENERATOR_REPAIR_TIME_STONE;
    }
    public ItemStack getItem(){
        return Generators.item(this);
    }

}
