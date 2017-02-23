package pl.otekplay.loveotek.commands.admin.mute;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.enums.UserRank;

public class MuteCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("mute", UserRank.HELPER,"mute [NICK] [TIME]");
    }

    @Override
    public int minArgs() {
        return 2;
    }

    @Override
    public void onCommand(Player player, String[] args) {

    }
}
