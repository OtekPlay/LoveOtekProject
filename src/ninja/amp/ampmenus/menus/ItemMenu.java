/*
 * This file is part of AmpMenus.
 *
 * Copyright (c) 2014 <http://github.com/ampayne2/AmpMenus/>
 *
 * AmpMenus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AmpMenus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with AmpMenus.  If not, see <http://www.gnu.org/licenses/>.
 */
package ninja.amp.ampmenus.menus;

import lombok.Getter;
import lombok.Setter;
import ninja.amp.ampmenus.MenuListener;
import ninja.amp.ampmenus.events.ItemClickEvent;
import ninja.amp.ampmenus.items.MenuItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A Menu controlled by ItemStacks in an Inventory.
 */
public class ItemMenu {
    private final JavaPlugin plugin;
    private final String name;
    private final Size size;
    @Getter
    @Setter
    private MenuItem[] items;

    public ItemMenu(String name, Size size, JavaPlugin plugin) {
        this.plugin = plugin;
        this.name = name;
        this.size = size;
        this.items = new MenuItem[size.getSize()];
    }

    public String getName() {
        return name;
    }

    public Size getSize() {
        return size;
    }

    public ItemMenu setItem(int position, MenuItem menuItem) {
        items[position] = menuItem;
        return this;
    }

    public ItemMenu fillEmptySlots(MenuItem menuItem) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = menuItem;
            }
        }
        return this;
    }

    public ItemMenu fillSlots(MenuItem menuItem) {
        for (int i = 0; i < items.length; i++) {
            items[i] = menuItem;
        }
        return this;
    }

    public void open(Player player) {
        if (!MenuListener.getInstance().isRegistered(plugin)) {
            MenuListener.getInstance().register(plugin);
        }
        Inventory inventory = Bukkit.createInventory(new MenuHolder(this, Bukkit.createInventory(player, size.getSize())), size.getSize(), name);
        apply(inventory, player);
        player.openInventory(inventory);
    }

    public void update(Player player) {
        if (player.getOpenInventory() != null) {
            Inventory inventory = player.getOpenInventory().getTopInventory();
            if (inventory.getHolder() instanceof MenuHolder && ((MenuHolder) inventory.getHolder()).getMenu().equals(this)) {
                apply(inventory, player);
                player.updateInventory();
            }
        }
    }

    private void apply(Inventory inventory, Player player) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                inventory.setItem(i, items[i].getFinalIcon(player));
            } else {
                inventory.setItem(i, null);
            }
        }
    }

    @SuppressWarnings("deprecation")
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT) {
            int slot = event.getRawSlot();
            if (slot >= 0 && slot < size.getSize() && items[slot] != null) {
                Player player = (Player) event.getWhoClicked();
                ItemClickEvent itemClickEvent = new ItemClickEvent(player, slot, event.getClick());
                items[slot].onItemClick(itemClickEvent);
            }
        }
    }


    /**
     * Possible sizes of an {@link ItemMenu}.
     */
    public enum Size {
        ONE_LINE(9),
        TWO_LINE(18),
        THREE_LINE(27),
        FOUR_LINE(36),
        FIVE_LINE(45),
        SIX_LINE(54);

        private final int size;

        Size(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }

        public static Size fit(int slots) {
            if (slots < 10) {
                return ONE_LINE;
            } else if (slots < 19) {
                return TWO_LINE;
            } else if (slots < 28) {
                return THREE_LINE;
            } else if (slots < 37) {
                return FOUR_LINE;
            } else if (slots < 46) {
                return FIVE_LINE;
            } else {
                return SIX_LINE;
            }
        }
    }
}
