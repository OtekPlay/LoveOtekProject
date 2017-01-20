package pl.otekplay.loveotek.managers;

import ninja.amp.ampmenus.items.BackupItem;
import ninja.amp.ampmenus.menus.ItemMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import pl.otekplay.loveotek.basic.Backup;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.builders.ItemBuilder;
import pl.otekplay.loveotek.enums.BackupType;
import pl.otekplay.loveotek.main.Core;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.BackupSettings;
import pl.otekplay.loveotek.utils.TimeUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BackupManager {

    private final List<Backup> backups = new ArrayList<>();
    private final ItemStack item_skull = new ItemStack(Material.SKULL_ITEM,1,(short) 3);
    public void registerBackup(UUID uuid, BackupType type){
        Player p = Bukkit.getPlayer(uuid);
        PlayerInventory playerInventory = p.getInventory();
        Backup backup = new Backup(uuid,type,System.currentTimeMillis(),playerInventory.getContents(),playerInventory.getArmorContents(),p.getEnderChest().getContents());
        if(backup.getContentsAmount() == 0 && backup.getArmorAmount() == 0 &&backup.getEnderchestAmount() == 0){
            return;
        }
        backups.add(backup);
    }
    public Backup getBackup(UUID uuid,long time){
        return getBackups(uuid).stream().filter(backup -> backup.getTime() == time).findFirst().orElse(null);
    }
    public boolean isBackup(UUID uuid,long time){
        return getBackup(uuid,time) != null;
    }
    private List<Backup> getBackups(UUID uuid){
        return backups.parallelStream().filter(backup -> backup.getUniqueID().equals(uuid)).collect(Collectors.toList());
    }
    public ItemMenu getItemMenu(UUID uuid){
        User user = Users.get(uuid);
        List<Backup> backups = getBackups(uuid);
        Collections.sort(backups, (o1, o2) -> Long.compare(o2.getTime(),o1.getTime()));
        ItemMenu menu = new ItemMenu(Replacer.build(BackupSettings.MESSAGE_BACKUP_MENU_HEADER).add("%name%",user.getName()).get()[0], ItemMenu.Size.ONE_LINE, Core.getInstance());
        int i = 0;
        for(Backup backup:backups){
            if(i == 9){
                break;
            }
            menu.setItem(i,new BackupItem(getItem(backup,i),backup.getUniqueID(),backup.getTime()));
            i++;
        }
        return menu;
    }
    private ItemStack getItem(Backup backup,int amount){
       return new ItemBuilder(item_skull.clone())
               .setName(Replacer.build(BackupSettings.MESSAGE_BACKUP_ITEM_HEADER).add("%amount%",amount+1+"").get()[0])
               .setLore(Replacer.build(BackupSettings.MESSAGE_BACKUP_ITEM_INFO).add("%type%",backup.getType().name()).add("%time%", TimeUtil.getDate(backup.getTime())).add("%contents%",backup.getContentsAmount()).add("%armor%",backup.getArmorAmount()).add("%enderchest%",backup.getEnderchestAmount()).get()).toItemStack();
    }
}
