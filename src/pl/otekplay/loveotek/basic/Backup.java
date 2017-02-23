package pl.otekplay.loveotek.basic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import pl.otekplay.loveotek.enums.BackupType;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class Backup {
    private final UUID uniqueID;
    private final BackupType type;
    private final long time;
    private final ItemStack[] contents,armor,enderchest;

    public void equip(){
        Player p = Bukkit.getPlayer(uniqueID);
        PlayerInventory inv = p.getInventory();
        inv.setArmorContents(armor);
        inv.setContents(contents);
        Inventory invChest = p.getEnderChest();
        invChest.setContents(enderchest);
    }

    public int getContentsAmount(){
        int amount = 0;
        for(ItemStack item:contents){
            if(item == null){
                continue;
            }
            amount = amount + item.getAmount();
        }
        return amount;
    }
    public int getArmorAmount(){
        int amount = 0;
        for(ItemStack item:armor){
            if(item == null){
                continue;
            }
            amount = amount + item.getAmount();
        }
        return amount;
    }
    public int getEnderchestAmount(){
        int amount = 0;
        for(ItemStack item:enderchest){
            if(item == null){
                continue;
            }
            amount = amount + item.getAmount();
        }
        return amount;
    }
}
