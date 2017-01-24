package pl.otekplay.loveotek.commands.player.guild.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.GuildRank;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GuildSettings;

public class PvPArgCommand implements SubCommand {
    @Override
    public void onCommand(Player player, String[] args) {
        User user = Users.get(player.getUniqueId());
        if (!user.hasGuild()) {
            player.sendMessage(GuildSettings.MESSAGE_GUILD_YOU_NEED);
            return;
        }
        Guild guild = user.getGuild();
        GuildRank rank = guild.getGuildRank(user.getUniqueID());
        if (rank == GuildRank.MEMBER) {
            player.sendMessage(GuildSettings.MESSAGE_GUILD_TOO_LOW_PERMISSIONS);
            return;
        }
        guild.setPvp(!guild.isPvp());
        Replacer.build(guild.isPvp() ? GuildSettings.MESSAGE_GUILD_PVP_HAS_BEEN_ENABLED : GuildSettings.MESSAGE_GUILD_PVP_HAS_BEEN_DISABLED).send(guild);
    }

    @Override
    public int minArgs() {
        return 0;
    }
}
