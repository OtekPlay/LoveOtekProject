package pl.otekplay.loveotek.commands.admin.backup;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.commands.admin.backup.subs.OpenArgCommand;
import pl.otekplay.loveotek.commands.admin.backup.subs.SaveArgCommand;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Backups;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GlobalSettings;

public class BackupCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("backup", UserRank.ADMIN,"backup [OPEN/SAVE] [NICK]","backupy")
                .sub("open",new OpenArgCommand())
                .sub("save",new SaveArgCommand());
    }

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
        Backups.menu(player,user.getUniqueID());
    }
}
