package pl.otekplay.loveotek.commands.admin.teleport;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GlobalSettings;
import pl.otekplay.loveotek.storage.TeleportSettings;

public class SummonCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("summon", UserRank.MODERATOR,"summon [NICK]","summ","s","tphere");
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
        if(!user.isOnline()){
            Replacer.build(GlobalSettings.MESSAGE_PLAYER_IS_OFFLINE).add("%name%", user.getName()).send(player);
            return;
        }
        Player online = user.getPlayer();
        online.teleport(player);
        Replacer.build(TeleportSettings.MESSAGE_SUMMON_PLAYER_SEND).add("%name%",online.getName()).send(player);
        Replacer.build(TeleportSettings.MESSAGE_SUMMON_PLAYER_GET).add("%name%",player.getName()).send(online);
    }
}
