package pl.otekplay.loveotek.commands.admin.group;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GlobalSettings;

public class GroupCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("grupa", UserRank.HEADADMIN, "grupa [NICK] [RANK]", "root", "group");
    }

    @Override
    public int minArgs() {
        return 2;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        String name = args[0];
        String group = args[1];
        if (!UserRank.isGroup(group)) {
            Replacer.build(GlobalSettings.MESSAGE_GROUP_NO_EXIST).add("%name%",group).send(player);
            return;
        }
        UserRank rank = UserRank.getGroup(group);
        if (!Users.is(name)) {
            Replacer.build(GlobalSettings.MESSAGE_PLAYER_NO_EXIST).add("%name%", name).send(player);
            return;
        }
        User user = Users.get(player.getUniqueId());
        if(rank.getPriority() > user.getRank().getPriority()){
            player.sendMessage(GlobalSettings.MESSAGE_GROUP_GIVE_TOO_HIGH);
            return;
        }
        User set = Users.get(name);
        if(set.getRank().getPriority() >= user.getRank().getPriority()){
            player.sendMessage(GlobalSettings.MESSAGE_GROUP_TAKE_TOO_HIGH);
            return;
        }
        set.setRank(rank);
        Replacer.build(GlobalSettings.MESSAGE_GROUP_SET_USER).add("%nick%", set.getName()).add("%name%", rank.name()).send(player);
    }
}
