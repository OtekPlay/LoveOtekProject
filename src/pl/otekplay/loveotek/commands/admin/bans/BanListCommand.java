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

public class BanListCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("banlist", UserRank.MODERATOR, "banlist", "blist");
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        for (Ban b : Bans.bans()) {
            Replacer.build(BanSettings.MESSAGE_BAN_LIST).add("%time%", TimeUtil.getPrettyDate(b.getTime())).add("%reason%", b.getReason()).add("%name%", b.getNickname()).send(player);
            return;
        }
    }
}
