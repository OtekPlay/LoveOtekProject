package pl.otekplay.loveotek.commands.admin.bans;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Bans;
import pl.otekplay.loveotek.storage.BanSettings;

public class UnbanCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("unban", UserRank.MODERATOR,"unban [NICK]","uban");
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
        Replacer.build(BanSettings.MESSAGE_PLAYER_GOT_UNBAN).add("%nick%",name).add("%admin%",player.getName()).broadcast();
        Bans.unban(name);
    }
}
