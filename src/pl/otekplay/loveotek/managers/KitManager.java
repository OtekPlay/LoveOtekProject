package pl.otekplay.loveotek.managers;

import lombok.Getter;
import ninja.amp.ampmenus.items.KitItem;
import ninja.amp.ampmenus.menus.ItemMenu;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import pl.otekplay.loveotek.basic.Kit;
import pl.otekplay.loveotek.builders.ItemBuilder;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Core;
import pl.otekplay.loveotek.storage.KitSettings;
import pl.otekplay.loveotek.utils.ChatUtil;
import pl.otekplay.loveotek.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KitManager {

    private final List<Kit> kits = new ArrayList<>();
    private File folder;
    @Getter
    private ItemMenu kitMenu;



    public void init() {
        folder = new File(Core.getInstance().getDataFolder(), "kits");
        saveDefaultKits();
        loadKits();
        loadMenu();
    }

    public Kit getKitByItem(ItemStack item){
        return kits.parallelStream().filter(kit -> kit.getIcon().equals(item)).findFirst().orElse(null);
    }

    private void loadKits() {
        for (File file : folder.listFiles()) {
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            String name = yaml.getString("Name");
            String menuName = ChatUtil.fixColors(yaml.getString("MenuName"));
            List<String> lore = ChatUtil.fixColors(yaml.getStringList("MenuLore"));
            int id = yaml.getInt("MenuID");
            UserRank rank = UserRank.getGroup(yaml.getString("Rank"));
            int slot = yaml.getInt("Slot");
            long cooldown = yaml.getLong("Cooldown");
            List<ItemStack> items = (List<ItemStack>) yaml.get("Items");
            ItemStack icon = new ItemBuilder(Material.getMaterial(id)).setName(menuName).setLore(lore).toItemStack();
            Kit kit = new Kit(name,icon,rank,slot,cooldown,items.toArray(new ItemStack[items.size()]));
            kits.add(kit);
        }
    }
    private void loadMenu(){
        kitMenu = new ItemMenu(KitSettings.MENU_KIT_NAME, ItemMenu.Size.THREE_LINE,Core.getInstance());
        for(Kit kit:kits){
            kitMenu.setItem(kit.getSlot(),new KitItem(kit.getIcon()));
        }
    }

    private void saveDefaultKits(){
        try {
            File file = FileUtil.createFile(new File(folder, "default.yml"));
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            yaml.addDefault("Name", "Default");
            yaml.addDefault("MenuName", "Defualtowy kit!");
            yaml.addDefault("MenuLore", Arrays.asList("Lore1", "Lore2", "Lore3"));
            yaml.addDefault("MenuID", 3);
            yaml.addDefault("Rank", UserRank.ADMIN.name());
            yaml.addDefault("Slot", 5);
            yaml.addDefault("Cooldown", 60000);
            ItemStack a = new ItemBuilder(Material.APPLE,30).setName("jabluszko").setLore("o takie","wielkie").addEnchant(Enchantment.LOOT_BONUS_BLOCKS,1).toItemStack();
            yaml.addDefault("Items", Arrays.asList(a,a));
            yaml.options().copyDefaults(true);
            yaml.save(file);
        }catch (Exception e){
            e.printStackTrace();
        }
    }




}
