package pl.otekplay.loveotek.commands.admin.inventory;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.storage.GlobalSettings;

public class ClearInventoryCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("wyczysc", UserRank.HELPER, "wyczysc", "clear", "ci", "czysc");
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        player.getInventory().clear();
        player.sendMessage(GlobalSettings.MESSAGE_ADMIN_CLEAR_INVENTORY);
    }
}
