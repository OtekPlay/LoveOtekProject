package pl.otekplay.loveotek.commands.player.history;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Histories;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GlobalSettings;

public class HistoryCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("historia", UserRank.PLAYER,"historia [NICK]","history","h");
    }

    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        User user;
        if(args.length > 1) {
            String name = args[0];
            if (!Users.is(name)) {
                Replacer.build(GlobalSettings.MESSAGE_PLAYER_NO_EXIST).add("%name%", name).send(player);
                return;
            }
            user = Users.get(name);
        }else{
            user = Users.get(player.getUniqueId());
        }
        Histories.menu(user.getUniqueID(),player);
    }
}
