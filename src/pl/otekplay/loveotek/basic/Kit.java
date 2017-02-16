package pl.otekplay.loveotek.basic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ninja.amp.ampmenus.items.StableMenuItem;
import ninja.amp.ampmenus.menus.ItemMenu;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Core;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.KitSettings;

import java.util.HashMap;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class Kit {
    private final String name;
    private final ItemStack icon;
    private final UserRank rank;
    private final int slot;
    private final long cooldown;
    private final ItemStack[] items;
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();

    public void takeKit(UUID uuid) {
        cooldowns.put(uuid, System.currentTimeMillis());
    }

    public boolean canTake(UUID uuid) {
        if (!cooldowns.containsKey(uuid)) {
            return true;
        }
        long time = cooldowns.get(uuid);
        if (System.currentTimeMillis() - time > cooldown) {
            return true;
        }
        return false;
    }

    public ItemMenu getMenu(){
        ItemMenu menu = new ItemMenu(Replacer.build(KitSettings.MENU_SINGLE_KIT_NAME).add("%name%",name).get()[0], ItemMenu.Size.fit(items.length), Core.getInstance());
        for(int i=0;i<items.length;i++){
            menu.setItem(i,new StableMenuItem(items[i]));
        }
        return menu;
    }

    public boolean hasPermissions(UUID uuid) {
        return Users.get(uuid).hasPermissions(rank);
    }

    public void give(Player p) {
        Replacer.build(KitSettings.MESSAGE_PLAYER_KIT_GIVE).add("%name%",icon.getItemMeta().getDisplayName()).send(p);
        p.getInventory().addItem(items);
    }
}
