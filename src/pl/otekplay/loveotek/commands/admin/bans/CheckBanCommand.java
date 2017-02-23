package pl.otekplay.loveotek.commands.admin.bans;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.Ban;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Bans;
import pl.otekplay.loveotek.storage.BanSettings;
import pl.otekplay.loveotek.utils.TimeUtil;

public class CheckBanCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("checkban", UserRank.MODERATOR,"checkban [NICK]","chban","cban");
    }

    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        String name = args[0];
        if(!Bans.is(name)){
            Replacer.build(BanSettings.MESSAGE_PLAYER_NO_BAN).add("%name%",name).send(player);
            return;
        }
        Ban b = Bans.get(name);
        Replacer.build(BanSettings.MESSAGE_PLAYER_HAS_BAN).add("%time%", TimeUtil.getPrettyDate(b.getTime())).add("%reason%",b.getReason()).add("%name%",b.getNickname()).send(player);

    }
}
