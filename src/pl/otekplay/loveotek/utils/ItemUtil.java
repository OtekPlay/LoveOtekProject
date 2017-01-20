package pl.otekplay.loveotek.utils;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Attachable;
import org.bukkit.material.MaterialData;

public class ItemUtil {

    public static int getItemCount(Inventory inventory, int typeid, int data) {
        if (typeid < 0) {
            int count = 0;
            for (org.bukkit.inventory.ItemStack item : inventory.getContents()) {
                if (!nullOrEmpty(item)) {
                    count += item.getAmount();
                }
            }
            return count;
        } else {
            org.bukkit.inventory.ItemStack rval = findItem(inventory, typeid, data);
            return rval == null ? 0 : rval.getAmount();
        }
    }
    public static void removeItems(Inventory inventory, int itemid, int data, int amount) {
        int countToRemove = amount < 0 ? Integer.MAX_VALUE : amount;
        for (int i = 0; i < inventory.getSize(); i++) {
            org.bukkit.inventory.ItemStack item = inventory.getItem(i);
            if (nullOrEmpty(item) || getTypeId(item) != itemid || (data != -1 && getRawData(item) != data)) {
                continue;
            }
            if (item.getAmount() <= countToRemove) {
                countToRemove -= item.getAmount();
                inventory.setItem(i, null);
            } else {
                subtractAmount(item, countToRemove);
                countToRemove = 0;
                inventory.setItem(i, item);
                break;
            }
        }
    }
    public static boolean nullOrEmpty(org.bukkit.inventory.ItemStack item) {
        return item == null || item.getTypeId() == 0 || item.getAmount() < 1;
    }
    public static void subtractAmount(org.bukkit.inventory.ItemStack item, int amount) {
        addAmount(item, -amount);
    }
    public static void addAmount(org.bukkit.inventory.ItemStack item, int amount) {
        item.setAmount(Math.max(item.getAmount() + amount, 0));
    }
    public static ItemStack findItem(Inventory inventory, int typeId, int data) {
        ItemStack rval = null;
        int itemData = data;
        int itemTypeId = typeId;
        for (org.bukkit.inventory.ItemStack item : inventory.getContents()) {
            if (nullOrEmpty(item)) {
                continue;
            }
            // Compare type Id
            if (itemTypeId == -1) {
                itemTypeId = getTypeId(item);
            } else if (itemTypeId != getTypeId(item)) {
                continue;
            }
            // Compare data
            if (itemData == -1) {
                itemData = getRawData(item);
            } else if (getRawData(item) != itemData) {
                continue;
            }
            // addition
            if (rval == null) {
                rval = item.clone();
            } else {
                addAmount(rval, item.getAmount());
            }
        }
        return rval;
    }
    public static MaterialData getData(Material type, int rawData) {
        if (type == null) {
            return new MaterialData(0, (byte) rawData);
        }
        final MaterialData mdata = type.getNewData((byte) rawData);

        if (mdata instanceof Attachable) {
            Attachable att = (Attachable) mdata;
            if (att.getAttachedFace() == null) {
                att.setFacingDirection(BlockFace.NORTH);
            }
        }
        return mdata;
    }

    public static int getRawData(ItemStack item) {
        return item.getDurability();
    }

    public static int getTypeId(ItemStack item) {
        return item.getTypeId();
    }
}
