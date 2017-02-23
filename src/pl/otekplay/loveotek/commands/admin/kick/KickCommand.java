package pl.otekplay.loveotek.commands.admin.kick;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.BanSettings;
import pl.otekplay.loveotek.storage.GlobalSettings;

public class KickCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("kick", UserRank.HELPER, "kick [NICK] [REASON]");
    }

    @Override
    public int minArgs() {
        return 2;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        User set = Users.get(player.getUniqueId());
        String name = args[0];
        if (!Users.is(name)) {
            Replacer.build(GlobalSettings.MESSAGE_PLAYER_NO_EXIST).add("%nick%",name).send(player);
            return;
        }
        User user = Users.get(name);
        if (!user.isOnline()) {
            Replacer.build(GlobalSettings.MESSAGE_PLAYER_IS_OFFLINE).add("%nick%",user.getName()).send(player);
            return;
        }
        if (user.getRank().getPriority() >= set.getRank().getPriority()) {
            Replacer.build(BanSettings.MESSAGE_YOU_CANT_KICK_HIGHER_RANK).add("%nick%", user.getName()).send(player);
            return;
        }
        StringBuilder reason = new StringBuilder();
        for(int i =1;i<args.length;i++){
            reason.append(args[i]);
        }
        String rg = reason.toString();
        user.getPlayer().kickPlayer(Replacer.build(BanSettings.MESSAGE_YOU_GOT_KICK).add("%reason%",rg).add("%admin%",player.getName()).get()[0]);
        Replacer.build(BanSettings.MESSAGE_PLAYER_GOT_KICK).add("%reason%",rg).add("%name%",user.getName()).add("%admin%",player.getName()).broadcast();

    }
}
