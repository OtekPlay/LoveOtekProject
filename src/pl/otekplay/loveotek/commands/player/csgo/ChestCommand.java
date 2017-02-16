package pl.otekplay.loveotek.commands.player.csgo;

import ninja.amp.ampmenus.items.StableMenuItem;
import ninja.amp.ampmenus.menus.ItemMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Core;
import pl.otekplay.loveotek.runnables.ChestTask;

public class ChestCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("csgo", UserRank.HEADADMIN, "csgo");
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        ItemMenu menu = new ItemMenu("CS GO", ItemMenu.Size.ONE_LINE,Core.getInstance());
        for(int i=0;i<9;i++){
            menu.setItem(i,new StableMenuItem(new ItemStack(Material.getMaterial(i+1))));
        }
        menu.open(player);
        Bukkit.getScheduler().runTaskLater(Core.getInstance(), new ChestTask(player.getUniqueId(),menu,1,5), 1);
    }
}
