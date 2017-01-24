package pl.otekplay.loveotek.commands.admin.backup.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.BackupType;
import pl.otekplay.loveotek.main.Backups;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.BackupSettings;
import pl.otekplay.loveotek.storage.GlobalSettings;

public class SaveArgCommand implements SubCommand {
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
            Replacer.build(GlobalSettings.MESSAGE_PLAYER_IS_OFFLINE).add("%name%",user.getName()).send(player);
            return;
        }
        Backups.save(user.getUniqueID(), BackupType.MANUAL);
        Replacer.build(BackupSettings.MESSAGE_BACKUP_SAVED_PLAYER).add("%name%", user.getName()).send(player);
    }
}
