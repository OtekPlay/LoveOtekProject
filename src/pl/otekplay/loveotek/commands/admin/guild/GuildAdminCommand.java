package pl.otekplay.loveotek.commands.admin.guild;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.commands.admin.guild.subs.*;
import pl.otekplay.loveotek.enums.UserRank;

public class GuildAdminCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("ga", UserRank.ADMIN, "ga", "guildadmin")
                        .sub("leader", new GuildAdminLeaderCommand())
                        .sub("stworz ", new GuildAdminCreateCommand())
                        .sub("zapros", new GuildAdminInviteCommand())
                        .sub("usun", new GuildAdminDeleteCommand())
                        .sub("tp", new GuildAdminTeleportCommand())
                        .sub("dolacz", new GuildAdminJoinCommand())
                        .sub("wyrzuc", new GuildAdminKickCommand());
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {

    }
}
