package pl.otekplay.loveotek.commands.admin.inventory;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GlobalSettings;

public class EnderchestCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("enderchest", UserRank.VIP,"enderchest","echest","e");
    }

    @Override
    public int minArgs() {
        return 0;
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
        player.openInventory(player.getEnderChest());
        Replacer.build(GlobalSettings.MESSAGE_ADMIN_OPEN_INVENTORY).add("%type%","").add("%admin%", player.getName()).add("%name%", user.getName()).broadcast(UserRank.HEADADMIN);

    }
}
