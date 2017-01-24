package pl.otekplay.loveotek.commands.player.guild.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.GuildRank;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GlobalSettings;
import pl.otekplay.loveotek.storage.GuildSettings;

public class CancelArgCommand implements SubCommand {
    @Override
    public int minArgs() {
        return 1;
    }

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
        String name = args[0];
        if (!Users.is(name)) {
            Replacer.build(GlobalSettings.MESSAGE_PLAYER_NO_EXIST).add("%nick%",name).send(player);
            return;
        }
        User cancel = Users.get(name);
        if (!guild.hasInvite(cancel.getUniqueID())) {
            Replacer.build(GuildSettings.MESSAGE_GUILD_NO_INVITED).add("%nick%", cancel.getName()).send(player);
            return;
        }
        if (cancel.hasGuild()) {
            Replacer.build(GuildSettings.MESSAGE_GUILD_INVITE_HAS_GUILD).add("%nick%", cancel.getName()).send(player);
            return;
        }
        guild.getInvites().remove(cancel.getUniqueID());
        Replacer.build(GuildSettings.MESSAGE_GUILD_CANCEL_THE_INVITE).add("%tag%", guild.getTag()).send(cancel);
        Replacer.build(GuildSettings.MESSAGE_GUILD_INVITE_CANCELLED).add("%nick%", cancel.getName()).send(guild);
    }
}
