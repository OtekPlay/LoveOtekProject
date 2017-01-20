package pl.otekplay.loveotek.commands.admin.inventory.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GlobalSettings;

public class OpenInventoryArgCommand implements SubCommand {
    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        String name = args[0];
        if (!Users.is(name)) {
            Replacer.build(GlobalSettings.MESSAGE_PLAYER_NO_EXIST).add("%name%", name).send(player);
            return;
        }
        User user = Users.get(name);
        if (!user.isOnline()) {
            Replacer.build(GlobalSettings.MESSAGE_PLAYER_IS_OFFLINE).add("%name%", user.getName()).send(player);
            return;
        }
        Player open = user.getPlayer();
        player.openInventory(open.getInventory());
        Replacer.build(GlobalSettings.MESSAGE_ADMIN_OPEN_INVENTORY).add("%type%", GlobalSettings.MESSAGE_ADMIN_NAME_INVENTORY).add("%admin%", player.getName()).add("%name%", user.getName()).broadcast(UserRank.HEADADMIN);
    }
}
