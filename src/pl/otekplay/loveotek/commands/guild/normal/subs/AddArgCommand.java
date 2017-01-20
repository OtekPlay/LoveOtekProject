package pl.otekplay.loveotek.commands.guild.normal.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.GuildRank;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GlobalSettings;
import pl.otekplay.loveotek.storage.GuildSettings;
import pl.otekplay.loveotek.utils.TagUtil;

public class AddArgCommand implements SubCommand {
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
        if (guild.getInvites().size() >= GuildSettings.GUILD_INVITE_MAX) {
            Replacer.build(GuildSettings.MESSAGE_GUILD_INVITED_MAX).add("%max%", GuildSettings.GUILD_INVITE_MAX + "").send(player);
            return;
        }
        String name = args[0];
        if (!Users.is(name)) {
            Replacer.build(GlobalSettings.MESSAGE_PLAYER_NO_EXIST).add("%name%", name).send(player);
            return;
        }
        User invite = Users.get(name);
        if (guild.hasInvite(invite.getUniqueID())) {
            Replacer.build(GuildSettings.MESSAGE_GUILD_ALREADY_INVITED).add("%nick%", invite.getName()).send(player);
            return;
        }
        if (invite.hasGuild()) {
            Replacer.build(GuildSettings.MESSAGE_GUILD_INVITE_HAS_GUILD).add("%nick%", invite.getName()).send(player);
            return;
        }
        guild.addInvite(invite.getUniqueID());
        Replacer.build(GuildSettings.MESSAGE_GUILD_GOT_INVITE).add("%name%", guild.getTag()).send(invite);
        Replacer.build(GuildSettings.MESSAGE_GUILD_INVITE_PLAYER).add("%invite%", invite.getName()).add("%nick%", user.getName()).send(guild);
        TagUtil.updateBoard(player);
    }
}
