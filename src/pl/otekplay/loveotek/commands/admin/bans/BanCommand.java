package pl.otekplay.loveotek.commands.admin.bans;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.Ban;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Bans;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.BanSettings;
import pl.otekplay.loveotek.storage.GlobalSettings;
import pl.otekplay.loveotek.utils.TimeUtil;

public class BanCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("ban", UserRank.MODERATOR, "ban [NICK] [TIME] [POWOD]", "zbanuj");
    }

    @Override
    public int minArgs() {
        return 3;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        String name = args[0];
        if (!Users.is(name)) {
            Replacer.build(GlobalSettings.MESSAGE_PLAYER_NO_EXIST).add("%nick%", name).send(player);
            return;
        }
        if (Bans.is(name)) {
            Ban b = Bans.get(name);
            Replacer.build(BanSettings.MESSAGE_PLAYER_HAS_ALREADY_BAN).add("%name%", b.getNickname()).send(player);
            return;
        }
        User user = Users.get(name);
        User admin = Users.get(player.getUniqueId());
        if (user.getRank().getPriority() >= admin.getRank().getPriority()) {
            Replacer.build(BanSettings.MESSAGE_YOU_CANT_BAN_HIGHER_RANK).add("%nick%", user.getName()).send(player);
            Replacer.build(BanSettings.MESSAGE_PLAYER_TYRING_BAN_HIGHER).add("%nick%", admin.getName()).add("%admin%", user.getName()).broadcast(UserRank.ADMIN);
            return;
        }
        long time = System.currentTimeMillis() + TimeUtil.parseString(args[1]);
        StringBuilder reason = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            reason.append(args[i]);
        }
        String rs = reason.toString();
        String prettyDate = TimeUtil.getPrettyDate(time);
        if (user.isOnline()) {
            user.getPlayer().kickPlayer(Replacer.build(BanSettings.MESSAGE_YOU_GOT_BAN).add("%admin%", admin.getName()).add("%reason%", rs).add("%time%", prettyDate).get()[0]);
        }
        Replacer.build(BanSettings.MESSAGE_PLAYER_GOT_BAN).add("%nick%", user.getName()).add("%time%", prettyDate).add("%reason%", rs).add("%admin%", player.getName()).broadcast();
        Bans.ban(name, rs, time, admin);

    }
}
