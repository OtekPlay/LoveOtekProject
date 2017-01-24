package pl.otekplay.loveotek.managers;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.otekplay.loveotek.builders.ItemBuilder;
import pl.otekplay.loveotek.storage.CobbleXSettings;
import pl.otekplay.loveotek.utils.ItemUtil;
import pl.otekplay.loveotek.utils.RandomUtil;

import java.util.ArrayList;
import java.util.List;

public class CobbleXManager {

    private final List<Location> cobblexLocations = new ArrayList<>();
    private final List<ItemStack> items = new ArrayList<>();
    private ItemStack COBBLEX_ITEM;

    public void init() {
        COBBLEX_ITEM = new ItemBuilder(Material.getMaterial(CobbleXSettings.COBBLEX_ITEM_ID), 1).setName(CobbleXSettings.COBBLEX_ITEM_NAME).setLore(CobbleXSettings.COBBLEX_ITEM_LORE).toItemStack();
        for (String s : CobbleXSettings.COBBLEX_ITEM_DROPS) {
            String[] tab = s.split(":");
            int ID = Integer.parseInt(tab[0]);
            int amount = Integer.parseInt(tab[1]);
            items.add(new ItemStack(Material.getMaterial(ID), amount));
        }
    }


    public void addCobble(Location location) {
        cobblexLocations.add(location);
    }

    public void removeCobble(Location location) {
        cobblexLocations.add(location);
    }

    public boolean isCobble(Location location) {
        return cobblexLocations.contains(location);
    }


    public boolean hasMaterialsForCraft(Inventory inventory) {
        return ItemUtil.getItemCount(inventory, Material.COBBLESTONE.getId(), 0) >= 64 * 9;
    }

    public ItemStack getRandomItem(){
        return items.get(RandomUtil.getRandInt(0,items.size()-1));
    }

    public ItemStack getItem() {
        return COBBLEX_ITEM.clone();
    }

    public boolean isCobbleXItem(ItemStack item) {
        if(item.getTypeId() != CobbleXSettings.COBBLEX_ITEM_ID){
            return false;
        }
        ItemMeta meta = item.getItemMeta();
        if(!meta.getDisplayName().equals(CobbleXSettings.COBBLEX_ITEM_NAME)){
            return false;
        }
        if(!meta.getLore().equals(CobbleXSettings.COBBLEX_ITEM_LORE)){
            return false;
        }
        return true;
    }
}
