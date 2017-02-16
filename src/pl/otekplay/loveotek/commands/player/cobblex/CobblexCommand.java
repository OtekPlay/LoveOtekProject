package pl.otekplay.loveotek.commands.player.cobblex;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.CobbleX;
import pl.otekplay.loveotek.storage.CobbleXSettings;
import pl.otekplay.loveotek.utils.ItemUtil;

public class CobblexCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("cobblex", UserRank.PLAYER, "cobblex", "cx");
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (!CobbleX.craft(player.getInventory())) {
            player.sendMessage(CobbleXSettings.MESSAGE_COBBLEX_NEED_COBBLESTONE);
            return;
        }
        ItemUtil.removeItems(player.getInventory(), Material.COBBLESTONE.getId(),0,9*64);
        player.getInventory().addItem(CobbleX.item());
        player.sendMessage(CobbleXSettings.MESSAGE_COBBLEX_CRAFT_COMPLETE);
    }
}
