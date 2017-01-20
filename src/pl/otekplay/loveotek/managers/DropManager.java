package pl.otekplay.loveotek.managers;

import lombok.Getter;
import ninja.amp.ampmenus.items.DropItem;
import ninja.amp.ampmenus.menus.ItemMenu;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import pl.otekplay.loveotek.basic.Drop;
import pl.otekplay.loveotek.basic.DropUser;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.builders.ItemBuilder;
import pl.otekplay.loveotek.main.Core;
import pl.otekplay.loveotek.main.Drops;
import pl.otekplay.loveotek.storage.DropSettings;
import pl.otekplay.loveotek.utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DropManager {

    @Getter
    private final List<Drop> drops = new ArrayList<>();
    @Getter
    private final HashMap<UUID, DropUser> dropUsers = new HashMap<>();
    private File folder;

    public void registerDropUser(UUID uuid) {
        dropUsers.put(uuid, new DropUser(uuid));
    }

    public void unregisterDropUser(UUID uuid) {
        dropUsers.remove(uuid);
    }

    public DropUser getUserDrop(UUID uuid) {
        return dropUsers.get(uuid);
    }

    public void init() {
        folder = FileUtil.createFolder(new File(Core.getInstance().getDataFolder(), "drops"));
        loadDrops();
    }

    public Drop getDrop(String name) {
        return drops.stream().filter(drop -> drop.getDropName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public boolean isDrop(String name) {
        return getDrop(name) != null;
    }

    public ItemMenu getMenu(UUID uuid) {
        DropUser user = Drops.get(uuid);
        ItemMenu menu = new ItemMenu(DropSettings.DROP_MENU_ITEM_HEADER, ItemMenu.Size.THREE_LINE, Core.getInstance());
        for (int i = 0; i < drops.size(); i++) {
            Drop drop = drops.get(i);
            menu.setItem(i, new DropItem(getDropItem(drop, user), drop));
        }
        return menu;
    }

    public ItemStack getDropItem(Drop drop, DropUser user) {
        ItemBuilder builder = new ItemBuilder(drop.getItem().clone());
        builder.setName(Replacer.build(DropSettings.DROP_MENU_ITEM_NAME).add("%name%", drop.getDropName()).get()[0]);
        Replacer rep = Replacer.build(DropSettings.DROP_MENU_ITEM_LORE);
        rep.add("%chance%", user.hasVip() ? drop.getVipChance() + "" : drop.getChance() + "");
        rep.add("%playerexp%", drop.getExpPlayer() + "");
        rep.add("%userexp%", drop.getExpUser() + "");
        rep.add("%type%", (user.isDisabledDrop(drop) ? DropSettings.DROP_MENU_ITEM_DISABLED : DropSettings.DROP_MENU_ITEM_ENABLED));
        rep.add("%height%", drop.getHeight());
        builder.setLore(rep.get());
        return builder.toItemStack();
    }

    private void loadDrops() {
        if (folder.listFiles().length == 0) {
            File file = FileUtil.createFile(new File(folder, "example.yml"));
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            yaml.addDefault("Name", "Example");
            yaml.addDefault("ID", 50);
            yaml.addDefault("Data", 5);
            yaml.addDefault("Height", 60);
            yaml.addDefault("Chance", 0.30);
            yaml.addDefault("VipChance", 0.50);
            yaml.addDefault("ExpPlayer", 1.0);
            yaml.addDefault("ExpUser", 1);
            yaml.options().copyDefaults(true);
            try {
                yaml.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        for (File file : folder.listFiles()) {
            if (file.getName().contains("example")) {
                continue;
            }
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            String name = yaml.getString("Name");
            int ID = yaml.getInt("ID");
            short data = (short) yaml.getInt("Data");
            int height = yaml.getInt("Height");
            double chance = yaml.getDouble("Chance");
            double vipChance = yaml.getDouble("VipChance");
            int player = yaml.getInt("ExpPlayer");
            int user = yaml.getInt("ExpUser");
            Drop drop = new Drop(name, chance, vipChance, player, user, height, new ItemStack(Material.getMaterial(ID), 1, data));
            drops.add(drop);
        }
    }

}
